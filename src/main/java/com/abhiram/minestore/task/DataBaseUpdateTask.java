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
                String sql_qury = "UPDATE playerdata SET prefix = ?, suffix = ?, balance = ?, player_group = ? WHERE uuid=?";
                PreparedStatement statement = MySQL.getConnection().prepareStatement(sql_qury);

                statement.setString(1, all.getPrefix());
                statement.setString(2, all.getSuffix());
                statement.setDouble(3, all.getBalance());
                statement.setString(4,all.getGroupName());
                statement.setString(5, all.getUuid());
                statement.executeUpdate();
            }catch (Exception e)
            {
                e.printStackTrace();
            }
        }
    }
}
