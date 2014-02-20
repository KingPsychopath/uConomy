package com.brysonm.uconomy.commands;

import com.brysonm.uconomy.BalanceUtils;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class BalanceCommand implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(sender instanceof Player) {

            Player player = (Player) sender;

            if(!player.hasPermission("uconomy." + cmd.getName().toLowerCase())) {

                player.sendMessage(ChatColor.RED + "You do not have permission to execute this command!");

                return true;

            }

            if(args.length == 0) {

                double balance = BalanceUtils.getBalance(player);

                player.sendMessage(ChatColor.GRAY + "You have " + balance + " gold in your bank account.");

                return true;

            } else {

                player.sendMessage(ChatColor.RED + "/balance");

                return true;

            }

        }
        return true;
    }

}
