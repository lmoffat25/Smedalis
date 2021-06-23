/*
 * Copyright (c) 2021, Nathan DJIAN-MARTIN (DevKrazy).
 * This CountMission.java file is a part of the Smedalis project.
 * Smedalis cannot be copied and/or distributed without the express permission of Nathan DJIAN-MARTIN (DevKrazy)
 *
 */

package com.devkrazy.citiesoffreedom.player.missions.count;

import com.devkrazy.citiesoffreedom.player.missions.MissionScope;
import com.devkrazy.citiesoffreedom.player.missions.Task;
import org.bukkit.entity.Player;

abstract public class CountTask extends Task {

    private int goal;
    private int counter;

    public CountTask(String description, Player player, int goal, MissionScope missionScope) {
        super(description, player, missionScope);
        this.goal = goal;
        this.counter = 0;
    }


    /*
    Getters
     */

    protected int getGoal() {
        return this.goal;
    }

    protected int getCounter() {
        return this.counter;
    }


    /*
    Methods
     */

    /**
     * Increments the mission counter by a given value. This function will make sure that the counter
     * remains lower than or equal to the goal. Does nothing if the counter already reached the mission goal.
     * @param count the value to increment the counter with (must be strictly positive)
     */
    protected void incrementCounterOf(int count) {
        if (this.isCompleted() == true) return;
        if (count <= 0) return;
        int increment = this.counter + count < this.goal ? count  : this.goal - this.counter;
        this.counter += increment;
        //this.getPlayer().sendMessage(Component.text(ChatColor.GREEN + "Mission \"" + this.getName() + "\" +" + count));
    }

    /*
    Overridden methods
     */

    /**
     * @return true if the counter reached the goal; false otherwise
     */
    @Override
    public boolean isCompleted() {

         if(this.counter == this.goal){
             return true;
         }
         else{
             return false;
         }
    }

    @Override
    public void checkAdvancementAndFinish(){
        if(isCompleted()){
            this.finish();
        }
    }

    /*
    Abstract method
    */
    abstract public String getLore();
}
