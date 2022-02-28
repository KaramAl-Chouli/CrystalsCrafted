package me.dev.crystalscrafted;

import me.dev.crystalscrafted.items.items;
import me.dev.crystalscrafted.events.events;
import org.bukkit.WorldCreator;
import org.bukkit.WorldType;
import org.bukkit.plugin.java.JavaPlugin;





public final class CrystalsCrafted extends JavaPlugin {

    @Override

    public void onEnable() {
        items.init();
        // Plugin startup logic

        getServer().getPluginManager().registerEvents(new events(), this);

        getServer().getConsoleSender().sendMessage("CrystalsCrafted is on!");

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        System.out.println("Thanks for choosing Crystals Crafted!");
    }
}


