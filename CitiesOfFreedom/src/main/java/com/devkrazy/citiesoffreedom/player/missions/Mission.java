package com.devkrazy.citiesoffreedom.player.missions;

import com.devkrazy.citiesoffreedom.player.missions.count.CountTask;
import com.devkrazy.citiesoffreedom.utils.ItemBuilder;
import net.kyori.adventure.text.Component;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;
import java.util.List;

public class Mission{


    private String name;
    private List<Task> taskList;
    private Player player;
    private ItemStack guiItemStack;
    private int xpReward;
    private int emeraldsReward;
    private MissionScope missionScope;
    boolean isChronological;


    public Mission(String name, Player player, Material guiMaterial, int xpReward, int emeraldsReward, MissionScope missionScope, boolean isChronological, Task... t){

        this.name = name;
        this.player = player;
        this.guiItemStack = new ItemBuilder(guiMaterial, 1).setName(ChatColor.WHITE + this.name).build();
        this.xpReward = xpReward;
        this.emeraldsReward = emeraldsReward;
        this.missionScope = missionScope;
        this.isChronological = isChronological;
        this.taskList = Arrays.asList(t);
    }

    /*
    Getters
     */

    public String getName() {
        return this.name;
    }

    public List<Task> getTaskList() {
        return this.taskList;
    }

    protected ItemStack getGUIItem() {
        return this.guiItemStack;
    }

    public Player getPlayer() {
        return this.player;
    }

    public int getXpReward() {
        return this.xpReward;
    }

    public int getEmeraldsReward() {
        return this.emeraldsReward;
    }

    public MissionScope getScope() {
        return this.missionScope;
    }

    /**
     * Gives the owner's mission the experience and emerald reward if the mission is completed; does nothing otherwise.
     * Marks the mission as finished.
     */
    private void finishAndReward() {

        this.guiItemStack = new ItemBuilder(this.guiItemStack).addGlow().build();
        ItemBuilder builder = new ItemBuilder(Material.EMERALD, this.emeraldsReward);
        this.player.getInventory().addItem(builder.build());
        // TODO: check if player's inventory is not full
        this.player.giveExp(this.xpReward);
        this.player.sendMessage(Component.text("" + ChatColor.GREEN + ChatColor.BOLD + "Vous avez réussi la mission " + this.name));

    }

    /**
     * Checks the mission advancement and rewards the player if the mission is completed.
     */

    protected void checkAdvancementAndReward() {
        if (this.isCompleted()) {
            this.finishAndReward();
        }
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
    }

    public boolean isCompleted(){
        for(Task t : this.getTaskList()){
            if(!t.isFinished()){
                return false;
            }
        }
        return true;
    }

    public void processEvent(Event event){

        if(!this.isCompleted()) {

            /*process the event*/
            if (this.isChronological) {
                /*Execute the process event of the first task not completed*/
                this.getFirstTaskNotCompleted().processEvent(event);
            } else {
                /*Execute all the processEvent*/
                for (Task t : this.getTaskList()) {
                    t.processEvent(event);
                }
            }
            /*check if all task are completed*/
            this.checkAdvancementAndReward();
        }
    }
    //TODO Change the gui representation

    public ItemStack buildGUIItem() {

        String description = "";

        for (Task t : this.getTaskList()){
            if(t instanceof CountTask){
                description = description + ((CountTask) t).getLore();
            }
            else{
                description = description + t.getDescription();
            }

        }
        return new ItemBuilder(this.getGUIItem()).setLore("" + ChatColor.GRAY+description).build();
    }


}
