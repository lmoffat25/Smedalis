/*
 * Copyright (c) 2021, Nathan DJIAN-MARTIN (DevKrazy).
 * This ListMission.java file is a part of the Smedalis project.
 * Smedalis cannot be copied and/or distributed without the express permission of Nathan DJIAN-MARTIN (DevKrazy)
 *
 */

package com.devkrazy.citiesoffreedom.game.missions;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;

public class ListMission extends Mission {

    private ArrayList<Object> missionItems;

    public ListMission(String name, Player player, Material guiMaterial, int xpReward, int emeraldsReward, MissionType missionType) {
        super(name, player, guiMaterial, xpReward, emeraldsReward, missionType);
        this.missionItems = new ArrayList<>();
    }


    @Override
    public Mission copy() {
        return null;
    }

    @Override
    public ItemStack getGUIItem() {
        return null;
    }
}
