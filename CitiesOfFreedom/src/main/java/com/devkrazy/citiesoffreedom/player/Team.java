/*
 * Copyright (c) 2021, Nathan DJIAN-MARTIN (DevKrazy).
 * This Team.java file is a part of the Smedalis project.
 * Smedalis cannot be copied and/or distributed without the express permission of Nathan DJIAN-MARTIN (DevKrazy)
 *
 */

package com.devkrazy.citiesoffreedom.player;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.HashSet;
import java.util.Set;

public enum Team {

    RED("rouge", ChatColor.RED),
    BLUE("bleue", ChatColor.BLUE),
    PURPLE("violette", ChatColor.DARK_PURPLE);

    private String name;
    private ChatColor color;
    private Set<Player> players;

    Team(String name, ChatColor color) {
        this.name = name;
        this.color = color;
        this.players = new HashSet<>();
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
