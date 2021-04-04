/*
 * Copyright (c) 2021, Nathan DJIAN-MARTIN (DevKrazy).
 * This Job.java file is a part of the Smedalis project.
 * Smedalis cannot be copied and/or distributed without the express permission of Nathan DJIAN-MARTIN (DevKrazy)
 *
 */

package com.devkrazy.citiesoffreedom.game;

import com.devkrazy.citiesoffreedom.game.mission.Mission;
import org.bukkit.Material;

import java.util.ArrayList;
import java.util.List;

/**
 * This enum represents all the available jobs in the game CitiesOfFreedom.
 */

public enum Job {

    MINER("Mineur", Material.STONE_PICKAXE),
    SOLDIER("Soldat", Material.STONE_SWORD);

    private String name;
    private Material guiMaterial;
    private List<Mission> missions;

    Job(String name, Material guiMaterial) {
        this.name = name;
        this.guiMaterial = guiMaterial;
        this.missions = new ArrayList<>();
    }

    /*
    Getters
     */

    public String getName() {
        return this.name;
    }

    public Material getGuiMaterial() {
        return this.guiMaterial;
    }

    public List<Mission> getMissions() {
        return this.missions;
    }
}
