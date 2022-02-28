package me.dev.crystalscrafted.items;


import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Item;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.RecipeChoice;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.ChatColor;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class items {

    public static ItemStack stealshard;
    public static ItemStack unenchantshard;
    public static ItemStack awakenshard;
    public static ItemStack healthswapshard;
    public static ItemStack ancientclock;
    public static ItemStack timeshard;
    public static ItemStack universalclock;
    public static void init() {
        //On initialization, run these methods
        createStealShard();
        createUnenchantShard();
        createAwakenShard();
        createHealthSwapShard();
        createAncientClock();
        createTimeShard();

    }

    private static void createStealShard() {
        //Sets up creation of steal shard
        ItemStack item = new ItemStack(Material.AMETHYST_SHARD, 1);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName("§eShard of Stealing");
        List<String> lore = new ArrayList<>();
        lore.add("§cHit a player to steal their held item!");

        lore.add("§cOne time use item.");

        meta.setLore(lore);
        meta.addEnchant(Enchantment.LUCK, 1 , false);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);

        item.setItemMeta(meta);
        stealshard = item;
        //Crafting recipe
        ShapedRecipe sr = new ShapedRecipe(NamespacedKey.minecraft("stealshard"),item);
        sr.shape(" X ",
                "YSY",
                " D ");
        sr.setIngredient('X', Material.SLIME_BALL);
        sr.setIngredient('Y', Material.EMERALD);
        sr.setIngredient('S', Material.AMETHYST_SHARD);
        sr.setIngredient('D', Material.DIAMOND);
        Bukkit.getServer().addRecipe(sr);
    }



    private static void createUnenchantShard(){
        ItemStack item = new ItemStack(Material.AMETHYST_SHARD, 1);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName("§dShard of Unenchanting");
        List<String> lore = new ArrayList<>();
        lore.add("§5Hit a player to unenchant their held item!");

        lore.add("§5One time use item.");

        meta.setLore(lore);
        meta.addEnchant(Enchantment.LUCK, 1 , false);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);

        item.setItemMeta(meta);
        unenchantshard = item;

        ShapedRecipe sr = new ShapedRecipe(NamespacedKey.minecraft("unenchantshard"),item);
        sr.shape(" X ",
                "YSY",
                " D ");
        sr.setIngredient('X', Material.EXPERIENCE_BOTTLE);
        sr.setIngredient('Y', Material.EMERALD);
        sr.setIngredient('S', Material.AMETHYST_SHARD);
        sr.setIngredient('D', Material.DIAMOND);
        Bukkit.getServer().addRecipe(sr);
    }



    private static void createAwakenShard(){
        ItemStack item = new ItemStack(Material.AMETHYST_SHARD, 1);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName("§aShard of Awakening");
        List<String> lore = new ArrayList<>();
        lore.add("§2Hit a player to send them back to their spawnpoint");

        lore.add("§2One time use item.");

        meta.setLore(lore);
        meta.addEnchant(Enchantment.LUCK, 1 , false);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);

        item.setItemMeta(meta);
        awakenshard = item;

        ShapedRecipe sr = new ShapedRecipe(NamespacedKey.minecraft("awakenshard"),item);
        sr.shape(" X ",
                "YSY",
                " D ");
        sr.setIngredient('X', Material.RED_BED);
        sr.setIngredient('Y', Material.EMERALD);
        sr.setIngredient('S', Material.AMETHYST_SHARD);
        sr.setIngredient('D', Material.DIAMOND_BLOCK);
        Bukkit.getServer().addRecipe(sr);
    }
    private static void createHealthSwapShard(){
        ItemStack item = new ItemStack(Material.AMETHYST_SHARD, 1);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName("§cShard of Health Swapping");
        List<String> lore = new ArrayList<>();
        lore.add("§4Hit a player to swap your health with theirs!");

        lore.add("§4One time use item.");

        meta.setLore(lore);
        meta.addEnchant(Enchantment.LUCK, 1 , false);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);

        item.setItemMeta(meta);
        healthswapshard = item;

        ShapedRecipe sr = new ShapedRecipe(NamespacedKey.minecraft("healthswapshard"),item);
        sr.shape(" X ",
                "YSY",
                " D ");
        sr.setIngredient('X', Material.ENCHANTED_GOLDEN_APPLE);
        sr.setIngredient('Y', Material.EMERALD);
        sr.setIngredient('S', Material.AMETHYST_SHARD);
        sr.setIngredient('D', Material.DIAMOND_BLOCK);
        Bukkit.getServer().addRecipe(sr);
    }
    private static void createAncientClock(){
        ItemStack item = new ItemStack(Material.CLOCK, 1);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName("§cAncient Clock");
        List<String> lore = new ArrayList<>();
        lore.add("§4A clock with a strange energy...");

        lore.add("§4It doesn't do much but maybe it can be used somehow...");

        meta.setLore(lore);
        meta.addEnchant(Enchantment.LUCK, 1 , false);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);

        item.setItemMeta(meta);
        ancientclock = item;

        ShapedRecipe sr = new ShapedRecipe(NamespacedKey.minecraft("ancientclock"),item);
        sr.shape(" X ",
                "YSY",
                " D ");
        sr.setIngredient('X', Material.NETHERITE_SCRAP);
        sr.setIngredient('Y', Material.OBSIDIAN);
        sr.setIngredient('S', Material.CLOCK);
        sr.setIngredient('D', Material.ENDER_EYE);
        Bukkit.getServer().addRecipe(sr);
    }



    private static void createTimeShard(){
        ItemStack item = new ItemStack(Material.AMETHYST_SHARD, 1);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName("§cShard of Time");
        List<String> lore = new ArrayList<>();
        lore.add("§cRight click to stop time for");
        lore.add("§c10 seconds within a 15 block radius");

        lore.add("§4One time use item.");

        meta.setLore(lore);
        meta.addEnchant(Enchantment.LUCK, 1 , false);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);

        item.setItemMeta(meta);
        timeshard = item;

        ShapedRecipe sr = new ShapedRecipe(NamespacedKey.minecraft("timeshard"),item);
        sr.shape(" X ",
                "YSY",
                " D ");
        sr.setIngredient('X', new RecipeChoice.ExactChoice(ancientclock));
        sr.setIngredient('Y', Material.DIAMOND);
        sr.setIngredient('S', Material.AMETHYST_SHARD);
        sr.setIngredient('D', Material.NETHERITE_SCRAP);
        Bukkit.getServer().addRecipe(sr);
    }
}

