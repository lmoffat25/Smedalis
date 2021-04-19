/*
 * Copyright (c) 2021, Nathan DJIAN-MARTIN (DevKrazy).
 * This ItemBuilder.java file is a part of the Smedalis project.
 * Smedalis cannot be copied and/or distributed without the express permission of Nathan DJIAN-MARTIN (DevKrazy)
 *
 */

package com.devkrazy.citiesoffreedom.utils;

import net.kyori.adventure.text.Component;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class ItemBuilder {

    private ItemStack itemStack;

    /**
     * Constructs an ItemBuilder with a material and a name
     * @param material the material
     * @param name the name that will be given to the item
     */
    public ItemBuilder(Material material, String name) {
        this.itemStack = new ItemStack(material);
        this.setName(name);
    }

    /**
     * Constructs an ItemBuilder with a material and a name
     * @param material the material
     * @param amount the name that will be given to the item
     */
    public ItemBuilder(Material material, int amount) {
        this.itemStack = new ItemStack(material, amount);
    }

    /**
     * @return the ItemStack's ItemMeta
     */
    private ItemMeta getMeta() {
        return this.itemStack.getItemMeta();
    }

    /**
     * Sets the display name of the ItemBuilder.
     * @param name the name that will be given to the item
     */
    public void setName(String name) {
        ItemMeta meta = this.getMeta();
        meta.displayName(Component.text(name));
        this.itemStack.setItemMeta(meta);
    }

    /**
     * Sets the lore of the ItemBuilder.
     * @param lore a list of Strings
     */
    public void setLore(String... lore) {
        ItemMeta meta = this.getMeta();
        List<Component> components = new ArrayList<>();
        for (String line : lore) {
            // converts each line of the lore into a TextComponent add adds it to the components list
            components.add(0, Component.text(line));
        }
        meta.lore(components);
        this.itemStack.setItemMeta(meta);
    }

    /**
     * Builds a new ItemStack based on the ItemBuilder attributes.
     * @return the new ItemStack
     */
    public ItemStack build() {
        return this.itemStack;
    }
}
