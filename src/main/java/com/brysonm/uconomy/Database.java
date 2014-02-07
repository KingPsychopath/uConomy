package com.brysonm.uconomy;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Database {

    private String host;

    private String database;

    private String user;

    private String password;

    private String table;

    private Connection connection;

    private Statement statement;

    private ResultSet resultSet;

    public Database(String host, String database, String user, String password, String table) {

        this.host = host;

        this.database = database;

        this.user = user;

        this.password = password;

        this.table = table;

        try {

            Class.forName("com.mysql.jdbc.Driver");

            connection = DriverManager.getConnection("jdbc:mysql://" + host + ":3306/" + database, user, password);

        } catch(ClassNotFoundException | SQLException ex) {

            ex.printStackTrace();

        }

        createTable();

    }

    //TODO: create table when it does not previously exist

    public boolean createTable() {

        return true;

    }

    public List<Sale> loadSales() {

        List<Sale> sales = new ArrayList<Sale>();

        try {

            statement = connection.createStatement();

            String query = "select * from " + table;

            resultSet = statement.executeQuery(query);

            while(resultSet.next()) {

                Player player = Bukkit.getPlayer(resultSet.getString("player"));

                Material material = Material.getMaterial(resultSet.getString("material"));

                double price = resultSet.getDouble("price");

                Sale sale = new Sale(player, material, price);

                sales.add(sale);

            }

        } catch(SQLException ex) {

            ex.printStackTrace();

        }

        return sales;

    }

    public void close() {

        try {

            connection.close();

        } catch(SQLException ex) {

            ex.printStackTrace();

        }

    }

}
