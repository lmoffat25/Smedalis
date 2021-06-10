/*
 * Copyright (c) 2021, Nathan DJIAN-MARTIN (DevKrazy).
 * This ListMission.java file is a part of the Smedalis project.
 * Smedalis cannot be copied and/or distributed without the express permission of Nathan DJIAN-MARTIN (DevKrazy)
 *
 */

package com.devkrazy.citiesoffreedom.player.missions.list;

import com.devkrazy.citiesoffreedom.player.missions.Mission;
import com.devkrazy.citiesoffreedom.player.missions.MissionScope;
import com.devkrazy.citiesoffreedom.player.missions.Task;
import com.devkrazy.citiesoffreedom.utils.ItemBuilder;
import com.devkrazy.citiesoffreedom.utils.StringUtils;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * This class is used to represent missions which have a list of actions.
 * For example killing one of every hostile mobs, breaking one of
 */
abstract public class ListTask<T> extends Task {

    private List<T> remainingItems;
    private List<T> completedItems;

    protected ListTask(String description, Player player, MissionScope missionScope, T... initialItems) {
        super(description, player, missionScope);
        this.remainingItems = new LinkedList<>(Arrays.asList(initialItems)); // linked list for faster remove
        this.completedItems = new ArrayList<>();

    }


    /*
    Methods
     */

    /**
     * Marks an item of the mission as completed. If the missions does not contain this item does nothing.
     * @param item the item we want to mark as completed
     */
    public void completeItem(T item) {
        if (this.remainingItems.contains(item) == true) {
            this.remainingItems.remove(item);
            this.completedItems.add(item);
        } else {
            Bukkit.getLogger().warning("Tried to move the item " + item + " but it is not in the remaining items list.");
        }
    }

    /*
    Overridden methods
     */
    @Override
    public boolean isCompleted(){
        return this.remainingItems.size() == 0;
    }
    @Override
    public void checkAdvancementAndFinish(){}
    /**
     * Formats a given item to a TextComponent. The returned TextComponent can then be added to the lore
     * @param item the item to format
     * @param color the color of the resulting TextComponent
     * @return
     */
    private TextComponent format(Object item, ChatColor color) {
        return Component.text(color + " - " + StringUtils.capitalize(item.toString()));
    }

}
