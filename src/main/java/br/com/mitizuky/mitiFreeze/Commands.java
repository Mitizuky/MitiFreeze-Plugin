package br.com.mitizuky.mitiFreeze;
//Imports
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class Commands implements CommandExecutor {


    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String label, String[] args) {

        if (command.getName().equalsIgnoreCase("freeze")) {
            if (commandSender instanceof Player) {
                Player player = (Player) commandSender;
                //Checking permissions
                if (!player.hasPermission("miti.freeze")) {
                    player.sendMessage(ChatColor.RED + "Você não tem permissão para usar este comando.");
                    return false;
                }
                //Checking args for freeze command
                if (args.length < 1) {
                    player.sendMessage(ChatColor.RED + "Uso: /freeze <jogador>");
                    return false;
                }
                //Store target string player name at position 0 of args
                String targetPlayerName = args[0];
                //Getting target player name
                Player targetPlayer = JavaPlugin.getPlugin(MitiFreeze.class).getServer().getPlayer(targetPlayerName);
                //Player not found logic
                if (targetPlayer == null) {
                    player.sendMessage(ChatColor.RED + "Jogador não encontrado!");
                    return false;
                }
                //If player has found, that player will be freezed
                else {
                        Bukkit.broadcastMessage(ChatColor.GOLD + player.getName() + ChatColor.DARK_PURPLE + " congelou " + ChatColor.GOLD + targetPlayer.getName());
                        targetPlayer.setWalkSpeed(0); //Freeze
                        MitiFreeze.freezedPlayers.add(targetPlayer.getUniqueId()); //Add target to freezed players list
                        //Prevents player flight
                        if (targetPlayer.getAllowFlight()) {
                            targetPlayer.setAllowFlight(false);
                        }
                        return false;
                }
            }
        }

        if (command.getName().equalsIgnoreCase("unfreeze")) {
            if (commandSender instanceof Player) {
                Player player = (Player) commandSender;
                //Checking permissions
                if (!player.hasPermission("miti.freeze")) {
                    player.sendMessage(ChatColor.RED + "Você não tem permissão para usar este comando.");
                    return false;
                }
                //Checking args for freeze command
                if (args.length < 1) {
                    player.sendMessage(ChatColor.RED + "Uso: /freeze <jogador>");
                    return false;
                }
                //Store target string player name at position 0 of args
                String targetPlayerName = args[0];
                //Getting target player name
                Player targetPlayer = JavaPlugin.getPlugin(MitiFreeze.class).getServer().getPlayer(targetPlayerName);
                //Player not found logic
                if (targetPlayer == null) {
                    player.sendMessage(ChatColor.RED + "Jogador não encontrado!");
                    return false;
                }
                //If player has found, that player will be unfreezed
                else {
                    Bukkit.broadcastMessage(ChatColor.GOLD + player.getName() + ChatColor.AQUA + " descongelou " + ChatColor.GOLD + targetPlayer.getName());
                    targetPlayer.setWalkSpeed(0.2f); //Unfreeze
                    MitiFreeze.freezedPlayers.remove(targetPlayer.getUniqueId()); //Remove target to freezed players list
                    //Reactive flight
                    if (!targetPlayer.getAllowFlight()) {
                        targetPlayer.setAllowFlight(true);
                    }
                    return false;
                }
            }
        }

        return false;
    }
}