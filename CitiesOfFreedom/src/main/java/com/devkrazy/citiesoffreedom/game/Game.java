/*
 * Copyright (c) 2021, Nathan DJIAN-MARTIN (DevKrazy).
 * This Game.java file is a part of the Smedalis project.
 * Smedalis cannot be copied and/or distributed without the express permission of Nathan DJIAN-MARTIN (DevKrazy)
 *
 */

package com.devkrazy.citiesoffreedom.game;


import com.devkrazy.citiesoffreedom.CitiesOfFreedom;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.TimeZone;
import java.util.Timer;

/**
 * A singleton class representing the game. It can manage its state, the pvp and many other things related
 * to a CitiesOfFreedom game.
 */

public class Game {

    private static Game instance = new Game();

    private boolean pvpEnabled;
    private GameState state;

    private Countdown gameStartCountdown = new Countdown(1290000, CitiesOfFreedom.getInstance()) {
        @Override
        protected void onEnd() {
            Game.getInstance().start();
        }
    };


    private Game() {
        this.pvpEnabled = false;
        this.state = GameState.WAITING;
    }

    /**
     * @return the Game's instance.
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
        return this.state;
    }

    public Countdown getGameStartCountdown() {
        return this.gameStartCountdown;
    }

    /*
    Setters
     */

    public void setState(GameState state) {
        this.state = state;
    }

    public void setPvpEnabled(boolean pvpEnabled) {
        this.pvpEnabled = pvpEnabled;
    }

    /*
    Methods
     */

    /**
     * Starts the game. If the game has already started does nothing.
     */
    public void start() {
        if (this.getState() == GameState.WAITING) {
            this.setState(GameState.PLAYING);
            this.setPvpEnabled(true);

            Bukkit.getOnlinePlayers().forEach(player ->
                    player.sendTitle(ChatColor.GOLD + "CitiesOfFreedom", ChatColor.GRAY + "Développé par " + ChatColor.YELLOW + "DevKrazy & _Rolyn", 20, 60, 20));

            // creates a calendar with the current time in the Europe/Paris timezone
            Calendar rightNow = new GregorianCalendar(TimeZone.getTimeZone("Europe/Paris"));
            rightNow.add(Calendar.SECOND, 10);
            new Timer().schedule(new EndGameTask(), rightNow.getTime());
        }
    }

    /**
     * Ends the game.
     */
    public void end() {
        if (this.getState() == GameState.PLAYING) {
            this.setState(GameState.FINISHED);
            this.setPvpEnabled(false);
            Bukkit.getOnlinePlayers().forEach(player ->
                    player.sendTitle(ChatColor.RED + "Partie terminée.", ChatColor.GRAY + "Votez pour votre ville préférée.", 20, 60, 20));
        }
    }
}
