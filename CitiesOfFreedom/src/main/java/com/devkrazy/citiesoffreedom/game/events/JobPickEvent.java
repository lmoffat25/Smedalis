/*
 * Copyright (c) 2021, Nathan DJIAN-MARTIN (DevKrazy).
 * This JobPickEvent.java file is a part of the Smedalis project.
 * Smedalis cannot be copied and/or distributed without the express permission of Nathan DJIAN-MARTIN (DevKrazy)
 *
 */

package com.devkrazy.citiesoffreedom.game.events;

import com.devkrazy.citiesoffreedom.player.Job;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public final class JobPickEvent extends Event {

    private static final HandlerList handlers = new HandlerList();
    private Player player;
    private Job job;

    public JobPickEvent(Player player, Job job) {
        this.player = player;
        this.job = job;
    }

    /*
    Getters
     */

    public Player getPlayer() {
        return this.player;
    }

    public Job getJob() {
        return this.job;
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
