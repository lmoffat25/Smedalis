/*
 * Copyright (c) 2021, Nathan DJIAN-MARTIN (DevKrazy).
 * This TeamsConfig.java file is a part of the Smedalis project.
 * Smedalis cannot be copied and/or distributed without the express permission of Nathan DJIAN-MARTIN (DevKrazy)
 *
 */

package com.devkrazy.citiesoffreedom.config.files;

import com.devkrazy.citiesoffreedom.CitiesOfFreedom;
import com.devkrazy.citiesoffreedom.config.CustomConfig;
import com.devkrazy.citiesoffreedom.player.Team;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;

public class TeamsConfig {

    private static TeamsConfig instance = new TeamsConfig();
    private CustomConfig settings;
    private FileConfiguration config;

    private TeamsConfig() {
        this.settings = new CustomConfig(CitiesOfFreedom.getInstance(), "teams.yml", false);
        this.config = this.settings.getConfig();
    }

    /**
     * @return the TeamsConfig's instance
     */
    public static TeamsConfig getInstance() {
        return instance;
    }

    /*
    Config file getters
     */

    /**
     * Gets the spawn of the given team from the teams.yml file.
     * @param team the team of which we want the spawn location
     * @return the spawn location of the given team
     */
    public Location getTeamSpawn(Team team) {
        return this.config.getLocation(team.getName() + ".spawn");
    }

    /*
    Config file setters
     */

    /**
     * Sets the spawn of the given team in the teams.yml file.
     * @param team the team of which we want to set the spawn location
     */
    public void setTeamSpawn(Team team, Location loc) {
        this.config.set(team.getName() + ".spawn", loc);
    }
}
