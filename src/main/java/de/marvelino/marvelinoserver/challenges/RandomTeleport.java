package de.marvelino.marvelinoserver.challenges;

import de.marvelino.marvelinoserver.Marvelinoserver;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitScheduler;

public class RandomTeleport
{
    private Player player;
    private boolean active = false;
    private int time_since_teleport = 0, time_teleport = (int)(Math.random() * 600 + 1);

    public void start(Player player, Marvelinoserver marvelinoserver)
    {
        this.player = player;
        active = true;
        player.sendMessage(ChatColor.DARK_AQUA + "Challenge RandomTeleport hat gestartet!");
        BukkitScheduler scheduler = marvelinoserver.getServer().getScheduler();
        scheduler.scheduleSyncRepeatingTask(marvelinoserver, () -> {
            if (!active)
            {
                scheduler.cancelTasks(marvelinoserver);
            }
            timeTeleport();
        }, 0, 20);
    }

    public void stop()
    {
        active = false;
        player.sendMessage(ChatColor.DARK_AQUA + "Challenge RandomEffectStack wurde gestoppt!");
    }

    public void timeTeleport()
    {
        if (time_since_teleport == time_teleport)
        {
            teleport();
            time_since_teleport = 0;
            time_teleport = (int)(Math.random() * 600 + 1);
        }

        time_since_teleport++;
    }

    public void teleport()
    {
        player.sendMessage(ChatColor.GREEN + "Teleport...");

        int RandomX = (int) (Math.random() * 50 + 1);
        int RandomXz = (int) (Math.random() * 2);
        int RandomY = (int) (Math.random() * 50 + 1);
        int RandomYz = (int) (Math.random() * 2);
        int RandomZ = (int) (Math.random() * 50 + 1);
        int RandomZz = (int) (Math.random() * 2);

        new Location(Bukkit.getWorld("world"), 0, 0, 0, 0, 0);
        Location location;
        location = player.getLocation();

        if (RandomXz == 0)
        {
            location.setX(location.getX() + RandomX);
        }
        else
        {
            location.setX(location.getX() - RandomX);
        }

        if (RandomYz == 0)
        {
            location.setY(location.getY() + RandomY);
        }
        else
        {
            location.setY(location.getY() - RandomY);
            if (location.getY() < -60)
            {
                while (location.getY() < -60)
                {
                    location.setY(location.getY() + 1);
                }
            }
        }

        if (RandomZz == 0)
        {
            location.setZ(location.getZ() + RandomZ);
        }
        else
        {
            location.setZ(location.getZ() - RandomZ);
        }

        player.teleport(location);
    }
}