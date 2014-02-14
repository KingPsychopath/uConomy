package com.brysonm.uconomy;

import com.brysonm.uconomy.commands.*;

import net.gravitydevelopment.updater.Updater;

import org.bukkit.plugin.java.JavaPlugin;
import org.mcstats.Metrics;

import java.io.IOException;

public class uConomy extends JavaPlugin {

    private static uConomy instance;

    private static YMLFactory.YML balancesYML;

    private static YMLFactory.YML salesYML;

    public void onEnable() {
        saveDefaultConfig();

        instance = this;
        balancesYML = YMLFactory.buildYML("balances", this);
        salesYML = YMLFactory.buildYML("sales", this);
        getCommand("balance").setExecutor(new BalanceCommand());
        getCommand("deposit").setExecutor(new DepositCommand());
        getCommand("withdraw").setExecutor(new WithdrawCommand());
        getCommand("sell").setExecutor(new SellCommand());
        getCommand("buy").setExecutor(new BuyCommand());
        getCommand("price").setExecutor(new PriceCommand());
        SaleUtils.loadSales();

        // Load Metrics
        try {
            Metrics metrics = new Metrics(this);
            metrics.start();
        } catch(IOException ex) {
            ex.printStackTrace();
        }

        // Load Auto Updater
        if (getConfig().getBoolean("updater", true)) {
            Updater updater = new Updater(this, 73898, this.getFile(), Updater.UpdateType.DEFAULT, false);
        }
    }

    public void onDisable() {
        balancesYML.saveConfig();
        salesYML.saveConfig();
    }

    public static uConomy getInstance() {
        return instance;
    }

    public static YMLFactory.YML getBalancesYML() {
        return balancesYML;
    }

    public static YMLFactory.YML getSalesYML() {
        return salesYML;
    }
}
