/*
 * Copyright (c) 2021, Nathan DJIAN-MARTIN (DevKrazy).
 * This BlockPlaceMission.java file is a part of the Smedalis project.
 * Smedalis cannot be copied and/or distributed without the express permission of Nathan DJIAN-MARTIN (DevKrazy)
 *
 */

package com.devkrazy.citiesoffreedom.player.missions.count;

import com.devkrazy.citiesoffreedom.player.missions.MissionScope;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.block.BlockPlaceEvent;

public class BlockPlaceTask extends CountTask {

    private Material blockType;

    public BlockPlaceTask(String description, Player player, int goal, MissionScope missionScope, Material blockType) {
        super(description, player, goal, missionScope);
        this.blockType = blockType;
    }

    @Override
    public String getLore(){
        return "Placez de la " + this.blockType.name() + ". (" + this.getCounter() + "/"+this.getGoal()+")";
    }
    /**
     * Processed a given event and updates the mission status if necessary.
     * @param event the event to process
     */
    @Override
    public void processEvent(Event event) {
        if (event instanceof BlockPlaceEvent) {
            if (((BlockPlaceEvent) event).getBlock().getType() == this.blockType) {
                this.incrementCounterOf(1);
                this.checkAdvancementAndFinish();
            }
        }
    }
}
