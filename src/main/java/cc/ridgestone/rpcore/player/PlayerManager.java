package cc.ridgestone.rpcore.player;

import cc.ridgestone.rpcore.RPCore;
import cc.ridgestone.rpcore.util.SerializationUtil;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.io.IOException;
import java.util.*;

public class PlayerManager {

    private List<RPPlayer> players = new ArrayList<>();
    private List<Player> playersInSetup = new ArrayList<>();

    public void loadPlayer(Player player) {
        Map<Integer, Character> characters = new HashMap<>();
        for (int i = 0; i < 3; i++) {
            if (RPCore.i.getPlayerConfig().contains(player.getUniqueId().toString() + ".character." + i + ".name")) {
                String name = RPCore.i.getPlayerConfig().getString(player.getUniqueId().toString() + ".character." + i + ".name");
                String bio = RPCore.i.getPlayerConfig().getString(player.getUniqueId().toString() + ".character." + i + ".bio");
                String role = RPCore.i.getPlayerConfig().getString(player.getUniqueId().toString() + ".character." + i + ".role");
                int age = RPCore.i.getPlayerConfig().getInt(player.getUniqueId().toString() + ".character." + i + ".age");

                String world = RPCore.i.getPlayerConfig().getString(player.getUniqueId().toString() + ".character." + i + ".world");
                int x = RPCore.i.getPlayerConfig().getInt(player.getUniqueId().toString() + ".character." + i + ".locX");
                int y = RPCore.i.getPlayerConfig().getInt(player.getUniqueId().toString() + ".character." + i + ".locY");
                int z = RPCore.i.getPlayerConfig().getInt(player.getUniqueId().toString() + ".character." + i + ".locZ");

                String inventoryContent64 = RPCore.i.getPlayerConfig().getString(player.getUniqueId().toString() + ".character." + i + ".inventoryContent");
                String armorContent64 = RPCore.i.getPlayerConfig().getString(player.getUniqueId().toString() + ".character." + i + ".inventoryArmor");

                try {
                    characters.put(i, new Character(name, bio, role, age, new Location(Bukkit.getWorld(world), x,y,z), SerializationUtil.itemStackArrayFromBase64(inventoryContent64), SerializationUtil.itemStackArrayFromBase64(armorContent64)));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        int currentCharacter = RPCore.i.getPlayerConfig().getInt(player.getUniqueId().toString() + ".currentCharacter");
        players.add(new RPPlayer(player.getUniqueId(), characters, currentCharacter));
    }

    public void removePlayer(Player player) {
        if (players.contains(getRpPlayer(player.getUniqueId())))
            players.remove(getRpPlayer(player.getUniqueId()));
    }

    public void registerPlayer(UUID uuid, Character mainCharacter) {
        Map<Integer, Character> characterMap = new HashMap();
        characterMap.put(0, mainCharacter);
        RPPlayer rpPlayer = new RPPlayer(uuid, characterMap, 0);
        players.add(rpPlayer);
        rpPlayer.saveCurrentCharacter();
        Bukkit.getLogger().info("Registered player " + uuid);
    }

    public RPPlayer getRpPlayer(UUID uuid) {
        RPPlayer rpPlayer = null;
        for (RPPlayer _rpPlayer : players) {
            if (_rpPlayer.getUuid().equals(uuid))
                rpPlayer = _rpPlayer;
        }
        return rpPlayer;
    }

    public List<Player> getPlayersInSetup() {
        return playersInSetup;
    }
}
