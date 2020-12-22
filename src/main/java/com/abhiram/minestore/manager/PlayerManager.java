package com.abhiram.minestore.manager;

import com.abhiram.minestore.util.MySQL;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class PlayerManager {
    private ArrayList<User> users = new ArrayList<User>();


    public PlayerManager()
    {
        loadUsers();
    }


    public void addUser(User user)
    {
        users.add(user);
    }

    public User getUser(String uuid)
    {
        for(User user : users)
        {
            if(user.getUuid().equalsIgnoreCase(uuid))
            {
                return user;
            }
        }

        return null;
    }

    private void loadUsers()
    {
        try {
            Statement stmt = MySQL.getConnection().createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM playerdata");

            while (rs.next())
            {
                User user = new User(rs.getString("uuid"),rs.getString("username"));

                user.setPrefix(rs.getString("prefix"));
                user.setSuffix(rs.getString("suffix"));
                user.setBalance(rs.getDouble("balance"));
                user.setGroup_name(rs.getString("group"));
                addUser(user);
            }
        }catch (Exception e)
        {
            // UnHandled Exception.
        }
    }
    public ArrayList<User> getUsers()
    {
        return this.users;
    }
}
