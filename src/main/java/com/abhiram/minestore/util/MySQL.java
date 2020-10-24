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
        }catch (Exception e)
        {
            // Main.getInstance().getLogger().info("Unable to connect to Database");

            // Unhandled Exception
        }
    }

    public static void setuptable(){
        try {
            if(connection == null)
            {
                Main.getInstance().getLogger().warning("Unable to connect to Database please check your database information in config.yml");
                Main.getInstance().getLogger().warning("NOTE: plugin functions will be broken!");
                return;
            }

            String sqlCreate = "CREATE TABLE IF NOT EXISTS " + "playerdata"
                    + "  (uuid           VARCHAR(90) UNIQUE,"
                    + "   username       VARCHAR(90),"
                    + "   prefix         VARCHAR(90),"
                    + "   suffix         VARCHAR(90),"
                    + "   balance        DOUBLE)";

            Statement statement = connection.createStatement();
            statement.execute(sqlCreate);
        }catch (Exception e)
        {
            e.printStackTrace();
            Main.getInstance().getLogger().info("Unable to create table");
        }
    }

    public static Connection getConnection()
    {
        return connection;
    }
}
