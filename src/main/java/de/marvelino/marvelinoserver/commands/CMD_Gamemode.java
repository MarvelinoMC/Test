package de.marvelino.marvelinoserver.commands;

import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CMD_Gamemode implements CommandExecutor
{
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
    {
        if (!(sender instanceof Player))
        {
            System.out.println(ChatColor.RED + "Nur Spieler können diesen Command ausführen!");
            return false;
        }

        Player player = (Player) sender;

        if (args[0].equalsIgnoreCase("0"))
        {
            player.setGameMode(GameMode.SURVIVAL);
            player.sendMessage("Du bist nun im Gamemode Survival!");
        }
        else if (args[0].equalsIgnoreCase("1"))
        {
            player.setGameMode(GameMode.CREATIVE);
            player.sendMessage("Du bist nun im Gamemode Creative!");
        }
        else if (args[0].equalsIgnoreCase("2"))
        {
            player.setGameMode(GameMode.ADVENTURE);
            player.sendMessage("Du bist nun im Gamemode Adventure!");
        }
        else if (args[0].equalsIgnoreCase("3"))
        {
            player.setGameMode(GameMode.SPECTATOR);
            player.sendMessage("Du bist nun im Gamemode Spectator!");
        }
        else
        {
            player.sendMessage(ChatColor.RED + "Syntax des Commands: /gm <0|1|2|3>");
        }
        return false;
    }
}