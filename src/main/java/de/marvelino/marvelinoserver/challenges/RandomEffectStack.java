package de.marvelino.marvelinoserver.challenges;

import de.marvelino.marvelinoserver.Marvelinoserver;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitScheduler;

public class RandomEffectStack
{
    private Player player;
    private boolean active = false;
    private int time_since_effect = 0;
    private int time_effect = (int)(Math.random() * 600 + 1);
    private int absorption_amp, bad_omen_amp, blindness_amp, conduit_power_amp, confusion_amp, damage_resistance_amp, dolphins_grace_amp, fast_digging_amp, fire_resistance_amp, glowing_amp, hero_of_the_village_amp, hunger_amp, increase_damage_amp, invisibility_amp, jump_amp, levitation_amp, luck_amp, night_vision_amp, poison_amp, regeneration_amp, saturation_amp, slow_amp, slow_digging_amp, slow_falling_amp, speed_amp, unluck_amp, water_breathing_amp, weakness_amp, heal_amp, health_boost_amp, wither_amp, harm_amp;

    public void start(Player player, Marvelinoserver marvelinoserver)
    {
        this.player = player;
        active = true;
        player.sendMessage(ChatColor.DARK_AQUA + "Challenge RandomEffectStack hat gestartet!");
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
        player.sendMessage(ChatColor.DARK_AQUA + "Challenge RandomEffectStack wurde gestoppt!");
    }

    public void timeEffect(Player player)
    {
        if (time_since_effect == time_effect)
        {
            effect(player);
            time_since_effect = 0;
            time_effect = (int)(Math.random() * 600 + 1);
        }

        time_since_effect++;
    }

    public void effect(Player player)
    {
        player.sendMessage(ChatColor.GREEN + "Neuer Effekt!");
        int random = (int) (Math.random() * 32 + 1);

        if (random == 1)
        {
            absorption_amp++;
            player.addPotionEffect(new PotionEffect(PotionEffectType.ABSORPTION, Integer.MAX_VALUE, absorption_amp - 1));
        }
        else if (random == 2)
        {
            bad_omen_amp++;
            player.addPotionEffect(new PotionEffect(PotionEffectType.BAD_OMEN, Integer.MAX_VALUE, bad_omen_amp - 1));
        }
        else if (random == 3)
        {
            blindness_amp++;
            player.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, Integer.MAX_VALUE, blindness_amp - 1));
        }
        else if (random == 4)
        {
            conduit_power_amp++;
            player.addPotionEffect(new PotionEffect(PotionEffectType.CONDUIT_POWER, Integer.MAX_VALUE, conduit_power_amp - 1));
        }
        else if (random == 5)
        {
            confusion_amp++;
            player.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, Integer.MAX_VALUE, confusion_amp - 1));
        }
        else if (random == 6)
        {
            damage_resistance_amp++;
            player.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, Integer.MAX_VALUE, damage_resistance_amp - 1));
        }
        else if (random == 7)
        {
            dolphins_grace_amp++;
            player.addPotionEffect(new PotionEffect(PotionEffectType.DOLPHINS_GRACE, Integer.MAX_VALUE, dolphins_grace_amp - 1));
        }
        else if (random == 8)
        {
            fast_digging_amp++;
            player.addPotionEffect(new PotionEffect(PotionEffectType.FAST_DIGGING, Integer.MAX_VALUE, fast_digging_amp - 1));
        }
        else if (random == 9)
        {
            fire_resistance_amp++;
            player.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, Integer.MAX_VALUE, fire_resistance_amp - 1));
        }
        else if (random == 10)
        {
            glowing_amp++;
            player.addPotionEffect(new PotionEffect(PotionEffectType.GLOWING, Integer.MAX_VALUE, glowing_amp - 1));
        }
        else if (random == 11)
        {
            hero_of_the_village_amp++;
            player.addPotionEffect(new PotionEffect(PotionEffectType.HERO_OF_THE_VILLAGE, Integer.MAX_VALUE, hero_of_the_village_amp - 1));
        }
        else if (random == 12)
        {
            hunger_amp++;
            player.addPotionEffect(new PotionEffect(PotionEffectType.HUNGER, Integer.MAX_VALUE, hunger_amp - 1));
        }
        else if (random == 13)
        {
            increase_damage_amp++;
            player.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, Integer.MAX_VALUE, increase_damage_amp - 1));
        }
        else if (random == 14)
        {
            invisibility_amp++;
            player.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, Integer.MAX_VALUE, invisibility_amp - 1));
        }
        else if (random == 15)
        {
            jump_amp++;
            player.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, Integer.MAX_VALUE, jump_amp - 1));
        }
        else if (random == 16)
        {
            levitation_amp++;
            player.addPotionEffect(new PotionEffect(PotionEffectType.LEVITATION, Integer.MAX_VALUE, levitation_amp - 1));
        }
        else if (random == 17)
        {
            luck_amp++;
            player.addPotionEffect(new PotionEffect(PotionEffectType.LUCK, Integer.MAX_VALUE, luck_amp - 1));
        }
        else if (random == 18)
        {
            night_vision_amp++;
            player.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, Integer.MAX_VALUE, night_vision_amp - 1));
        }
        else if (random == 19)
        {
            poison_amp++;
            player.addPotionEffect(new PotionEffect(PotionEffectType.POISON, Integer.MAX_VALUE, poison_amp - 1));
        }
        else if (random == 20)
        {
            regeneration_amp++;
            player.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, Integer.MAX_VALUE, regeneration_amp - 1));
        }
        else if (random == 21)
        {
            saturation_amp++;
            player.addPotionEffect(new PotionEffect(PotionEffectType.SATURATION, Integer.MAX_VALUE, saturation_amp - 1));
        }
        else if (random == 22)
        {
            slow_amp++;
            player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, Integer.MAX_VALUE, slow_amp - 1));
        }
        else if (random == 23)
        {
            slow_digging_amp++;
            player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_DIGGING, Integer.MAX_VALUE, slow_digging_amp - 1));
        }
        else if (random == 24)
        {
            slow_falling_amp++;
            player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_FALLING, Integer.MAX_VALUE, slow_falling_amp - 1));
        }
        else if (random == 25)
        {
            speed_amp++;
            player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, Integer.MAX_VALUE, speed_amp - 1));
        }
        else if (random == 26)
        {
            unluck_amp++;
            player.addPotionEffect(new PotionEffect(PotionEffectType.UNLUCK, Integer.MAX_VALUE, unluck_amp - 1));
        }
        else if (random == 27)
        {
            water_breathing_amp++;
            player.addPotionEffect(new PotionEffect(PotionEffectType.WATER_BREATHING, Integer.MAX_VALUE, water_breathing_amp - 1));
        }
        else if (random == 28)
        {
            weakness_amp++;
            player.addPotionEffect(new PotionEffect(PotionEffectType.WEAKNESS, Integer.MAX_VALUE, weakness_amp - 1));
        }
        else if (random == 29)
        {
            heal_amp++;
            player.addPotionEffect(new PotionEffect(PotionEffectType.HEAL, Integer.MAX_VALUE, heal_amp - 1));
        }
        else if (random == 30)
        {
            health_boost_amp++;
            player.addPotionEffect(new PotionEffect(PotionEffectType.HEALTH_BOOST, Integer.MAX_VALUE, health_boost_amp - 1));
        }
        else if (random == 31)
        {
            wither_amp++;
            player.addPotionEffect(new PotionEffect(PotionEffectType.WITHER, Integer.MAX_VALUE, wither_amp - 1));
        }
        else if (random == 32)
        {
            harm_amp++;
            player.addPotionEffect(new PotionEffect(PotionEffectType.HARM, Integer.MAX_VALUE, harm_amp - 1));
        }
        else
        {
            player.sendMessage(ChatColor.RED + "Ungültiger Effekt!");
        }
    }
}