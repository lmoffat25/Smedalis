/*
 * Copyright (c) 2021, Nathan DJIAN-MARTIN (DevKrazy).
 * This Game.java file is a part of the Smedalis project.
 * Smedalis cannot be copied and/or distributed without the express permission of Nathan DJIAN-MARTIN (DevKrazy)
 *
 */

package com.devkrazy.citiesoffreedom.game;


/**
 * A singleton class representing the game. It can manage its state, the pvp and many other things related
 * to a CitiesofFreedom game.
 */

public class Game {

    private static Game instance = new Game();

    private boolean pvpEnabled;
    private GameState state;


    private Game() {
        this.pvpEnabled = false;
        this.state = GameState.WAITING;
    }

    /**
     * @return the CoFPlayersManager instance.
     */
    public static Game getInstance() {
        return instance;
    }

    /*
    Getters
     */

    public boolean isPvpEnabled() {
        return this.pvpEnabled;
    }

    public GameState getState() {
        return state;
    }

    /*
    Setters
     */

    public void setState(GameState state) {
        this.state = state;
    }

    /*
    Methods
     */

    public void start() {
        this.setState(GameState.PLAYING);
    }
}
