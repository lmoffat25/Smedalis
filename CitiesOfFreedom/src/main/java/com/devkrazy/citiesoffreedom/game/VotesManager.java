/*
 * Copyright (c) 2021, Nathan DJIAN-MARTIN (DevKrazy).
 * This VotesManager.java file is a part of the Smedalis project.
 * Smedalis cannot be copied and/or distributed without the express permission of Nathan DJIAN-MARTIN (DevKrazy)
 *
 */

package com.devkrazy.citiesoffreedom.game;

import com.devkrazy.citiesoffreedom.guis.guis.TeamsTeleportationGUI;
import com.devkrazy.citiesoffreedom.guis.guis.TeamsVotingGUI;
import com.devkrazy.citiesoffreedom.player.Team;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.HashMap;

public class VotesManager {

    private static VotesManager instance = new VotesManager();
    private HashMap<Team, Integer> votesCounter;
    private HashMap<Player, Team> votes;
    private boolean votingEnabled;

    private VotesManager() {
        this.votesCounter = new HashMap<>();
        this.votesCounter.put(Team.RED, 0);
        this.votesCounter.put(Team.BLUE, 0);
        this.votesCounter.put(Team.PURPLE, 0);
        this.votes = new HashMap<>();
        this.votingEnabled = false;
    }

    /**
     * @return the CoFPlayersManager instance.
     */
    public static VotesManager getInstance() {
        return instance;
    }

    /*
    Getters
     */

    /**
     * Gets the current amount of votes a given team currently has.
     * @param team the team of which we want the votes
     * @return the amount of votes of for the given team
     */
    public int getVotes(Team team) {
        return this.votesCounter.get(team);
    }

    public boolean isVotingEnabled() {
        return this.votingEnabled;
    }


    /*
    Methods
     */

    /**
     * Adds a vote to a given team and removes the old vote if the player has already voted.
     * @param player the author of the vote
     * @param team the team which will receive a vote
     */
    public void addVote(Player player, Team team) {
        int currentVotes = this.getVotes(team);

        this.removeVoteOf(player);

        // Adds the new vote
        this.votes.put(player, team);
        this.votesCounter.put(team, currentVotes + 1);
    }

    /**
     * Removes a vote of a given player.
     * @param player the player of who the vote will be removed
     */
    public void removeVoteOf(Player player) {
        if (this.votes.containsKey(player)) {
            Team team = this.votes.get(player);
            int currentVotes = this.getVotes(team);
            this.votesCounter.put(this.votes.get(player), currentVotes - 1);
        }
    }

    /**
     * Starts a voting session.
     */
    public void startVotingSession() {
        this.votingEnabled = true;

        for (Player online : Bukkit.getServer().getOnlinePlayers()) {
            online.setAllowFlight(true);
            online.setFlying(true);
            online.getInventory().clear();
            online.getInventory().addItem(TeamsTeleportationGUI.getInstance().getOpener());
            online.getInventory().addItem(TeamsVotingGUI.getInstance().getOpener());
        }
    }

    /**
     * Ends a voting session.
     */
    public void endVotingSession() {
        this.votingEnabled = false;

        for (Player online : Bukkit.getServer().getOnlinePlayers()) {
            online.getInventory().clear();
        }
    }
}
