package com.devkrazy.citiesoffreedom.player.missions;

import com.devkrazy.citiesoffreedom.player.missions.MissionScope;
import com.devkrazy.citiesoffreedom.player.missions.MissionRequirement;
import org.bukkit.entity.Player;

public class MissionCompletionRequirement implements MissionRequirement {
    private Mission mission;

    public MissionCompletionRequirement(Mission mission) {
        this.mission = mission;
    }

    @Override
    public boolean isMetBy(Player player) {
        // Check if the mission has been completed by the player
        // You can implement this by checking if the player has the mission in their list of completed missions
        return player.hasCompletedMission(mission);
    }
}


