package com.devkrazy.citiesoffreedom.player.missions.count;


import com.devkrazy.citiesoffreedom.player.missions.MissionScope;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.potion.PotionData;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.potion.PotionType;

public class ItemConsumeMission extends CountMission {

    private PotionEffectType potionType;
    private int level;

    public ItemConsumeMission(String name, String description, Player player, Material guiMaterial, int xpReward, int emeraldsReward, int goal, MissionScope missionScope, PotionEffectType potionType,int level) {
        super(name, description, player, guiMaterial, xpReward, emeraldsReward, goal, missionScope);
        this.potionType = potionType;
        this.level = level;
    }

    /**
     * Processes a given event and updates the mission status if necessary.
     * @param event the event to process
     */
    @Override
    public void processEvent(Event event) {
        if (event instanceof PlayerItemConsumeEvent) {
            PotionMeta meta = (PotionMeta) ((PlayerItemConsumeEvent) event).getItem().getItemMeta();
            if(meta.getCustomEffects().size() == 1){

                PotionEffectType potionEffect= meta.getCustomEffects().get(0).getType();
                int amplifier = meta.getCustomEffects().get(0).getAmplifier();

                System.out.println(amplifier);

                if(potionEffect == this.potionType && amplifier == this.level){
                    this.incrementCounterOf(1);
                    this.checkAdvancementAndReward();
                }
            }
        }
    }

}
