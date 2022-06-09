/*
 * Copyright (c) 2021, Nathan DJIAN-MARTIN (DevKrazy).
 * This EndVotesTask.java file is a part of the Smedalis project.
 * Smedalis cannot be copied and/or distributed without the express permission of Nathan DJIAN-MARTIN (DevKrazy)
 *
 */

package com.devkrazy.citiesoffreedom.game;

import com.devkrazy.citiesoffreedom.player.Team;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

import java.util.TimerTask;

public class EndVotesTask extends TimerTask {

    @Override
    public void run() {
        VotesManager votesManager = VotesManager.getInstance();
        votesManager.endVotingSession();
        Team winner = votesManager.getWinningTeam();

        Bukkit.getOnlinePlayers().forEach(player ->
                player.sendTitle(winner.getColor() + "Équipe victorieuse : " + winner.getColoredName(), ChatColor.GRAY + "Merci d'avoir joué !", 20, 60, 20));

        // TODO: start countdown before stopping server
    }
}
