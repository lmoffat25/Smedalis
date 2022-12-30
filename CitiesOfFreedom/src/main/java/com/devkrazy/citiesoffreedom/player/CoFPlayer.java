/*
 * Copyright (c) 2021, Nathan DJIAN-MARTIN (DevKrazy).
 * This CoFPlayer.java file is a part of the Smedalis project.
 * Smedalis cannot be copied and/or distributed without the express permission of Nathan DJIAN-MARTIN (DevKrazy)
 *
 */

package com.devkrazy.citiesoffreedom.player;

import com.devkrazy.citiesoffreedom.player.missions.Mission;
import com.devkrazy.citiesoffreedom.player.missions.MissionScope;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.block.Chest;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import java.io.FileNotFoundException;


import java.util.ArrayList;
import java.util.List;

public class CoFPlayer {

    private Job job;
    private List<Mission> missions;
    private Chest chest;
    private Location spawnPoint;
    private Team team;

    /**
     * Saves this CoFPlayer's data to a JSON file.
     * @param filePath the path of the file to save to
     */
    public void saveToFile(String filePath) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(this);
        try {
            FileWriter writer = new FileWriter(filePath);
            writer.write(json);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



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

    public Team getTeam() {
        return this.team;
    }

    public void setTeam(Team team) {
        this.team = team;
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
     * Adds a Mission to the CoFPlayer's personal missions list.
     * @param mission the mission to add
     */
    public void addMission(Mission mission) {
        this.missions.add(mission);
    }

    /**
     * Removes all the job missions of the current cof player's missions list.
     */
    public void removeJobMissions() {
        this.missions.removeIf(mission -> mission.getScope() == MissionScope.JOB);
    }

    /**
     * @return true if the CoFPlayer has a personal chest; false otherwise
     */
    public boolean hasChest() {
        return this.chest != null;
    }

    /**
     * Removes the current CoFPlayer's team.
     */
    public void removeTeam() {
        this.team = null;
    }

    /**
     * @return true if the CoFPlayer has a team; false otherwise
     */
    public boolean hasTeam() {
        return this.team != null;
    }

    /**
     * Processes a given event for each of the player's missions (personal and job missions).
     * @param event the event to process
     */
    public void processEvent(Event event) {
        for (Mission mission : this.missions) {
            mission.processEvent(event);
        }
    }
}
