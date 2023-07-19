package cc.ridgestone.rpcore.player;

import cc.ridgestone.rpcore.RPCore;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.concurrent.CompletableFuture;

public class PlayerSetup implements Listener {

    private final Player player;
    private int setupStage = 1;
    private String name;
    private String bio;
    private boolean student;
    private String role;

    private Character character;

    private CompletableFuture<Character> completableFuture = new CompletableFuture<>();


    public PlayerSetup(Player player) {
        this.player = player;
        Bukkit.getPluginManager().registerEvents(this, RPCore.i);
        player.sendMessage(ChatColor.DARK_AQUA + "Please input your character's name: ex (John Bill)");
    }

    @EventHandler
    public void onChat(AsyncPlayerChatEvent event) {
        if (event.getPlayer().getUniqueId() != player.getUniqueId())
            return;

        event.setCancelled(true);

        switch (setupStage) {
            case 1:
                name = event.getMessage();
                player.sendMessage(ChatColor.GREEN + "Successfully set your character's name to: " + ChatColor.BOLD + "" + name);
                player.sendMessage(ChatColor.DARK_AQUA + "Please input your character's bio: ex (John Bill is tall.)");
                sendSpace();
                setupStage = 2;
                break;
            case 2:
                bio = event.getMessage();
                player.sendMessage(ChatColor.GREEN + "Successfully your character's bio to: " + ChatColor.BOLD + "" + bio);
                player.sendMessage(ChatColor.DARK_AQUA + "Please type your character's role: adult or student");
                sendSpace();
                setupStage = 3;
                break;
            case 3:
                if (event.getMessage().equalsIgnoreCase("adult")) {
                    student = false;
                    role = "Adult";
                    player.sendMessage(ChatColor.GREEN + "Successfully your character's role to: " + ChatColor.BOLD + "adult");
                    player.sendMessage(ChatColor.DARK_AQUA + "Please input your character's age: 20-80");
                } else if (event.getMessage().equalsIgnoreCase("student")) {
                    student = true;
                    role = "Student";
                    player.sendMessage(ChatColor.GREEN + "Successfully your character's role to: " + ChatColor.BOLD + "student");
                    player.sendMessage(ChatColor.DARK_AQUA + "Please input your character's age: [Year 1: 18-22] [Year 2: 19-23] [Year 3: 20-24] [Year 4: [21-26]");
                } else {
                    player.sendMessage(ChatColor.RED + "Please type your character's role: adult or student");
                    return;
                }
                setupStage = 4;
                sendSpace();
                break;
            case 4:
                try {
                    Integer.parseInt(event.getMessage());
                } catch (NumberFormatException e) {
                    player.sendMessage(ChatColor.RED + "No numeric value found. Please only use numbers.");
                    return;
                }
                int age = Integer.parseInt(event.getMessage());
                if (student) {
                    if (age < 18 || age > 26) {
                        player.sendMessage(ChatColor.RED + "A student can only be 18-26 years old");
                        return;
                    }
                } else {
                    if (age < 20 || age > 80) {
                        player.sendMessage(ChatColor.RED + "An adult can only be 20-80 years old");
                        return;
                    }
                }
                player.sendMessage(ChatColor.GREEN + "Successfully your character's age to: " + ChatColor.BOLD + "" + age);
                sendSpace();
                completableFuture.complete(new Character(name, bio, role, age, player.getLocation()));
                player.sendMessage(ChatColor.GREEN + "Character " + name + " created successfully");
                cancel();
                break;
        }
    }

    @EventHandler
    public void move(PlayerMoveEvent event) {
        if(event.getPlayer().getUniqueId() != player.getUniqueId())
            return;

        Location from = event.getFrom();
        Location to = event.getTo();
        double x = Math.floor(from.getX());
        double z = Math.floor(from.getZ());
        if(Math.floor(to.getX())!=x||Math.floor(to.getZ())!=z) {
            x+=.5;
            z+=.5;
            event.getPlayer().teleport(new Location(from.getWorld(),x,from.getY(),z,from.getYaw(),from.getPitch()));
        }
    }

    //Cancel setup on disconnect
    @EventHandler
    public void onDisconnect(PlayerQuitEvent event) {
        if(event.getPlayer().getUniqueId() != player.getUniqueId())
            return;
        cancel();
    }

    public void cancel() {
        HandlerList.unregisterAll(this);
    }

    public void sendSpace() {
        player.sendMessage(ChatColor.GRAY + "---------------------");
    }

    public CompletableFuture<Character> getCompletableFuture() {
        return completableFuture;
    }
}
