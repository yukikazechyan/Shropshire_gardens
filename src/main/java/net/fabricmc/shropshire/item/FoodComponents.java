package net.fabricmc.shropshire.item;

import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.FoodComponent;

public class FoodComponents {
    public  static  final FoodComponent TEST_FOOOD = new FoodComponent.Builder().hunger(6)//6饱食度,
            .statusEffect(new StatusEffectInstance(StatusEffects.LUCK, 6000,1), 1.0f)//食物的效果也在这后面追加.可以追加多个药水效果,最后这个1.0f是指的获得概率，
            .saturationModifier(0.8f).build();//饱和度，带f是浮点数，小数的，这个其实可以回车的喵。注意最后一定要加.build()

    public  static  final FoodComponent TOAST = new FoodComponent.Builder().hunger(4)
            .statusEffect(new StatusEffectInstance(StatusEffects.DOLPHINS_GRACE, 2400,1), 1.0f)
            .saturationModifier(0.5f).build();

    public  static  final FoodComponent SALAMI_PIZZA = new FoodComponent.Builder().hunger(3)
            .statusEffect(new StatusEffectInstance(StatusEffects.STRENGTH, 1800,1), 1.0f)
            .saturationModifier(1f).build();

    public  static  final FoodComponent BORSCHT = new FoodComponent.Builder().hunger(1)
            .statusEffect(new StatusEffectInstance(StatusEffects.STRENGTH, 1200,1), 1.0f)
            .saturationModifier(2f).build();

    public  static  final FoodComponent NAVY_BAKED_BEANS = new FoodComponent.Builder().hunger(2)
            .statusEffect(new StatusEffectInstance(StatusEffects.LUCK, 1200,1), 1.0f)
            .saturationModifier(1f).build();

    public  static  final FoodComponent APPLE_PIE = new FoodComponent.Builder().hunger(1)
            .statusEffect(new StatusEffectInstance(StatusEffects.HASTE, 2400,1), 1.0f)
            .saturationModifier(1f).build();

    public  static  final FoodComponent FISH_AND_CHIPS = new FoodComponent.Builder().hunger(2)
            .statusEffect(new StatusEffectInstance(StatusEffects.JUMP_BOOST, 2600,1), 1.0f)
            .saturationModifier(0.5f).build();

    public  static  final FoodComponent THURINGIAN_SAUSAGE_BEER = new FoodComponent.Builder().hunger(3)
            .statusEffect(new StatusEffectInstance(StatusEffects.RESISTANCE, 3600,1), 1.0f)
            .saturationModifier(1f).build();

    public  static  final FoodComponent WHITE_SAUSAGE_BAGELS = new FoodComponent.Builder().hunger(2)
            .statusEffect(new StatusEffectInstance(StatusEffects.CONDUIT_POWER, 6000,1), 1.0f)
            .saturationModifier(0.5f).build();

    public  static  final FoodComponent NAVY_CURRY = new FoodComponent.Builder().hunger(3)
            .statusEffect(new StatusEffectInstance(StatusEffects.NIGHT_VISION, 3000,1), 1.0f)
            .saturationModifier(1f).build();

    public  static  final FoodComponent MARSEILLE_FISH_SOUP = new FoodComponent.Builder().hunger(1)
            .statusEffect(new StatusEffectInstance(StatusEffects.DOLPHINS_GRACE, 2400,1), 1.0f)
            .saturationModifier(2f).build();

    public  static  final FoodComponent MAPO_TOFU = new FoodComponent.Builder().hunger(3)
            .statusEffect(new StatusEffectInstance(StatusEffects.STRENGTH, 1200,2), 1.0f)
            .statusEffect(new StatusEffectInstance(StatusEffects.FIRE_RESISTANCE, 3600,1), 1.0f)
            .saturationModifier(1f).build();

    public  static  final FoodComponent LOOKING_UP_AT_THE_STARS = new FoodComponent.Builder().hunger(2)
            .statusEffect(new StatusEffectInstance(StatusEffects.NAUSEA, 1200,1), 0.9f)
            .statusEffect(new StatusEffectInstance(StatusEffects.HASTE, 3600,3), 1.0f)
            .saturationModifier(0.5f).build();

    public  static  final FoodComponent ROYAL_NAVY_CORNED_BEEF = new FoodComponent.Builder().hunger(3)
            .statusEffect(new StatusEffectInstance(StatusEffects.INSTANT_HEALTH, 1200,1), 1.0f)
            .saturationModifier(1f).build();
    public  static  final FoodComponent TORPEDO_JUICE = new FoodComponent.Builder().hunger(0)
            .statusEffect(new StatusEffectInstance(StatusEffects.HUNGER, 1200,1), 1.0f)
            .statusEffect(new StatusEffectInstance(StatusEffects.HASTE, 2400,1), 1.0f)
            .statusEffect(new StatusEffectInstance(StatusEffects.FIRE_RESISTANCE, 3600,1), 1.0f)
            .saturationModifier(5f).build();

    public  static  final FoodComponent CANNED_HERRING = new FoodComponent.Builder().hunger(3)
            .statusEffect(new StatusEffectInstance(StatusEffects.STRENGTH, 4800,2), 1.0f)
            .saturationModifier(0.5f).build();
    public  static  final FoodComponent BLACK_FOREST_CAKE = new FoodComponent.Builder().hunger(3)
            .statusEffect(new StatusEffectInstance(StatusEffects.STRENGTH, 1200,1), 1.0f)
            .saturationModifier(1f).build();

    public  static  final FoodComponent MUSSOLINI_IS_ASS = new FoodComponent.Builder().hunger(6)
            .statusEffect(new StatusEffectInstance(StatusEffects.NAUSEA, 1200,1), 1.0f)
            .statusEffect(new StatusEffectInstance(StatusEffects.RESISTANCE, 3000,1), 1.0f)
            .statusEffect(new StatusEffectInstance(StatusEffects.INSTANT_HEALTH, 2400,1), 1.0f)
            .saturationModifier(1f).build();

    public  static  final FoodComponent YOKAN = new FoodComponent.Builder().hunger(1)
            .statusEffect(new StatusEffectInstance(StatusEffects.JUMP_BOOST, 6000,1), 1.0f)
            .saturationModifier(1f).build();


    public  static  final FoodComponent FRIED_SHRIMP_TEMPURA = new FoodComponent.Builder().hunger(3)
            .statusEffect(new StatusEffectInstance(StatusEffects.HASTE, 1200,1), 1.0f)
            .saturationModifier(1f).build();

    public  static  final FoodComponent MACARON = new FoodComponent.Builder().hunger(1)
            .statusEffect(new StatusEffectInstance(StatusEffects.STRENGTH, 2400,1), 1.0f)
            .statusEffect(new StatusEffectInstance(StatusEffects.GLOWING, 4800,1), 1.0f)
            .saturationModifier(2f).build();

    public  static  final FoodComponent HELEBA = new FoodComponent.Builder().hunger(4)
            .statusEffect(new StatusEffectInstance(StatusEffects.SATURATION, 400,1), 1.0f)
            .saturationModifier(1f).build();

    public  static  final FoodComponent SPICY_STRIPS = new FoodComponent.Builder().hunger(1)
            .statusEffect(new StatusEffectInstance(StatusEffects.SPEED, 1800,3), 1.0f)
            .saturationModifier(3f).build();

    public  static  final FoodComponent ASSORTED_CHAR_SIEW_FRIED_RICE = new FoodComponent.Builder().hunger(3)
            .statusEffect(new StatusEffectInstance(StatusEffects.SPEED, 3600,2), 1.0f)
            .saturationModifier(1f).build();

    public  static  final FoodComponent GOLD_INLAID_JADE_BUNS = new FoodComponent.Builder().hunger(3)
            .statusEffect(new StatusEffectInstance(StatusEffects.HASTE, 3600,1), 1.0f)
            .saturationModifier(1f).build();

    public  static  final FoodComponent SWEET_TOFU_BRAIN = new FoodComponent.Builder().hunger(3)
            .statusEffect(new StatusEffectInstance(StatusEffects.STRENGTH, 1200,1), 1.0f)
            .statusEffect(new StatusEffectInstance(StatusEffects.WATER_BREATHING, 3600,1), 1.0f)
            .saturationModifier(2f).build();

    public  static  final FoodComponent SALTY_TOFU_BRAIN = new FoodComponent.Builder().hunger(3)
            .statusEffect(new StatusEffectInstance(StatusEffects.STRENGTH, 1200,1), 1.0f)
            .statusEffect(new StatusEffectInstance(StatusEffects.WATER_BREATHING, 3600,1), 1.0f)
            .saturationModifier(2f).build();

    public  static  final FoodComponent AMERICAN_BURGER = new FoodComponent.Builder().hunger(4)
            .statusEffect(new StatusEffectInstance(StatusEffects.HASTE, 6000,1), 1.0f)
            .saturationModifier(1f).build();

    public  static  final FoodComponent BLACK_TEA_SANDWICH = new FoodComponent.Builder().hunger(3)
            .statusEffect(new StatusEffectInstance(StatusEffects.HASTE, 6000,1), 1.0f)
            .saturationModifier(2f).build();
    public  static  final FoodComponent ROAST_PORK_KNUCKLE = new FoodComponent.Builder().hunger(4)
            .statusEffect(new StatusEffectInstance(StatusEffects.HASTE, 6000,1), 1.0f)
            .saturationModifier(1f).build();

    public  static  final FoodComponent LONGTIAN_YAKI = new FoodComponent.Builder().hunger(3)
            .statusEffect(new StatusEffectInstance(StatusEffects.HASTE, 6000,1), 1.0f)
            .saturationModifier(1f).build();

    public  static  final FoodComponent SWEET_DUMPLING = new FoodComponent.Builder().hunger(3)
            .statusEffect(new StatusEffectInstance(StatusEffects.SPEED, 1600,1), 1.0f)
            .saturationModifier(2f).build();

    public  static  final FoodComponent TOMATO_BOLOGNESE_LASAGNA = new FoodComponent.Builder().hunger(3)
            .statusEffect(new StatusEffectInstance(StatusEffects.SPEED, 2400,1), 1.0f)
            .saturationModifier(1f).build();

    public  static  final FoodComponent KVASS = new FoodComponent.Builder().hunger(2)
            .statusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, 6000,1), 1.0f)
            .statusEffect(new StatusEffectInstance(StatusEffects.SLOW_FALLING, 6000,1), 1.0f)
            .statusEffect(new StatusEffectInstance(StatusEffects.INVISIBILITY, 6000,1), 1.0f)
            .saturationModifier(3f).build();

    public  static  final FoodComponent EGG_BENEDICT = new FoodComponent.Builder().hunger(2)
            .statusEffect(new StatusEffectInstance(StatusEffects.LUCK, 6000,1), 1.0f)
            .saturationModifier(1f).build();

    public  static  final FoodComponent RYE_BREAD_LENTIL_SOUP = new FoodComponent.Builder().hunger(1)
            .statusEffect(new StatusEffectInstance(StatusEffects.RESISTANCE, 2400,1), 1.0f)
            .saturationModifier(5f).build();

    public  static  final FoodComponent BLACK_TEA_SCONES = new FoodComponent.Builder().hunger(3)
            .statusEffect(new StatusEffectInstance(StatusEffects.SPEED, 3000,1), 1.0f)
            .saturationModifier(1f).build();

    public  static  final FoodComponent GRILLED_FISH_AND_MISO_SOUP = new FoodComponent.Builder().hunger(1)
            .statusEffect(new StatusEffectInstance(StatusEffects.HASTE, 3600,1), 1.0f)
            .saturationModifier(4f).build();

    public  static  final FoodComponent HOT_DOG = new FoodComponent.Builder().hunger(3)
            .statusEffect(new StatusEffectInstance(StatusEffects.HASTE, 600,1), 1.0f)
            .saturationModifier(1f).build();

    public  static  final FoodComponent TARTAN_APPLE_TART= new FoodComponent.Builder().hunger(2)
            .statusEffect(new StatusEffectInstance(StatusEffects.RESISTANCE, 3600,1), 1.0f)
            .saturationModifier(1f).build();

    public  static  final FoodComponent TEMPURA_SOBA = new FoodComponent.Builder().hunger(2)
            .statusEffect(new StatusEffectInstance(StatusEffects.STRENGTH, 1200,1), 1.0f)
            .saturationModifier(2f).build();

    public  static  final FoodComponent YORKSHIRE_PUDDING = new FoodComponent.Builder().hunger(3)
            .statusEffect(new StatusEffectInstance(StatusEffects.SPEED, 1200,3), 1.0f)
            .saturationModifier(1f).build();
    public  static  final FoodComponent BARBECUE = new FoodComponent.Builder().hunger(3)
            .statusEffect(new StatusEffectInstance(StatusEffects.FIRE_RESISTANCE, 600,3), 1.0f)
            .saturationModifier(1f).build();

    public  static  final FoodComponent DONGPO_PORK = new FoodComponent.Builder().hunger(3)
            .statusEffect(new StatusEffectInstance(StatusEffects.STRENGTH, 600,2), 1.0f)
            .statusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 2400,1), 1.0f)
            .saturationModifier(1f).build();

    public  static  final FoodComponent RUSSIAN_DUMPLING = new FoodComponent.Builder().hunger(3)
            .statusEffect(new StatusEffectInstance(StatusEffects.STRENGTH, 2100,2), 1.0f)
            .saturationModifier(1f).build();

    public  static  final FoodComponent RUSSIAN_ASPIC = new FoodComponent.Builder().hunger(3)
            .statusEffect(new StatusEffectInstance(StatusEffects.RESISTANCE, 2100,2), 1.0f)
            .saturationModifier(1f).build();

    public  static  final FoodComponent VENETIAN_CUTTLEFISH_NOODLES = new FoodComponent.Builder().hunger(3)
            .statusEffect(new StatusEffectInstance(StatusEffects.JUMP_BOOST, 600,3), 1.0f)
            .saturationModifier(1f).build();





}
