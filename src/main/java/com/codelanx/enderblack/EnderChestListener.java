/*
 * Copyright (C) 2014 Codelanx, All Rights Reserved
 *
 * This work is licensed under a Creative Commons
 * Attribution-NonCommercial-NoDerivs 3.0 Unported License.
 *
 * This program is protected software: You are free to distrubute your
 * own use of this software under the terms of the Creative Commons BY-NC-ND
 * license as published by Creative Commons in the year 2014 or as published
 * by a later date. You may not provide the source files or provide a means
 * of running the software outside of those licensed to use it.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 *
 * You should have received a copy of the Creative Commons BY-NC-ND license
 * long with this program. If not, see <https://creativecommons.org/licenses/>.
 */
package com.codelanx.enderblack;

import com.codelanx.codelanxlib.listener.SubListener;
import java.util.EnumSet;
import java.util.List;
import java.util.Set;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.inventory.InventoryMoveItemEvent;
import org.bukkit.event.inventory.InventoryType;

/**
 * Class description for {@link EnderChestListener}
 *
 * @since 1.0.0
 * @author 1Rogue
 * @version 1.0.0
 */
public class EnderChestListener extends SubListener<EnderBlack> {
    
    private final Set<Material> disallowed = EnumSet.noneOf(Material.class);

    public EnderChestListener(EnderBlack plugin) {
        super(plugin);
    }

    @EventHandler(ignoreCancelled = true)
    public void onInventoryMove(InventoryMoveItemEvent event) {
        if (event.getDestination().getType() == InventoryType.ENDER_CHEST) {
            if (this.disallowed.contains(event.getItem().getType())) {
                event.setCancelled(true);
            }
        }
    }

    public void reload() {
        this.disallowed.clear();
        List<String> lis = (List<String>) this.plugin.getConfiguration().get(ConfigValue.DISALLOWED_ITEMS);
        for (String s : lis) {
            this.disallowed.add(Material.matchMaterial(s));
        }
    }
}
