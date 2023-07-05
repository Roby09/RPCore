package cc.ridgestone.rpcore.player;

import cc.ridgestone.rpcore.RPCore;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class PlayerManager {

    private List<RPPlayer> players = new ArrayList<>();

    public void loadPlayer(Player player) {
        List<Character> characters = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            if (RPCore.i.getPlayerConfig().contains(player.getUniqueId().toString() + ".character." + i + ".name")) {
                String name = RPCore.i.getPlayerConfig().getString(player.getUniqueId().toString() + ".character." + i + ".name");
                String bio = RPCore.i.getPlayerConfig().getString(player.getUniqueId().toString() + ".character." + i + ".bio");
                boolean student = RPCore.i.getPlayerConfig().getBoolean(player.getUniqueId().toString() + ".character." + i + ".student");
                int age = RPCore.i.getPlayerConfig().getInt(player.getUniqueId().toString() + ".character." + i + ".age");

                String world = RPCore.i.getPlayerConfig().getString(player.getUniqueId().toString() + ".character." + i + ".world");
                int x = RPCore.i.getPlayerConfig().getInt(player.getUniqueId().toString() + ".character." + i + ".locX");
                int y = RPCore.i.getPlayerConfig().getInt(player.getUniqueId().toString() + ".character." + i + ".locY");
                int z = RPCore.i.getPlayerConfig().getInt(player.getUniqueId().toString() + ".character." + i + ".locZ");

                characters.add(new Character(name, bio, student, age, new Location(Bukkit.getWorld(world), x,y,z)));
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
        List<Character> charactersList = new ArrayList<>();
        charactersList.add(mainCharacter);
        RPPlayer rpPlayer = new RPPlayer(uuid, charactersList, 0);
        players.add(rpPlayer);

        savePlayer(rpPlayer);
        Bukkit.getLogger().info("Registered player " + uuid);
    }

    public void savePlayer(RPPlayer rpPlayer) {
        //Save the location before saving
        rpPlayer.getCurrentCharacter().setLocation(Bukkit.getPlayer(rpPlayer.getUuid()).getLocation());
        //TODO save inventory

        int  i = 0;
        for (Character character : rpPlayer.getCharacters()) {
            RPCore.i.getPlayerConfig().set(rpPlayer.getUuid().toString() + ".character." + i + ".name", character.getName());
            RPCore.i.getPlayerConfig().set(rpPlayer.getUuid().toString() + ".character." + i + ".bio", character.getBio());
            RPCore.i.getPlayerConfig().set(rpPlayer.getUuid().toString() + ".character." + i + ".student", character.isStudent());
            RPCore.i.getPlayerConfig().set(rpPlayer.getUuid().toString() + ".character." + i + ".age", character.getAge());
            RPCore.i.getPlayerConfig().set(rpPlayer.getUuid().toString() + ".character." + i + ".world", character.getLocation().getWorld().getName());
            RPCore.i.getPlayerConfig().set(rpPlayer.getUuid().toString() + ".character." + i + ".locX", character.getLocation().getBlockZ());
            RPCore.i.getPlayerConfig().set(rpPlayer.getUuid().toString() + ".character." + i + ".locY", character.getLocation().getBlockY());
            RPCore.i.getPlayerConfig().set(rpPlayer.getUuid().toString() + ".character." + i + ".locZ", character.getLocation().getBlockZ());
        }

        RPCore.i.getPlayerConfig().set(rpPlayer.getUuid().toString() + ".currentCharacter", rpPlayer.getCurrentCharacterInt());

        RPCore.i.savePlayerFile();
    }

    public RPPlayer getRpPlayer(UUID uuid) {
        RPPlayer rpPlayer = null;
        for (RPPlayer _rpPlayer : players) {
            if (_rpPlayer.getUuid().equals(uuid))
                rpPlayer = _rpPlayer;
        }
        return rpPlayer;
    }

}
