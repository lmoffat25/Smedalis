/*
 * Copyright (c) 2021, Nathan DJIAN-MARTIN (DevKrazy).
 * This BlockBreakMission.java file is a part of the Smedalis project.
 * Smedalis cannot be copied and/or distributed without the express permission of Nathan DJIAN-MARTIN (DevKrazy)
 *
 */

package com.devkrazy.citiesoffreedom.game.missions;

import com.devkrazy.citiesoffreedom.utils.ItemBuilder;
import net.kyori.adventure.text.Component;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

public class BlockBreakMission extends Mission {

    private int goal;
    private int counter;
    private Material blockType;

    public BlockBreakMission(String name, Player player, Material guiMaterial, int xpReward, int emeraldsReward, int goal, Material blockType) {
        super(name, player, guiMaterial, xpReward, emeraldsReward);
        this.goal = goal;
        this.counter = 0;
        this.blockType = blockType;
    }


    /*
    Getters and setters
     */

    public int getGoal() {
        return goal;
    }

    public void setGoal(int goal) {
        this.goal = goal;
    }

    public int getCounter() {
        return counter;
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }


    /*
    Methods
     */

    @Override
    public void processEvent(Event event) {
        if (event instanceof BlockBreakEvent) {
            if (((BlockBreakEvent) event).getBlock().getType() == this.blockType) {
                this.incrementCounterOf(1);
                if (this.isGoalReached() == true) {
                    this.completeAndReward();
                }
            }
        }
    }

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
        return new ItemBuilder(this.getGuiMaterial(), this.getName()).setLore("" + ChatColor.GRAY + this.counter + "/" + this.goal).build();
    }
}
