/*
 * Copyright (c) 2021, Nathan DJIAN-MARTIN (DevKrazy).
 * This JobPickEvent.java file is a part of the Smedalis project.
 * Smedalis cannot be copied and/or distributed without the express permission of Nathan DJIAN-MARTIN (DevKrazy)
 *
 */

package com.devkrazy.citiesoffreedom.events;

import com.devkrazy.citiesoffreedom.player.Job;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

/**
 * A custom event called when a player picks a job.
 */

public final class JobPickEvent extends Event implements Cancellable {

    private static final HandlerList handlers = new HandlerList();
    private Player player;
    private Job job;
    private boolean cancelled;

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
