/*
 * Copyright (c) 2021, Nathan DJIAN-MARTIN (DevKrazy).
 * This EndGameTask.java file is a part of the Smedalis project.
 * Smedalis cannot be copied and/or distributed without the express permission of Nathan DJIAN-MARTIN (DevKrazy)
 *
 */

package com.devkrazy.citiesoffreedom.game;

import java.util.*;

/**
 * This task ends the game when it is ran. It can be scheduled using a Java timer.
 */
public class EndGameTask extends TimerTask {

    /**
     * Ends the game when the task is executed.
     */
    @Override
    public void run() {
        Game.getInstance().end();
        VotesManager.getInstance().startVotingSession();

        Calendar rightNow = new GregorianCalendar(TimeZone.getTimeZone("Europe/Paris"));
        rightNow.add(Calendar.SECOND, 20);
        new Timer().schedule(new EndVotesTask(), rightNow.getTime());
    }
}
