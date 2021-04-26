/*
 * Copyright (c) 2021, Nathan DJIAN-MARTIN (DevKrazy).
 * This ConnectionListener.java file is a part of the Smedalis project.
 * Smedalis cannot be copied and/or distributed without the express permission of Nathan DJIAN-MARTIN (DevKrazy)
 *
 */

package com.devkrazy.citiesoffreedom.listeners;

import com.devkrazy.citiesoffreedom.player.missions.BlockBreakMission;
import com.devkrazy.citiesoffreedom.player.CoFPlayer;
import com.devkrazy.citiesoffreedom.player.CoFPlayersManager;
import com.devkrazy.citiesoffreedom.player.missions.MissionScope;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class ConnectionListener implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        CoFPlayersManager manager = CoFPlayersManager.getInstance();
        CoFPlayer cofPlayer = manager.createCoFPlayer(player);


        // Test //
        cofPlayer.addMission(new BlockBreakMission("Casser de la redstone", player, Material.REDSTONE,
                100, 10, 10, MissionScope.PERSONAL, Material.REDSTONE));
        // End Test //
    }

    @EventHandler
    public void onLeave(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        CoFPlayersManager manager = CoFPlayersManager.getInstance();
        manager.deleteCoFPlayer(player);
    }
}
