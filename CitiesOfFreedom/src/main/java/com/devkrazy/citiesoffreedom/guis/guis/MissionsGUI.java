/*
 * Copyright (c) 2021, Nathan DJIAN-MARTIN (DevKrazy).
 * This MissionsGUI.java file is a part of the Smedalis project.
 * Smedalis cannot be copied and/or distributed without the express permission of Nathan DJIAN-MARTIN (DevKrazy)
 *
 */

package com.devkrazy.citiesoffreedom.guis.guis;

import com.devkrazy.citiesoffreedom.guis.GUIButton;
import com.devkrazy.citiesoffreedom.guis.GUIMenu;
import com.devkrazy.citiesoffreedom.player.CoFPlayer;
import com.devkrazy.citiesoffreedom.player.CoFPlayersManager;
import com.devkrazy.citiesoffreedom.player.missions.Mission;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.entity.Player;

public class MissionsGUI {

    private static MissionsGUI instance = new MissionsGUI();

    /*
    Constructor
    */
    private MissionsGUI(){

    }

    /*
    Getters
     */

    /**
     * @return the TeamsGUI's instance
     */
    public static MissionsGUI getInstance() {
        return instance;
    }


     /*
    Methods
     */

    /**
     * Opens the team selection GUI to the given player.
     * @param player the player
     */
    public void open(Player player) {
        GUIMenu menu = new GUIMenu(ChatColor.of("#33914c") + "Vos missions", 3);
        CoFPlayersManager manager = CoFPlayersManager.getInstance();
        CoFPlayer cofPlayer = manager.getCoFPlayer(player);

        // displays the player's personal missions
        for (Mission mission : cofPlayer.getMissions()) {
            menu.addButton(new GUIButton(mission.buildGUIItem()));
        }

        menu.pack();
        menu.open(player);
    }
}
