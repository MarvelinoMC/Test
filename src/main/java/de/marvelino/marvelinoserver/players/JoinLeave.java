package de.marvelino.marvelinoserver.players;

import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;

public class JoinLeave implements Listener
{
    @EventHandler
    public void onJoin(PlayerJoinEvent event)
    {
        Player player = event.getPlayer();
        event.setJoinMessage(ChatColor.YELLOW + player.getName() + " ist auf den Server gehüpft!");
        player.setGameMode(GameMode.CREATIVE);
        ItemStack compass = new ItemStack(Material.COMPASS);
        player.getInventory().addItem(compass);
    }

    @EventHandler
    public void onLeft(PlayerQuitEvent event)
    {
        Player player = event.getPlayer();
        event.setQuitMessage(ChatColor.YELLOW + player.getName() + " verabschiedet sich!");
    }
}
