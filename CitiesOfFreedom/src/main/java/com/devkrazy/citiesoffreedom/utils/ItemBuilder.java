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
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
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
     * Constructs an ItemBuilder from an existing itemstack.
     * @param itemStack the itemstack used to construct the ItemBuilder
     */
    public ItemBuilder(ItemStack itemStack) {
        this.itemStack = itemStack;
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
     * @return the updated ItemBuilder
     * @param name the name that will be given to the item
     */
    public ItemBuilder setName(String name) {
        ItemMeta meta = this.getMeta();
        meta.displayName(Component.text(name));
        this.itemStack.setItemMeta(meta);
        return this;
    }

    /**
     * Sets the lore of the ItemBuilder.
     * @param lore an array of Strings
     * @return the updated ItemBuilder
     */
    public ItemBuilder setLore(String... lore) {
        ItemMeta meta = this.getMeta();
        List<Component> components = new ArrayList<>();
        for (String line : lore) {
            // converts each line of the lore into a TextComponent add adds it to the components list
            components.add(Component.text(line));
        }
        meta.lore(components);
        this.itemStack.setItemMeta(meta);
        return this;
    }

    /**
     * Sets the lore of the ItemBuilder.
     * @param lore a list of components
     * @return the updated ItemBuilder
     */
    public ItemBuilder setLore(List<Component> lore) {
        ItemMeta meta = this.getMeta();
        meta.lore(lore);
        this.itemStack.setItemMeta(meta);
        return this;
    }

    /**
     * Adds new lines to the lore of the ItemBuilder.
     * @param lore a list of Strings
     * @return the updated ItemBuilder
     */
    public ItemBuilder addToLore(String... lore) {
        ItemMeta meta = this.getMeta();

        // initializes a new ArrayList if the lore is null
        List<Component> components = meta.lore() == null ? new ArrayList<>() : meta.lore();

        for (String line : lore) {
            // converts each line of the lore into a TextComponent add adds it to the components list
            components.add(Component.text(line));
        }
        meta.lore(components);
        this.itemStack.setItemMeta(meta);
        return this;
    }


    /**
     * Adds the enchantment glow effect to the item without actually enchanting it.
     * @return the updated ItemBuilder
     */
    public ItemBuilder addGlow() {
        ItemMeta meta = this.getMeta();
        if (this.itemStack.getType().toString().contains("BOW")) {
            // add a protection enchantment
            this.itemStack.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1);
        } else {
            // add an infinity enchantment
            this.itemStack.addUnsafeEnchantment(Enchantment.ARROW_INFINITE, 1);
        }
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS); // hides the enchantment in the lore
        this.itemStack.setItemMeta(meta);
        // TODO: fix the method, not displaying the enchantment glow
        return this;
    }

    /**
     * Builds a new ItemStack based on the ItemBuilder attributes.
     * @return the new ItemStack
     */
    public ItemStack build() {
        return this.itemStack;
    }
}
