/*
 * Copyright (c) 2021, Nathan DJIAN-MARTIN (DevKrazy).
 * This TeamCommand.java file is a part of the Smedalis project.
 * Smedalis cannot be copied and/or distributed without the express permission of Nathan DJIAN-MARTIN (DevKrazy)
 *
 */

package com.devkrazy.citiesoffreedom.commands;

import com.devkrazy.citiesoffreedom.guis.guis.TeamsGUI;
import com.devkrazy.citiesoffreedom.player.Team;
import com.devkrazy.citiesoffreedom.player.CoFPlayer;
import com.devkrazy.citiesoffreedom.player.CoFPlayersManager;
import net.kyori.adventure.text.Component;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TeamCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender  instanceof Player) {
            Player player = (Player) sender;
            CoFPlayersManager manager = CoFPlayersManager.getInstance();
            CoFPlayer cofPlayer = manager.getCoFPlayer(player);
            if (cofPlayer.hasTeam()) {
                Team team = cofPlayer.getTeam();
                player.sendMessage(Component.text(ChatColor.GRAY + "Vous êtes dans l'équipe " +
                        ChatColor.BOLD + team.getColoredName() + ChatColor.GRAY + "."));
            } else {
                player.sendMessage(Component.text(ChatColor.GRAY + "Vous n'avez pas d'équipe."));
            }
            if (args.length == 1) {
                if (args[0].equalsIgnoreCase("item")) {
                    player.getInventory().addItem(TeamsGUI.getInstance().getOpener());
                    player.sendMessage(Component.text(ChatColor.GRAY + "Vous avez reçu l'item de sélection d'équipe."));
                }
                if (args[0].equalsIgnoreCase("open")) {
                    TeamsGUI.getInstance().open(player);
                    player.sendMessage(Component.text(ChatColor.GRAY + "Choisissez votre équipe."));
                }
            }
            return true;
        } else {
            sender.sendMessage("Vous devez être un joueur pour éxecuter cette commande.");
            return true;
        }
    }
}
