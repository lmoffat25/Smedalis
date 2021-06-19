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

public class EntityKillTask extends CountTask {

    private EntityType entityType;

    public EntityKillTask(String description, Player player,int goal, MissionScope missionScope, EntityType entityType) {
        super(description, player, goal, missionScope);
        this.entityType = entityType;
    }

    @Override
    public String getLore(){
        return "Tuez des " + this.entityType.name() + ". (" + this.getCounter() + "/"+this.getGoal()+")";
    }
    @Override
    public void processEvent(Event event) {
        if (event instanceof EntityDeathEvent) {
            if (((EntityDeathEvent) event).getEntity().getType() == this.entityType) {
                this.incrementCounterOf(1);
                this.checkAdvancementAndFinish();
            }
        }
    }
}
