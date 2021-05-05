/*
 * Copyright (c) 2021, Nathan DJIAN-MARTIN (DevKrazy).
 * This EndGameTask.java file is a part of the Smedalis project.
 * Smedalis cannot be copied and/or distributed without the express permission of Nathan DJIAN-MARTIN (DevKrazy)
 *
 */

package com.devkrazy.citiesoffreedom.game;

import java.util.TimerTask;

/**
 * This task ends the game when it is ran. It can be scheduled using a Java timer.
 */
public class EndGameTask extends TimerTask {

    @Override
    public void run() {
        Game game = Game.getInstance();
        game.end();
    }
}
