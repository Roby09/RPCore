package cc.ridgestone.rpcore.player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public class RPPlayer {

    private UUID uuid;
    private List<Character> characters = new ArrayList<>();
    private int currentCharacter;

    public RPPlayer(UUID uuid, List<Character> characters, int currentCharacter) {
        this.uuid = uuid;
        this.characters = characters;
        this.currentCharacter = currentCharacter;
    }

    public void setCurrentCharacter(int currentCharacter) {
        this.currentCharacter = currentCharacter;
    }

    public List<Character> getCharacters() {
        return characters;
    }

    public Character getCurrentCharacter() {
        return characters.get(currentCharacter);
    }

    public int getCurrentCharacterInt() {
        return currentCharacter;
    }

    public UUID getUuid() {
        return uuid;
    }
}
