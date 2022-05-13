package de.marvelino.marvelinoserver;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import org.bukkit.ChatColor;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;
import java.lang.reflect.Field;
import java.util.UUID;

public class ItemBuilder
{
    private ItemMeta itemMeta;

    public SkullMeta createHead(ItemStack itemStack, String texture)
    {
        SkullMeta skullMeta = (SkullMeta) itemStack.getItemMeta();
        GameProfile profile = new GameProfile(UUID.randomUUID(), null);
        profile.getProperties().put("textures", new Property("textures", texture));

        try
        {
            Field profileField = skullMeta.getClass().getDeclaredField("profile");
            profileField.setAccessible(true);
            profileField.set(skullMeta, profile);
        }
        catch (IllegalArgumentException | NoSuchFieldException | SecurityException | IllegalAccessException e)
        {
            e.printStackTrace();
        }

        return skullMeta;
    }

    public ItemMeta setName(ItemStack itemStack, String name)
    {
        itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(ChatColor.WHITE + name);

        return itemMeta;
    }

    public ItemMeta setName(ItemStack itemStack, String name, ChatColor color)
    {
        itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(color + name);

        return itemMeta;
    }

    public ItemMeta setNameC(ItemStack itemStack, String name)
    {
        itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(ChatColor.WHITE + name + ChatColor.WHITE + " (" + ChatColor.RED + "Deaktiviert" + ChatColor.WHITE + ")");
        itemMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);

        return itemMeta;
    }

    public ItemMeta setNameCP(ItemStack itemStack, String name)
    {
        itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(ChatColor.WHITE + name + ChatColor.WHITE + " (" + ChatColor.RED + "Deaktiviert" + ChatColor.WHITE + ")");
        itemMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        itemMeta.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS);

        return itemMeta;
    }

    public ItemMeta changeNameC(ItemMeta itemMeta, String name, boolean activated)
    {
        this.itemMeta = itemMeta;

        if (activated)
        {
            itemMeta.setDisplayName(ChatColor.WHITE + name + ChatColor.WHITE + " (" + ChatColor.GREEN + "Aktiviert" + ChatColor.WHITE + ")");
        }
        else
        {
            itemMeta.setDisplayName(ChatColor.WHITE + name + ChatColor.WHITE + " (" + ChatColor.RED + "Deaktiviert" + ChatColor.WHITE + ")");
        }

        return itemMeta;
    }
}