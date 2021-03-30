/*
 * Copyright (c) 2021, Nathan DJIAN-MARTIN (DevKrazy).
 * This CoFPlayersManager.java file is a part of the Smedalis project.
 * Smedalis cannot be copied and/or distributed without the express permission of Nathan DJIAN-MARTIN (DevKrazy)
 *
 */

package com.devkrazy.citiesoffreedom.player;

import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.UUID;

public class CoFPlayersManager {

    private static CoFPlayersManager instance = new CoFPlayersManager();
    private HashMap<UUID, CoFPlayer> cofPlayers;

    /**
     * Singleton constructor
     */
    private CoFPlayersManager() {
        this.cofPlayers = new HashMap<>();
    }

    /**
     * @return the CoFPlayersManager instance.
     */
    public static CoFPlayersManager getInstance() {
        return instance;
    }

    /**
     * Creates a new CoFPlayer from a Bukkit Player's UUID and returns it.
     * @param uuid the Bukkit Player's UUID
     * @return the created CoFPlayer if it didn't already exist; null otherwise
     */
    public CoFPlayer createCoFPlayer(UUID uuid) {
        if (this.existsCoFPlayer(uuid) == false) {
            CoFPlayer coFPlayer = new CoFPlayer();
            this.cofPlayers.put(uuid, coFPlayer);
            return coFPlayer;
        } else {
            return null;
        }
    }

    /**
     * Creates a new CoFPlayer from a Bukkit Player and returns it.
     * @param player the Bukkit Player
     * @return the created CoFPlayer if it didn't already exist; null otherwise
     */
    public CoFPlayer createCoFPlayer(Player player) {
        return this.createCoFPlayer(player.getUniqueId());
    }

    /**
     * Returns a CoFPlayer from a Bukkit Player's UUID.
     * @param uuid the Bukkit Player's UUID
     * @return the CoFPlayer if it exists; null otherwise
     */
    public CoFPlayer getCoFPlayer(UUID uuid) {
        return this.cofPlayers.get(uuid);
    }

    /**
     * Returns a CoFPlayer from a Bukkit Player.
     * @param player the Bukkit Player
     * @return the CoFPlayer if it exists; null otherwise
     */
    public CoFPlayer getCoFPlayer(Player player) {
        return this.getCoFPlayer(player.getUniqueId());
    }

    /**
     * @param uuid a Bukkit Player's UUID
     * @return true if the CoFPlayer exists; false otherwise
     */
    public boolean existsCoFPlayer(UUID uuid) {
        return this.cofPlayers.containsKey(uuid);
    }

    /**
     * @param player a Bukkit Player
     * @return true if the CoFPlayer exists; false otherwise
     */
    public boolean existsCoFPlayer(Player player) {
        return this.cofPlayers.containsKey(player.getUniqueId());
    }

    /**
     * Deletes a CoFPlayer from a Bukkit Player's UUID, does nothing if it doesn't already exist.
     * @param uuid the Bukkit Player's UUID
     */
    public void deleteCoFPlayer(UUID uuid) {
        this.cofPlayers.remove(uuid);
    }

    /**
     * Deletes a CoFPlayer from a Bukkit Player, does nothing if it doesn't already exist.
     * @param player the Bukkit Player
     */
    public void deleteCoFPlayer(Player player) {
        this.deleteCoFPlayer(player.getUniqueId());
    }
}
