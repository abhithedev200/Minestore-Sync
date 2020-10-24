package com.abhiram.minestore.file;

import com.abhiram.minestore.Main;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class AbstractFile{
    private Main plugin;
    private File file;
    protected FileConfiguration configuration;
    protected Boolean save;


    public AbstractFile(Main main,String filename,String datafolder,Boolean save)
    {
        this.plugin = main;
        File file1 = new File(main.getDataFolder() + datafolder);

        if(!file1.exists())
        {
            file1.mkdirs();
        }

        file = new File(file1,filename);
        if(!file.exists())
        {
            if(save)
            {
                Main.getInstance().saveResource(filename,false);
                configuration = YamlConfiguration.loadConfiguration(file);
                return;
            }

            try
            {
                file.createNewFile();
            }catch (Exception exp)
            {
                exp.printStackTrace();
            }
        }

        configuration = YamlConfiguration.loadConfiguration(file);
    }

    public void save(){
        try{
            configuration.save(file);
        }catch(IOException e){
            Main.getInstance().getLogger().info("Unable to save files");
        }
    }
    public FileConfiguration getConfig(){
        return configuration;
    }

    public void reload(){
        configuration = YamlConfiguration.loadConfiguration(file);
    }

    public void delete() {
        file.delete();
    }
}
