/*
 * Copyright (c) 2021, Nathan DJIAN-MARTIN (DevKrazy).
 * This MissionsGUI.java file is a part of the Smedalis project.
 * Smedalis cannot be copied and/or distributed without the express permission of Nathan DJIAN-MARTIN (DevKrazy)
 *
 */

package com.devkrazy.citiesoffreedom.guis.guis;

import com.devkrazy.citiesoffreedom.events.JobPickEvent;
import com.devkrazy.citiesoffreedom.player.Job;
import com.devkrazy.citiesoffreedom.guis.GUIButton;
import com.devkrazy.citiesoffreedom.guis.GUIMenu;
import com.devkrazy.citiesoffreedom.player.CoFPlayer;
import com.devkrazy.citiesoffreedom.player.CoFPlayersManager;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class JobsGUI {

    private static JobsGUI instance = new JobsGUI();

    /*
    Constructor
    */
    private JobsGUI(){

    }

    /*
    Getters
     */

    /**
     * @return the TeamsGUI's instance
     */
    public static JobsGUI getInstance() {
        return instance;
    }


     /*
    Methods
     */

    /**
     * Opens the team selection GUI to the given player.
     *
     * @param player the player
     */
    public void open(Player player) {
        GUIMenu menu = new GUIMenu(ChatColor.of("#2c596e") + "Choix du métier", 3);
        CoFPlayersManager manager = CoFPlayersManager.getInstance();
        CoFPlayer cofPlayer = manager.getCoFPlayer(player);

        // Populates the GUI with all the available jobs
        for (Job job : Job.values()) {
            menu.addButton(new GUIButton(job.getGuiItem()) {
                @Override
                public void onClick() {
                    Bukkit.getServer().getPluginManager().callEvent(new JobPickEvent(player, job));
                    player.closeInventory();
                }
            });

            menu.pack();
            menu.open(player);
        }
    }
}
