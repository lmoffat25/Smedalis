/*
 * Copyright (c) 2021, Nathan DJIAN-MARTIN (DevKrazy).
 * This Mission.java file is a part of the Smedalis project.
 * Smedalis cannot be copied and/or distributed without the express permission of Nathan DJIAN-MARTIN (DevKrazy)
 *
 */

package com.devkrazy.citiesoffreedom.player.missions;

import com.devkrazy.citiesoffreedom.utils.ItemBuilder;
import net.kyori.adventure.text.Component;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.inventory.ItemStack;

abstract public class Task {

    private Player player;
    private MissionScope missionScope;
    private boolean finished;
    private String description;


    protected Task(String name,String description, Player player, Material guiMaterial,MissionScope missionScope) {

        this.description = description;
        this.player = player;
        this.missionScope = missionScope;
        this.finished = false;
    }


    /*
    Getters
     */

    public String getDescription() { return this.description;}

    public Player getPlayer() {
        return this.player;
    }

    public MissionScope getScope() {
        return this.missionScope;
    }

    public boolean isFinished() {
        return this.finished;
    }

    /*
    Methods
     */

    /**
     * Marks the mission as finished.
     */
    protected void finish() {
        this.finished = true;
    }

    /*
    Abstract
     */

    /**
     * Processed a given event and updates the mission status if necessary.
     * @param event the event to process
     */
    abstract public void processEvent(Event event);

}
