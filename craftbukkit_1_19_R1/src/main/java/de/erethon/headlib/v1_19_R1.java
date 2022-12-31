/*
 * Written from 2018-2022 by Daniel Saukel
 *
 * To the extent possible under law, the author(s) have dedicated all
 * copyright and related and neighboring rights to this software
 * to the public domain worldwide.
 *
 * This software is distributed without any warranty.
 *
 * You should have received a copy of the CC0 Public Domain Dedication
 * along with this software. If not, see <http://creativecommons.org/publicdomain/zero/1.0/>.
 */
package de.erethon.headlib;

import de.erethon.headlib.HeadLib.InternalsProvider;
import java.util.UUID;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.Tag;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_19_R1.inventory.CraftItemStack;
import org.bukkit.inventory.ItemStack;

/**
 * @author Daniel Saukel
 */
class v1_19_R1 implements InternalsProvider {

    @Override
    public ItemStack newPlayerHead(int amount) {
        return new ItemStack(Material.PLAYER_HEAD, amount);
    }

    @Override
    public String getTextureValue(ItemStack item) {
        net.minecraft.world.item.ItemStack nmsStack = CraftItemStack.asNMSCopy(item);

        CompoundTag tag = nmsStack.getTag();
        if (tag == null) {
            return null;
        }

        CompoundTag skullOwner = tag.getCompound("SkullOwner");
        if (skullOwner == null) {
            return null;
        }
        CompoundTag properties = skullOwner.getCompound("Properties");
        if (properties == null) {
            return null;
        }
        ListTag textures = properties.getList("textures", 10);
        if (textures == null) {
            return null;
        }

        for (Tag base : textures) {
            if (base instanceof CompoundTag && ((CompoundTag) base).contains("Value", 8)) {
                return ((CompoundTag) base).getString("Value");
            }
        }
        return null;
    }

    @Override
    public ItemStack setSkullOwner(ItemStack item, Object compound) {
        net.minecraft.world.item.ItemStack nmsStack = CraftItemStack.asNMSCopy(item);
        nmsStack.getOrCreateTag().put("SkullOwner", (Tag) compound);
        return CraftItemStack.asBukkitCopy(nmsStack);
    }

    @Override
    public CompoundTag createOwnerCompound(String id, String textureValue) {
        CompoundTag skullOwner = new CompoundTag();
        skullOwner.putUUID("Id", UUID.fromString(id));
        CompoundTag properties = new CompoundTag();
        ListTag textures = new ListTag();
        CompoundTag value = new CompoundTag();
        value.putString("Value", textureValue);
        textures.add(value);
        properties.put("textures", textures);
        skullOwner.put("Properties", properties);
        return skullOwner;
    }

}
