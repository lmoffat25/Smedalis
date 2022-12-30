/*
 * Copyright (c) 2021, Nathan DJIAN-MARTIN (DevKrazy).
 * This BlockBreakMission.java file is a part of the Smedalis project.
 * Smedalis cannot be copied and/or distributed without the express permission of Nathan DJIAN-MARTIN (DevKrazy)
 *
 */
package com.devkrazy.citiesoffreedom.player.missions.count;

import com.devkrazy.citiesoffreedom.player.missions.MissionScope;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.inventory.ItemStack;

public class CraftItemMission extends CountMission {

    private Material itemType;

    public CraftItemMission(String name, String description, Player player, Material guiMaterial, int xpReward, int emeraldsReward, int goal, MissionScope missionScope, Material itemType) {
        super(name, description, player, guiMaterial, xpReward, emeraldsReward, goal, missionScope);
        this.itemType = itemType;
    }

    @Override
    public void processEvent(Event event) {
        if (event instanceof CraftItemEvent) {
            CraftItemEvent craftEvent = (CraftItemEvent) event;
            ItemStack result = craftEvent.getCurrentItem();
            if (result.isSimilar(new ItemStack(itemType))) {
                int amount = result.getAmount();
                this.incrementCounterOf(amount);
                this.checkAdvancementAndReward();
            }
        }
    }

}

