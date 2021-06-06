/*
 * Copyright (c) 2021, Nathan DJIAN-MARTIN (DevKrazy).
 * This EntityKillMission.java file is a part of the Smedalis project.
 * Smedalis cannot be copied and/or distributed without the express permission of Nathan DJIAN-MARTIN (DevKrazy)
 *
 */

package com.devkrazy.citiesoffreedom.player.missions.count;

import com.devkrazy.citiesoffreedom.player.missions.MissionScope;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.entity.EntityDeathEvent;

public class EntityKillMission extends CountMission {

    private EntityType entityType;

    public EntityKillMission(String name,String description, Player player, Material guiMaterial, int xpReward, int emeraldsReward, int goal, MissionScope missionScope, EntityType entityType) {
        super(name,description, player, guiMaterial, xpReward, emeraldsReward, goal, missionScope);
        this.entityType = entityType;
    }

    @Override
    public void processEvent(Event event) {
        if (event instanceof EntityDeathEvent) {
            if (((EntityDeathEvent) event).getEntity().getType() == this.entityType) {
                this.incrementCounterOf(1);
                this.checkAdvancementAndReward();
            }
        }
    }
}
