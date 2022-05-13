package de.marvelino.marvelinoserver;

import de.marvelino.marvelinoserver.challenges.RandomEffect;
import de.marvelino.marvelinoserver.challenges.RandomEffectStack;
import de.marvelino.marvelinoserver.challenges.RandomTeleport;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.*;

public class ChallengePlugIn implements Listener
{
    // Inventorys / Items
    private final ArrayList<Inventory> inventorys = new ArrayList<>();
    private final ArrayList<ItemStack> itemStacks = new ArrayList<>();
    private final ItemBuilder itemBuilder = new ItemBuilder();

    // Challenges
    private boolean challengeStarted = false;
    private final RandomTeleport randomTeleport = new RandomTeleport();
    private final RandomEffect randomEffect = new RandomEffect();
    private final RandomEffectStack randomEffectStack = new RandomEffectStack();

    // Sonstiges
    private Player player;
    private ItemMeta clickedItemMeta;
    private final Marvelinoserver marvelinoserver;

    public ChallengePlugIn(Marvelinoserver marvelinoserver)
    {
        this.marvelinoserver = marvelinoserver;

        // CREATE INVENTORYS AND ITEMS
        // Create Inventorys
        Inventory overview = Bukkit.createInventory(null, 27, "Challenge Settings"); // 0
        Inventory challengeOne = Bukkit.createInventory(null, 27, "Challenge Auswahl (Seite 1)"); // 1
        Inventory challengeTwo = Bukkit.createInventory(null, 27, "Challenge Auswahl (Seite 2)"); // 2

        List<Inventory> inventorysList = Arrays.asList(overview, challengeOne, challengeTwo);
        inventorys.addAll(inventorysList);

        // Create ItemStacks
        ItemStack openCompass = new ItemStack(Material.COMPASS); // 0

        ItemStack wsgp = new ItemStack(Material.WHITE_STAINED_GLASS_PANE); // 1
        ItemStack grassBlock = new ItemStack(Material.GRASS_BLOCK); // 2
        ItemStack limeDye = new ItemStack(Material.LIME_DYE); // 3

        ItemStack randomTeleportItem = new ItemStack(Material.ENDER_PEARL); // 4
        ItemStack randomEffectItem = new ItemStack(Material.GLASS_BOTTLE); // 5
        ItemStack randomEffectStackItem = new ItemStack(Material.POTION); // 6

        ItemStack arrowRight = new ItemStack(Material.PLAYER_HEAD); // 7

        List<ItemStack> itemStacksList = Arrays.asList(openCompass, wsgp, grassBlock, limeDye, randomTeleportItem, randomEffectItem, randomEffectStackItem, arrowRight);
        itemStacks.addAll(itemStacksList);

        // Set ItemMeta
        itemStacks.get(0).setItemMeta(itemBuilder.setName(itemStacks.get(0), "Challenge Menü"));

        itemStacks.get(1).setItemMeta(itemBuilder.setName(itemStacks.get(1), ""));
        itemStacks.get(2).setItemMeta(itemBuilder.setName(itemStacks.get(2), "Challenge Auswahl"));
        itemStacks.get(3).setItemMeta(itemBuilder.setName(itemStacks.get(3), "Challenge starten"));

        itemStacks.get(4).setItemMeta(itemBuilder.setNameC(itemStacks.get(4), "RandomTeleport"));
        itemStacks.get(5).setItemMeta(itemBuilder.setNameC(itemStacks.get(5), "RandomEffect"));
        itemStacks.get(6).setItemMeta(itemBuilder.setNameCP(itemStacks.get(6), "RandomEffectStack"));

        itemStacks.get(7).setItemMeta(itemBuilder.createHead(itemStacks.get(7), "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNGVmMzU2YWQyYWE3YjE2NzhhZWNiODgyOTBlNWZhNWEzNDI3ZTVlNDU2ZmY0MmZiNTE1NjkwYzY3NTE3YjgifX19"));
        itemStacks.get(7).setItemMeta(itemBuilder.setName(itemStacks.get(7), "Nächste Seite", ChatColor.YELLOW));

        // FILL INVENTORYS
        // Overview
        for (int i = 0; i <= 26; i++)
        {
            inventorys.get(0).setItem(i, itemStacks.get(1));
        }

        inventorys.get(0).setItem(10, itemStacks.get(2));
        inventorys.get(0).setItem(16, itemStacks.get(3));

        // Challenge One
        for (int i = 0; i <= 26; i++)
        {
            inventorys.get(1).setItem(i, itemStacks.get(1));
        }

        inventorys.get(1).setItem(10, itemStacks.get(4));
        inventorys.get(1).setItem(11, itemStacks.get(5));
        inventorys.get(1).setItem(12, itemStacks.get(6));
        inventorys.get(1).setItem(16, itemStacks.get(7));

        // Challenge Two
        for (int i = 0; i <= 26; i++)
        {
            inventorys.get(2).setItem(i, itemStacks.get(1));
        }
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event)
    {
        player = event.getPlayer();
        event.setJoinMessage(ChatColor.YELLOW + player.getName() + " ist auf den Server gehüpft!");

        if (!(challengeStarted))
        {
            player.setGameMode(GameMode.CREATIVE);
            if (!(player.getInventory().contains(itemStacks.get(0))))
            {
                player.getInventory().addItem(itemStacks.get(0));
            }
        }
    }

    @EventHandler
    public void onLeft(PlayerQuitEvent event)
    {
        player = event.getPlayer();
        event.setQuitMessage(ChatColor.YELLOW + player.getName() + " verabschiedet sich!");
    }

    @EventHandler
    public void onInteract(PlayerInteractEvent event)
    {
        if (event.getItem() == null) return;

        ItemStack itemStack = event.getItem();
        player = event.getPlayer();

        if (itemStack.equals(itemStacks.get(0)))
        {
            player.openInventory(inventorys.get(0));
        }
    }

    @EventHandler
    public void inventoryClickEvent(InventoryClickEvent event)
    {
        ItemStack clicked = event.getCurrentItem();
        Inventory clickedInventory = event.getClickedInventory();
        clickedItemMeta = clicked.getItemMeta();

        if (!(challengeStarted))
        {
            event.setCancelled(true);
        }

        if (clickedInventory == inventorys.get(0))
        {
            event.setCancelled(true);

            if (clicked.equals(itemStacks.get(2)))
            {
                player.openInventory(inventorys.get(1));
            }
            else if (clicked.equals(itemStacks.get(3)))
            {
                player.closeInventory();
                player.getInventory().clear();
                player.setGameMode(GameMode.SURVIVAL);

                if (randomTeleport.getActivated()) {randomTeleport.start(player, marvelinoserver);}
                if (randomEffect.getActivated()) {randomEffect.start(player, marvelinoserver);}
                if (randomEffectStack.getActivated()) {randomEffectStack.start(player, marvelinoserver);}

                challengeStarted = true;
            }
        }
        else if (event.getInventory() == inventorys.get(1))
        {
            event.setCancelled(true);

            if (!(clicked.equals(itemStacks.get(1))) && !(clicked.getType() == Material.PLAYER_HEAD))
            {
                if (clickedItemMeta.hasEnchants())
                {
                    clickedItemMeta.removeEnchant(Enchantment.MENDING);
                }
                else
                {
                    clickedItemMeta.addEnchant(Enchantment.MENDING, 1, true);
                }
            }

            if (clicked.equals(itemStacks.get(4)))
            {
                randomTeleport.setActivated();
                clickedItemMeta = itemBuilder.changeNameC(clickedItemMeta, randomTeleport.getChallengeName(), randomTeleport.getActivated());
                itemStacks.get(4).setItemMeta(clickedItemMeta);
            }
            else if (clicked.equals(itemStacks.get(5)))
            {
                randomEffect.setActivated();
                clickedItemMeta = itemBuilder.changeNameC(clickedItemMeta, randomEffect.getChallengeName(), randomEffect.getActivated());
                itemStacks.get(5).setItemMeta(clickedItemMeta);
            }
            else if (clicked.equals(itemStacks.get(6)))
            {
                randomEffectStack.setActivated();
                clickedItemMeta = itemBuilder.changeNameC(clickedItemMeta, randomEffectStack.getChallengeName(), randomEffectStack.getActivated());
                itemStacks.get(6).setItemMeta(clickedItemMeta);
            }
            else if (clickedItemMeta.getDisplayName().equals(itemStacks.get(7).getItemMeta().getDisplayName()))
            {
                player.openInventory(inventorys.get(2));
            }
        }

        clicked.setItemMeta(clickedItemMeta);
    }
}
//TODO: Neue Sortierung zeigen