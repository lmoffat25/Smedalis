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
import org.bukkit.event.block.BlockBreakEvent;

public class BlockBreakTask extends CountTask {

    private Material blockType;

    public BlockBreakTask(String description, Player player, int goal, MissionScope missionScope, Material blockType) {
        super(description, player, goal, missionScope);
        this.blockType = blockType;
    }

    /**
     * Processes a given event and updates the mission status if necessary.
     * @param event the event to process
     */
    @Override
    public void processEvent(Event event) {
        if (event instanceof BlockBreakEvent) {
            if (((BlockBreakEvent) event).getBlock().getType() == this.blockType) {
                this.incrementCounterOf(1);
                this.checkAdvancementAndFinish();
            }
        }
    }
}