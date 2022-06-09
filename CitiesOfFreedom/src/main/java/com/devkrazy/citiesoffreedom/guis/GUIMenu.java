/*
 * Copyright (c) 2021, Nathan DJIAN-MARTIN (DevKrazy).
 * This CustomConfig.java file is a part of the Smedalis project.
 * Smedalis cannot be copied and/or distributed without the express permission of Nathan DJIAN-MARTIN (DevKrazy)
 *
 */

package com.devkrazy.citiesoffreedom.guis;

import com.devkrazy.citiesoffreedom.CitiesOfFreedom;
import com.devkrazy.citiesoffreedom.listeners.GUIListener;
import org.bukkit.Bukkit;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class GUIMenu {

    private String name;
    private final int rows;
    private Inventory inventory;
    private final Map<Integer, GUIButton> buttons = new ConcurrentHashMap<>();

    public GUIMenu(String name, int rows) {
        this.name = name;
        this.rows = rows;
        this.inventory = Bukkit.createInventory(null, rows * 9, name);
    }

    /**
     * Open this GUI to the player
     *
     * @param p
     */
    public void open(Player p) {
        new BukkitRunnable() {
            @Override
            public void run() {
                p.openInventory(inventory);
                GUIListener.menuMap.put(p.getUniqueId(), GUIMenu.this);
            }
        }.runTaskLater(CitiesOfFreedom.getInstance(), 1);
    }

    /**
     * Set all items to the Bukkit Inventory
     */
    public void pack() {
        buttons.keySet().forEach(id -> {
            if (id <= (rows * 9) - 1) {
                inventory.setItem(id, buttons.get(id).getItem());
            }
        });
    }

    /**
     * Called when the player close the inventory
     *
     * @param player
     */
    public void onClose(Player player) {
    }

    // SETTERS

    /**
     * Set the name of the inventory (will update all clients)
     *
     * @param name
     */
    public void setName(String name) {
        this.name = name;
        List<HumanEntity> viewers = inventory.getViewers();
        this.inventory = Bukkit.createInventory(null, rows * 9, name);
        pack();
        viewers.forEach(player -> open((Player) player));
    }

    /**
     * Set a button to a specific slot
     *
     * @param slot
     * @param guiButton
     */
    public void setButton(int slot, GUIButton guiButton) {
        buttons.put(slot, guiButton);
    }

    /**
     * Set a button to a specific colon and line
     *
     * @param row
     * @param slot
     * @param guiButton
     */
    public void setButton(int row, int slot, GUIButton guiButton) {
        setButton(row * 9 + slot, guiButton);
    }

    /**
     * Add a button to the currents
     *
     * @param guiButton
     */
    public void addButton(GUIButton guiButton) {
        for (int i = 0; i < rows * 9; i++) {
            if (!buttons.containsKey(i)) {
                buttons.put(i, guiButton);
                break;
            }
        }
    }

    /**
     * Remove a slot button
     *
     * @param slot
     */
    private void removeButton(int slot) {
        buttons.remove(slot);
    }

    /**
     * Remove a slot button
     *
     * @param row
     * @param slot
     */
    public void removeButton(int row, int slot) {
        removeButton(row * 9 + slot);
    }

    /**
     * Remove all the items
     */
    public void clear() {
        buttons.clear();
    }

    /**
     * Clear all buttons of a line
     *
     * @param l
     */
    public void clearLine(int l) {
        for (int i = 1; i < 10; i++) removeButton(l, i);
    }

    /**
     * Clear all buttons of a column
     *
     * @param col
     */
    public void clearColumn(int col) {
        for (int i = 1; i < rows; i++) removeButton(i, col);
    }

    /**
     * Set a line
     *
     * @param l
     * @param guiButton
     */
    public void setLine(int l, GUIButton guiButton) {
        for (int i = 0; i < 9; i++) setButton(l, i + 1, guiButton);
    }

    /**
     * Set a column
     *
     * @param col
     * @param guiButton
     */
    public void setColumn(int col, GUIButton guiButton) {
        for (int i = 1; i < rows + 1; i++) setButton(i, col, guiButton);
    }

    // GETTERS

    /**
     * Get inventory name
     *
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     * Get Bukkit inventory
     *
     * @return
     */
    public Inventory getInventory() {
        return inventory;
    }

    /**
     * Get Button to a specific position
     *
     * @param slot
     * @return
     */
    public GUIButton getButton(int slot) {
        return buttons.get(slot);
    }

    /**
     * Get Button to a specific position
     *
     * @param row
     * @param slot
     * @return
     */
    public GUIButton getButton(int row, int slot) {
        return getButton(row * (slot - 1));
    }

}