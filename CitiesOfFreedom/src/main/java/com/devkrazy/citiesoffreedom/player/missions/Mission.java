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

import java.util.ArrayList;
import java.util.Arrays;

abstract public class Mission {

    private String name;
    private ArrayList<Task> taskList;
    private Player player;
    private ItemStack guiItemStack;
    private int xpReward;
    private int emeraldsReward;
    private MissionScope missionScope;
    private String description;
    boolean isChronological;


    protected Mission(String name,String description, Player player, Material guiMaterial, int xpReward, int emeraldsReward, MissionScope missionScope,boolean isChronological,Task... task) {
        this.name = name;
        this.description = description;
        this.player = player;
        this.guiItemStack = new ItemBuilder(guiMaterial, 1).setName(ChatColor.WHITE + this.name).build();
        this.xpReward = xpReward;
        this.emeraldsReward = emeraldsReward;
        this.missionScope = missionScope;
        this.isChronological = isChronological;
        this.taskList = (ArrayList<Task>) Arrays.asList(task);
    }


    /*
    Getters
     */

    public String getName() {
        return this.name;
    }

    public ArrayList<Task> getTaskList(){return this.taskList;}

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

    public Task getFirstTaskNotCompleted(){
        boolean found = false;
        int c = 0;
        while(!found){
            if(!taskList.get(c).isFinished()){
                found = true;
            }
            else{
                c++;
            }
        }
        return taskList.get(c);
    };


    /*
    Methods
     */

    /**
     * Gives the owner's mission the experience and emerald reward if the mission is completed; does nothing otherwise.
     * Marks the mission as finished.
     */
    private void finishAndReward() {

        if(this.isCompleted()){

            this.guiItemStack = new ItemBuilder(this.guiItemStack).addGlow().build();
            ItemBuilder builder = new ItemBuilder(Material.EMERALD, this.emeraldsReward);
            this.player.getInventory().addItem(builder.build());
            // TODO: check if player's inventory is not full
            this.player.giveExp(this.xpReward);
            this.player.sendMessage(Component.text("" + ChatColor.GREEN + ChatColor.BOLD + "Vous avez réussi la mission " + this.name));
        }

    }
    //TODO check if it's really needed, in my opinion not needed

    /**
     * Checks the mission advancement and rewards the player if the mission is completed.
     */

    protected void checkAdvancementAndReward() {
        if (this.isCompleted()) {
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
