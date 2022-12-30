/*
 * Copyright (c) 2021, Nathan DJIAN-MARTIN (DevKrazy).
 * This JobsListener.java file is a part of the Smedalis project.
 * Smedalis cannot be copied and/or distributed without the express permission of Nathan DJIAN-MARTIN (DevKrazy)
 *
 */

package com.devkrazy.citiesoffreedom.listeners;

import com.devkrazy.citiesoffreedom.events.JobPickEvent;
import com.devkrazy.citiesoffreedom.game.Game;
import com.devkrazy.citiesoffreedom.game.GameState;
import com.devkrazy.citiesoffreedom.player.CoFPlayer;
import com.devkrazy.citiesoffreedom.player.CoFPlayersManager;
import com.devkrazy.citiesoffreedom.player.Job;
import com.devkrazy.citiesoffreedom.player.missions.MissionScope;
import com.devkrazy.citiesoffreedom.player.missions.count.*;
import com.devkrazy.citiesoffreedom.player.missions.list.*;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
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
            player.sendMessage(ChatColor.RED + "Vous ne pouvez plus changer de métier.");
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
        if (job == Job.FARMER) {
            giveFarmerMissions(cofPlayer, player);
        }
        if (job == Job.FORGEMAGICIAN) {
            giveForgeMagicianMissions(cofPlayer, player);
        }


        player.sendMessage(ChatColor.GRAY + "Vous avez choisi le métier " +
                ChatColor.of("#2c596e") + job.getName() + ChatColor.GRAY + ".");
    }

    /**
     * Gives all the miner missions to a player and its associated cofPlayer.
     * @param cofPlayer the associated cofPlayer
     * @param player the player
     */
    private void giveMinerMissions(CoFPlayer cofPlayer, Player player) {
        cofPlayer.addMission(new BlockBreakMission("Casser de la stone", "Casser le nombre de bloc de stone requis.", player, Material.STONE,
                100, 10, 10000, MissionScope.JOB, Material.STONE));
        cofPlayer.addMission(new BlockBreakMission("Casser du minerai d'émeraude", "Casser le nombre de minerai d'émeraude requis.", player, Material.EMERALD_ORE,
                100, 10, 50, MissionScope.JOB, Material.EMERALD_ORE));
        cofPlayer.addMission(new BlockBreakMission("Casser du minerai de fer", "Casser le nombre de minerai de fer requis.", player, Material.IRON_ORE,
                100, 10, 500, MissionScope.JOB, Material.IRON_ORE));
        cofPlayer.addMission(new BlockBreakMission("Casser du minerai de charbon", "Casser le nombre de minerai de charbon requis.", player, Material.COAL_ORE,
                100, 10, 500, MissionScope.JOB, Material.COAL_ORE));
        cofPlayer.addMission(new BlockBreakMission("Casser du minerai de diamant", "Casser le nombre de minerai de diamant requis.", player, Material.DIAMOND_ORE,
                100, 10, 100, MissionScope.JOB, Material.DIAMOND_ORE));
        cofPlayer.addMission(new BlockBreakMission("Casser du minerai d'or", "Casser le nombre de minerai d'or requis.", player, Material.GOLD_ORE,
                100, 10, 150, MissionScope.JOB, Material.GOLD_ORE));
        cofPlayer.addMission(new BlockBreakMission("Casser du gravier", "Casser le nombre de bloc de gravier requis.", player, Material.GRAVEL,
                100, 10, 200, MissionScope.JOB, Material.GRAVEL));
        cofPlayer.addMission(new BlockBreakMission("Casser du minerai de quartz", "Casser le nombre de minerai de quartz requis.", player, Material.NETHER_QUARTZ_ORE,
                100, 10, 300, MissionScope.JOB, Material.NETHER_QUARTZ_ORE));
        cofPlayer.addMission(new BlockBreakMission("Casser des débris antiques", "Casser le nombre de bloc de débris antiques requis.", player, Material.ANCIENT_DEBRIS,
                100, 10, 30, MissionScope.JOB, Material.ANCIENT_DEBRIS));
        cofPlayer.addMission(new BlockBreakMission("Casser de l'obsidienne", "Casser le nombre de bloc d'obsidienne requis.", player, Material.OBSIDIAN,
                100, 10, 50, MissionScope.JOB, Material.OBSIDIAN));
        cofPlayer.addMission(new ItemBreakMission("Briser une pioche en bois", "", player, Material.WOODEN_PICKAXE,
                100, 1, 1, MissionScope.JOB, Material.WOODEN_PICKAXE));
        cofPlayer.addMission(new ItemBreakMission("Briser une pioche en pierre", "", player, Material.STONE_PICKAXE,
                200, 2, 1, MissionScope.JOB, Material.STONE_PICKAXE));
        cofPlayer.addMission(new ItemBreakMission("Briser une pioche en fer", "", player, Material.IRON_PICKAXE,
                300, 3, 1, MissionScope.JOB, Material.IRON_PICKAXE));
        cofPlayer.addMission(new ItemBreakMission("Briser une pioche en diamant", "", player, Material.DIAMOND_PICKAXE,
                500, 5, 1, MissionScope.JOB, Material.DIAMOND_PICKAXE));
        cofPlayer.addMission(new ItemBreakMission("Briser une pioche en netherite", "", player, Material.NETHERITE_PICKAXE,
                1200, 12, 1, MissionScope.JOB, Material.NETHERITE_PICKAXE));
    }

    /**
     * Gives all the soldier missions to a player and its associated cofPlayer.
     * @param cofPlayer the associated cofPlayer
     * @param player the player
     */
    private void giveSoldierMissions(CoFPlayer cofPlayer, Player player) {

        cofPlayer.addMission(new EntitiesKillMission("Mobocide", "Tuer un mob de chacun des types suivants.", player, Material.DIAMOND_SWORD,
                100, 10, MissionScope.JOB, EntityType.SPIDER, EntityType.SKELETON, EntityType.CREEPER, null));
        cofPlayer.addMission(new BlockPlaceMission("Poser des torches", "Poser le nombre de torche requis.", player, Material.TORCH,
                100, 10, 100, MissionScope.JOB, Material.TORCH));
        cofPlayer.addMission(new EntityKillMission("Respect aux ancêtres", "Tuer le nombre de zombies requis.", player, Material.ZOMBIE_SPAWN_EGG,
                100, 10, 50, MissionScope.JOB, EntityType.ZOMBIE, null));
        cofPlayer.addMission(new EntityKillMission("Boss final des internets", "Tuer un wither.", player, Material.WITHER_SKELETON_SKULL,
                1000, 100, 1, MissionScope.JOB, EntityType.WITHER, null));
        cofPlayer.addMission(new EntityKillMission("Du calcium pour tout le monde", "Tuer le nombre de squelettes requis.", player, Material.SKELETON_SPAWN_EGG,
                100, 10, 50, MissionScope.JOB, EntityType.SKELETON, null));
        cofPlayer.addMission(new EntityKillMission("Une espèce d'un autre monde", "Tuer le nombre de d'endermen requis.", player, Material.ENDERMAN_SPAWN_EGG,
                100, 10, 20, MissionScope.JOB, EntityType.ENDERMAN, null));
        cofPlayer.addMission(new EntitiesTameMission("Défense déléguée", "Apprivoiser un mob de chacun des types suivants.", player, Material.DIAMOND_SWORD,
                100, 10, MissionScope.JOB, EntityType.WOLF, EntityType.POLAR_BEAR, EntityType.FOX, EntityType.CAT));
        cofPlayer.addMission(new ItemConsumeMission("Monstre de puissance", "Consommer une potion de force II.", player,Material.POTION,2,
                100, 1, MissionScope.JOB, PotionType.STRENGTH,2));
        cofPlayer.addMission(new EntityKillMission("Traîtrise contrôlée", "Tuer un golem.", player,Material.IRON_INGOT,2,
                100,1,MissionScope.JOB, EntityType.IRON_GOLEM, null));

    }


    /**
     * Gives all the Famer missions to a player and its associated cofPlayer.
     * @param cofPlayer the associated cofPlayer
     * @param player the player
     */
    private void giveFarmerMissions(CoFPlayer cofPlayer, Player player) {

       cofPlayer.addMission(new CraftItemMission("Fabriquer des épées en diamant", "Fabriquer le nombre d'épées en diamant requis.", player, Material.DIAMOND_SWORD,
                100, 10, 50, MissionScope.JOB, Material.DIAMOND_SWORD));

        cofPlayer.addMission(new MoveNPCMission("Move NPC", "", player, Material.NETHERITE_PICKAXE,
                1200, 12, 1, MissionScope.JOB, 0, 0, 0, "NPC Name", EntityType.VILLAGER));


        Material[] itemsToCraft = {Material.GOLD_BLOCK, Material.DIAMOND_BLOCK, Material.OBSIDIAN};
        cofPlayer.addMission(new CraftItemsMission("Fabriquer des blocs", "Fabriquer les blocs spécifiés.", player, Material.GOLD_BLOCK,
                100, 10, MissionScope.JOB, itemsToCraft));

        cofPlayer.addMission(new PlayerMovedMission("Move around the city", "Move around the city for a certain distance", player, Material.MINECART,
                1000, 10, 100, MissionScope.JOB, 50, 50, 50, 20));

        cofPlayer.addMission(new SpawnMobMission("Spawn the mob", "Spawn the specified mob in the world.", player, Material.COW_SPAWN_EGG,
                100, 10, MissionScope.JOB, EntityType.IRON_GOLEM, EntityType.WITHER, EntityType.SNOWMAN));



    }

    /**
     * Gives all the ForgeMagician missions to a player and its associated cofPlayer.
     * @param cofPlayer the associated cofPlayer
     * @param player the player
     */
    private void giveForgeMagicianMissions(CoFPlayer cofPlayer, Player player) {
       
    }
}
