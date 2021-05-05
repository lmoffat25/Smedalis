/*
 * Copyright (c) 2021, Nathan DJIAN-MARTIN (DevKrazy).
 * This EntitiesListeners.java file is a part of the Smedalis project.
 * Smedalis cannot be copied and/or distributed without the express permission of Nathan DJIAN-MARTIN (DevKrazy)
 *
 */

package com.devkrazy.citiesoffreedom.listeners;

import com.devkrazy.citiesoffreedom.game.Game;
import com.devkrazy.citiesoffreedom.game.GameState;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class EntitiesListeners implements Listener {

    @EventHandler
    public void onHit(EntityDamageByEntityEvent event) {
        Entity damaged = event.getEntity();
        Entity damager = event.getDamager();
        Game game = Game.getInstance();

        if (game.getState() == GameState.WAITING) {
            event.setCancelled(true);
        }

        if (damaged instanceof Player && damager instanceof Player) {
            // a player damaged an other player
            if (game.isPvpEnabled() == false) {
                event.setCancelled(true);
            }
        }
    }
}
