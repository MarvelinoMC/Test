package de.marvelino.marvelinoserver;

import de.marvelino.marvelinoserver.commands.*;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import java.util.Objects;

public final class Marvelinoserver extends JavaPlugin
{
    @Override
    public void onEnable()
    {
        ActivateCommands();
        ActivateEvents();
    }

    @Override
    public void onDisable()
    {

    }

    public void ActivateCommands()
    {
        Objects.requireNonNull(getCommand("gm")).setExecutor(new CMD_Gamemode());
        Objects.requireNonNull(getCommand("hsc")).setExecutor(new CMD_Healthscale());
    }

    public void ActivateEvents()
    {
        PluginManager pluginManager = Bukkit.getPluginManager();
        pluginManager.registerEvents(new ChallengePlugIn(this), this);
    }
}