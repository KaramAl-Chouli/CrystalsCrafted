package me.dev.crystalscrafted.events;
import me.dev.crystalscrafted.CrystalsCrafted;
import me.dev.crystalscrafted.items.items;
import org.bukkit.*;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.ItemStack;
import java.util.*;



public class events implements Listener {


    private CrystalsCrafted plugin;
    private int task1;

    Map<Player, Integer> timestop = new HashMap<>(); //Used to tell if a player is frozen in time (Time shard)


    public ArrayList<Player> getNearbyPlayers(Player pl) { //Get nearby players in a range of 15
        ArrayList<Player> nearby = new ArrayList<Player>();

        double range = 15;
        nearby.add(pl);

        for (Entity e : pl.getNearbyEntities(range, range, range)) { //Nearby entity

            if (e instanceof Player) { //If entity is player
                nearby.add((Player) e);
            }
        }
        return nearby;
    }



    @EventHandler

    public void onPlayerJoin(PlayerJoinEvent event) {
        Player p = event.getPlayer();
        timestop.put(p, 0);

    }

    @EventHandler
    public void onEntityDamageByEntityEvent(EntityDamageByEntityEvent event) {
        if (event.getEntity() instanceof Player && event.getDamager() instanceof Player) //Player hitting player
        {

            Player attacker = (Player) event.getDamager(); //Gets the player that attacked
            Player attacked = (Player) event.getEntity();//Gets the attacked player
            attacked.getInventory().getContents();

            ItemStack attacked_helditem = attacked.getInventory().getItemInMainHand(); //Held item of attacked


            //Awaken shard
            if (attacker.getInventory().getItemInMainHand().getItemMeta().equals(items.awakenshard.getItemMeta())) { //If the attacker is holding an "awaken shard"
                int awaken_shard_amount = attacker.getInventory().getItemInMainHand().getAmount() - 1; //Integer amount of one less than awaken shards in attackers hand
                ItemStack awaken_shard = attacker.getInventory().getItemInMainHand(); //Gets item in attacker's main hand
                awaken_shard.setAmount(awaken_shard_amount);
                ItemStack[] temp_inv = (attacked.getInventory().getContents()); //Saves the attacked player's inventory in order to kill them and then give them their items back
                attacked.getInventory().clear();
                attacked.setHealth(0);
                attacked.spigot().respawn(); //This seamlessly respawns the player (Skips respawn menu)
                attacked.getInventory().setContents(temp_inv); //Gives player their items

                //Steal shard
            } else if (attacker.getInventory().getItemInMainHand().getItemMeta().equals(items.stealshard.getItemMeta()) && !(attacked_helditem.getType().equals(Material.AIR))) { //Steal shard
                attacked.sendMessage(ChatColor.RED + "Your item has been taken by " + attacker.getName() + "!");
                attacked.playSound(attacked.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1, 1);
                attacker.sendMessage(ChatColor.RED + "You have taken an item from " + attacked.getName() + "!");
                attacker.playSound(attacked.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1, 1);
                attacker.getInventory().setItem(attacker.getInventory().getHeldItemSlot(), new ItemStack(attacked_helditem)); //Gives attacker the item held by attacked player
                attacked.getInventory().setItem(attacked.getInventory().getHeldItemSlot(), new ItemStack(Material.AIR)); //Sets the attacked player's held item to air

                //Unenchant shard
            } else if (attacker.getInventory().getItemInMainHand().getItemMeta().equals(items.unenchantshard.getItemMeta()) && !(attacked_helditem.getType().equals(Material.AIR)) && !(attacked_helditem.getEnchantments().isEmpty())) { //Unenchant shard
                //The attacked player must be holding an enchanted item

                int unenchant_shard_amount = attacker.getInventory().getItemInMainHand().getAmount() - 1;
                ItemStack unenchant_shard = attacker.getInventory().getItemInMainHand();
                unenchant_shard.setAmount(unenchant_shard_amount);
                //Removes 1 shard on use

                attacked.sendMessage(ChatColor.RED + "Your item has been unenchanted by " + attacker.getName() + "!");
                attacked.playSound(attacked.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1, 1);
                attacker.sendMessage(ChatColor.RED + "You have unenchanted " + attacked.getName() + "'s item!");
                attacker.playSound(attacked.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1, 1);
                Material unenchanted = attacked_helditem.getType(); //Gets type of item held by attacked player
                int unenchanted_amount = attacked_helditem.getAmount();
                attacked.getInventory().setItem(attacked.getInventory().getHeldItemSlot(), new ItemStack(unenchanted, unenchanted_amount));  //Gives attacked player unenchanted item
                attacker.getInventory().setItem(attacker.getInventory().getHeldItemSlot(), unenchant_shard); //Simulates losing an unenchant shard on use


                //Health swap shard
            } else if (attacker.getInventory().getItemInMainHand().getItemMeta().equals(items.healthswapshard.getItemMeta())) {
                int healthswap_shard_amount = attacker.getInventory().getItemInMainHand().getAmount() - 1;
                ItemStack healthswap_shard = attacker.getInventory().getItemInMainHand();
                healthswap_shard.setAmount(healthswap_shard_amount);
                double attacked_health = attacked.getHealth(); //Attacked player's health
                double attacker_health = attacker.getHealth(); //Attacker's health
                attacked.setHealth(attacker_health); //Sets attacked player's health to the attacker's health
                attacker.setHealth(attacked_health);//"" vice versa


                attacker.getInventory().setItem(attacker.getInventory().getHeldItemSlot(), healthswap_shard); //Simulates losing a health swap shard on use
            }


        }

    }


    @EventHandler
    public void onPlayerDeathEvent(PlayerDeathEvent event) {

        timestop.put(event.getEntity().getPlayer(), 0); //On death unfreeze player
        Player p = event.getEntity();
        p.setWalkSpeed(0.2f); //resets walk speed
        final LivingEntity entity = event.getEntity();
        Player k = entity.getKiller();
        if (k.getInventory().getItemInMainHand().getItemMeta().equals(items.awakenshard.getItemMeta())) { //If player has awaken shard on them
            event.setDeathMessage("");  //In order to make it seem that they "woke up" as opposed to dying

        }
    }

    @EventHandler
    public void onPlayerMoveEvent(PlayerMoveEvent event) {
        if (timestop.get(event.getPlayer()) == 1) { //If the player is frozen in time, cancel the move event
            event.setCancelled(true);
        }
    }




    @EventHandler
    public void onInteract(PlayerInteractEvent e) {

        Player p = e.getPlayer();


        if (timestop.get(e.getPlayer()) == 1) {
            if (e.getAction().equals(Action.RIGHT_CLICK_AIR) || e.getAction().equals(Action.RIGHT_CLICK_BLOCK) || e.getAction().equals(Action.LEFT_CLICK_AIR) || e.getAction().equals(Action.LEFT_CLICK_BLOCK)) {

                e.setCancelled(true);
                p.updateInventory(); //Prevents throwable items (enderpearls, snowballs) from dissapearing from inventory due to visual glitch
                //If the player is frozen in time and attempts to right or left click the air or a block, the interact event is cancelled


            }
        }
        if (e.getAction().equals(Action.RIGHT_CLICK_AIR) || e.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
            if (e.getItem() != null) {
                if (p.getInventory().getItemInMainHand().getItemMeta().equals(items.timeshard.getItemMeta())) { //If player right clicks time shard
                    int time_shard_amount = p.getInventory().getItemInMainHand().getAmount() - 1;
                    ItemStack time_shard = p.getInventory().getItemInMainHand();
                    time_shard.setAmount(time_shard_amount);
                    p.getInventory().setItem(p.getInventory().getHeldItemSlot(), time_shard);
                    ArrayList<Player> nearby = getNearbyPlayers(p); //Gets nearby players
                    nearby.remove(p); //Excludes user of time shard
                    int player_len = nearby.size();
                    p.sendMessage(ChatColor.RED + "Time will resume in 10 seconds...");
                    p.playSound(p.getLocation(), Sound.AMBIENT_SOUL_SAND_VALLEY_MOOD, 100, (float) 0.0001);
                    p.playSound(p.getLocation(), Sound.BLOCK_LARGE_AMETHYST_BUD_BREAK, 100, (float) 0.0001);
                    for (int i = 0; i < player_len; i++) { //For loop to freeze all players in range

                        nearby.get(i).sendMessage(ChatColor.RED + "Time will resume in 10 seconds...");
                        nearby.get(i).playSound(nearby.get(i).getLocation(), Sound.AMBIENT_SOUL_SAND_VALLEY_MOOD, 100, (float) 0.0001);
                        timestop.put(nearby.get(i).getPlayer(), 1); //Freezes player

                                nearby.get(i).setAllowFlight(true); //Allows flight to avoid "Kicked for flying" message while frozen in time mid-air


                        Timer yourtimer = new Timer(true);
                        yourtimer.schedule(new TimerTask() { //Timed event, after certain amount of time play is unfrozen
                            @Override
                            public void run() {
                                for (int x = 0; x < player_len; x++) {

                                    if (nearby.get(x).getGameMode().equals(GameMode.SURVIVAL) || (nearby.get(x).getGameMode().equals(GameMode.ADVENTURE))) {
                                        nearby.get(x).setAllowFlight(false); //If the player is in survival or adventure mode the player will be unallowed to fly after being unfrozen

                                    }
                                    timestop.put(nearby.get(x).getPlayer(), 0);

                                    nearby.get(x).playSound(nearby.get(x).getLocation(), Sound.ITEM_TRIDENT_RETURN, 100, (float) 0.0001);
                                    nearby.get(x).sendMessage(ChatColor.RED + "Time resumes...");

                                    p.sendMessage(ChatColor.RED + "Time resumes...");


                                }
                            }
                        }, 10000L); //Timed delay


                    }
                }
            }


        } //STOP TIME

    }
}





