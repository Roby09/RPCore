package cc.ridgestone.rpcore.player;

import org.bukkit.Location;

public class Character {

    private String name;
    private String bio;
    private boolean student;
    private int age;
    private Location location;

    public Character(String name, String bio, boolean student, int age, Location location) {
        this.name = name;
        this.bio = bio;
        this.student = student;
        this.age = age;
        this.location = location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public boolean isStudent() {
        return student;
    }

    public int getAge() {
        return age;
    }

    public String getBio() {
        return bio;
    }

    public Location getLocation() {
        return location;
    }
}
