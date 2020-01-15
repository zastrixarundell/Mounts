package com.github.zastrixarundell.mounts;

import com.github.zastrixarundell.mounts.commands.MountsCommand;
import com.github.zastrixarundell.mounts.entities.Mount;
import com.github.zastrixarundell.mounts.listeners.MountStateListener;
import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Horse;
import org.bukkit.entity.LivingEntity;
import org.bukkit.plugin.java.JavaPlugin;

public class Mounts extends JavaPlugin
{

    private static Mounts plugin;

    public static final String prefix =
            ChatColor.GRAY + "[" + ChatColor.AQUA + "Mounts" + ChatColor.GRAY + "] " + ChatColor.RESET;

    @Override
    public void onEnable()
    {
        saveDefaultConfig();
        plugin = this;
        new MountsCommand(this);
        new MountStateListener(this);
    }

    @Override
    public void onDisable()
    {
        for (World world : getServer().getWorlds())
            for (Entity horse : world.getEntitiesByClasses(Horse.class))
                if(Mount.isMount((LivingEntity) horse))
                    horse.remove();
    }

    public static Mounts getInstance()
    {
        return plugin;
    }

}