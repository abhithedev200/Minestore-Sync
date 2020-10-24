package com.abhiram.minestore.file;

import com.abhiram.minestore.Main;

public class Config extends AbstractFile {

    public Config() {
        super(Main.getInstance(), "config.yml", "", true);
    }
}
