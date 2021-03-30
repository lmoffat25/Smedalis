package com.devkrazy.smedaliscore.player;

import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.UUID;

public class SmedalisPlayersManager {

    private static SmedalisPlayersManager instance = new SmedalisPlayersManager();
    private HashMap<UUID, SmedalisPlayer> smedalisPlayers;

    /**
     * Singleton constructor
     */
    private SmedalisPlayersManager() {
        this.smedalisPlayers = new HashMap<>();
    }

    /**
     * @return the SmedalisPlayersManager instance.
     */
    public static SmedalisPlayersManager getInstance() {
        return instance;
    }

    /**
     * Creates a new SmedalisPlayer from a Bukkit Player's UUID and returns it.
     * @param uuid the Bukkit Player's UUID
     * @return the created SmedalisPlayer if it didn't already exist; null otherwise
     */
    public SmedalisPlayer createSmedalisPlayer(UUID uuid) {
        if (this.existsSmedalisPlayer(uuid) == false) {
            SmedalisPlayer smedalisPlayer = new SmedalisPlayer();
            this.smedalisPlayers.put(uuid, smedalisPlayer);
            return smedalisPlayer;
        } else {
            return null;
        }
    }

    /**
     * Creates a new SmedalisPlayer from a Bukkit Player and returns it.
     * @param player the Bukkit Player
     * @return the created SmedalisPlayer if it didn't already exist; null otherwise
     */
    public SmedalisPlayer createSmedalisPlayer(Player player) {
        return this.createSmedalisPlayer(player.getUniqueId());
    }

    /**
     * Returns a SmedalisPlayer from a Bukkit Player's UUID.
     * @param uuid the Bukkit Player's UUID
     * @return the SmedalisPlayer if it exists; null otherwise
     */
    public SmedalisPlayer getSmedalisPlayer(UUID uuid) {
        return this.smedalisPlayers.get(uuid);
    }

    /**
     * Returns a SmedalisPlayer from a Bukkit Player.
     * @param player the Bukkit Player
     * @return the SmedalisPlayer if it exists; null otherwise
     */
    public SmedalisPlayer getSmedalisPlayer(Player player) {
        return this.getSmedalisPlayer(player.getUniqueId());
    }

    /**
     * @param uuid a Bukkit Player's UUID
     * @return true if the SmedalisPlayer exists; false otherwise
     */
    public boolean existsSmedalisPlayer(UUID uuid) {
        return this.smedalisPlayers.containsKey(uuid);
    }

    /**
     * @param player a Bukkit Player
     * @return true if the SmedalisPlayer exists; false otherwise
     */
    public boolean existsSmedalisPlayer(Player player) {
        return this.smedalisPlayers.containsKey(player.getUniqueId());
    }

    /**
     * Deletes a SmedalisPlayer from a Bukkit Player's UUID, does nothing if it doesn't already exist.
     * @param uuid the Bukkit Player's UUID
     */
    public void deleteSmedalisPlayer(UUID uuid) {
        this.smedalisPlayers.remove(uuid);
    }

    /**
     * Deletes a SmedalisPlayer from a Bukkit Player, does nothing if it doesn't already exist.
     * @param player the Bukkit Player
     */
    public void deleteSmedalisPlayer(Player player) {
        this.deleteSmedalisPlayer(player.getUniqueId());
    }
}
