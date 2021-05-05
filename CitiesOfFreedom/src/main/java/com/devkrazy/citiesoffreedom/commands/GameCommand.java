/*
 * Copyright (c) 2021, Nathan DJIAN-MARTIN (DevKrazy).
 * This GameCommand.java file is a part of the Smedalis project.
 * Smedalis cannot be copied and/or distributed without the express permission of Nathan DJIAN-MARTIN (DevKrazy)
 *
 */

package com.devkrazy.citiesoffreedom.commands;

import com.devkrazy.citiesoffreedom.game.Game;
import com.devkrazy.citiesoffreedom.game.GameState;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class GameCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Game game = Game.getInstance();

        if (args.length == 1) {
            if (args[0].equalsIgnoreCase("start")) {
                if (game.getState() == GameState.WAITING) {
                    game.getGameStartCountdown().start();
                    sender.sendMessage(ChatColor.GREEN + "Vous avez démarré la partie !");
                } else {
                    sender.sendMessage(ChatColor.RED + "Impossible de démarrer la partie maintenant.");
                }
                return true;
            }

            if (args[0].equalsIgnoreCase("state")) {
                sender.sendMessage(ChatColor.GRAY + "État de la partie : " + ChatColor.YELLOW + game.getState().toString());
                return true;
            }
        }
        return false;
    }
}
