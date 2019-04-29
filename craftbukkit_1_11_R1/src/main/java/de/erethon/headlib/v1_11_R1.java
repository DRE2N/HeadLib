/*
 * Written from 2018-2019 by Daniel Saukel
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
import net.minecraft.server.v1_11_R1.NBTBase;
import net.minecraft.server.v1_11_R1.NBTTagCompound;
import net.minecraft.server.v1_11_R1.NBTTagList;
import org.bukkit.craftbukkit.v1_11_R1.inventory.CraftItemStack;
import org.bukkit.inventory.ItemStack;

/**
 * @author Daniel Saukel
 */
class v1_11_R1 implements InternalsProvider {

    @Override
    public String getTextureValue(ItemStack item) {
        net.minecraft.server.v1_11_R1.ItemStack nmsStack = CraftItemStack.asNMSCopy(item);

        NBTTagCompound tag = nmsStack.getTag();
        if (tag == null) {
            return null;
        }

        NBTTagCompound skullOwner = tag.getCompound("SkullOwner");
        if (skullOwner == null) {
            return null;
        }
        NBTTagCompound properties = skullOwner.getCompound("Properties");
        if (properties == null) {
            return null;
        }
        NBTTagList textures = properties.getList("textures", 10);
        if (textures == null) {
            return null;
        }

        for (int i = 0; i < textures.size(); i++) {
            NBTBase base = textures.get(i);
            if (base instanceof NBTTagCompound && ((NBTTagCompound) base).hasKeyOfType("Value", 8)) {
                return ((NBTTagCompound) base).getString("Value");
            }
        }
        return null;
    }

    @Override
    public ItemStack setSkullOwner(ItemStack item, Object compound) {
        net.minecraft.server.v1_11_R1.ItemStack nmsStack = CraftItemStack.asNMSCopy(item);
        NBTTagCompound tag = nmsStack.getTag();
        if (tag == null) {
            tag = new NBTTagCompound();
        }
        tag.set("SkullOwner", (NBTBase) compound);
        nmsStack.setTag(tag);
        return CraftItemStack.asBukkitCopy(nmsStack);
    }

    @Override
    public NBTTagCompound createOwnerCompound(String id, String textureValue) {
        NBTTagCompound skullOwner = new NBTTagCompound();
        skullOwner.setString("Id", id);
        NBTTagCompound properties = new NBTTagCompound();
        NBTTagList textures = new NBTTagList();
        NBTTagCompound value = new NBTTagCompound();
        value.setString("Value", textureValue);
        textures.add(value);
        properties.set("textures", textures);
        skullOwner.set("Properties", properties);
        return skullOwner;
    }

}
