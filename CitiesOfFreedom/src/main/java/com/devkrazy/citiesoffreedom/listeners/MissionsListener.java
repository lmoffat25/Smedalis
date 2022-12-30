/*
 * Copyright (c) 2021, Nathan DJIAN-MARTIN (DevKrazy).
 * This MissionsListener.java file is a part of the Smedalis project.
 * Smedalis cannot be copied and/or distributed without the express permission of Nathan DJIAN-MARTIN (DevKrazy)
 *
 */

package com.devkrazy.citiesoffreedom.listeners;

import com.devkrazy.citiesoffreedom.player.CoFPlayer;
import com.devkrazy.citiesoffreedom.player.CoFPlayersManager;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.entity.EntitySpawnEvent;


public class MissionsListener implements Listener {

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        Player player = event.getPlayer();
        CoFPlayersManager manager = CoFPlayersManager.getInstance();
        CoFPlayer cofPlayer = manager.getCoFPlayer(player);
        cofPlayer.processEvent(event);
    }

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event) {
        Player player = event.getPlayer();
        CoFPlayersManager manager = CoFPlayersManager.getInstance();
        CoFPlayer cofPlayer = manager.getCoFPlayer(player);
        cofPlayer.processEvent(event);
    }

    @EventHandler
    public void onEntityDeath(EntityDeathEvent event) {
        LivingEntity victim = event.getEntity();
        Entity killer = victim.getKiller();
        if (killer instanceof Player) {
            Player killerPlayer = (Player) killer;
            CoFPlayersManager manager = CoFPlayersManager.getInstance();
            CoFPlayer cofPlayer = manager.getCoFPlayer(killerPlayer);
            cofPlayer.processEvent(event);
        }
    }

    @EventHandler
    public void onPlayerItemConsumeEvent(PlayerItemConsumeEvent event){

        Player player = event.getPlayer();
        CoFPlayersManager manager = CoFPlayersManager.getInstance();
        CoFPlayer cofPlayer = manager.getCoFPlayer(player);
        cofPlayer.processEvent(event);

    }

    @EventHandler
    public void onCraft(CraftItemEvent event) {

        Player player = (Player) event.getWhoClicked();
        CoFPlayer cofPlayer = CoFPlayersManager.getInstance().getCoFPlayer(player);
        cofPlayer.processEvent(event);
    }

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
        Player player = event.getPlayer();
        CoFPlayer cofPlayer = CoFPlayersManager.getInstance().getCoFPlayer(player);
        cofPlayer.processEvent(event);
    }

    @EventHandler
    public void onPlayerInteractEntity(PlayerInteractEntityEvent event) {
        Player player = event.getPlayer();
        CoFPlayer cofPlayer = CoFPlayersManager.getInstance().getCoFPlayer(player);
        Entity entity = event.getRightClicked();
        cofPlayer.processEvent(event);
    }


    @EventHandler
    public void onEntitySpawn(EntitySpawnEvent event) {
        Entity entity = event.getEntity();
        if (entity instanceof Player) {
            Player player = (Player) entity;
            CoFPlayersManager manager = CoFPlayersManager.getInstance();
            CoFPlayer cofPlayer = manager.getCoFPlayer(player);
            cofPlayer.processEvent(event);
        }
    }
}
