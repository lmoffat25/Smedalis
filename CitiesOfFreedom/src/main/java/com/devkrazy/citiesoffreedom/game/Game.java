/*
 * Copyright (c) 2021, Nathan DJIAN-MARTIN (DevKrazy).
 * This Game.java file is a part of the Smedalis project.
 * Smedalis cannot be copied and/or distributed without the express permission of Nathan DJIAN-MARTIN (DevKrazy)
 *
 */

package com.devkrazy.citiesoffreedom.game;


import net.kyori.adventure.text.Component;
import net.kyori.adventure.title.Title;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

import java.util.*;

/**
 * A singleton class representing the game. It can manage its state, the pvp and many other things related
 * to a CitiesOfFreedom game.
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

    public void setPvpEnabled(boolean pvpEnabled) {
        this.pvpEnabled = pvpEnabled;
    }

    /*
    Methods
     */

    public void start() {
        this.setState(GameState.PLAYING);
        this.setPvpEnabled(true);
        Bukkit.getServer().showTitle(Title.title(Component.text(ChatColor.GOLD + "CitiesOfFreedom"),
                Component.text(ChatColor.GRAY + "Développé par " + ChatColor.DARK_PURPLE + "DevKrazy")));

        // creates a calendar with the current time in the Europe/Paris timezone
        Calendar rightNow = new GregorianCalendar(TimeZone.getTimeZone("Europe/Paris"));
        rightNow.add(Calendar.MINUTE, 1);

        new Timer().schedule(new EndGameTask(), rightNow.getTime());

    }

    public void end() {
        Bukkit.getServer().sendMessage(Component.text("FIN DE LA PARTIE"));
    }
}
