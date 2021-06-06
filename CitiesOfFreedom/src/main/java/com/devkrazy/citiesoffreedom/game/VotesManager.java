/*
 * Copyright (c) 2021, Nathan DJIAN-MARTIN (DevKrazy).
 * This VotesManager.java file is a part of the Smedalis project.
 * Smedalis cannot be copied and/or distributed without the express permission of Nathan DJIAN-MARTIN (DevKrazy)
 *
 */

package com.devkrazy.citiesoffreedom.game;

import com.devkrazy.citiesoffreedom.player.Team;

import java.util.HashMap;

public class VotesManager {

    private static VotesManager instance = new VotesManager();
    private HashMap<Team, Integer> votes;

    private VotesManager() {
        this.votes = new HashMap<>();
        this.votes.put(Team.RED, 0);
        this.votes.put(Team.BLUE, 0);
        this.votes.put(Team.PURPLE, 0);
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
        return this.votes.get(team);
    }


    /*
    Methods
     */

    /**
     * Adds a vote to a given team.
     * @param team the team which will receive a vote
     */
    public void addVote(Team team) {
        int currentVotes = this.getVotes(team);
        this.votes.put(team, currentVotes + 1);
    }

    /**
     * Removes a vote from a given team.
     * @param team the team which will loose a vote
     */
    public void removeVote(Team team) {
        int currentVotes = this.getVotes(team);
        this.votes.put(team, currentVotes - 1);
    }
}
