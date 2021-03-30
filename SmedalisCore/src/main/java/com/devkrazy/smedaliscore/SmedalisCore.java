package com.devkrazy.smedaliscore;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class SmedalisCore extends JavaPlugin {

    @Override
    public void onEnable() {
        Bukkit.getLogger().info("SmedalisCore activé !");
    }

    @Override
    public void onDisable() {
        Bukkit.getLogger().info("SmedalisCore désactivé.");
    }
}
