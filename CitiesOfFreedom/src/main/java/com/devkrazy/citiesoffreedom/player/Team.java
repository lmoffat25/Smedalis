/*
 * Copyright (c) 2021, Nathan DJIAN-MARTIN (DevKrazy).
 * This Team.java file is a part of the Smedalis project.
 * Smedalis cannot be copied and/or distributed without the express permission of Nathan DJIAN-MARTIN (DevKrazy)
 *
 */

package com.devkrazy.citiesoffreedom.player;

import com.devkrazy.citiesoffreedom.utils.ItemBuilder;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.HashSet;
import java.util.Set;

public enum Team {

    RED("rouge", ChatColor.RED, Material.RED_CONCRETE_POWDER),
    BLUE("bleue", ChatColor.BLUE, Material.BLUE_CONCRETE_POWDER),
    PURPLE("violette", ChatColor.DARK_PURPLE, Material.PURPLE_CONCRETE_POWDER);

    private String name;
    private ChatColor color;
    private ItemStack guiItem;
    private Set<Player> players;

    Team(String name, ChatColor color, Material guiMaterial) {
        this.name = name;
        this.color = color;
        this.players = new HashSet<>();
        this.guiItem = new ItemBuilder(guiMaterial, color + "Équipe " + name).build();
    }

    /*
    Getters
     */

    public String getName() {
        return this.name;
    }

    /**
     * @return the Team's name colored
     */
    public String getColoredName() {
        return this.getColor() + this.getName();
    }

    public ChatColor getColor() {
        return this.color;
    }

    public ItemStack getGuiItem() {
        return this.guiItem;
    }

    /*
    Methods
     */

    /**
     * Adds a player to the Team's set of players.
     * @param player the player to add
     */
    public void addPlayer(Player player) {
        CoFPlayersManager manager = CoFPlayersManager.getInstance();
        manager.getCoFPlayer(player).setTeam(this);
        this.players.add(player);
    }

    /**
     * Removes a player from the Team's set of players if it is present.
     * @param player the player to remove
     */
    public void removePlayer(Player player) {
        CoFPlayersManager manager = CoFPlayersManager.getInstance();
        manager.getCoFPlayer(player).removeTeam();
        this.players.remove(player);
    }

    /**
     * @return the Team's set of players
     */
    public Set<Player> getPlayers() {
        return this.players;
    }
}
