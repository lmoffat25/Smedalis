/*
 * Copyright (c) 2021, Nathan DJIAN-MARTIN (DevKrazy).
 * This JobsListener.java file is a part of the Smedalis project.
 * Smedalis cannot be copied and/or distributed without the express permission of Nathan DJIAN-MARTIN (DevKrazy)
 *
 */

package com.devkrazy.citiesoffreedom.listeners;

import com.devkrazy.citiesoffreedom.game.events.JobPickEvent;
import com.devkrazy.citiesoffreedom.player.Job;
import com.devkrazy.citiesoffreedom.player.missions.BlockBreakMission;
import com.devkrazy.citiesoffreedom.player.CoFPlayer;
import com.devkrazy.citiesoffreedom.player.CoFPlayersManager;
import com.devkrazy.citiesoffreedom.player.missions.MissionScope;
import net.kyori.adventure.text.Component;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class JobsListener implements Listener {

    @EventHandler
    public void onJobPick(JobPickEvent event) {
        Player player = event.getPlayer();
        CoFPlayersManager manager = CoFPlayersManager.getInstance();
        CoFPlayer cofPlayer = manager.getCoFPlayer(player);
        cofPlayer.removeJobMissions();

        // TODO: check that the player can choose a job (game hasn't started yet)
        // TODO: make JobPickEvent cancellable
        Job job = event.getJob();
        cofPlayer.setJob(job);

        if (job == Job.MINER) {
            cofPlayer.addMission(new BlockBreakMission("Casser de la stone", player, Material.STONE,
                    100, 10, 10000, MissionScope.JOB, Material.STONE));
            cofPlayer.addMission(new BlockBreakMission("Casser du minerai d'émeraude", player, Material.EMERALD_ORE,
                    100, 10, 50, MissionScope.JOB, Material.EMERALD_ORE));
            cofPlayer.addMission(new BlockBreakMission("Casser du minerai de fer", player, Material.IRON_ORE,
                    100, 10, 500, MissionScope.JOB, Material.IRON_ORE));
            cofPlayer.addMission(new BlockBreakMission("Casser du minerai de charbon", player, Material.COAL_ORE,
                    100, 10, 500, MissionScope.JOB, Material.COAL_ORE));
            cofPlayer.addMission(new BlockBreakMission("Casser du minerai de diamant", player, Material.DIAMOND_ORE,
                    100, 10, 100, MissionScope.JOB, Material.DIAMOND_ORE));
            cofPlayer.addMission(new BlockBreakMission("Casser du minerai de diamant", player, Material.DIAMOND_ORE,
                    100, 10, 100, MissionScope.JOB, Material.DIAMOND_ORE));
        }

        if (job == Job.SOLDIER) {

        }

        player.sendMessage(Component.text(ChatColor.GRAY + "Vous avez choisi le métier " +
                ChatColor.of("#2c596e") + job.getName() + ChatColor.GRAY + "."));
    }
}
