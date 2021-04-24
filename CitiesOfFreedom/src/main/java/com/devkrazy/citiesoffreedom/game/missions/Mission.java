/*
 * Copyright (c) 2021, Nathan DJIAN-MARTIN (DevKrazy).
 * This Mission.java file is a part of the Smedalis project.
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
import org.bukkit.inventory.ItemStack;

abstract public class Mission {

    private String name;
    private Player player;
    private Material guiMaterial;
    private int xpReward;
    private int emeraldsReward;
    private boolean completed;



    protected Mission(String name, Player player, Material guiMaterial, int xpReward, int emeraldsReward) {
        this.name = name;
        this.player = player;
        this.guiMaterial = guiMaterial;
        this.xpReward = xpReward;
        this.emeraldsReward = emeraldsReward;
        this.completed = false;
    }


    /*
    Getters and setters
     */

    public String getName() {
        return name;
    }

    public boolean isCompleted() {
        return completed;
    }

    public Material getGuiMaterial() {
        return guiMaterial;
    }

    public Player getPlayer() {
        return player;
    }

    public int getXpReward() {
        return xpReward;
    }

    public int getEmeraldsReward() {
        return emeraldsReward;
    }



    /*
    Methods
     */

    /**
     * Gives the owner's mission the experience and emerald reward.
     */
    protected void completeAndReward() {
        if (this.completed == true) return;

        this.completed = true;
        ItemBuilder builder = new ItemBuilder(Material.EMERALD, this.emeraldsReward);
        this.player.getInventory().addItem(builder.build());
        // TODO: check if player's inventory is not full
        this.player.giveExp(this.xpReward);
        this.player.sendMessage(Component.text("" + ChatColor.GREEN + ChatColor.BOLD + "Vous avez réussi la mission " + this.name));
    }

    /**
     * @return a copy of the current Mission
     */
    abstract public Mission copy();

    /**
     * @return an itemstack to display the Mission's status in a GUI
     */
    abstract public ItemStack getGUIItem();

    abstract public void processEvent(Event event);
}
