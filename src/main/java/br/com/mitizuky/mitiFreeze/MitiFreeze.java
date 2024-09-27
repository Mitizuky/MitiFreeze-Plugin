package br.com.mitizuky.mitiFreeze;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

public final class MitiFreeze extends JavaPlugin {

    @Override
    public void onEnable() {
        getCommand("freeze").setExecutor(new Commands());
        getCommand("unfreeze").setExecutor(new Commands());
        Bukkit.getConsoleSender().sendMessage(ChatColor.GREEN + "MitiFreeze iniciado.");
    }

    @Override
    public void onDisable() {
        Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "MitiFreeze desligado.");
    }
}
