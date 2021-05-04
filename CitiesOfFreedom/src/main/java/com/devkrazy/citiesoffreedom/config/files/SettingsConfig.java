/*
 * Copyright (c) 2021, Nathan DJIAN-MARTIN (DevKrazy).
 * This SettingsConfig.java file is a part of the Smedalis project.
 * Smedalis cannot be copied and/or distributed without the express permission of Nathan DJIAN-MARTIN (DevKrazy)
 *
 */

package com.devkrazy.citiesoffreedom.config.files;

import com.devkrazy.citiesoffreedom.CitiesOfFreedom;
import com.devkrazy.citiesoffreedom.config.CustomConfig;
import org.bukkit.configuration.file.FileConfiguration;

/**
 * This class gives access to all of the settings.yml config file.
 */
public class SettingsConfig {

    private static SettingsConfig instance = new SettingsConfig();
    private CustomConfig settings;
    private FileConfiguration config;

    private SettingsConfig() {
        this.settings = new CustomConfig(CitiesOfFreedom.getInstance(), "settings.yml", true);
        this.config = this.settings.getConfig();
    }

    /**
     * @return the SettingsConfig's instance
     */
    public static SettingsConfig getInstance() {
        return instance;
    }
}
