/*
 * Copyright (c) 2021, Nathan DJIAN-MARTIN (DevKrazy).
 * This InteractListener.java file is a part of the Smedalis project.
 * Smedalis cannot be copied and/or distributed without the express permission of Nathan DJIAN-MARTIN (DevKrazy)
 *
 */

package com.devkrazy.citiesoffreedom.listeners;

import com.devkrazy.citiesoffreedom.guis.guis.TeamsSelectionGUI;
import com.devkrazy.citiesoffreedom.guis.guis.TeamsTeleportationGUI;
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
            System.out.println(meta.displayName());
            System.out.println(TeamsSelectionGUI.getInstance().getOpener().getItemMeta().displayName());
            System.out.println(TeamsTeleportationGUI.getInstance().getOpener().getItemMeta().displayName());
             */
            if (meta.displayName().toString().compareTo(TeamsSelectionGUI.getInstance().getOpener().getItemMeta().displayName().toString()) == 0) {
                TeamsSelectionGUI.getInstance().open(player);
                event.setCancelled(true);
            }
            if (meta.displayName().toString().equals(TeamsTeleportationGUI.getInstance().getOpener().getItemMeta().displayName().toString())) {
                TeamsTeleportationGUI.getInstance().open(player);
                event.setCancelled(true);
            }
        }
    }
}
