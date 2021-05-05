/*
 * Copyright (c) 2021, Nathan DJIAN-MARTIN (DevKrazy).
 * This Countdown.java file is a part of the Smedalis project.
 * Smedalis cannot be copied and/or distributed without the express permission of Nathan DJIAN-MARTIN (DevKrazy)
 *
 */

package com.devkrazy.citiesoffreedom.game;

import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitScheduler;
import org.bukkit.scheduler.BukkitTask;

/**
 * This class is used to create countdowns.
 * You just need to override the onCount method which is called every second.
 */
abstract public class Countdown {

    private final int INITIAL_TIME;
    private int remainingTime;

    private BukkitTask task;
    private Plugin plugin;

    public Countdown(int initialTime, Plugin plugin) {
        this.INITIAL_TIME = initialTime;
        this.remainingTime = initialTime;
        this.plugin = plugin;
    }

    /**
     * Called every second excepted at 0. To do something when the counter reaches 0 override the {@link #onEnd()} method.
     * @param currentTime the current remaining time
     */
    protected void onCount(int currentTime) {
        // TODO: add a boolean to display time
        this.displayTime();
    }

    /**
     * Called when the counter ends.
     */
    protected abstract void onEnd();

    /**
     * @return true if the countdown has started; false otherwise
     */
    public boolean hasStarted() {
        BukkitScheduler scheduler = Bukkit.getScheduler();
        if (this.task == null) {
            return false;
        } else if (scheduler.isQueued(this.task.getTaskId()) == false && scheduler.isCurrentlyRunning(this.task.getTaskId()) == false) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * Starts the countdown. Does nothing if the countdown has already started.
     */
    public void start() {

        Bukkit.getServer().sendMessage(Component.text("START"));


        if (hasStarted()) {
            Bukkit.getServer().sendMessage(Component.text(ChatColor.GOLD + "Did not start the countdown, it was already running or queued."));
            return;
        }

        this.task = new BukkitRunnable() {

            @Override
            public void run() {
                if (remainingTime <= 0) { // cancels the task if it reached 0
                    onEnd();
                    reset();
                } else {
                    onCount(remainingTime);
                }
                remainingTime--;
            }

        }.runTaskTimer(this.plugin, 0L, 20L); // starts the task immediately and repeats it every second
    }

    /**
     * Displays the remaining time of the countdown.
     */
    private void displayTime() {
        // TODO: check all cases
        if (this.remainingTime != 0) {
            Bukkit.getServer().sendMessage(Component.text(ChatColor.RED + "Fin du countdown dans " + this.remainingTime + "."));
        }
    }

    /**
     * Cancels the countdown and resets the remaining time to the initial time value.
     * Does nothing if the countdown hasn't started yet.
     */
    public void reset() {
        if (hasStarted() == true) {
            this.remainingTime = this.INITIAL_TIME;
            this.task.cancel();
        }
    }
}
