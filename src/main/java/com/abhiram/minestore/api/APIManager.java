package com.abhiram.minestore.api;

import com.abhiram.minestore.Main;
import net.milkbowl.vault.chat.Chat;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.plugin.RegisteredServiceProvider;

public class APIManager {
    private static Economy economy;
    private static Chat chat;


    public static void vaulthook()
    {
        if(Bukkit.getPluginManager().getPlugin("Vault") == null)
        {
            Main.getInstance().getLogger().info("MINESTORE | vault not found disabling plugin....");
            Bukkit.getPluginManager().disablePlugin(Main.getInstance());
            return;
        }

        RegisteredServiceProvider<Economy> esp = Main.getInstance().getServer().getServicesManager().getRegistration(Economy.class);
        RegisteredServiceProvider<Chat> csp = Main.getInstance().getServer().getServicesManager().getRegistration(Chat.class);

        if(esp == null)
        {
            Main.getInstance().getLogger().warning("Economy was not found. MineStore-Sync will just skip this...");
        }else
        {
            economy = esp.getProvider();
        }

        if(csp == null)
        {
            Main.getInstance().getLogger().warning("Hmm, something went wrong... Please, report this issue to Official MineStore Discord server!");
        }else
        {
            chat = csp.getProvider();
        }
    }

    public static Economy getEconomy()
    {
        return economy;
    }

    public static Chat getChat()
    {
        return chat;
    }
}
