/*
 * Written from 2018-2020 by Daniel Saukel
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

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * @author Daniel Saukel
 */
public class HeadLibPlugin extends JavaPlugin {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (args.length == 0 || (args.length == 1 && !(sender instanceof Player))) {
            return false;
        }

        Player player = null;
        HeadLib head = null;
        int amount = 1;
        UUID uuid = null;
        String textureValue = null;
        String displayName = null;
        List<String> lore = new ArrayList<>();
        int i = 0;

        if (args.length >= 2) {
            Player argPlayer = Bukkit.getPlayer(args[0]);
            if (argPlayer != null) {
                player = argPlayer;
                i++;
            }
        }
        if (player == null) {
            if (!(sender instanceof Player)) {
                return false;
            } else {
                player = (Player) sender;
            }
        }

        while (i < args.length) {
            String arg = args[i].toUpperCase();

            if (arg.startsWith("HEAD:")) {
                arg = arg.replace("HEAD:", "");
                try {
                    head = HeadLib.valueOf(arg);
                } catch (IllegalArgumentException exception) {
                    sender.sendMessage("The head \"" + arg + "\" does not exist.");
                    return false;
                }

            } else if (arg.startsWith("AMOUNT:")) {
                arg = arg.replace("AMOUNT:", "");
                try {
                    amount = Integer.parseInt(arg);
                    if (amount < 1) {
                        amount = 1;
                    }
                } catch (NumberFormatException exception) {
                    sender.sendMessage("\"" + arg + "\" is not a valid number.");
                    return false;
                }

            } else if (arg.startsWith("UUID:")) {
                arg = arg.replace("UUID:", "");
                try {
                    uuid = UUID.fromString(arg);
                } catch (IllegalArgumentException exception) {
                    sender.sendMessage("\"" + arg + "\" is not a valid UUID.");
                }

            } else if (arg.startsWith("TEXTUREVALUE:")) {
                textureValue = arg.replace("TEXTUREVALUE:", "");

            } else if (displayName == null) {
                displayName = ChatColor.translateAlternateColorCodes('&', arg);

            } else {
                lore.add(ChatColor.translateAlternateColorCodes('&', arg));
            }

            i++;
        }

        if (head != null) {
            head.give(player, amount, displayName, lore.toArray(new String[lore.size()]));
            return true;

        } else if (uuid != null) {
            ItemStack stack = HeadLib.internals.newPlayerHead(amount);
            SkullMeta meta = (SkullMeta) stack.getItemMeta();
            if (displayName != null) {
                meta.setDisplayName(displayName);
                meta.setLore(lore);
            }
            if (textureValue == null) {
                meta.setOwningPlayer(Bukkit.getOfflinePlayer(uuid));
            }
            stack.setItemMeta(meta);
            if (textureValue != null) {
                HeadLib.setSkullOwner(stack, uuid, textureValue);
            }
            player.getInventory().addItem(stack);
            return true;

        } else {
            return false;
        }
    }

}
