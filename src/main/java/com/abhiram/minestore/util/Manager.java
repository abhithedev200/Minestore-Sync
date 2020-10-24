package com.abhiram.minestore.util;

import com.abhiram.minestore.manager.PlayerManager;

public class Manager {
    public static PlayerManager playerManager;

    public static PlayerManager getPlayerManager()
    {
        if(playerManager == null)
        {
            playerManager = new PlayerManager();
            return playerManager;
        }

        return playerManager;
    }
}
