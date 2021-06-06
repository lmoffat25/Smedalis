/*
 * Copyright (c) 2021, Nathan DJIAN-MARTIN (DevKrazy).
 * This TeamsTeleportationGUI.java file is a part of the Smedalis project.
 * Smedalis cannot be copied and/or distributed without the express permission of Nathan DJIAN-MARTIN (DevKrazy)
 *
 */

package com.devkrazy.citiesoffreedom.guis.guis;

import com.devkrazy.citiesoffreedom.config.files.TeamsConfig;
import com.devkrazy.citiesoffreedom.guis.GUIButton;
import com.devkrazy.citiesoffreedom.guis.GUIMenu;
import com.devkrazy.citiesoffreedom.player.Team;
import com.devkrazy.citiesoffreedom.utils.ItemBuilder;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class TeamsTeleportationGUI {

    private static TeamsTeleportationGUI instance = new TeamsTeleportationGUI();
    private final ItemStack OPENER = new ItemBuilder(Material.COMPASS, ChatColor.GRAY + "Téléportation équipes").build();

    /*
    Constructor
     */
    private TeamsTeleportationGUI() {}


    /*
    Getters
     */

    /**
     * @return the TeamsTeleportationGUI's instance
     */
    public static TeamsTeleportationGUI getInstance() {
        return instance;
    }

    /**
     * @return the ItemStack that can be used to open the teams teleportation GUI
     */
    public ItemStack getOpener() {
        return this.OPENER;
    }


     /*
    Methods
     */

    /**
     * Opens the team teleportation GUI to the given player.
     * @param player the player
     */
    public void open(Player player) {
        GUIMenu menu = new GUIMenu(ChatColor.of("#53e669") + "Choisissez l'équipe vers laquelle se téléporter", 1);

        // Populates the GUI with all the available teams
        int slot = 0;
        for (Team team : Team.values()) {
            menu.setButton(slot, new GUIButton(team.getGuiItem()) {
                @Override
                public void onClick() {
                    player.teleport(TeamsConfig.getInstance().getTeamSpawn(team));
                    player.closeInventory();
                }
            });
            slot += 4;
        }

        menu.pack();
        menu.open(player);
    }
}
