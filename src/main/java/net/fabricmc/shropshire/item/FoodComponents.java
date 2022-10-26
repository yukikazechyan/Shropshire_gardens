package net.fabricmc.shropshire.item;

import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.FoodComponent;

public class FoodComponents {
    public  static  final FoodComponent TEST_FOOOD = new FoodComponent.Builder().hunger(6)//6饱食度,
            .statusEffect(new StatusEffectInstance(StatusEffects.LUCK, 6000,1), 1.0f)//食物的效果也在这后面追加.可以追加多个药水效果,最后这个1.0f是指的获得概率，
            .saturationModifier(0.8f).build();//饱和度，带f是浮点数，小数的，这个其实可以回车的喵。注意最后一定要加.build()


}
