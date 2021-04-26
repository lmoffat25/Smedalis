/*
 * Copyright (c) 2021, Nathan DJIAN-MARTIN (DevKrazy).
 * This TeamSelectionGUI.java file is a part of the Smedalis project.
 * Smedalis cannot be copied and/or distributed without the express permission of Nathan DJIAN-MARTIN (DevKrazy)
 *
 */

package com.devkrazy.citiesoffreedom.guis.guis;

import com.devkrazy.citiesoffreedom.game.Team;
import com.devkrazy.citiesoffreedom.guis.GUIButton;
import com.devkrazy.citiesoffreedom.guis.GUIMenu;
import com.devkrazy.citiesoffreedom.player.CoFPlayer;
import com.devkrazy.citiesoffreedom.player.CoFPlayersManager;
import com.devkrazy.citiesoffreedom.utils.ItemBuilder;
import net.kyori.adventure.text.Component;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class TeamSelectionGUI {

    private static TeamSelectionGUI instance = new TeamSelectionGUI();
    private final ItemStack OPENER = new ItemBuilder(Material.WHITE_BED, ChatColor.of("#c73434") + "Choix équipes").build();


    /*
    Getters
     */

    /**
     * @return the TeamSelectionGUI's instance
     */
    public static TeamSelectionGUI getInstance() {
        return instance;
    }

    public ItemStack getOpener() {
        return this.OPENER;
    }


    /*
    Methods
     */

    /**
     * Opens the team selection GUI to the given player.
     * @param player the player
     */
    public void open(Player player) {
        GUIMenu menu = new GUIMenu(ChatColor.of("#c73434") + "Choisissez votre équipe", 1);
        CoFPlayersManager manager = CoFPlayersManager.getInstance();
        CoFPlayer cofPlayer = manager.getCoFPlayer(player);

        ItemStack blueTeam = new ItemBuilder(Material.BLUE_CONCRETE_POWDER,
                ChatColor.BLUE + "Équipe bleue").build();

        ItemStack redTeam = new ItemBuilder(Material.RED_CONCRETE_POWDER,
                ChatColor.RED + "Équipe rouge").build();

        ItemStack purpleTeam = new ItemBuilder(Material.PURPLE_CONCRETE_POWDER,
                ChatColor.DARK_PURPLE + "Équipe violette").build();

        menu.setButton(1, new GUIButton(blueTeam) {
            @Override
            public void onClick() {
                cofPlayer.setTeam(Team.BLUE);
                player.closeInventory();
                player.sendMessage(Component.text(ChatColor.GRAY + "Vous avez choisi l'équipe " +
                        ChatColor.BLUE + "bleue" + ChatColor.GRAY + "."));
            }
        });

        menu.setButton(4, new GUIButton(redTeam) {
            @Override
            public void onClick() {
                cofPlayer.setTeam(Team.RED);
                player.closeInventory();
                player.sendMessage(Component.text(ChatColor.GRAY + "Vous avez choisi l'équipe " +
                        ChatColor.RED + "rouge" + ChatColor.GRAY + "."));
            }
        });

        menu.setButton(7, new GUIButton(purpleTeam) {
            @Override
            public void onClick() {
                cofPlayer.setTeam(Team.PURPLE);
                player.closeInventory();
                player.sendMessage(Component.text(ChatColor.GRAY + "Vous avez choisi l'équipe " +
                        ChatColor.DARK_PURPLE + "violette" + ChatColor.GRAY + "."));
            }
        });
        menu.pack();
        menu.open(player);
    }
}
