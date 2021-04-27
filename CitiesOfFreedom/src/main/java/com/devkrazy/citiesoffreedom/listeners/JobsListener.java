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
            cofPlayer.addMission(new BlockBreakMission("Casser du minerai d'or", player, Material.GOLD_ORE,
                    100, 10, 150, MissionScope.JOB, Material.GOLD_ORE));
            cofPlayer.addMission(new BlockBreakMission("Casser du gravier", player, Material.GRAVEL,
                    100, 10, 200, MissionScope.JOB, Material.GRAVEL));
            cofPlayer.addMission(new BlockBreakMission("Casser du minerai de quartz", player, Material.NETHER_QUARTZ_ORE,
                    100, 10, 300, MissionScope.JOB, Material.NETHER_QUARTZ_ORE));
            cofPlayer.addMission(new BlockBreakMission("Casser des débris antiques", player, Material.ANCIENT_DEBRIS,
                    100, 10, 30, MissionScope.JOB, Material.ANCIENT_DEBRIS));
            cofPlayer.addMission(new BlockBreakMission("Casser de l'obsidienne", player, Material.OBSIDIAN,
                    100, 10, 50, MissionScope.JOB, Material.OBSIDIAN));
        }

        if (job == Job.SOLDIER) {

        }

        player.sendMessage(Component.text(ChatColor.GRAY + "Vous avez choisi le métier " +
                ChatColor.of("#2c596e") + job.getName() + ChatColor.GRAY + "."));
    }
}
