package com.brysonm.uconomy;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public class BalanceUtils {

    public static double getBalance(Player player) {

        FileConfiguration config = uConomy.getBalancesYML().getConfig();

        return config.contains(player.getName()) ? config.getDouble(player.getName()) : 0.0D;

    }

    public static void depositAmount(Player player, double amount) {

        double balance = getBalance(player);

        balance += amount;

        uConomy.getBalancesYML().getConfig().set(player.getName(), balance);

    }

    public static void withdrawAmount(Player player, double amount) {

        double balance = getBalance(player);

        balance -= amount;

        uConomy.getBalancesYML().getConfig().set(player.getName(), balance);

    }

}
