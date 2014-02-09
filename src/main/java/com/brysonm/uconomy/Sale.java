package com.brysonm.uconomy;

import org.bukkit.Material;
import org.bukkit.entity.Player;

import java.util.UUID;

public class Sale {

    private UUID uuid;

    private Player player;

    private Material material;

    private double price;

    public Sale(Player player, Material material, double price) {

        this.player = player;

        this.material = material;

        this.price = price;

        this.uuid = UUID.randomUUID();

        this.save();

    }

    public Sale(UUID uuid, Player player, Material material, double price) {

        this.uuid = uuid;

        this.player = player;

        this.material = material;

        this.price = price;

    }

    public UUID getUUID() {
        return uuid;
    }

    public Player getPlayer() {
        return player;
    }

    public Material getMaterial() {
        return material;
    }

    public double getPrice() {
        return price;
    }

    public void save() {

        uConomy.getSalesYML().getConfig().set("sales." + uuid.toString() + ".player", player.getName());

        uConomy.getSalesYML().getConfig().set("sales." + uuid.toString() + ".material", material.name());

        uConomy.getSalesYML().getConfig().set("sales." + uuid.toString() + ".price", price);

        uConomy.getSalesYML().saveConfig();

    }

}
