/*
 * Copyright (c) 2021, Nathan DJIAN-MARTIN (DevKrazy).
 * This CountMission.java file is a part of the Smedalis project.
 * Smedalis cannot be copied and/or distributed without the express permission of Nathan DJIAN-MARTIN (DevKrazy)
 *
 */

package com.devkrazy.citiesoffreedom.player.missions.types;

import com.devkrazy.citiesoffreedom.player.missions.MissionScope;
import com.devkrazy.citiesoffreedom.utils.ItemBuilder;
import net.kyori.adventure.text.Component;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

abstract public class CountMission extends Mission {

    private int goal;
    private int counter;

    public CountMission(String name, Player player, Material guiMaterial, int xpReward, int emeraldsReward, int goal, MissionScope missionScope) {
        super(name, player, guiMaterial, xpReward, emeraldsReward, missionScope);
        this.goal = goal;
        this.counter = 0;
    }


    /*
    Getters
     */

    protected int getGoal() {
        return this.goal;
    }

    protected int getCounter() {
        return this.counter;
    }


    /*
    Methods
     */

    /**
     * Increments the mission counter by a given value. If the counter reaches the mission goal,
     * then the player is rewarded with the mission reward.
     * Does nothing if the counter already reached the mission goal.
     * @param count the value to increment the counter with (must be strictly positive)
     */
    public void incrementCounterOf(int count) {
        if (this.isCompleted()) return;
        if (count <= 0) return;

        int increment = this.counter + count < this.goal ? count  : this.goal - this.counter;
        this.counter += increment;
        this.getPlayer().sendMessage(Component.text(ChatColor.GREEN + "Mission \"" + this.getName() + "\" +" + count));
    }

    /**
     * @return true if the mission goal is reached; false otherwise
     */
    public boolean isGoalReached() {
        return this.counter == this.goal;
    }

    /**
     * @return an itemstack representing the mission to display in a GUI
     */
    @Override
    public ItemStack getGUIItem() {
        return new ItemBuilder(this.getGuiMaterial(), this.getName()).setLore("" + ChatColor.GRAY + this.getCounter() + "/" + this.getGoal()).build();
    }
}
