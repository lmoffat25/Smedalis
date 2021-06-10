/*
 * Copyright (c) 2021, Nathan DJIAN-MARTIN (DevKrazy).
 * This TeamsSelectionGUI.java file is a part of the Smedalis project.
 * Smedalis cannot be copied and/or distributed without the express permission of Nathan DJIAN-MARTIN (DevKrazy)
 *
 */

package com.devkrazy.citiesoffreedom.guis.guis;

import com.devkrazy.citiesoffreedom.events.TeamPickEvent;
import com.devkrazy.citiesoffreedom.player.Team;
import com.devkrazy.citiesoffreedom.guis.GUIButton;
import com.devkrazy.citiesoffreedom.guis.GUIMenu;
import com.devkrazy.citiesoffreedom.utils.ItemBuilder;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class TeamsSelectionGUI {

    private static TeamsSelectionGUI instance = new TeamsSelectionGUI();
    private final ItemStack OPENER = new ItemBuilder(Material.WHITE_BED, ChatColor.of("#c73434") + "Choix équipes").build();

    /*
    Constructor
    */
    private TeamsSelectionGUI() {}

    /*
    Getters
     */

    /**
     * @return the TeamsSelectionGUI's instance
     */
    public static TeamsSelectionGUI getInstance() {
        return instance;
    }

    /**
     * @return the ItemStack that can be used to open the teams selection GUI
     */
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

        // Populates the GUI with all the available teams
        int slot = 0;
        for (Team team : Team.values()) {
            menu.setButton(slot, new GUIButton(team.getGuiItem()) {
                @Override
                public void onClick() {
                    Bukkit.getServer().getPluginManager().callEvent(new TeamPickEvent(player, team));
                    player.closeInventory();
                }
            });
            slot += 4;
        }

        menu.pack();
        menu.open(player);
    }
}
