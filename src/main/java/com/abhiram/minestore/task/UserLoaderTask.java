package com.abhiram.minestore.task;

import com.abhiram.minestore.api.APIManager;
import com.abhiram.minestore.manager.User;
import com.abhiram.minestore.util.Manager;
import com.abhiram.minestore.util.MySQL;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.sql.PreparedStatement;

public class UserLoaderTask implements Runnable {

    public void run()
    {
        for(Player all : Bukkit.getOnlinePlayers())
        {
            User user = Manager.getPlayerManager().getUser(all.getUniqueId().toString());

            if(user == null)
            {
                try {
                    String sql_qury = "INSERT INTO playerdata (uuid,username,prefix,suffix,balance,player_group) VALUES (?,?,?,?,?,?)";
                    PreparedStatement statement = MySQL.getConnection().prepareStatement(sql_qury);
                    statement.setString(1, all.getPlayer().getUniqueId().toString());
                    statement.setString(2,all.getPlayer().getName());
                    statement.setString(3,"null");
                    statement.setString(4,"null");
                    statement.setFloat(5,0);
                    statement.setString(6,APIManager.getChat().getPlayerGroups(all)[0]);


                    statement.execute();
                }catch (Exception ez)
                {
                    ez.printStackTrace();
                }

                User new_user = new User(all.getUniqueId().toString(),all.getName());
                new_user.setBalance(APIManager.getEconomy().getBalance(all));
                new_user.setPrefix(APIManager.getChat().getPlayerPrefix(all));
                new_user.setSuffix(APIManager.getChat().getPlayerSuffix(all));


                String[] group = APIManager.getChat().getPlayerGroups(all);
                if(group.length == 0)
                {
                    return;
                }

                new_user.setGroup_name(group[0]);


                Manager.getPlayerManager().addUser(new_user);

                return;
            }


            user.setBalance(APIManager.getEconomy().getBalance(all));
            user.setPrefix(APIManager.getChat().getPlayerPrefix(all));
            user.setSuffix(APIManager.getChat().getPlayerSuffix(all));

            String[] group = APIManager.getChat().getPlayerGroups(all);

            if(group.length == 0)
            {
                return;
            }
            user.setGroup_name(group[0]);
            return;
        }
    }
}
