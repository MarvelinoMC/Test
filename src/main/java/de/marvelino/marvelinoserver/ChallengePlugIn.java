package de.marvelino.marvelinoserver;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import de.marvelino.marvelinoserver.challenges.RandomEffect;
import de.marvelino.marvelinoserver.challenges.RandomEffectStack;
import de.marvelino.marvelinoserver.challenges.RandomTeleport;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.block.Skull;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.lang.reflect.Field;
import java.util.UUID;

public class ChallengePlugIn implements Listener
{
    // Inventorys
    private final Inventory overview = Bukkit.createInventory(null, 27, "Challenge Settings");
    private final Inventory challengesOne = Bukkit.createInventory(null, 27, "Challenge Auswahl");

    // Challenges
    private final boolean[] challengesActivated = new boolean[3];

    private final RandomTeleport randomTeleport = new RandomTeleport();
    private final RandomEffect randomEffect = new RandomEffect();
    private final RandomEffectStack randomEffectStack = new RandomEffectStack();

    // Sonstiges
    private Player player;
    private final Marvelinoserver marvelinoserver;
    private ItemMeta itemMeta;
    private SkullMeta skullMeta;
    private boolean challengeison = false;

    public ChallengePlugIn(Marvelinoserver marvelinoserver)
    {
        this.marvelinoserver = marvelinoserver;

        // Creating ItemStacks
        ItemStack wsgp = new ItemStack(Material.WHITE_STAINED_GLASS_PANE);
        ItemStack grass_block = new ItemStack(Material.GRASS_BLOCK);
        ItemStack lime_dye = new ItemStack(Material.LIME_DYE);
        ItemStack red_dye = new ItemStack(Material.RED_DYE);

        ItemStack randomTeleportItem = new ItemStack(Material.ENDER_PEARL);
        ItemStack randomEffectItem = new ItemStack(Material.GLASS_BOTTLE);
        ItemStack randomEffectStackItem = new ItemStack(Material.POTION);

        // Creating Heads
        /**
        ItemStack arrow_right = new ItemStack(Material.PLAYER_HEAD);
        skullMeta = (SkullMeta) arrow_right.getItemMeta();
        GameProfile profile = new GameProfile(UUID.randomUUID(), null);
        profile.getProperties().put("textures", new Property("textures", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNGVmMzU2YWQyYWE3YjE2NzhhZWNiODgyOTBlNWZhNWEzNDI3ZTVlNDU2ZmY0MmZiNTE1NjkwYzY3NTE3YjgifX19"));

        try {
            Field profileField = skullMeta.getClass().getDeclaredField("profile");
            profileField.setAccessible(true);
            profileField.set(skullMeta, profile);
        } catch (IllegalArgumentException | NoSuchFieldException | SecurityException | IllegalAccessException e) {
            e.printStackTrace();
        }

        arrow_right.setItemMeta(skullMeta);
         **/

        // Item Meta
        itemMeta = grass_block.getItemMeta();
        itemMeta.setDisplayName(ChatColor.WHITE + "Challenge Auswahl");
        grass_block.setItemMeta(itemMeta);

        itemMeta = wsgp.getItemMeta();
        itemMeta.setDisplayName(ChatColor.WHITE + "");
        wsgp.setItemMeta(itemMeta);

        itemMeta = red_dye.getItemMeta();
        itemMeta.setDisplayName(ChatColor.WHITE + "Challenge stoppen");
        red_dye.setItemMeta(itemMeta);

        itemMeta = lime_dye.getItemMeta();
        itemMeta.setDisplayName(ChatColor.WHITE + "Challenge starten");
        lime_dye.setItemMeta(itemMeta);

        itemMeta = randomTeleportItem.getItemMeta();
        itemMeta.setDisplayName(ChatColor.WHITE + "RandomTeleport (" + ChatColor.RED + "Deaktiviert" + ChatColor.WHITE + ")");
        itemMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        randomTeleportItem.setItemMeta(itemMeta);

        itemMeta = randomEffectItem.getItemMeta();
        itemMeta.setDisplayName(ChatColor.WHITE + "RandomEffect (" + ChatColor.RED + "Deaktiviert" + ChatColor.WHITE + ")");
        itemMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        randomEffectItem.setItemMeta(itemMeta);

        itemMeta = randomEffectStackItem.getItemMeta();
        itemMeta.setDisplayName(ChatColor.WHITE + "RandomEffectStack (" + ChatColor.RED + "Deaktiviert" + ChatColor.WHITE + ")");
        itemMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        itemMeta.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS);
        randomEffectStackItem.setItemMeta(itemMeta);

        // Overview
        for (int i = 0; i <= 26; i++)
        {
            overview.setItem(i, wsgp);
        }

        overview.setItem(10, grass_block);
        overview.setItem(13, red_dye);
        overview.setItem(16, lime_dye);

        // Challenge One
        for (int i = 0; i <= 26; i++)
        {
            challengesOne.setItem(i, wsgp);
        }

        challengesOne.setItem(10, randomTeleportItem);
        challengesOne.setItem(11, randomEffectItem);
        challengesOne.setItem(12, randomEffectStackItem);
        //challengesOne.setItem(16, arrow_right);
    }

    @EventHandler
    public void onInteract(PlayerInteractEvent event)
    {
        if (event.getItem() == null) return;

        ItemStack itemStack = event.getItem();
        player = event.getPlayer();

        if (itemStack.getType() == Material.COMPASS)
        {
            player.openInventory(overview);
        }
    }

    @EventHandler
    public void inventoryClickEvent(InventoryClickEvent event)
    {
        ItemStack clicked = event.getCurrentItem();
        Inventory clickedInventory = event.getClickedInventory();
        Material clickedMaterial = clicked.getType();
        ItemMeta clickedMeta = clicked.getItemMeta();

        if (!(challengeison))
        {
            event.setCancelled(true);
        }

        if (clickedInventory == overview)
        {
            event.setCancelled(true);

            if (clickedMaterial == Material.GRASS_BLOCK)
            {
                player.openInventory(challengesOne);
            }
            else if (clickedMaterial == Material.LIME_DYE)
            {
                player.closeInventory();
                player.getInventory().clear();

                player.setGameMode(GameMode.SURVIVAL);

                if (challengesActivated[0]) {randomTeleport.start(player, marvelinoserver);}
                if (challengesActivated[1]) {randomEffect.start(player, marvelinoserver);}
                if (challengesActivated[2]) {randomEffectStack.start(player, marvelinoserver);}

                challengeison = true;
            }
            else if (clickedMaterial == Material.RED_DYE)
            {
                ItemStack compass = new ItemStack(Material.COMPASS);
                player.closeInventory();
                player.getInventory().addItem(compass);

                player.setGameMode(GameMode.CREATIVE);

                if (challengesActivated[0]) {randomTeleport.stop();}
                if (challengesActivated[1]) {randomEffect.stop();}
                if (challengesActivated[2]) {randomEffectStack.stop();}

                challengeison = false;
            }
        }
        else if (event.getInventory() == challengesOne)
        {
            event.setCancelled(true);

            if (!(clickedMaterial == Material.WHITE_STAINED_GLASS_PANE))
            {
                if (clickedMeta.hasEnchants())
                {
                    clickedMeta.removeEnchant(Enchantment.MENDING);
                }
                else
                {
                    clickedMeta.addEnchant(Enchantment.MENDING, 1, true);
                }
            }

            if (clickedMaterial == Material.ENDER_PEARL)
            {
                if (challengesActivated[0])
                {
                    challengesActivated[0] = false;
                    clickedMeta.setDisplayName(ChatColor.WHITE + "RandomTeleport (" + ChatColor.RED + "Deaktiviert" + ChatColor.WHITE + ")");
                }
                else
                {
                    challengesActivated[0] = true;
                    clickedMeta.setDisplayName(ChatColor.WHITE + "RandomTeleport (" + ChatColor.GREEN + "Aktiviert" + ChatColor.WHITE + ")");
                }
            }
            if (clickedMaterial == Material.GLASS_BOTTLE)
            {
                if (challengesActivated[1])
                {
                    clickedMeta.setDisplayName(ChatColor.WHITE + "RandomEffect (" + ChatColor.RED + "Deaktiviert" + ChatColor.WHITE + ")");
                    challengesActivated[1] = false;
                }
                else
                {
                    clickedMeta.setDisplayName(ChatColor.WHITE + "RandomEffect (" + ChatColor.GREEN + "Aktiviert" + ChatColor.WHITE + ")");
                    challengesActivated[1] = true;
                }
            }
            if (clickedMaterial == Material.POTION)
            {
                if (challengesActivated[2])
                {
                    clickedMeta.setDisplayName(ChatColor.WHITE + "RandomEffectStack (" + ChatColor.RED + "Deaktiviert" + ChatColor.WHITE + ")");
                    challengesActivated[2] = false;
                }
                else
                {
                    clickedMeta.setDisplayName(ChatColor.WHITE + "RandomEffectStack (" + ChatColor.GREEN + "Aktiviert" + ChatColor.WHITE + ")");
                    challengesActivated[2] = true;
                }
            }
        }

        clicked.setItemMeta(clickedMeta);
    }
}