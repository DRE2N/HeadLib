## License
Written in 2018 by Daniel Saukel

To the extent possible under law, the author(s) have dedicated all
copyright and related and neighboring rights to this software
to the public domain worldwide.

This software is distributed without any warranty.

You should have received a copy of the CC0 Public Domain Dedication
along with this software. If not, see <http://creativecommons.org/publicdomain/zero/1.0/>.

## Usage
```
// Creating a custom head item stack
HeadLib.WOODEN_EXCLAMATION_MARK.toItemStack();
HeadLib.WOODEN_EXCLAMATION_MARK.toItemStack(3);
HeadLib.WOODEN_EXCLAMATION_MARK.toItemStack(3, ChatColor.DARK_RED + "Item Display Name", "var", "args", "for", "lore");
HeadLib.WOODEN_EXCLAMATION_MARK.toItemStack(ChatColor.DARK_RED + "Item Display Name", "var", "args", "for", "lore");

// Giving an item to a player
HeadLib.WOODEN_EXCLAMATION_MARK.give(player);
HeadLib.WOODEN_EXCLAMATION_MARK.give(player, 3);
HeadLib.WOODEN_EXCLAMATION_MARK.give(player, 3, ChatColor.DARK_RED + "Item Display Name", "var", "args", "for", "lore");
HeadLib.WOODEN_EXCLAMATION_MARK.give(player, ChatColor.DARK_RED + "Item Display Name", "var", "args", "for", "lore");
```

## Maven Repository
```
    <dependencies>
        <dependency>
            <groupId>de.erethon</groupId>
            <artifactId>headlib</artifactId>
            <version>1.1</version>
        </dependency>
    </dependencies>
    <repositories>
        <repository>
            <id>dre-repo</id>
            <url>https://erethon.de/repo/</url>
        </repository>
    </repositories>
```
