/*
 * Copyright (c) 2021, Nathan DJIAN-MARTIN (DevKrazy).
 * This JobsListener.java file is a part of the Smedalis project.
 * Smedalis cannot be copied and/or distributed without the express permission of Nathan DJIAN-MARTIN (DevKrazy)
 *
 */

package com.devkrazy.citiesoffreedom.listeners;

import com.devkrazy.citiesoffreedom.game.Game;
import com.devkrazy.citiesoffreedom.game.GameState;
import com.devkrazy.citiesoffreedom.events.JobPickEvent;
import com.devkrazy.citiesoffreedom.player.Job;
import com.devkrazy.citiesoffreedom.player.missions.count.BlockBreakMission;
import com.devkrazy.citiesoffreedom.player.CoFPlayer;
import com.devkrazy.citiesoffreedom.player.CoFPlayersManager;
import com.devkrazy.citiesoffreedom.player.missions.MissionScope;
import com.devkrazy.citiesoffreedom.player.missions.count.BlockPlaceMission;
import com.devkrazy.citiesoffreedom.player.missions.count.EntityKillMission;
import com.devkrazy.citiesoffreedom.player.missions.list.EntitiesKillMission;
import net.kyori.adventure.text.Component;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class JobsListener implements Listener {

    @EventHandler
    public void onJobPick(JobPickEvent event) {
        Player player = event.getPlayer();
        Game game = Game.getInstance();
        CoFPlayersManager manager = CoFPlayersManager.getInstance();
        CoFPlayer cofPlayer = manager.getCoFPlayer(player);
        cofPlayer.removeJobMissions();

        if (game.getState() != GameState.WAITING) {
            // prevents the player to change job if the game has started
            event.setCancelled(true);
            player.sendMessage(Component.text(ChatColor.RED + "La partie a déjà commencé vous ne pouvez plus changer."));
            // TODO: set a default job
            return;
        }

        Job job = event.getJob();
        cofPlayer.setJob(job);

        if (job == Job.MINER) {
            giveMinerMissions(cofPlayer, player);
        }
        if (job == Job.SOLDIER) {
            giveSoldierMissions(cofPlayer, player);
        }

        player.sendMessage(Component.text(ChatColor.GRAY + "Vous avez choisi le métier " +
                ChatColor.of("#2c596e") + job.getName() + ChatColor.GRAY + "."));
    }

    /**
     * Gives all the miner missions to a player and its associated cofPlayer.
     * @param cofPlayer the associated cofPlayer
     * @param player the player
     */
    private void giveMinerMissions(CoFPlayer cofPlayer, Player player) {
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

    /**
     * Gives all the soldier missions to a player and its associated cofPlayer.
     * @param cofPlayer the associated cofPlayer
     * @param player the player
     */
    private void giveSoldierMissions(CoFPlayer cofPlayer, Player player) {
        cofPlayer.addMission(new EntitiesKillMission("Mobocide", player, Material.DIAMOND_SWORD,
                100, 10, MissionScope.JOB, EntityType.SPIDER, EntityType.SKELETON, EntityType.CREEPER));
        cofPlayer.addMission(new BlockPlaceMission("Poser des torches", player, Material.TORCH,
                100, 10, 100, MissionScope.JOB, Material.TORCH));
        cofPlayer.addMission(new EntityKillMission("Respect aux ancêtres", player, Material.ZOMBIE_SPAWN_EGG,
                100, 10, 50, MissionScope.JOB, EntityType.ZOMBIE));
        cofPlayer.addMission(new EntityKillMission("Araknophobe", player, Material.SPIDER_SPAWN_EGG,
                100, 10, 50, MissionScope.JOB, EntityType.SPIDER));
        cofPlayer.addMission(new EntityKillMission("Du calcium pour tout le monde", player, Material.SKELETON_SPAWN_EGG,
                100, 10, 50, MissionScope.JOB, EntityType.SKELETON));
        cofPlayer.addMission(new EntityKillMission("Une espèce d'un autre monde", player, Material.ENDERMAN_SPAWN_EGG,
                100, 10, 20, MissionScope.JOB, EntityType.ENDERMAN));
    }
}
