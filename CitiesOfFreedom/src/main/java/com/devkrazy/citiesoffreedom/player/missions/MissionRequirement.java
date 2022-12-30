package com.devkrazy.citiesoffreedom.player.missions;

import org.bukkit.entity.Player;

public interface MissionRequirement {
    boolean isMetBy(Player player);
}

