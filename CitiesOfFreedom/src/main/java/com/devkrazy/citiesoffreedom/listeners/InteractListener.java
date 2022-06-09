/*
 * Copyright (c) 2021, Nathan DJIAN-MARTIN (DevKrazy).
 * This InteractListener.java file is a part of the Smedalis project.
 * Smedalis cannot be copied and/or distributed without the express permission of Nathan DJIAN-MARTIN (DevKrazy)
 *
 */

package com.devkrazy.citiesoffreedom.listeners;

import com.devkrazy.citiesoffreedom.guis.guis.TeamsSelectionGUI;
import com.devkrazy.citiesoffreedom.guis.guis.TeamsTeleportationGUI;
import com.devkrazy.citiesoffreedom.guis.guis.TeamsVotingGUI;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class InteractListener implements Listener {

    @EventHandler
    public void onInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        ItemStack item = event.getItem();
        if (item != null) {
            ItemMeta meta = item.getItemMeta();
            /*
            System.out.println(meta.getDisplayName());
            System.out.println(TeamsSelectionGUI.getInstance().getOpener().getItemMeta().getDisplayName());
            System.out.println(TeamsTeleportationGUI.getInstance().getOpener().getItemMeta().getDisplayName());
             */

            if (meta != null) {
                if (meta.getDisplayName().toString().compareTo(TeamsSelectionGUI.getInstance().getOpener().getItemMeta().getDisplayName().toString()) == 0) {
                    TeamsSelectionGUI.getInstance().open(player);
                    event.setCancelled(true);
                }
                if (meta.getDisplayName().toString().equals(TeamsTeleportationGUI.getInstance().getOpener().getItemMeta().getDisplayName().toString())) {
                    TeamsTeleportationGUI.getInstance().open(player);
                    event.setCancelled(true);
                }
                if (meta.getDisplayName().toString().equals(TeamsVotingGUI.getInstance().getOpener().getItemMeta().getDisplayName().toString())) {
                    TeamsVotingGUI.getInstance().open(player);
                    event.setCancelled(true);
                }
            }
        }
    }
}
