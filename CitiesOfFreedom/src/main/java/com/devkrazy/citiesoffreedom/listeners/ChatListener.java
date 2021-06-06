package com.devkrazy.citiesoffreedom.listeners;

import com.devkrazy.citiesoffreedom.player.CoFPlayer;
import com.devkrazy.citiesoffreedom.player.CoFPlayersManager;
import io.papermc.paper.chat.ChatRenderer;
import io.papermc.paper.event.player.AsyncChatEvent;
import net.kyori.adventure.text.Component;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;


public class ChatListener implements Listener {

    @EventHandler
    public void chatFormat(AsyncChatEvent event){


        /*extracting the player infos from the event*/

        Player p = event.getPlayer();
        CoFPlayersManager manager = CoFPlayersManager.getInstance();
        CoFPlayer CoFp = manager.getCoFPlayer(p.getUniqueId());


        /*chat formatting */

        //TODO check if it's more effecient to add little by little or define the entire component at once

        ChatRenderer newChatRender = (player, component, component1, audience) -> {

            Component renderedChat;

            if(CoFp.getTeam() == null){

                if(CoFp.getJob() == null) {
                    renderedChat = Component.text(ChatColor.GRAY + p.getName() + " : ");
                }
                else{
                    renderedChat = Component.text(ChatColor.GRAY + "[" + CoFp.getJob().getName() +"] "+ p.getName() + " : ");
                }

            }
            else {
                ChatColor teamColor = CoFp.getTeam().getColor();
                if(CoFp.getJob()!=null){
                    renderedChat = Component.text(ChatColor.GRAY +"[" + teamColor +  CoFp.getJob().getName()+ ChatColor.GRAY + "] " + teamColor + p.getName() + ChatColor.GRAY +" : ");
                }
                else{
                    renderedChat = Component.text(teamColor + p.getName() + ChatColor.GRAY +" : ");
                }

            }
            renderedChat = renderedChat.append(event.originalMessage());
            return renderedChat;
        };

        event.renderer(newChatRender);
    }

}
