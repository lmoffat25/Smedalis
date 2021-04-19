/*
 * Copyright (c) 2021, Nathan DJIAN-MARTIN (DevKrazy).
 * This CountMission.java file is a part of the Smedalis project.
 * Smedalis cannot be copied and/or distributed without the express permission of Nathan DJIAN-MARTIN (DevKrazy)
 *
 */

package com.devkrazy.citiesoffreedom.game.missions;

import net.kyori.adventure.text.Component;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.entity.Player;

public class CountMission extends Mission {

    private int goal;
    private int counter;

    public CountMission(String name, Player player, int xpReward, int emeraldsReward, int goal, MissionType missionType) {
        super(name, player, xpReward, emeraldsReward, missionType);
        this.goal = goal;
        this.counter = 0;
    }


    /*
    Getters
     */

    public int getGoal() {
        return this.goal;
    }

    public int getCounter() {
        return this.counter;
    }


    /*
    Methods
     */

    /**
     * Increments the mission counter of 1. If the counter reaches the mission goal,
     * then the player is rewarded with the mission reward.
     * Does nothing if the counter already reached the mission goal.
     */
    public void incrementCounter() {
        this.incrementCounterOf(1);
    }

    /**
     * Increments the mission counter by a given value. If the counter reaches the mission goal,
     * then the player is rewarded with the mission reward.
     * Does nothing if the counter already reached the mission goal.
     * @param count the value to increment the counter with
     */
    public void incrementCounterOf(int count) {
        if (this.isCompleted()) return;

        int increment = this.counter + count < this.goal ? count  : this.goal - this.counter;
        this.counter += increment;
        this.getPlayer().sendMessage(Component.text(ChatColor.GREEN + "Mission \"" + this.getName() + "\" +" + count));

        if (this.counter == this.goal) {
            this.giveReward();
        }
    }

    /**
     * @return a copy of the current CountMission
     */
    @Override
    public CountMission copy() {
        CountMission newMission = new CountMission(this.getName(), this.getPlayer(), this.getXpReward(),
                this.getEmeraldsReward(), this.getGoal(), this.getType());
        newMission.counter = this.counter;
        return newMission;
    }
}
