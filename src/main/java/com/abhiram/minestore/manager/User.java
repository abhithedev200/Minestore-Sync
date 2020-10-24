package com.abhiram.minestore.manager;

public class User {
    private String uuid;
    private String username;
    private String prefix;
    private String suffix;
    private double balance;
    private String group_name;

    public User(String uuid,String username)
    {
        this.uuid = uuid;
        this.username = username;
    }

    public void setPrefix(String prefix)
    {
        this.prefix = prefix;
    }

    public String getPrefix()
    {
        return prefix;
    }


    public void setSuffix(String suffix)
    {
        this.suffix = suffix;
    }

    public String getSuffix()
    {
        return suffix;
    }

    public void setBalance(double balance)
    {
        this.balance = balance;
    }

    public double getBalance()
    {
        return balance;
    }

    public String getUuid()
    {
        return uuid;
    }

    public String getUsername()
    {
        return username;
    }

    public void setGroup_name(String group_name)
    {
        this.group_name = group_name;
    }

    public String getGroupName()
    {
        return group_name;
    }
}
