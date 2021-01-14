package com.abhiram.minestore;

import com.abhiram.minestore.api.APIManager;
import com.abhiram.minestore.file.Config;
import com.abhiram.minestore.task.DataBaseUpdateTask;
import com.abhiram.minestore.task.UserLoaderTask;
import com.abhiram.minestore.util.MySQL;
import org.bstats.bukkit.Metrics;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
    private static Main instance;
    private Config config;

    @Override
    public void onEnable()
    {
        instance = this;
        config = new Config();
        APIManager.vaulthook();
        MySQL.setuptable();
        initTask();

        // Start Bstats
        new Metrics(this,9729);
    }

    public Config getPluginConfig()
    {
        return config;
    }
    private void initTask()
    {
        getLogger().info("Starting Plugin Tasks....");
        Bukkit.getScheduler().runTaskTimer(this,new UserLoaderTask(),2,2);
        Bukkit.getScheduler().runTaskTimer(this,new DataBaseUpdateTask(),30 * 20,30 * 20);
    }
    public static Main getInstance()
    {
        return instance;
    }
}
