package de.marvelino.marvelinoserver.challenges;

import de.marvelino.marvelinoserver.Marvelinoserver;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitScheduler;

public class RandomEffectStack
{
    private boolean activated = false;

    private Player player;
    private boolean active = false;
    private int timeSinceEffect = 0;
    private int timeEffect = (int)(Math.random() * 600 + 1);
    private int absorptionAmp, badOmenAmp, blindnessAmp, conduitPowerAmp, confusionAmp, damageResistanceAmp, dolphinsGraceAmp, fastDiggingAmp, fireResistanceAmp, glowingAmp, heroOfTheVillageAmp, hungerAmp, increaseDamageAmp, invisibilityAmp, jumpAmp, levitationAmp, luckAmp, nightVisionAmp, poisonAmp, regenerationAmp, saturationAmp, slowAmp, slowDiggingAmp, slowFallingAmp, speedAmp, unluckAmp, waterBreathingAmp, weaknessAmp, healAmp, healthBoostAmp, witherAmp, harmAmp;

    public boolean getActivated()
    {
        return activated;
    }

    public String getChallengeName()
    {
        return "RandomEffectStack";
    }

    public void setActivated()
    {
        activated = !activated;
    }

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
            timeEffect();
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

    public void timeEffect()
    {
        if (timeSinceEffect == timeEffect)
        {
            effect();
            timeSinceEffect = 0;
            timeEffect = (int)(Math.random() * 600 + 1);
        }

        timeSinceEffect++;
    }

    public void effect()
    {
        player.sendMessage(ChatColor.GREEN + "Neuer Effekt!");
        int random = (int) (Math.random() * 32 + 1);

        if (random == 1)
        {
            absorptionAmp++;
            player.addPotionEffect(new PotionEffect(PotionEffectType.ABSORPTION, Integer.MAX_VALUE, absorptionAmp - 1));
        }
        else if (random == 2)
        {
            badOmenAmp++;
            player.addPotionEffect(new PotionEffect(PotionEffectType.BAD_OMEN, Integer.MAX_VALUE, badOmenAmp - 1));
        }
        else if (random == 3)
        {
            blindnessAmp++;
            player.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, Integer.MAX_VALUE, blindnessAmp - 1));
        }
        else if (random == 4)
        {
            conduitPowerAmp++;
            player.addPotionEffect(new PotionEffect(PotionEffectType.CONDUIT_POWER, Integer.MAX_VALUE, conduitPowerAmp - 1));
        }
        else if (random == 5)
        {
            confusionAmp++;
            player.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, Integer.MAX_VALUE, confusionAmp - 1));
        }
        else if (random == 6)
        {
            damageResistanceAmp++;
            player.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, Integer.MAX_VALUE, damageResistanceAmp - 1));
        }
        else if (random == 7)
        {
            dolphinsGraceAmp++;
            player.addPotionEffect(new PotionEffect(PotionEffectType.DOLPHINS_GRACE, Integer.MAX_VALUE, dolphinsGraceAmp - 1));
        }
        else if (random == 8)
        {
            fastDiggingAmp++;
            player.addPotionEffect(new PotionEffect(PotionEffectType.FAST_DIGGING, Integer.MAX_VALUE, fastDiggingAmp - 1));
        }
        else if (random == 9)
        {
            fireResistanceAmp++;
            player.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, Integer.MAX_VALUE, fireResistanceAmp - 1));
        }
        else if (random == 10)
        {
            glowingAmp++;
            player.addPotionEffect(new PotionEffect(PotionEffectType.GLOWING, Integer.MAX_VALUE, glowingAmp - 1));
        }
        else if (random == 11)
        {
            heroOfTheVillageAmp++;
            player.addPotionEffect(new PotionEffect(PotionEffectType.HERO_OF_THE_VILLAGE, Integer.MAX_VALUE, heroOfTheVillageAmp - 1));
        }
        else if (random == 12)
        {
            hungerAmp++;
            player.addPotionEffect(new PotionEffect(PotionEffectType.HUNGER, Integer.MAX_VALUE, hungerAmp - 1));
        }
        else if (random == 13)
        {
            increaseDamageAmp++;
            player.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, Integer.MAX_VALUE, increaseDamageAmp - 1));
        }
        else if (random == 14)
        {
            invisibilityAmp++;
            player.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, Integer.MAX_VALUE, invisibilityAmp - 1));
        }
        else if (random == 15)
        {
            jumpAmp++;
            player.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, Integer.MAX_VALUE, jumpAmp - 1));
        }
        else if (random == 16)
        {
            levitationAmp++;
            player.addPotionEffect(new PotionEffect(PotionEffectType.LEVITATION, Integer.MAX_VALUE, levitationAmp - 1));
        }
        else if (random == 17)
        {
            luckAmp++;
            player.addPotionEffect(new PotionEffect(PotionEffectType.LUCK, Integer.MAX_VALUE, luckAmp - 1));
        }
        else if (random == 18)
        {
            nightVisionAmp++;
            player.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, Integer.MAX_VALUE, nightVisionAmp - 1));
        }
        else if (random == 19)
        {
            poisonAmp++;
            player.addPotionEffect(new PotionEffect(PotionEffectType.POISON, Integer.MAX_VALUE, poisonAmp - 1));
        }
        else if (random == 20)
        {
            regenerationAmp++;
            player.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, Integer.MAX_VALUE, regenerationAmp - 1));
        }
        else if (random == 21)
        {
            saturationAmp++;
            player.addPotionEffect(new PotionEffect(PotionEffectType.SATURATION, Integer.MAX_VALUE, saturationAmp - 1));
        }
        else if (random == 22)
        {
            slowAmp++;
            player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, Integer.MAX_VALUE, slowAmp - 1));
        }
        else if (random == 23)
        {
            slowDiggingAmp++;
            player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_DIGGING, Integer.MAX_VALUE, slowDiggingAmp - 1));
        }
        else if (random == 24)
        {
            slowFallingAmp++;
            player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_FALLING, Integer.MAX_VALUE, slowFallingAmp - 1));
        }
        else if (random == 25)
        {
            speedAmp++;
            player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, Integer.MAX_VALUE, speedAmp - 1));
        }
        else if (random == 26)
        {
            unluckAmp++;
            player.addPotionEffect(new PotionEffect(PotionEffectType.UNLUCK, Integer.MAX_VALUE, unluckAmp - 1));
        }
        else if (random == 27)
        {
            waterBreathingAmp++;
            player.addPotionEffect(new PotionEffect(PotionEffectType.WATER_BREATHING, Integer.MAX_VALUE, waterBreathingAmp - 1));
        }
        else if (random == 28)
        {
            weaknessAmp++;
            player.addPotionEffect(new PotionEffect(PotionEffectType.WEAKNESS, Integer.MAX_VALUE, weaknessAmp - 1));
        }
        else if (random == 29)
        {
            healAmp++;
            player.addPotionEffect(new PotionEffect(PotionEffectType.HEAL, Integer.MAX_VALUE, healAmp - 1));
        }
        else if (random == 30)
        {
            healthBoostAmp++;
            player.addPotionEffect(new PotionEffect(PotionEffectType.HEALTH_BOOST, Integer.MAX_VALUE, healthBoostAmp - 1));
        }
        else if (random == 31)
        {
            witherAmp++;
            player.addPotionEffect(new PotionEffect(PotionEffectType.WITHER, Integer.MAX_VALUE, witherAmp - 1));
        }
        else if (random == 32)
        {
            harmAmp++;
            player.addPotionEffect(new PotionEffect(PotionEffectType.HARM, Integer.MAX_VALUE, harmAmp - 1));
        }
        else
        {
            player.sendMessage(ChatColor.RED + "Ungültiger Effekt!");
        }
    }
}