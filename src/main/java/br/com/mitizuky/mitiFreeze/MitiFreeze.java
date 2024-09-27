package br.com.mitizuky.mitiFreeze;
//Imports
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashSet;
import java.util.UUID;

public final class MitiFreeze extends JavaPlugin implements Listener {

    public static HashSet<UUID> freezedPlayers = new HashSet<>();

    @Override
    public void onEnable() {
        getCommand("freeze").setExecutor(new Commands());
        getCommand("unfreeze").setExecutor(new Commands());
        Bukkit.getServer().getPluginManager().registerEvents(this, this);
        Bukkit.getConsoleSender().sendMessage(ChatColor.GREEN + "MitiFreeze iniciado.");
    }

    @Override
    public void onDisable() {
        Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "MitiFreeze desligado.");
    }

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
        Player player = event.getPlayer();
        if (freezedPlayers.contains(player.getUniqueId())) {
            //While freezed that player cant jump
            if (event.getFrom().getY() < event.getTo().getY()) {
                player.teleport(event.getFrom());
            }
        }
    }
}
