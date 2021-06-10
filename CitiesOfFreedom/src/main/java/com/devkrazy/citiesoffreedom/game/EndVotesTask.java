/*
 * Copyright (c) 2021, Nathan DJIAN-MARTIN (DevKrazy).
 * This EndVotesTask.java file is a part of the Smedalis project.
 * Smedalis cannot be copied and/or distributed without the express permission of Nathan DJIAN-MARTIN (DevKrazy)
 *
 */

package com.devkrazy.citiesoffreedom.game;

import com.devkrazy.citiesoffreedom.player.Team;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.title.Title;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

import java.util.TimerTask;

public class EndVotesTask extends TimerTask {

    @Override
    public void run() {
        VotesManager votesManager = VotesManager.getInstance();
        votesManager.endVotingSession();
        Team winner = votesManager.getWinningTeam();

        Bukkit.getServer().showTitle(Title.title(Component.text(winner.getColor() + "Équipe victorieuse : " + winner.getColoredName()),
                Component.text(ChatColor.GRAY + "Merci d'avoir joué !")));

        // TODO: start countdown before stopping server
    }
}
