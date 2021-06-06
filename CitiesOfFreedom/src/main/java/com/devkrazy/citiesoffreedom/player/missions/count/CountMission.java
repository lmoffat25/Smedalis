/*
 * Copyright (c) 2021, Nathan DJIAN-MARTIN (DevKrazy).
 * This CountMission.java file is a part of the Smedalis project.
 * Smedalis cannot be copied and/or distributed without the express permission of Nathan DJIAN-MARTIN (DevKrazy)
 *
 */

package com.devkrazy.citiesoffreedom.player.missions.count;

import com.devkrazy.citiesoffreedom.player.missions.MissionScope;
import com.devkrazy.citiesoffreedom.player.missions.Mission;
import com.devkrazy.citiesoffreedom.utils.ItemBuilder;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

abstract public class CountMission extends Mission {

    private int goal;
    private int counter;

    public CountMission(String name, String description, Player player, Material guiMaterial, int xpReward, int emeraldsReward, int goal, MissionScope missionScope) {
        super(name, description, player, guiMaterial, xpReward, emeraldsReward, missionScope);
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
     * Increments the mission counter by a given value. This function will make sure that the counter
     * remains lower than or equal to the goal. Does nothing if the counter already reached the mission goal.
     * @param count the value to increment the counter with (must be strictly positive)
     */
    protected void incrementCounterOf(int count) {
        if (this.isCompleted() == true) return;
        if (count <= 0) return;
        int increment = this.counter + count < this.goal ? count  : this.goal - this.counter;
        this.counter += increment;
        //this.getPlayer().sendMessage(Component.text(ChatColor.GREEN + "Mission \"" + this.getName() + "\" +" + count));
    }

    /*
    Overridden methods
     */

    /**
     * @return an itemstack representing the mission to display in a GUI
     */
    @Override
    public ItemStack buildGUIItem() {
        ChatColor counterColor = this.counter == this.goal ? ChatColor.GREEN : ChatColor.RED;
        return new ItemBuilder(this.getGUIItem()).setLore("" + counterColor + this.getCounter() + ChatColor.GRAY + "/" + this.getGoal(),this.getDescription()).build();
    }

    /**
     * @return true if the counter reached the goal; false otherwise
     */
    @Override
    public boolean isCompleted() {
        return this.counter == this.goal;
    }
}
