/*
 * Copyright (c) 2021, Nathan DJIAN-MARTIN (DevKrazy).
 * This Mission.java file is a part of the Smedalis project.
 * Smedalis cannot be copied and/or distributed without the express permission of Nathan DJIAN-MARTIN (DevKrazy)
 *
 */

package com.devkrazy.citiesoffreedom.player.missions;

import com.devkrazy.citiesoffreedom.utils.ItemBuilder;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.inventory.ItemStack;

abstract public class Mission {

    private String name;
    private Player player;
    private ItemStack guiItemStack;
    private int xpReward;
    private int emeraldsReward;
    private MissionScope missionScope;
    private boolean finished;
    private String description;


    protected Mission(String name,String description, Player player, Material guiMaterial, int xpReward, int emeraldsReward, MissionScope missionScope) {
        this.name = name;
        this.description = description;
        this.player = player;
        this.guiItemStack = new ItemBuilder(guiMaterial, 1).setName(ChatColor.WHITE + this.name).build();
        this.xpReward = xpReward;
        this.emeraldsReward = emeraldsReward;
        this.missionScope = missionScope;
        this.finished = false;
    }


    /*
    Getters
     */

    public String getName() {
        return this.name;
    }

    public String getDescription() { return this.description;}

    protected ItemStack getGUIItem() {
        return this.guiItemStack;
    }

    public Player getPlayer() {
        return this.player;
    }

    public int getXpReward() {
        return this.xpReward;
    }

    public int getEmeraldsReward() {
        return this.emeraldsReward;
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
     * Gives the owner's mission the experience and emerald reward if the mission is completed; does nothing otherwise.
     * Marks the mission as finished.
     */
    private void finishAndReward() {
        this.finished = true;
        this.guiItemStack = new ItemBuilder(this.guiItemStack).addGlow().build();
        ItemBuilder builder = new ItemBuilder(Material.EMERALD, this.emeraldsReward);
        this.player.getInventory().addItem(builder.build());
        // TODO: check if player's inventory is not full
        this.player.giveExp(this.xpReward);
        this.player.sendMessage("§a§bVous avez réussi la mission " + this.name);
    }

    /**
     * Checks the mission advancement and rewards the player if the mission is completed.
     */
    protected void checkAdvancementAndReward() {
        if (this.isCompleted() == true && this.finished == false) {
            this.finishAndReward();
        }
    }

    /*
    Abstract
     */

    /**
     * Processed a given event and updates the mission status if necessary.
     * @param event the event to process
     */
    abstract public void processEvent(Event event);

    /**
     * @return an itemstack to display the Mission's status in a GUI
     */
    abstract public ItemStack buildGUIItem();

    /**
     * @return true if the mission is completed; false otherwise
     */
    abstract public boolean isCompleted();
}
