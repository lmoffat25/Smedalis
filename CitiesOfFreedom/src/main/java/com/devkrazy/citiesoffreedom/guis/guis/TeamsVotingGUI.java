/*
 * Copyright (c) 2021, Nathan DJIAN-MARTIN (DevKrazy).
 * This TeamsVotingGUI.java file is a part of the Smedalis project.
 * Smedalis cannot be copied and/or distributed without the express permission of Nathan DJIAN-MARTIN (DevKrazy)
 *
 */

package com.devkrazy.citiesoffreedom.guis.guis;

import com.devkrazy.citiesoffreedom.game.VotesManager;
import com.devkrazy.citiesoffreedom.guis.GUIButton;
import com.devkrazy.citiesoffreedom.guis.GUIMenu;
import com.devkrazy.citiesoffreedom.player.CoFPlayer;
import com.devkrazy.citiesoffreedom.player.CoFPlayersManager;
import com.devkrazy.citiesoffreedom.player.Team;
import com.devkrazy.citiesoffreedom.utils.ItemBuilder;
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
        VotesManager votesManager = VotesManager.getInstance();
        CoFPlayersManager cofPlayersManager = CoFPlayersManager.getInstance();
        CoFPlayer cofPlayer = cofPlayersManager.getCoFPlayer(player);

        // Populates the GUI with all the available teams
        int slot = 0;
        for (Team team : Team.values()) {
            ItemBuilder teamItemBuilder = new ItemBuilder(team.getGuiItem()).setLore(ChatColor.GRAY + "Votes : " + ChatColor.YELLOW + votesManager.getVotes(team));
            menu.setButton(slot, new GUIButton(teamItemBuilder.build()) {

                @Override
                public void onClick() {
                    if (votesManager.isVotingEnabled() == true) {
                        if (cofPlayer.getTeam() != team) {
                            votesManager.addVote(player, team);
                            player.sendMessage(ChatColor.GRAY + "Vous avez voté pour l'équipe " + team.getColoredName());
                        } else { // player cannot vote for its own team
                            player.sendMessage(ChatColor.RED + "Vous ne pouvez pas voter pour votre équipe.");
                        }
                    } else { // player cannot vote for its own team
                        player.sendMessage(ChatColor.RED + "Les votes sont désactivés pour le moment.");
                    }
                    player.closeInventory();
                }

            });
            slot += 4;
        }

        menu.pack();
        menu.open(player);
    }
}
