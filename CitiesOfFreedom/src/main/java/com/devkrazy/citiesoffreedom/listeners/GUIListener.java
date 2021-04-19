/*
 * Copyright (c) 2020.
 *
 * This project (Holobroadcast) and this file is part of Romain Storaï (_Rolyn) and Nathan Djian-Martin (DevKrazy)
 *
 * Holobroadcast cannot be copied and/or distributed without the express permission of Romain Storaï (_Rolyn) and Nathan Djian-Martin (DevKrazy)
 */

package com.devkrazy.citiesoffreedom.listeners;

import com.devkrazy.citiesoffreedom.guis.GUIButton;
import com.devkrazy.citiesoffreedom.guis.GUIMenu;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryType;

import java.util.HashMap;
import java.util.UUID;

public class GUIListener implements Listener {

    public static HashMap<UUID, GUIMenu> menuMap = new HashMap<>();

    @EventHandler
    public void onCloseInventory(InventoryCloseEvent event) {
        UUID id = event.getPlayer().getUniqueId();
        GUIMenu menu = menuMap.get(id);
        if (menu != null)
            menu.onClose((Player) event.getPlayer());
        menuMap.remove(id);
    }

    @EventHandler
    public void onClickInventory(InventoryClickEvent event) {
        UUID id = event.getWhoClicked().getUniqueId();
        GUIMenu menu = menuMap.get(id);
        if (menu != null && !event.getSlotType().equals(InventoryType.SlotType.OUTSIDE)) {
            event.setCancelled(true);
            GUIButton button = menu.getButton(event.getSlot());
            if (button == null) return;
            button.onClick(event);
            button.onClick();
        }
    }
}