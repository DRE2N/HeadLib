## Usage
HeadLib is a library for CraftBukkit servers to create custom player heads
from Base64 encoded Strings. It contains hundreds of built-in heads.

### Code example
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

// Using a Base64 encoded String to create a head
String texture = "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMmM2NDVhNDI1OTg3ZjNiN2MyZmFjMDIwNmNlOThiYTVlMjNiOWM0ODUyZmVhNWIxOTc4Zjc0NjdlOGQzMTMifX19";
ItemStack texturedSkull = HeadLib.setSkullOwner(head, player.getUniqueId(), textureValue);
String textureFromSkull = HeadLib.getTextureValue(head);
```

## Maven Repository

```
    <dependencies>
        <dependency>
            <groupId>de.erethon</groupId>
            <artifactId>headlib</artifactId>
            <version>{number of latest version}</version>
        </dependency>
    </dependencies>
    <repositories>
        <repository>
            <id>dre-repo</id>
            <url>https://erethon.de/repo/</url>
        </repository>
    </repositories>
```

HeadLib is a library that developers can shade or copy into their own plugin.
This is not a standalone plugin.
Shading libraries can be done using Maven and its shade plugin:

```
    <build>
        <plugins>
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-shade-plugin</artifactId>
                <version>3.2.1</version>
                <executions>
                    <execution>
                        <phase>package</phase>
                        <goals>
                            <goal>shade</goal>
                        </goals>
                        <configuration>
                            <artifactSet>
                                <includes>
                                    <include>de.erethon:headlib</include>
                                </includes>
                            </artifactSet>
                        </configuration>
                    </execution>
                </executions>
            </plugin>
        </plugins>
    </build>
```

## License
Written from 2018-2020 by Daniel Saukel

To the extent possible under law, the author(s) have dedicated all
copyright and related and neighboring rights to this software
to the public domain worldwide.

This software is distributed without any warranty.

You should have received a copy of the CC0 Public Domain Dedication
along with this software. If not, see <http://creativecommons.org/publicdomain/zero/1.0/>.
