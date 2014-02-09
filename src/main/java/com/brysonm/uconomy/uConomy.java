package com.brysonm.uconomy;

import com.brysonm.uconomy.commands.*;
import org.bukkit.plugin.java.JavaPlugin;
import org.mcstats.Metrics;

import java.io.IOException;

public class uConomy extends JavaPlugin {

    private static uConomy instance;

    private static YMLFactory.YML balancesYML;

    private static YMLFactory.YML salesYML;

    public boolean mysql;

    public Database database;

    public void onEnable() {
        instance = this;
        balancesYML = YMLFactory.buildYML("balances", this);
        salesYML = YMLFactory.buildYML("sales", this);
        saveDefaultConfig();
        mysql = getConfig().getBoolean("mysql-enabled");
        getCommand("balance").setExecutor(new BalanceCommand());
        getCommand("deposit").setExecutor(new DepositCommand());
        getCommand("withdraw").setExecutor(new WithdrawCommand());
        getCommand("sell").setExecutor(new SellCommand());
        getCommand("buy").setExecutor(new BuyCommand());
        getCommand("price").setExecutor(new PriceCommand());
        SaleUtils.loadSales();

        try {

            Metrics metrics = new Metrics(this);

            metrics.start();

        } catch(IOException ex) {

            ex.printStackTrace();

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
