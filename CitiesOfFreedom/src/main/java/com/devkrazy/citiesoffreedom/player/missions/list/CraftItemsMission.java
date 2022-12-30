/*
 * Copyright (c) 2021, Nathan DJIAN-MARTIN (DevKrazy).
 * This BlockBreakMission.java file is a part of the Smedalis project.
 * Smedalis cannot be copied and/or distributed without the express permission of Nathan DJIAN-MARTIN (DevKrazy)
 *
 */
package com.devkrazy.citiesoffreedom.player.missions.list;

import com.devkrazy.citiesoffreedom.player.missions.MissionScope;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.inventory.ItemStack;

/* Adds a mission that requires to craft a list of items */
public class CraftItemsMission extends ListMission<Material> {

    public CraftItemsMission(String name, String description, Player player, Material guiMaterial, int xpReward, int emeraldsReward, MissionScope missionScope, Material... initialItems) {
        super(name,description, player, guiMaterial, xpReward, emeraldsReward, missionScope, initialItems);
    }

    @Override
    public void processEvent(Event event) {
        if (event instanceof CraftItemEvent) {
            ItemStack result = ((CraftItemEvent) event).getRecipe().getResult();
            if (result.getType().isBlock()) {
                this.completeItem(result.getType());
                this.checkAdvancementAndReward();
            }
        }
    }
}
