/*
 * Copyright (c) 2021, Nathan DJIAN-MARTIN (DevKrazy).
 * This TeamsVotingGUI.java file is a part of the Smedalis project.
 * Smedalis cannot be copied and/or distributed without the express permission of Nathan DJIAN-MARTIN (DevKrazy)
 *
 */

package com.devkrazy.citiesoffreedom.guis.guis;

import com.devkrazy.citiesoffreedom.config.files.TeamsConfig;
import com.devkrazy.citiesoffreedom.game.VotesManager;
import com.devkrazy.citiesoffreedom.guis.GUIButton;
import com.devkrazy.citiesoffreedom.guis.GUIMenu;
import com.devkrazy.citiesoffreedom.player.Team;
import com.devkrazy.citiesoffreedom.utils.ItemBuilder;
import net.kyori.adventure.text.Component;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class TeamsVotingGUI {
    
    
    private static TeamsVotingGUI instance = new TeamsVotingGUI();
    private final ItemStack OPENER = new ItemBuilder(Material.PAPER, ChatColor.GOLD + "Vote équipes").build();

    /*
    Constructor
     */
    private TeamsVotingGUI() {}


    /*
    Getters
     */

    /**
     * @return the TeamsVotingGUI's instance
     */
    public static TeamsVotingGUI getInstance() {
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
        GUIMenu menu = new GUIMenu(ChatColor.of("#53e669") + "Votez pour la plus belle ville", 1);

        // Populates the GUI with all the available teams
        int slot = 0;
        for (Team team : Team.values()) {
            menu.setButton(slot, new GUIButton(team.getGuiItem()) {

                @Override
                public void onClick() {
                    VotesManager.getInstance().addVote(team);
                    player.sendMessage(Component.text(ChatColor.GRAY + "Vous avez voté pour l'équipe " + team.getColoredName()));
                    player.closeInventory();
                }

            });
            slot += 4;
        }

        menu.pack();
        menu.open(player);
    }
}
