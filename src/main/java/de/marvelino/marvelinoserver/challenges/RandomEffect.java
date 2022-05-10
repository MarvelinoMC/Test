package de.marvelino.marvelinoserver.challenges;

import de.marvelino.marvelinoserver.Marvelinoserver;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitScheduler;

public class RandomEffect
{
    private Player player;
    private boolean active = false;
    private PotionEffectType potionEffectType = PotionEffectType.ABSORPTION;
    private int time_since_effect = 0, time_effect = (int)(Math.random() * 300 + 1);

    public void start(Player player, Marvelinoserver marvelinoserver)
    {
        this.player = player;
        active = true;
        player.sendMessage(ChatColor.DARK_AQUA + "Challenge RandomEffect hat gestartet!");
        BukkitScheduler scheduler = marvelinoserver.getServer().getScheduler();
        scheduler.scheduleSyncRepeatingTask(marvelinoserver, () -> {
            if (!active)
            {
                scheduler.cancelTasks(marvelinoserver);
            }
            timeEffect(player);
        }, 0, 20);
    }

    public void stop()
    {
        active = false;
        for(PotionEffect effect : player.getActivePotionEffects())
        {
            player.removePotionEffect(effect.getType());
        }
        player.sendMessage(ChatColor.DARK_AQUA + "Challenge RandomEffect wurde gestoppt!");
    }

    public void timeEffect(Player player)
    {
        if (time_since_effect == time_effect)
        {
            effect(player);
            time_since_effect = 0;
            time_effect = (int)(Math.random() * 300 + 1);
        }

        time_since_effect++;
    }

    public void effect(Player player)
    {
        player.sendMessage(ChatColor.GREEN + "Neuer Effekt!");
        int random = (int) (Math.random() * 32 + 1);
        int randomAmplifier = (int) (Math.random() * 10);
        int randomDuration = (int) (Math.random() * 2400 + 1);

        if (random == 1) {potionEffectType = PotionEffectType.ABSORPTION;}
        else if (random == 2) {potionEffectType = PotionEffectType.BAD_OMEN;}
        else if (random == 3) {potionEffectType = PotionEffectType.BLINDNESS;}
        else if (random == 4) {potionEffectType = PotionEffectType.CONDUIT_POWER;}
        else if (random == 5) {potionEffectType = PotionEffectType.CONFUSION;}
        else if (random == 6) {potionEffectType = PotionEffectType.DAMAGE_RESISTANCE;}
        else if (random == 7) {potionEffectType = PotionEffectType.DOLPHINS_GRACE;}
        else if (random == 8) {potionEffectType = PotionEffectType.FAST_DIGGING;}
        else if (random == 9) {potionEffectType = PotionEffectType.FIRE_RESISTANCE;}
        else if (random == 10) {potionEffectType = PotionEffectType.GLOWING;}
        else if (random == 11) {potionEffectType = PotionEffectType.HERO_OF_THE_VILLAGE;}
        else if (random == 12) {potionEffectType = PotionEffectType.HUNGER;}
        else if (random == 13) {potionEffectType = PotionEffectType.INCREASE_DAMAGE;}
        else if (random == 14) {potionEffectType = PotionEffectType.INVISIBILITY;}
        else if (random == 15) {potionEffectType = PotionEffectType.JUMP;}
        else if (random == 16) {potionEffectType = PotionEffectType.LEVITATION;}
        else if (random == 17) {potionEffectType = PotionEffectType.LUCK;}
        else if (random == 18) {potionEffectType = PotionEffectType.NIGHT_VISION;}
        else if (random == 19) {potionEffectType = PotionEffectType.POISON;}
        else if (random == 20) {potionEffectType = PotionEffectType.REGENERATION;}
        else if (random == 21) {potionEffectType = PotionEffectType.SATURATION;}
        else if (random == 22) {potionEffectType = PotionEffectType.SLOW;}
        else if (random == 23) {potionEffectType = PotionEffectType.SLOW_DIGGING;}
        else if (random == 24) {potionEffectType = PotionEffectType.SLOW_FALLING;}
        else if (random == 25) {potionEffectType = PotionEffectType.SPEED;}
        else if (random == 26) {potionEffectType = PotionEffectType.UNLUCK;}
        else if (random == 27) {potionEffectType = PotionEffectType.WATER_BREATHING;}
        else if (random == 28) {potionEffectType = PotionEffectType.WEAKNESS;}
        else if (random == 29) {potionEffectType = PotionEffectType.HEAL;}
        else if (random == 30) {potionEffectType = PotionEffectType.HEALTH_BOOST;}
        else if (random == 31) {potionEffectType = PotionEffectType.WITHER;}
        else if (random == 32) {potionEffectType = PotionEffectType.HARM;}
        else {player.sendMessage(ChatColor.RED + "Ungültiger Effekt!");}

        player.addPotionEffect(new PotionEffect(potionEffectType, randomDuration, randomAmplifier));
    }
}
