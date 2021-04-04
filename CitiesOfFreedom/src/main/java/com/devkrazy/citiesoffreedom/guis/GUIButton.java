/*
 * Copyright (c) 2021, Nathan DJIAN-MARTIN (DevKrazy).
 * This CustomConfig.java file is a part of the Smedalis project.
 * Smedalis cannot be copied and/or distributed without the express permission of Nathan DJIAN-MARTIN (DevKrazy)
 *
 */


package com.devkrazy.citiesoffreedom.guis;

import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

public class GUIButton {

    private ItemStack item;

    public GUIButton(ItemStack item) {
        this.item = item;
    }

    /**
     * Set Itemstack
     *
     * @param item
     */
    public void setItem(ItemStack item) {
        this.item = item;
    }

    /**
     * Get the itemStack of the button
     *
     * @return
     */
    ItemStack getItem() {
        return this.item;
    }

    /**
     * Call when the player click the button with the event data
     *
     * @param event
     */
    public void onClick(InventoryClickEvent event) { }

    /**
     * Called when player click the button
     */
    public void onClick() { }
}