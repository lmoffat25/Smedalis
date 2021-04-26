/*
 * Copyright (c) 2021, Nathan DJIAN-MARTIN (DevKrazy).
 * This SmedalisCore.java file is a part of the Smedalis project.
 * Smedalis cannot be copied and/or distributed without the express permission of Nathan DJIAN-MARTIN (DevKrazy)
 *
 */

package com.devkrazy.smedaliscore;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class SmedalisCore extends JavaPlugin {

    @Override
    public void onEnable() {
        Bukkit.getLogger().info("SmedalisCore enabled !");
    }

    @Override
    public void onDisable() {
        Bukkit.getLogger().info("SmedalisCore enabled.");
    }
}
