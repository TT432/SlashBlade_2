package mods.flammpfeil.slashblade.entity;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.potion.PotionUtils;

import java.util.List;

public class EntityUtils {
    public static <T extends ProjectileEntity & IShootable> List<EffectInstance> getPotionEffects(T projectile){
        List<EffectInstance> effects = PotionUtils.getEffectsFromTag(projectile.getPersistentData());

        if(effects.isEmpty())
            effects.add(new EffectInstance(Effects.POISON, 1, 1));

        return effects;
    }

    public static <T extends ProjectileEntity & IShootable> void affectEntity(T projectile, LivingEntity focusEntity, List<EffectInstance> effects, double factor){
        for(EffectInstance effectinstance : getPotionEffects(projectile)) {
            Effect effect = effectinstance.getPotion();
            if (effect.isInstant()) {
                effect.affectEntity(projectile, projectile.getShooter(), focusEntity, effectinstance.getAmplifier(), factor);
            } else {
                int duration = (int)(factor * (double)effectinstance.getDuration() + 0.5D);
                if (duration > 0) {
                    focusEntity.addPotionEffect(new EffectInstance(effect, duration, effectinstance.getAmplifier(), effectinstance.isAmbient(), effectinstance.doesShowParticles()));
                }
            }
        }
    }
}
