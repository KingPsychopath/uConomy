package com.brysonm.uconomy.commands;

import com.brysonm.uconomy.BalanceUtils;
import com.brysonm.uconomy.Sale;
import com.brysonm.uconomy.SaleUtils;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

public class BuyCommand implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(sender instanceof Player) {

            Player player = (Player) sender;

            if(args.length == 3) {

                try {

                    int amount = Integer.parseInt(args[0]);

                    if(amount <= 0) {

                        player.sendMessage(ChatColor.RED + "You must use positive integers to sell items.");

                        return true;

                    }

                    Material material = Material.getMaterial(args[1].toUpperCase());

                    if(material == null) {

                        player.sendMessage(ChatColor.RED + "Item '" + args[1] + "' not found.");

                        return true;

                    }

                    double limit = Double.parseDouble(args[2]);

                    double balance = BalanceUtils.getBalance(player);

                    if(limit > balance) {

                        player.sendMessage(ChatColor.RED + "Your limit is " + limit + " while you only have " + balance + ".");

                        return true;

                    }

                    if(amount > 1024) {

                        player.sendMessage(ChatColor.RED + "You may buy items that cost 1024 or less.");

                        return true;

                    }

                    List<Sale> sales = SaleUtils.getSales(material, amount, true);

                    if(sales.isEmpty()) {

                        player.sendMessage(ChatColor.RED + "The uconomy has no " + material.name() + " left.");

                        return true;

                    }

                    double price = 0.0D;

                    for(Sale sale : sales) {

                        price += sale.getPrice();

                    }

                    if(limit < price) {

                        player.sendMessage(ChatColor.RED + "This costs " + price + " while your limit is " + limit + ".");

                        return true;

                    }

                    if(price > balance) {

                        player.sendMessage(ChatColor.RED + "This costs " + price + " while you only have " + balance + ".");

                        return true;

                    }

                    if(sales.size() < amount) {

                        player.sendMessage(ChatColor.RED + "Only " + sales.size() + " " + material.name() + " remains in the uconomy.");

                        return true;

                    }

                    SaleUtils.buyItems(player, sales);

                    player.sendMessage(ChatColor.GRAY + "You have bought " + amount + " " + material.name() + " for " + price + " gold. Your new balance is " + BalanceUtils.getBalance(player) + ".");

                    return true;

                } catch(NumberFormatException ex) {

                    player.sendMessage(ChatColor.RED + "/buy <amount> <item> <limit>");

                    return true;

                }

            } else {

                player.sendMessage(ChatColor.RED + "/buy <amount> <item> <limit>");

                return true;

            }

        }
        return true;
    }

}