/*
 * Copyright (c) 2021, Nathan DJIAN-MARTIN (DevKrazy).
 * This ConnectionListener.java file is a part of the Smedalis project.
 * Smedalis cannot be copied and/or distributed without the express permission of Nathan DJIAN-MARTIN (DevKrazy)
 *
 */

package com.devkrazy.citiesoffreedom.listeners;

import com.devkrazy.citiesoffreedom.CitiesOfFreedom;
import com.devkrazy.citiesoffreedom.config.files.SettingsConfig;
import com.devkrazy.citiesoffreedom.game.Countdown;
import com.devkrazy.citiesoffreedom.game.Game;
import com.devkrazy.citiesoffreedom.game.GameState;
import com.devkrazy.citiesoffreedom.player.CoFPlayer;
import com.devkrazy.citiesoffreedom.player.CoFPlayersManager;
import com.devkrazy.citiesoffreedom.player.missions.MissionScope;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class ConnectionListener implements Listener {

    private final CoFPlayersManager manager = CoFPlayersManager.getInstance();
    private final SettingsConfig settings = SettingsConfig.getInstance();
    private final int onlinePlayersAmount = Bukkit.getServer().getOnlinePlayers().size();
    private final Game game = Game.getInstance();


    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        CoFPlayer cofPlayer = manager.createCoFPlayer(player);

        Bukkit.getServer().sendMessage(Component.text("JOUEURS " + settings.getMinimumPlayers()));
        if (onlinePlayersAmount == settings.getMinimumPlayers() - 1 && game.getState() == GameState.WAITING) {
            game.getGameStartCountdown().start();
        }

        // Test //
        //cofPlayer.addMission(new BlockBreakMission("Casser de la redstone", "Casser le nombre de poudre de redstone requis.", player, Material.REDSTONE,
        //        100, 10, 10, MissionScope.PERSONAL, Material.REDSTONE_WIRE));
        // End Test //
    }

    @EventHandler
    public void onLeave(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        manager.deleteCoFPlayer(player);

        if (onlinePlayersAmount <= settings.getMinimumPlayers()) {
            game.getGameStartCountdown().reset();
        }
    }
}
