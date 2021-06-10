/*
 * Copyright (c) 2021, Nathan DJIAN-MARTIN (DevKrazy).
 * This EntitiesTameMission.java file is a part of the Smedalis project.
 * Smedalis cannot be copied and/or distributed without the express permission of Nathan DJIAN-MARTIN (DevKrazy)
 *
 */

package com.devkrazy.citiesoffreedom.player.missions.list;

import com.devkrazy.citiesoffreedom.player.missions.MissionScope;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.entity.EntityTameEvent;

public class EntitiesTameTask extends ListTask<EntityType> {

    public EntitiesTameTask(String description, Player player, MissionScope missionScope, EntityType... initialItems) {
        super(description, player, missionScope, initialItems);
    }

    @Override
    public void processEvent(Event event) {
        if (event instanceof EntityTameEvent) {
            EntityTameEvent entityTameEvent = (EntityTameEvent) event;
            this.completeItem(entityTameEvent.getEntity().getType());
            this.checkAdvancementAndFinish();
        }
    }
}
