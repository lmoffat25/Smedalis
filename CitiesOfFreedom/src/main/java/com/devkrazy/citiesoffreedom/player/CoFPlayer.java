/*
 * Copyright (c) 2021, Nathan DJIAN-MARTIN (DevKrazy).
 * This CoFPlayer.java file is a part of the Smedalis project.
 * Smedalis cannot be copied and/or distributed without the express permission of Nathan DJIAN-MARTIN (DevKrazy)
 *
 */

package com.devkrazy.citiesoffreedom.player;

import com.devkrazy.citiesoffreedom.game.mission.Job;
import com.devkrazy.citiesoffreedom.game.mission.Mission;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.block.Chest;

import java.util.ArrayList;
import java.util.List;

public class CoFPlayer {

    private Job job;
    private List<Mission> missions;
    private Chest chest;
    private Location spawnPoint;
    // team ?


    public CoFPlayer() {
        this.missions = new ArrayList<>();
        this.spawnPoint = Bukkit.getWorlds().get(0).getSpawnLocation();
    }

    /*
    Getters and setters
     */

    public Job getJob() {
        return this.job;
    }

    public void setJob(Job job) {
        this.job = job;
    }

    public List<Mission> getMissions() {
        return this.missions;
    }

    public Chest getChest() {
        return this.chest;
    }

    public void setChest(Chest chest) {
        this.chest = chest;
    }

    public Location getSpawnPoint() {
        return this.spawnPoint;
    }

    public void setSpawnPoint(Location spawnPoint) {
        this.spawnPoint = spawnPoint;
    }


    /*
    Methods
     */

    /**
     * @return true if the CoFPlayer has a Job; false otherwise
     */
    public boolean hasJob() {
        return this.job != null;
    }

    /**
     * Adds a Mission to the CoFPlayer's missions list.
     * @param mission the mission to add
     */
    public void addMission(Mission mission) {
        this.missions.add(mission);
    }

    /**
     * Removes a Mission from the CoFPlayer's missions list.
     * @param mission the mission to remove
     */
    public void removeMission(Mission mission) {
        this.missions.remove(mission);
    }

    /**
     * @return true if the CoFPlayer has a personal chest; false otherwise
     */
    public boolean hasChest() {
        return this.chest != null;
    }
}
