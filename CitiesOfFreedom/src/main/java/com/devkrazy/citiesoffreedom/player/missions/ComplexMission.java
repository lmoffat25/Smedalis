package com.devkrazy.citiesoffreedom.player.missions;

import com.devkrazy.citiesoffreedom.utils.ItemBuilder;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.inventory.ItemStack;

public class ComplexMission extends Mission{

    public ComplexMission(String name, Player player, Material guiMaterial, int xpReward, int emeraldsReward, MissionScope missionScope, boolean isChronological, Task... t){
        super(name,player,guiMaterial,xpReward,emeraldsReward,missionScope,isChronological,t);
    }

    public Task getFirstTaskNotCompleted(){

        boolean found = false;
        int c = 0;

        while(!found){

            if(!getTaskList().get(c).isFinished()){
                found = true;
            }
            else{
                c++;
            }
        }
        return getTaskList().get(c);
    };

    @Override
    public boolean isCompleted(){
        for(Task t : this.getTaskList()){
            if(!t.isFinished()){
                return false;
            }
        }
        return true;
    }

    @Override
    public void processEvent(Event event){

        if(this.isChronological){
            /*Execute the process event of the first task not completed*/
            this.getFirstTaskNotCompleted().processEvent(event);
        }
        else{
            /*Execute all the processEvent*/
            for(Task t : this.getTaskList()){
                t.processEvent(event);
            }
        }
    }
    //TODO Change the gui representation
    @Override
    public ItemStack buildGUIItem() {
        String Lore;
        return new ItemBuilder(this.getGUIItem()).setLore("" + ChatColor.GRAY+this.getName()).build();
    }


}
