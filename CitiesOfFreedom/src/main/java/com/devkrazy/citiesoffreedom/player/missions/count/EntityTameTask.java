package com.devkrazy.citiesoffreedom.player.missions.count;

import com.devkrazy.citiesoffreedom.player.missions.Mission;
import com.devkrazy.citiesoffreedom.player.missions.MissionScope;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.EntityTameEvent;


public class EntityTameTask extends CountTask {

    EntityType mobToTame;
    public EntityTameTask(String description, Player player,int goal, MissionScope missionScope,EntityType mob) {
        super(description, player, goal, missionScope);
        this.mobToTame = mob;
    }

    @Override
    public String getLore(){
        return "Apprivoiser des " + this.mobToTame.name() + ". (" + this.getCounter() + "/"+this.getGoal()+")";
    }
    @Override
    public void processEvent(Event event) {
        if (event instanceof EntityDeathEvent) {
            if (((EntityTameEvent) event).getEntity().getType() == this.mobToTame) {
                this.incrementCounterOf(1);
                this.checkAdvancementAndFinish();
            }
        }
    }
}
