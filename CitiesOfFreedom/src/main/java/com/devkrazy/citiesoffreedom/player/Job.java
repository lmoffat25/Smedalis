/*
 * Copyright (c) 2021, Nathan DJIAN-MARTIN (DevKrazy).
 * This Job.java file is a part of the Smedalis project.
 * Smedalis cannot be copied and/or distributed without the express permission of Nathan DJIAN-MARTIN (DevKrazy)
 *
 */

package com.devkrazy.citiesoffreedom.player;

import com.devkrazy.citiesoffreedom.utils.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;


/**
 * This enum represents all the available jobs in the game CitiesOfFreedom.
 */

public enum Job {

    MINER("Mineur", Material.STONE_PICKAXE),
    SOLDIER("Soldat", Material.STONE_SWORD),
    FARMER("Paysan", Material.STONE_HOE),
    FORGEMAGICIAN("ForgeMage", Material.STONE_HOE);

    private String name;
    private Material guiMaterial;

    Job(String name, Material guiMaterial) {
        this.name = name;
        this.guiMaterial = guiMaterial;
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


    /*
    Methods
     */

    /**
     * @return an itemstack representing the job to display in a GUI
     */
    public ItemStack getGuiItem() {
        return new ItemBuilder(this.guiMaterial, this.name).build();
    }

}