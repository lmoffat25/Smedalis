package com.devkrazy.citiesoffreedom.listeners;

import com.devkrazy.citiesoffreedom.player.CoFPlayer;
import com.devkrazy.citiesoffreedom.player.CoFPlayersManager;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;


public class ChatListener implements Listener {

    @EventHandler
    public void chatFormat(AsyncPlayerChatEvent event) {


        /*extracting the player infos from the event*/

        Player p = event.getPlayer();
        CoFPlayersManager manager = CoFPlayersManager.getInstance();
        CoFPlayer CoFp = manager.getCoFPlayer(p.getUniqueId());


        /*chat formatting */

        //TODO check if it's more effecient to add little by little or define the entire component at once

        if (CoFp.getTeam() == null) {
            if (CoFp.getJob() == null) {
                event.setMessage(ChatColor.GRAY + p.getName() + " : ");
            } else {
                event.setMessage(ChatColor.GRAY + "[" + CoFp.getJob().getName() + "] " + p.getName() + " : ");
            }
        } else {
            ChatColor teamColor = CoFp.getTeam().getColor();
            if (CoFp.getJob() != null) {
                event.setMessage(ChatColor.GRAY + "[" + teamColor + CoFp.getJob().getName() + ChatColor.GRAY + "] " + teamColor + p.getName() + ChatColor.GRAY + " : ");
            } else {
                event.setMessage(teamColor + p.getName() + ChatColor.GRAY + " : ");
            }
        }
    }

}
