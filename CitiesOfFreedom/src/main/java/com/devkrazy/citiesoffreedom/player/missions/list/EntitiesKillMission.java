/*
 * Copyright (c) 2021, Nathan DJIAN-MARTIN (DevKrazy).
 * This EntitiesKillMission.java file is a part of the Smedalis project.
 * Smedalis cannot be copied and/or distributed without the express permission of Nathan DJIAN-MARTIN (DevKrazy)
 *
 */

package com.devkrazy.citiesoffreedom.player.missions.list;

import com.devkrazy.citiesoffreedom.player.missions.MissionScope;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.entity.EntityDeathEvent;

public class EntitiesKillMission extends ListMission<EntityType> {

    public EntitiesKillMission(String name, String description, Player player, Material guiMaterial, int xpReward, int emeraldsReward, MissionScope missionScope, EntityType... initialItems) {
        super(name,description, player, guiMaterial, xpReward, emeraldsReward, missionScope, initialItems);
    }

    @Override
    public void processEvent(Event event) {
        if (event instanceof EntityDeathEvent) {
            EntityDeathEvent entityDeathEvent = (EntityDeathEvent) event;
            this.completeItem(entityDeathEvent.getEntity().getType());
            this.checkAdvancementAndReward();
        }
    }
}
