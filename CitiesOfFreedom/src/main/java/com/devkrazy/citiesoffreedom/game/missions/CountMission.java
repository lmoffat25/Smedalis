/*
 * Copyright (c) 2021, Nathan DJIAN-MARTIN (DevKrazy).
 * This CountMission.java file is a part of the Smedalis project.
 * Smedalis cannot be copied and/or distributed without the express permission of Nathan DJIAN-MARTIN (DevKrazy)
 *
 */

package com.devkrazy.citiesoffreedom.game.missions;

import com.devkrazy.citiesoffreedom.utils.ItemBuilder;
import net.kyori.adventure.text.Component;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class CountMission extends Mission {

    private int goal;
    private int counter;

    public CountMission(String name, Player player, Material guiMaterial, int xpReward, int emeraldsReward, int goal, MissionType missionType) {
        super(name, player, guiMaterial, xpReward, emeraldsReward, missionType);
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
     * @param count the value to increment the counter with (must be strictly positive)
     */
    public void incrementCounterOf(int count) {
        if (this.isCompleted()) return;
        if (count <= 0) return;

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
        CountMission newMission = new CountMission(this.getName(), this.getPlayer(), this.getGuiMaterial(),
                this.getXpReward(), this.getEmeraldsReward(), this.getGoal(), this.getType());
        newMission.counter = this.counter;
        return newMission;
    }

    /**
     * @return an itemstack to display the CountMission's status in a GUI
     */
    @Override
    public ItemStack getGUIItem() {
        ItemBuilder builder = new ItemBuilder(this.getGuiMaterial(), this.getName());
        String completed = this.isCompleted() ? ChatColor.GREEN + "Mission terminée" : ChatColor.RED + "A terminer";
        builder.setLore(ChatColor.GRAY + "Avancement : " + ChatColor.YELLOW + this.counter + "/" + this.goal, completed);
        return builder.build();
    }
}
