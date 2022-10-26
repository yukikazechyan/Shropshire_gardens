package net.fabricmc.shropshire.item;

import net.fabricmc.shropshire.materials.GildedNetheriteArmorMaterial;
import net.fabricmc.shropshire.shropshire;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.*;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class RegisterItems {
    public static final ArmorMaterial gildedNetheriteArmorMaterial = new GildedNetheriteArmorMaterial();
    public static final Item GILDED_NETHERITE = new GildedNetheriteItem(new Item.Settings().group(shropshire.GILDED_NETHERITE_GROUP));
    public static final Item GILDED_NETHERITE_HELMET = new ArmorItem(gildedNetheriteArmorMaterial, EquipmentSlot.HEAD, new Item.Settings().group(shropshire.GILDED_NETHERITE_GROUP).fireproof());
    public static final Item GILDED_NETHERITE_CHESTPLATE = new ArmorItem(gildedNetheriteArmorMaterial, EquipmentSlot.CHEST, new Item.Settings().group(shropshire.GILDED_NETHERITE_GROUP).fireproof());
    public static final Item GILDED_NETHERITE_LEGGINGS = new ArmorItem(gildedNetheriteArmorMaterial, EquipmentSlot.LEGS, new Item.Settings().group(shropshire.GILDED_NETHERITE_GROUP).fireproof());
    public static final Item GILDED_NETHERITE_BOOTS = new ArmorItem(gildedNetheriteArmorMaterial, EquipmentSlot.FEET, new Item.Settings().group(shropshire.GILDED_NETHERITE_GROUP).fireproof());

    public static void register() {
        Registry.register(Registry.ITEM, new Identifier("shropshire", "gilded_netherite"), GILDED_NETHERITE);
        Registry.register(Registry.ITEM, new Identifier("shropshire", "gilded_netherite_helmet"), GILDED_NETHERITE_HELMET);
        Registry.register(Registry.ITEM, new Identifier("shropshire", "gilded_netherite_chestplate"), GILDED_NETHERITE_CHESTPLATE);
        Registry.register(Registry.ITEM, new Identifier("shropshire", "gilded_netherite_leggings"), GILDED_NETHERITE_LEGGINGS);
        Registry.register(Registry.ITEM, new Identifier("shropshire", "gilded_netherite_boots"), GILDED_NETHERITE_BOOTS);
    //gilded netherite是盔甲名字
    }
}
