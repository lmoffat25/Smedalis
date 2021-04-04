/*
 * Copyright (c) 2021, Nathan DJIAN-MARTIN (DevKrazy).
 * This CitiesOfFreedom.java file is a part of the Smedalis project.
 * Smedalis cannot be copied and/or distributed without the express permission of Nathan DJIAN-MARTIN (DevKrazy)
 *
 */

package com.devkrazy.citiesoffreedom;

import com.devkrazy.citiesoffreedom.commands.TeamCommand;
import com.devkrazy.citiesoffreedom.game.Team;
import com.devkrazy.citiesoffreedom.listeners.ConnectionListeners;
import com.devkrazy.citiesoffreedom.listeners.GUIListeners;
import com.devkrazy.citiesoffreedom.listeners.InteractListeners;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class CitiesOfFreedom extends JavaPlugin {

    private static CitiesOfFreedom instance;

    @Override
    public void onEnable() {
        instance = this;

        // register events
        Bukkit.getServer().getPluginManager().registerEvents(new ConnectionListeners(), instance);
        Bukkit.getServer().getPluginManager().registerEvents(new GUIListeners(), instance);
        Bukkit.getServer().getPluginManager().registerEvents(new InteractListeners(), instance);


        // register commands
        instance.getCommand("team").setExecutor(new TeamCommand());

        Bukkit.getLogger().info("CitiesOfFreedom enabled !");
    }

    @Override
    public void onDisable() {
        Bukkit.getLogger().info("CitiesOfFreedom disabled.");
    }

    /**
     * @return the JavaPlugin instance
     */
    public static CitiesOfFreedom getInstance() {
        return instance;
    }
}
