/*
 * Copyright (c) 2021, Nathan DJIAN-MARTIN (DevKrazy).
 * This ItemBreakMission.java file is a part of the Smedalis project.
 * Smedalis cannot be copied and/or distributed without the express permission of Nathan DJIAN-MARTIN (DevKrazy)
 *
 */

package com.devkrazy.citiesoffreedom.player.missions.count;

import com.devkrazy.citiesoffreedom.player.missions.MissionScope;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.player.PlayerItemBreakEvent;

public class ItemBreakMission extends CountMission {

    private Material itemMaterial;

    public ItemBreakMission(String name,String description, Player player, Material guiMaterial, int xpReward, int emeraldsReward, int goal, MissionScope missionScope, Material itemMaterial) {
        super(name,description, player, guiMaterial, xpReward, emeraldsReward, goal, missionScope);
        this.itemMaterial = itemMaterial;
    }

    /**
     * Processes a given event and updates the mission status if necessary.
     * @param event the event to process
     */
    @Override
    public void processEvent(Event event) {
        if (event instanceof PlayerItemBreakEvent) {
            if (((PlayerItemBreakEvent) event).getBrokenItem().getType() == this.itemMaterial) {
                this.incrementCounterOf(1);
                this.checkAdvancementAndReward();
            }
        }
    }
}
