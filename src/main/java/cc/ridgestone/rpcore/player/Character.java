package cc.ridgestone.rpcore.player;

import org.bukkit.Location;
import org.bukkit.inventory.ItemStack;

public class Character {

    private String name;
    private String bio;
    private String role;
    private int age;
    private Location location;
    private ItemStack[] inventoryContent;
    private ItemStack[] armorContent;

    public Character(String name, String bio, String role, int age, Location location, ItemStack[] inventoryContent, ItemStack[] armorContent) {
        this.name = name;
        this.bio = bio;
        this.role = role;
        this.age = age;
        this.location = location;
        this.inventoryContent = inventoryContent;
        this.armorContent = armorContent;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setInventoryContent(ItemStack[] inventoryContent) {
        this.inventoryContent = inventoryContent;
    }

    public void setArmorContent(ItemStack[] armorContent) {
        this.armorContent = armorContent;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getBio() {
        return bio;
    }

    public String getRole() {
        return role;
    }

    public ItemStack[] getInventoryContent() {
        return inventoryContent;
    }

    public ItemStack[] getArmorContent() {
        return armorContent;
    }

    public Location getLocation() {
        return location;
    }
}
