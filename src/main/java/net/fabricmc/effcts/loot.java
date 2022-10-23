package net.fabricmc.effcts;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.*;
import net.minecraft.entity.player.PlayerEntity;

public class loot extends InstantStatusEffect {
    public loot() {
        super(StatusEffectCategory.BENEFICIAL, 0x0b6138);
    }

    @Override//这个是瞬间效果
    public boolean isInstant() {
        return false;//此处代表此效果不为瞬间效果
    }

    @Override//是否是随时可以生效的
    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        return true;
    }

    @Override//此处应是关于药水效果
    public void applyUpdateEffect(LivingEntity entity, int amplifier) {
        if(entity instanceof PlayerEntity player)
            //player.removeStatusEffect(StatusEffects.POISON);//教程中的“删除中毒效果”

            player.addStatusEffect(new StatusEffectInstance(StatusEffects.BAD_OMEN));//此处添加了一个”不详之兆“效果

        super.applyUpdateEffect(entity, amplifier);
    }
}
