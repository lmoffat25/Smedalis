/*
 * Copyright (c) 2021, Nathan DJIAN-MARTIN (DevKrazy).
 * This ListMission.java file is a part of the Smedalis project.
 * Smedalis cannot be copied and/or distributed without the express permission of Nathan DJIAN-MARTIN (DevKrazy)
 *
 */

package com.devkrazy.citiesoffreedom.player.missions.list;

import com.devkrazy.citiesoffreedom.player.missions.Mission;
import com.devkrazy.citiesoffreedom.player.missions.MissionScope;
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
abstract public class ListMission<T> extends Mission {

    private List<T> remainingItems;
    private List<T> completedItems;

    protected ListMission(String name, String description, Player player, Material guiMaterial, int xpReward, int emeraldsReward, MissionScope missionScope, T... initialItems) {
        super(name,description, player, guiMaterial, xpReward, emeraldsReward, missionScope);
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

    /**
     * @return an itemstack representing the mission to display in a GUI
     */
    @Override
    public ItemStack buildGUIItem() {
        ItemBuilder builder = new ItemBuilder(this.getGUIItem());
        List<Component> lore = new ArrayList<>();
        lore.add(Component.text(ChatColor.GRAY + this.getDescription()));

        for (Object item : this.remainingItems) {
            lore.add(format(item, ChatColor.RED));
        }
        for (Object item : this.completedItems) {
            lore.add(format(item, ChatColor.GREEN));
        }

        builder.setLore(lore);
        return builder.build();
    }

    /**
     * Formats a given item to a TextComponent. The returned TextComponent can then be added to the lore
     * @param item the item to format
     * @param color the color of the resulting TextComponent
     * @return
     */
    private TextComponent format(Object item, ChatColor color) {
        return Component.text(color + " - " + StringUtils.capitalize(item.toString()));
    }

    /**
     * @return true if the remaining items list is empty; false otherwise
     */
    @Override
    public boolean isCompleted() {
        return this.remainingItems.size() == 0;
    }
}
