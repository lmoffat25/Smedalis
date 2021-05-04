/*
 * Copyright (c) 2021, Nathan DJIAN-MARTIN (DevKrazy).
 * This Countdown.java file is a part of the Smedalis project.
 * Smedalis cannot be copied and/or distributed without the express permission of Nathan DJIAN-MARTIN (DevKrazy)
 *
 */

package com.devkrazy.citiesoffreedom.game;

import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

abstract public class Countdown {

    private int remainingTime;

    private BukkitTask task;
    private Plugin plugin;

    public Countdown(int remainingTime, Plugin plugin) {
        this.remainingTime = remainingTime;
        this.plugin = plugin;
    }

    /**
     * Called every second.
     * @param currentTime the current remaining time
     */
    public abstract void onCount(int currentTime);

    /**
     * Starts the countdown
     */
    public void start() {
        this.task = new BukkitRunnable() {

            @Override
            public void run() {
                onCount(remainingTime);
                if (remainingTime-- <= 0) cancel(); // decrements the time and cancels the task if it reached 0
            }

        }.runTaskTimer(this.plugin, 0L, 20L); // starts the task immediately and
    }

    public void cancel() {
        this.task.cancel();
    }
}
