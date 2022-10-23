package net.fabricmc.shropshire;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.effcts.loot;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.registry.FuelRegistry;
import net.fabricmc.shropshire.block.ExampleBlock;
import net.fabricmc.shropshire.mixin.BrewingMixin;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.item.*;
import net.minecraft.potion.Potion;
import net.minecraft.potion.Potions;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

public class shropshire implements ModInitializer {
	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it 's clear which mod wrote info, warnings, and errors.
	public static final Logger LOGGER = LoggerFactory.getLogger("shropshire");

	public static final Item CUSTOM_ITEM = new Item(new FabricItemSettings().group(ItemGroup.MISC));//此处为一个物品实例
	//public static final Item test_c = new Item(new FabricItemSettings().group(ItemGroup.MISC));

	/*public static final ItemGroup ITEM_GROUP = FabricItemGroupBuilder.build(//此处为分组相关
			new Identifier("shropshire", "general"),
			() -> new ItemStack(Blocks.COBBLESTONE));
			//这部分为另一个分组，但妾身并不会使用，已经注释掉了
	 */

	public static final ItemGroup OTHER_GROUP = FabricItemGroupBuilder.create(//此处为分组相关
					new Identifier("shropshire", "other"))
			.icon(() -> new ItemStack(Items.BOWL))

			.appendItems(stacks -> {//这部分可能为定义分类用
				stacks.add(new ItemStack(CUSTOM_ITEM));//CUSTOM_ITEM可能为定义分类的关键词，但妾身并不会使用
			})
			.build();


	/* 声明和初始化我们的自定义方块实例。
       我们将方块材质（material）设置为METAL（金属），需要镐来高效挖掘。

       `strength`会将方块的硬度和抗性设为同一个值。
       硬度决定了方块需要多久挖掘，抗性决定了方块抵御爆破伤害（如爆炸）的能力。
       石头的硬度为1.5f，抗性为6.0f，黑曜石的硬度为50.0f，抗性为1200.0f。

       可以在`Blocks`类中查找所有原版方块的统计。
    */
	//	public static final ExampleBlock EXAMPLE_BLOCK = new ExampleBlock(Block.Settings.of(Material.STONE).hardness(4.0f));//创建一个方块

	//贵安，正在阅读此注释的程序员小姐。此文件为本mod的主要文件，也就是注册文件，有些mod中此文件引用了几个其他的注册物品的文件，本身里面没有写别的。因本mod体量较小，故大部分物品在此注册。
	//有些自定义物品妾身会每个物品新建一个单独的文件


	public static final ExampleBlock luxurious_wood_furniture = new ExampleBlock(Block.Settings.of(Material.STONE).hardness(4.0f));//创建方块
	public static final ExampleBlock confidential_cargo = new ExampleBlock(Block.Settings.of(Material.STONE).hardness(4.0f));//public static final ExampleBlock 此处一般直接使用此物品id = new ExampleBlock(Block.Settings.of(Material.此处填写物品材质，决定了使用何种工具能够较快挖掘).hardness(硬度，抗爆能力f));
	//public static final ExampleBlock dressing_table = new ExampleBlock(Block.Settings.of(Material.STONE).hardness(4.0f));
	public static final ExampleBlock italian_dish_kit = new ExampleBlock(Block.Settings.of(Material.STONE).hardness(4.0f));
	public static final ExampleBlock railgun = new ExampleBlock(Block.Settings.of(Material.STONE).hardness(4.0f));

	public static final loot LOOT_EFFECT = new loot();//此处关于药水
	public static final Potion LOOT = new Potion(new StatusEffectInstance(shropshire.LOOT_EFFECT, 2000));//此药水效果时间为100s，但此效果应是妾身新增的效果，可能并无用处


	@Override
	public void onInitialize() {
	//public void registerDefaulst() {

			// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.

		LOGGER.info("御机嫌よう，即将开始注册物品");




		Registry.register(Registry.ITEM, new Identifier( "shropshire", "test_item"), CUSTOM_ITEM);//Registry.register(Registry.此处应是继承的类, new Identifier( "此处为模组名，请注意开头不要大写", "物品的ID"), 所属的类);
		//Registry.register(Registry.ITEM, new Identifier( "shropshire", "test_c"), test_c);
		//Registry.register(Registry.BLOCK, new Identifier( "shropshire", "example_block"), EXAMPLE_BLOCK);


		Registry.register(Registry.BLOCK, new Identifier("shropshire", "luxurious_wood_furniture"), luxurious_wood_furniture);
		Registry.register(Registry.ITEM, new Identifier("shropshire", "luxurious_wood_furniture"), new BlockItem(luxurious_wood_furniture, new Item.Settings().group(ItemGroup.MISC)));//		Registry.register(Registry.ITEM, new Identifier("shropshire", "luxurious_wood_furniture"), new BlockItem(此处写这个文件中前面注册的，一般为物品id, new Item.Settings().group(ItemGroup.MISC)));


		Registry.register(Registry.BLOCK, new Identifier("shropshire", "confidential_cargo"), confidential_cargo);
		Registry.register(Registry.ITEM, new Identifier("shropshire", "confidential_cargo"), new BlockItem(confidential_cargo, new Item.Settings().group(ItemGroup.MISC)));

		//Registry.register(Registry.BLOCK, new Identifier("shropshire", "dressing_table"), dressing_table);//因梳妆台模型无法正常渲染，故妾身将其注释了
		//Registry.register(Registry.ITEM, new Identifier("shropshire", "dressing_table"), new BlockItem(dressing_table, new Item.Settings().group(ItemGroup.MISC)));

		Registry.register(Registry.BLOCK, new Identifier("shropshire", "italian_dish_kit"), italian_dish_kit);
		Registry.register(Registry.ITEM, new Identifier("shropshire", "italian_dish_kit"), new BlockItem(italian_dish_kit, new Item.Settings().group(ItemGroup.MISC)));

		Registry.register(Registry.BLOCK, new Identifier("shropshire", "railgun"), railgun);
		Registry.register(Registry.ITEM, new Identifier("shropshire", "railgun"), new BlockItem(railgun, new Item.Settings().group(ItemGroup.MISC)));
		// 请注意方块前物品在后


		FuelRegistry.INSTANCE.add(CUSTOM_ITEM, 200);//此处为测试物品的可燃性，测试物品可燃10s

		Registry.register(Registry.STATUS_EFFECT, new Identifier("shropshire", "loot"), LOOT_EFFECT);//此处为药水注册
		Registry.register(Registry.POTION, new Identifier("shropshire", "loot"), LOOT);

	}






}

