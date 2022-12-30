/*
 * Copyright (c) 2021, Nathan DJIAN-MARTIN (DevKrazy).
 * This BlockBreakMission.java file is a part of the Smedalis project.
 * Smedalis cannot be copied and/or distributed without the express permission of Nathan DJIAN-MARTIN (DevKrazy)
 *
 */
package com.devkrazy.citiesoffreedom.player.missions.list;

import com.devkrazy.citiesoffreedom.player.missions.MissionScope;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.entity.EntitySpawnEvent;

public class SpawnMobMission extends ListMission<EntityType> {

    public SpawnMobMission(String name, String description, Player player, Material guiMaterial, int xpReward, int emeraldsReward, MissionScope missionScope, EntityType... initialItems) {
        super(name, description, player, guiMaterial, xpReward, emeraldsReward, missionScope, initialItems);
    }

    @Override
    public void processEvent(Event event) {
        if (event instanceof EntitySpawnEvent) {
            EntitySpawnEvent spawnEvent = (EntitySpawnEvent) event;
            EntityType entityType = spawnEvent.getEntity().getType();
            // Check if the spawned entity is in the list of mobs to track
            this.completeItem(entityType);
            this.checkAdvancementAndReward();
        }
    }
}
