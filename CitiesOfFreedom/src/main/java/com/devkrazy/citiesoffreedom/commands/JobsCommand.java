/*
 * Copyright (c) 2021, Nathan DJIAN-MARTIN (DevKrazy).
 * This MissionsCommand.java file is a part of the Smedalis project.
 * Smedalis cannot be copied and/or distributed without the express permission of Nathan DJIAN-MARTIN (DevKrazy)
 *
 */

package com.devkrazy.citiesoffreedom.commands;

import com.devkrazy.citiesoffreedom.guis.guis.JobsGUI;
import com.devkrazy.citiesoffreedom.guis.guis.MissionsGUI;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class JobsCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender  instanceof Player) {
            Player player = (Player) sender;
            JobsGUI.getInstance().open(player);
        }
        return true;
    }
}
