package com.devkrazy.citiesoffreedom.player.missions.count;


import com.devkrazy.citiesoffreedom.player.missions.MissionScope;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.potion.PotionData;
import org.bukkit.potion.PotionType;

public class ItemConsumeTask extends CountTask {

    private PotionType potionType;
    private int level;

    public ItemConsumeTask(String description, Player player, int goal, MissionScope missionScope, PotionType potionType, int level) {
        super(description, player, goal, missionScope);
        this.potionType = potionType;
        this.level = level;
    }
    @Override
    public String getLore(){
        return "Consommez de la " + this.potionType.name() + ". (" + this.getCounter() + "/"+this.getGoal()+")";
    }
    /**
     * Processes a given event and updates the mission status if necessary.
     * @param event the event to process
     */
    @Override
    public void processEvent(Event event) {
        if (event instanceof PlayerItemConsumeEvent) {
            if(((PlayerItemConsumeEvent) event).getItem().getItemMeta() instanceof PotionMeta){

                PotionMeta meta = (PotionMeta) ((PlayerItemConsumeEvent) event).getItem().getItemMeta();
                PotionData data = meta.getBasePotionData();
                if(data.getType()== this.potionType){

                    if(this.level>1 && data.isUpgraded()){

                        this.incrementCounterOf(1);
                        this.checkAdvancementAndFinish();
                    }
                    else{
                        if(level<1){
                            this.incrementCounterOf(1);
                            this.checkAdvancementAndFinish();
                        }
                    }
                }
            }
        }
    }

}
