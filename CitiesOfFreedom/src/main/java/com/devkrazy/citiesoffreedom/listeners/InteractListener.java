/*
 * Copyright (c) 2021, Nathan DJIAN-MARTIN (DevKrazy).
 * This InteractListener.java file is a part of the Smedalis project.
 * Smedalis cannot be copied and/or distributed without the express permission of Nathan DJIAN-MARTIN (DevKrazy)
 *
 */

package com.devkrazy.citiesoffreedom.listeners;

import com.devkrazy.citiesoffreedom.guis.guis.TeamSelectionGUI;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

public class InteractListener implements Listener {

    @EventHandler
    public void onInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        if (event.getItem() != null) {
            if (event.getItem().equals(TeamSelectionGUI.getInstance().getOpener())) {
                TeamSelectionGUI.getInstance().open(player);
            }
        }
    }
}
