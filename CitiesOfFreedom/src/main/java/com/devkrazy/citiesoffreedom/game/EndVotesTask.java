/*
 * Copyright (c) 2021, Nathan DJIAN-MARTIN (DevKrazy).
 * This EndVotesTask.java file is a part of the Smedalis project.
 * Smedalis cannot be copied and/or distributed without the express permission of Nathan DJIAN-MARTIN (DevKrazy)
 *
 */

package com.devkrazy.citiesoffreedom.game;

import java.util.TimerTask;

public class EndVotesTask extends TimerTask {

    @Override
    public void run() {
        VotesManager.getInstance().endVotingSession();
        // announce winner
        // start countdown before stopping server
    }
}
