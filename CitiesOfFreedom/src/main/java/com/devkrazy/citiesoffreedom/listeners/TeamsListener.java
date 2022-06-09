/*
 * Copyright (c) 2021, Nathan DJIAN-MARTIN (DevKrazy).
 * This TeamsListener.java file is a part of the Smedalis project.
 * Smedalis cannot be copied and/or distributed without the express permission of Nathan DJIAN-MARTIN (DevKrazy)
 *
 */

package com.devkrazy.citiesoffreedom.listeners;

import com.devkrazy.citiesoffreedom.events.TeamPickEvent;
import com.devkrazy.citiesoffreedom.game.Game;
import com.devkrazy.citiesoffreedom.game.GameState;
import com.devkrazy.citiesoffreedom.player.CoFPlayer;
import com.devkrazy.citiesoffreedom.player.CoFPlayersManager;
import com.devkrazy.citiesoffreedom.player.Team;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class TeamsListener implements Listener {

    @EventHandler
    public void onTeamPick(TeamPickEvent event) {
        Player player = event.getPlayer();
        Game game = Game.getInstance();
        CoFPlayersManager manager = CoFPlayersManager.getInstance();
        CoFPlayer cofPlayer = manager.getCoFPlayer(player);
        Team team = event.getTeam();

        if (game.getState() != GameState.WAITING) {
            // prevents the player to change team if the game has started
            event.setCancelled(true);
            player.sendMessage(ChatColor.RED + "Vous ne pouvez plus changer d'équipe.");
            return;
        }

        cofPlayer.setTeam(team);
        player.sendMessage(ChatColor.GRAY + "Vous avez choisi l'équipe " +
                team.getColor() + team.getName() + ChatColor.GRAY + ".");

    }
}
