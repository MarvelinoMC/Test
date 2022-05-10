package de.marvelino.marvelinoserver.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CMD_Healthscale implements CommandExecutor
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

        if (!(Integer.parseInt(args[0]) == 0) && Integer.parseInt(args[0]) <= 200)
        {
            player.setMaxHealth(Integer.parseInt(args[0]));
            double health = Double.parseDouble(args[0]) / 2;
            player.sendMessage(ChatColor.GREEN + "Deine maximale Herzensanzahl beträgt nun " + health + " Herzen!");
        }
        else
        {
            player.sendMessage(ChatColor.RED + "Ungültige Anzahl an Herzen!");
        }

        return false;
    }
}