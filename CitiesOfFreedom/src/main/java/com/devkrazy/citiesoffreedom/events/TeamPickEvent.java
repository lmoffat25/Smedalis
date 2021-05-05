/*
 * Copyright (c) 2021, Nathan DJIAN-MARTIN (DevKrazy).
 * This TeamPickEvent.java file is a part of the Smedalis project.
 * Smedalis cannot be copied and/or distributed without the express permission of Nathan DJIAN-MARTIN (DevKrazy)
 *
 */

package com.devkrazy.citiesoffreedom.events;

import com.devkrazy.citiesoffreedom.player.Team;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class TeamPickEvent extends Event implements Cancellable {

    private static final HandlerList handlers = new HandlerList();
    private Player player;
    private Team team;
    private boolean cancelled;

    public TeamPickEvent(Player player, Team team) {
        this.player = player;
        this.team = team;
    }

    /*
    Getters
     */

    public Player getPlayer() {
        return this.player;
    }

    public Team getTeam() {
        return this.team;
    }

    @Override
    public boolean isCancelled() {
        return this.cancelled;
    }

    /*
    Setters
     */

    @Override
    public void setCancelled(boolean cancelled) {
        this.cancelled = cancelled;
    }


    /*
    Methods
     */

    /**
     * @return the current event's handler list
     */
    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    /**
     * @return the current event's handler list
     */
    public static HandlerList getHandlerList() {
        return handlers;
    }
}
