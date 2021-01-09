package com.abhiram.minestore.task;

import com.abhiram.minestore.manager.User;
import com.abhiram.minestore.util.Manager;
import com.abhiram.minestore.util.MySQL;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.sql.PreparedStatement;

public class DataBaseUpdateTask implements Runnable {


    public void run() {
        for(Player all_player : Bukkit.getOnlinePlayers())
        {
            User all = Manager.getPlayerManager().getUser(all_player.getUniqueId().toString());
            if(all != null)
            {
                try {
                    String sql_qury = "UPDATE playerdata SET username = ?,prefix = ?,suffix = ?,balance = ?,player_group = ? WHERE uuid = ?;";
                    PreparedStatement statement = MySQL.getConnection().prepareStatement(sql_qury);

                    statement.setString(1,all.getUsername());
                    statement.setString(2,all.getPrefix());
                    statement.setString(3,all.getSuffix());
                    statement.setDouble(4,all.getBalance());
                    statement.setString(5,all.getGroupName());
                    statement.setString(6,all.getUuid());
                    statement.executeUpdate();
                }catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        }
    }
}
