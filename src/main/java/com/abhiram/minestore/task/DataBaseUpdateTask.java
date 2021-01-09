package com.abhiram.minestore.task;

import com.abhiram.minestore.manager.User;
import com.abhiram.minestore.util.Manager;
import com.abhiram.minestore.util.MySQL;

import java.sql.PreparedStatement;

public class DataBaseUpdateTask implements Runnable {


    public void run() {
        for(User all : Manager.getPlayerManager().getUsers())
        {
            try {
                String sql_qury = "INSERT IGNORE INTO playerdata VALUES (?,?,?,?,?,?);";
                PreparedStatement statement = MySQL.getConnection().prepareStatement(sql_qury);

                statement.setString(1,all.getUuid());
                statement.setString(2,all.getUsername());
                statement.setString(3,all.getSuffix());
                statement.setString(4,all.getSuffix());
                statement.setDouble(5,all.getBalance());
                statement.setString(6,all.getGroupName());
                statement.executeUpdate();
            }catch (Exception e)
            {
                e.printStackTrace();
            }
        }
    }
}
