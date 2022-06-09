/*
 * Copyright (c) 2021, Nathan DJIAN-MARTIN (DevKrazy).
 * This TeamCommand.java file is a part of the Smedalis project.
 * Smedalis cannot be copied and/or distributed without the express permission of Nathan DJIAN-MARTIN (DevKrazy)
 *
 */

package com.devkrazy.citiesoffreedom.commands;

import com.devkrazy.citiesoffreedom.guis.guis.TeamsSelectionGUI;
import com.devkrazy.citiesoffreedom.guis.guis.TeamsTeleportationGUI;
import com.devkrazy.citiesoffreedom.guis.guis.TeamsVotingGUI;
import com.devkrazy.citiesoffreedom.player.CoFPlayer;
import com.devkrazy.citiesoffreedom.player.CoFPlayersManager;
import com.devkrazy.citiesoffreedom.player.Team;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TeamCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            CoFPlayersManager manager = CoFPlayersManager.getInstance();
            CoFPlayer cofPlayer = manager.getCoFPlayer(player);
            if (cofPlayer.hasTeam()) {
                Team team = cofPlayer.getTeam();
                player.sendMessage(ChatColor.GRAY + "Vous êtes dans l'équipe " +
                        ChatColor.BOLD + team.getColoredName() + ChatColor.GRAY + ".");
            } else {
                player.sendMessage(ChatColor.GRAY + "Vous n'avez pas d'équipe.");
            }
            if (args[0].equalsIgnoreCase("item")) {
                if (args.length == 2) {
                    if (args[1].equalsIgnoreCase("select")) {
                        player.getInventory().addItem(TeamsSelectionGUI.getInstance().getOpener());
                        player.sendMessage(ChatColor.GRAY + "Vous avez reçu l'item de sélection d'équipe.");
                    }
                    if (args[1].equalsIgnoreCase("tp")) {
                        player.getInventory().addItem(TeamsTeleportationGUI.getInstance().getOpener());

                        player.sendMessage(ChatColor.GRAY + "Vous avez reçu l'item de téléportation d'équipe.");
                    }
                    if (args[1].equalsIgnoreCase("vote")) {
                        player.getInventory().addItem(TeamsVotingGUI.getInstance().getOpener());

                        player.sendMessage(ChatColor.GRAY + "Vous avez reçu l'item de vote des équipes.");
                    }
                }
            } else {
                return false;
            }
            if (args[0].equalsIgnoreCase("open")) {
                TeamsSelectionGUI.getInstance().open(player);
                player.sendMessage(ChatColor.GRAY + "Choisissez votre équipe.");
            }
        } else {
            sender.sendMessage("Vous devez être un joueur pour éxecuter cette commande.");
        }
        return true;
    }
}
