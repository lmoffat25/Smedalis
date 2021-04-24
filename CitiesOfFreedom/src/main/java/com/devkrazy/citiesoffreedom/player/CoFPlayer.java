/*
 * Copyright (c) 2021, Nathan DJIAN-MARTIN (DevKrazy).
 * This CoFPlayer.java file is a part of the Smedalis project.
 * Smedalis cannot be copied and/or distributed without the express permission of Nathan DJIAN-MARTIN (DevKrazy)
 *
 */

package com.devkrazy.citiesoffreedom.player;

import com.devkrazy.citiesoffreedom.game.Job;
import com.devkrazy.citiesoffreedom.game.Team;
import com.devkrazy.citiesoffreedom.game.missions.Mission;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.block.Chest;
import org.bukkit.event.Event;

import java.util.ArrayList;
import java.util.List;

public class CoFPlayer {

    private Job job;
    private final List<Mission> missions;
    private Chest chest;
    private Location spawnPoint;
    private Team team;


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
     * Adds a Mission to the CoFPlayer's missions list. If the CoFPlayer already has a mission of the same
     * type, does nothing.
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


    public void processEvent(Event event) {
        for (Mission mission : this.missions) {
            mission.processEvent(event);
        }
    }

/*    *//**
     * @param missionType the mission type
     * @return true if the player has a mission of the given mission type; false otherwise
     *//*
    public boolean hasMission(MissionType missionType) {
        for (Mission mission : this.missions) {
            if (mission.getType() == missionType) return true;
        }
        return false;
    }

    *//**
     * @param missionType the type of the mission
     * @return the Mission of the given type if the CoFPlayer has one of the given type; null otherwise
     *//*
    public Mission getMission(MissionType missionType) {
        for (Mission mission : this.missions) {
            if (mission.getType() == missionType) return mission;
        }
        return null;
    }*/
}
