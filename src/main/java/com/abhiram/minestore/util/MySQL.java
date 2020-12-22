package com.abhiram.minestore.util;

import com.abhiram.minestore.Main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class MySQL {
    private static final String mysql_ip = Main.getInstance().getPluginConfig().getConfig().getString("mysql.server_ip");
    private static final String username = Main.getInstance().getPluginConfig().getConfig().getString("mysql.username");
    private static final String password = Main.getInstance().getPluginConfig().getConfig().getString("mysql.password");
    private static final String database_name = Main.getInstance().getPluginConfig().getConfig().getString("mysql.databasename");
    private static Connection connection;


    static
    {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://" + mysql_ip + "/" + database_name, username, password);
            Main.getInstance().getLogger().info("Connected to DataBase!");
        }catch (Exception e)
        {
            // Main.getInstance().getLogger().info("Unable to connect to database");

            // Unhandled Exception
        }
    }

    public static void setuptable(){
        try {
            if(connection == null)
            {
                Main.getInstance().getLogger().warning("Unable to connect to database. Please, check your database credentials in config.yml");
                Main.getInstance().getLogger().warning("NOTE: Plugin functions will be stopped!");
                return;
            }

        String sqlCreate = "CREATE TABLE IF NOT EXISTS playerdata"
                    + "  (uuid           VARCHAR(255) UNIQUE,"
                    + "   username       VARCHAR(255) NOT NULL default '',"
                    + "   prefix         VARCHAR(255) NOT NULL default '',"
                    + "   suffix         VARCHAR(255) NOT NULL default '',"
                    + "   balance             DOUBLE NOT NULL default 0.00,"
                    + "   player_group          VARCHAR(255) NOT NULL default 0,"
                    + "   PRIMARY KEY  (uuid));";

            Statement statement = connection.createStatement();
            statement.execute(sqlCreate);
        }catch (Exception e)
        {
            e.printStackTrace();
            Main.getInstance().getLogger().info("MineStore was unable to create a table.");
        }
    }

    public static Connection getConnection()
    {
        return connection;
    }
}
