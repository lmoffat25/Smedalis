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
import com.devkrazy.citiesoffreedom.player.missions.Mission;
import com.devkrazy.citiesoffreedom.player.missions.count.*;
import com.devkrazy.citiesoffreedom.player.CoFPlayer;
import com.devkrazy.citiesoffreedom.player.CoFPlayersManager;
import com.devkrazy.citiesoffreedom.player.missions.MissionScope;
import net.kyori.adventure.text.Component;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Skeleton;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityTameEvent;
import org.bukkit.potion.PotionType;

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
            player.sendMessage(Component.text(ChatColor.RED + "Vous ne pouvez plus changer de métier."));
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
        /*Test mission chrono*/
        cofPlayer.addMission(new Mission("Nathan gaga de ruby",player,Material.STONE,10,10,MissionScope.JOB,true,new BlockBreakTask("Casser de la cobble",player,3,MissionScope.JOB,Material.COBBLESTONE),new BlockBreakTask("Casser du bois",player,3,MissionScope.JOB,Material.SAND),new BlockBreakTask("Casser un bloc de fer",player,3,MissionScope.JOB,Material.IRON_BLOCK)));

        /*Fin test*/
        cofPlayer.addMission(new Mission("Mineur Fou",player,Material.STONE,10,10,MissionScope.JOB,false,new BlockBreakTask("Casser de la stone",player,3,MissionScope.JOB,Material.STONE)));
        cofPlayer.addMission(new Mission("Mineur Fou",player,Material.IRON_ORE,10,10,MissionScope.JOB,false,new BlockBreakTask("Casser du minerai de fer",player,3,MissionScope.JOB,Material.IRON_ORE)));
        cofPlayer.addMission(new Mission("Mineur Fou",player,Material.EMERALD_ORE,10,10,MissionScope.JOB,false,new BlockBreakTask("Casser de des minerai d'émeraude",player,3,MissionScope.JOB,Material.EMERALD_ORE)));
        cofPlayer.addMission(new Mission("Mineur Fou",player,Material.COAL_ORE,10,10,MissionScope.JOB,false,new BlockBreakTask("Casser du minerai de charbon",player,3,MissionScope.JOB,Material.COAL_ORE)));
        cofPlayer.addMission(new Mission("Mineur Fou",player,Material.GOLD_ORE,10,10,MissionScope.JOB,false,new BlockBreakTask("Casser du minerai d'or ",player,3,MissionScope.JOB,Material.GOLD_ORE)));
        cofPlayer.addMission(new Mission("Mineur Fou",player,Material.DIAMOND_ORE,10,10,MissionScope.JOB,false,new BlockBreakTask("Casser du minerai de diamant",player,3,MissionScope.JOB,Material.DIAMOND_ORE)));
        cofPlayer.addMission(new Mission("Mineur Fou",player,Material.GRAVEL,10,10,MissionScope.JOB,false,new BlockBreakTask("Casser du gravier",player,3,MissionScope.JOB,Material.GRAVEL)));
        cofPlayer.addMission(new Mission("Mineur Fou",player,Material.NETHER_QUARTZ_ORE,10,10,MissionScope.JOB,false,new BlockBreakTask("Casser du minerai de quartz",player,3,MissionScope.JOB,Material.NETHER_QUARTZ_ORE)));
        cofPlayer.addMission(new Mission("Mineur Fou",player,Material.ANCIENT_DEBRIS,10,10,MissionScope.JOB,false,new BlockBreakTask("Casser des blocs de débris antiques",player,3,MissionScope.JOB,Material.ANCIENT_DEBRIS)));
        cofPlayer.addMission(new Mission("Mineur Fou",player,Material.OBSIDIAN,10,10,MissionScope.JOB,false,new BlockBreakTask("Casser le nombre de bloc d'obsidienne requis",player,3,MissionScope.JOB,Material.OBSIDIAN)));
        cofPlayer.addMission(new Mission("Mineur Fou",player,Material.WOODEN_PICKAXE,10,10,MissionScope.JOB,false,new ItemBreakTask("Briser une pioche en bois",player,1,MissionScope.JOB,Material.WOODEN_PICKAXE)));
        cofPlayer.addMission(new Mission("Mineur Fou",player,Material.STONE_PICKAXE,10,10,MissionScope.JOB,false,new ItemBreakTask("Briser une pioche en pierre",player,1,MissionScope.JOB,Material.STONE_PICKAXE)));
        cofPlayer.addMission(new Mission("Mineur Fou",player,Material.IRON_PICKAXE,10,10,MissionScope.JOB,false,new ItemBreakTask("Briser une pioche en fer",player,1,MissionScope.JOB,Material.IRON_PICKAXE)));
        cofPlayer.addMission(new Mission("Mineur Fou",player,Material.DIAMOND_PICKAXE,10,10,MissionScope.JOB,false,new ItemBreakTask("Briser une pioche en diamant",player,1,MissionScope.JOB,Material.DIAMOND_PICKAXE)));
        cofPlayer.addMission(new Mission("Mineur Fou",player,Material.WOODEN_PICKAXE,10,10,MissionScope.JOB,false,new ItemBreakTask("Briser une pioche en netherite",player,1,MissionScope.JOB,Material.WOODEN_PICKAXE)));
    }

    /**
     * Gives all the soldier missions to a player and its associated cofPlayer.
     * @param cofPlayer the associated cofPlayer
     * @param player the player
     */

    private void giveSoldierMissions(CoFPlayer cofPlayer, Player player) {
        cofPlayer.addMission(new Mission("Mobicide",player,Material.DIAMOND_SWORD,10,10,MissionScope.JOB,false,new EntityKillTask("Tuez un squelette",player,1,MissionScope.JOB, EntityType.SKELETON),new EntityKillTask("Tuez une arraigné",player,1,MissionScope.JOB, EntityType.SPIDER),new EntityKillTask("Tuez un squelette",player,1,MissionScope.JOB, EntityType.CREEPER),new EntityKillTask("Tuez un zombie",player,1,MissionScope.JOB, EntityType.ZOMBIE),new EntityKillTask("Tuez un enderman",player,1,MissionScope.JOB, EntityType.ENDERMAN)));
        cofPlayer.addMission(new Mission("Mobicide",player,Material.TORCH,10,10,MissionScope.JOB,false,new BlockPlaceTask("Poser des lampes torches",player,1,MissionScope.JOB, Material.TORCH)));
        cofPlayer.addMission(new Mission("Boss final des internets",player,Material.WITHER_SKELETON_SKULL,10,10,MissionScope.JOB,false,new EntityKillTask("Tuez un wither",player,1,MissionScope.JOB, EntityType.WITHER)));
        cofPlayer.addMission(new Mission("Araknophobe",player,Material.SPIDER_SPAWN_EGG,10,10,MissionScope.JOB,false,new EntityKillTask("Tuez le nombre d'arraignées requis",player,10,MissionScope.JOB, EntityType.SPIDER)));
        cofPlayer.addMission(new Mission("Respect aux ancêtres",player,Material.ZOMBIE_SPAWN_EGG,10,10,MissionScope.JOB,false,new EntityKillTask("Tuez le nombre de zombies requis",player,10,MissionScope.JOB, EntityType.ZOMBIE)));
        cofPlayer.addMission(new Mission("Du calcium pour tous",player,Material.SKELETON_SPAWN_EGG,10,10,MissionScope.JOB,false,new EntityKillTask("Tuez le nombre de squelettes requis",player,10,MissionScope.JOB, EntityType.SKELETON)));
        cofPlayer.addMission(new Mission("Une espèce d'un autre monde",player,Material.ENDERMAN_SPAWN_EGG,10,10,MissionScope.JOB,false,new EntityKillTask("Tuez le nombre d'enderman requis",player,10,MissionScope.JOB, EntityType.ENDERMAN)));
        cofPlayer.addMission(new Mission("Respect aux ancêtres",player,Material.ZOMBIE_SPAWN_EGG,10,10,MissionScope.JOB,false,new EntityKillTask("Tuez le nombre de zombies requis",player,10,MissionScope.JOB, EntityType.SPIDER)));
        cofPlayer.addMission(new Mission("Monstre de puissance",player,Material.POTION,10,10,MissionScope.JOB,false,new ItemConsumeTask("Consommez une potion de force II",player,1,MissionScope.JOB, PotionType.STRENGTH,2)));
        cofPlayer.addMission(new Mission("Traitrise contrôlée",player,Material.IRON_INGOT,10,10,MissionScope.JOB,false,new EntityKillTask("Tuez un golem de fer",player,1,MissionScope.JOB, EntityType.IRON_GOLEM)));
        cofPlayer.addMission(new Mission("Défense déléguée",player,Material.SHIELD,10,10,MissionScope.JOB,false,new EntityTameTask("Approvoisez ces animaux",player,1,MissionScope.JOB, EntityType.CAT)));


        /*


        cofPlayer.addMission(new EntitiesTameTask("Défense déléguée", "Apprivoiser un mob de chacun des types suivants.", player, Material.DIAMOND_SWORD,
                100, 10, MissionScope.JOB, EntityType.WOLF, EntityType.POLAR_BEAR, EntityType.FOX, EntityType.CAT));

         */
    }
}
