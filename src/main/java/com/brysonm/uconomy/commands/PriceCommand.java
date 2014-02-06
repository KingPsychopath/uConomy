package com.brysonm.uconomy.commands;

import com.brysonm.uconomy.Sale;
import com.brysonm.uconomy.SaleUtils;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

public class PriceCommand implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(sender instanceof Player) {

            Player player = (Player) sender;

            if(args.length == 2) {

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

                    if(amount > 1024) {

                        player.sendMessage(ChatColor.RED + "You may check the price of items that cost 1024 or less.");

                        return true;

                    }

                    List<Sale> sales = SaleUtils.getSales(material, amount, false);

                    if(sales.isEmpty()) {

                        player.sendMessage(ChatColor.RED + "The uconomy has no " + material.name() + " left.");

                        return true;

                    }

                    if(sales.size() < amount) {

                        player.sendMessage(ChatColor.RED + "Only " + sales.size() + " " + material.name() + " remains in the uconomy.");

                        return true;

                    }

                    double price = 0.0D;

                    for(Sale sale : sales) {

                        price += sale.getPrice();

                    }

                    player.sendMessage(ChatColor.GRAY + "" + amount + " " + material.name() + " costs " + price + " gold.");

                    return true;


                } catch(NumberFormatException ex) {

                    player.sendMessage(ChatColor.RED + "'" + args[0] + " is not a number.");

                    return true;

                }

            } else {

                player.sendMessage(ChatColor.RED + "/price <amount> <item>");

                return true;

            }

        }
        return true;
    }

}
