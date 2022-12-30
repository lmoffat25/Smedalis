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
import org.bukkit.event.player.PlayerMoveEvent;

public class PlayerMovedMission extends CountMission {

    private int targetX;
    private int targetY;
    private int targetZ;
    private int radius;

    public PlayerMovedMission(String name, String description, Player player, Material guiMaterial, int xpReward, int emeraldsReward, int goal, MissionScope missionScope, int targetX, int targetY, int targetZ, int radius) {
        super(name, description, player, guiMaterial, xpReward, emeraldsReward, goal, missionScope);
        this.targetX = targetX;
        this.targetY = targetY;
        this.targetZ = targetZ;
        this.radius = radius;
    }
 

    @Override
    public void processEvent(Event event) {
        if (event instanceof PlayerMoveEvent) {
            PlayerMoveEvent moveEvent = (PlayerMoveEvent) event;
            if (Math.abs(moveEvent.getTo().getBlockX() - targetX) <= radius && Math.abs(moveEvent.getTo().getBlockY() - targetY) <= radius && Math.abs(moveEvent.getTo().getBlockZ() - targetZ) <= radius) {
                this.incrementCounterOf(1);
                this.checkAdvancementAndReward();
            }
        }
    }
}
