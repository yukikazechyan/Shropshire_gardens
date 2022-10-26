package net.fabricmc.shropshire;

import com.google.common.collect.Lists;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.effcts.loot;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.provider.SimpleFabricLootTableProvider;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.loot.v2.LootTableEvents;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.registry.FuelRegistry;
import net.fabricmc.shropshire.block.ExampleBlock;
import net.fabricmc.shropshire.block.rice;
import net.fabricmc.shropshire.item.FoodComponents;
import net.fabricmc.shropshire.item.RegisterItems;
import net.minecraft.block.*;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.command.argument.BlockArgumentParser;
import net.minecraft.data.server.BlockLootTableGenerator;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.item.*;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.context.LootContextTypes;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.function.EnchantWithLevelsLootFunction;
import net.minecraft.loot.function.SetCountLootFunction;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
import net.minecraft.loot.provider.number.UniformLootNumberProvider;
import net.minecraft.potion.Potion;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.function.BiConsumer;

public class shropshire implements ModInitializer {
	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it 's clear which mod wrote info, warnings, and errors.
	public static final Logger LOGGER = LoggerFactory.getLogger("shropshire");


	public static final ArrayList<DamageSource> DAMAGE_SOURCES = Lists.newArrayList(//盔甲相关
			// All the damage sources we should be immune to

			DamageSource.HOT_FLOOR,
			DamageSource.IN_FIRE,
			DamageSource.LAVA,
			DamageSource.LIGHTNING_BOLT,
			DamageSource.ON_FIRE
	);






	private static final Identifier COAL_ORE_LOOT_TABLE_ID = Blocks.COAL_ORE.getLootTableId();//关于劫持战利品表的喵
	private static final Identifier RICE_CROP_LOOT_TABLE_ID = Blocks.GOLD_ORE.getLootTableId();//关于劫持水稻战利品表的喵





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

	public static final ItemGroup GILDED_NETHERITE_GROUP = FabricItemGroupBuilder.create(//新建了盔甲这个分类
					new Identifier("shropshire", "gilded_netherite_group"))
			.icon(() -> new ItemStack(RegisterItems.GILDED_NETHERITE))
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


	public static final ExampleBlock luxurious_wood_furniture = new ExampleBlock(Block.Settings.of(Material.STONE).hardness(4.0f));//创建方块(定义物品)
	public static final ExampleBlock confidential_cargo = new ExampleBlock(Block.Settings.of(Material.STONE).hardness(4.0f));//public static final ExampleBlock 此处一般直接使用此物品id = new ExampleBlock(Block.Settings.of(Material.此处填写物品材质，决定了使用何种工具能够较快挖掘).hardness(硬度，抗爆能力f));
	//public static final ExampleBlock dressing_table = new ExampleBlock(Block.Settings.of(Material.STONE).hardness(4.0f));
	public static final ExampleBlock italian_dish_kit = new ExampleBlock(Block.Settings.of(Material.STONE).hardness(4.0f));
	public static final ExampleBlock railgun = new ExampleBlock(Block.Settings.of(Material.STONE).hardness(4.0f));

	public static final loot LOOT_EFFECT = new loot();//此处关于药水
	public static final Potion LOOT = new Potion(new StatusEffectInstance(shropshire.LOOT_EFFECT, 2000));//此药水效果时间为100s，但此效果应是妾身新增的效果，可能并无用处

	public static final Item TEST_FOOD = new Item(new Item.Settings().group(ItemGroup.FOOD).food(FoodComponents.TEST_FOOOD));//新增食物

	public static final Block RICE_CROP = new rice(FabricBlockSettings.copyOf(Blocks.WHEAT));//测试作物
	//public static final Item TEST_SEEDS = new TestSeeds(shropshire.TEST_CROP, new FabricItemSettings());//种子
	public static final Item RICE_SEEDS = new AliasedBlockItem(shropshire.RICE_CROP, new Item.Settings().group(ItemGroup.MISC));
	//public static final Item RICE_PRODUC = new Item(new FabricItemSettings().group(ItemGroup.MISC));//水稻产物




	public void onInitializeClient() {
	}


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

		Registry.register(Registry.ITEM, new Identifier("shropshire", "test_food"), TEST_FOOD);//测试食物

		//BlockRenderLayerMap.INSTANCE.putBlock(TEST_CROP, RenderLayer.getCutout());//测试作物的方块
		Registry.register(Registry.BLOCK, new Identifier("shropshire", "rice_crop"), RICE_CROP);//
		BlockRenderLayerMap.INSTANCE.putBlocks(RenderLayer.getCutout(), RICE_CROP);//作物方块的背景透明，后面的在这里加就行
		Registry.register(Registry.ITEM, new Identifier("shropshire", "rice_seeds"), RICE_SEEDS);//测试种子
		//Registry.register(Registry.ITEM, new Identifier( "shropshire", "rice_product"), RICE_PRODUC);



		RegisterItems.register();//盔甲部分注册




		LootTableEvents.MODIFY.register((resourceManager, lootManager, id, tableBuilder, source) -> {//劫持战利品表的
			if (source.isBuiltin() && COAL_ORE_LOOT_TABLE_ID.equals(id)) {
				LootPool.Builder poolBuilder = LootPool.builder()
						.with(ItemEntry.builder(Items.EGG));//掉落物

				tableBuilder.pool(poolBuilder);}
			if (source.isBuiltin() && RICE_CROP_LOOT_TABLE_ID.equals(id)) {
				LootPool.Builder poolBuilder = LootPool.builder()
						.with(ItemEntry.builder(Items.EGG))
				        .with(ItemEntry.builder(shropshire.RICE_SEEDS));

				tableBuilder.pool(poolBuilder);}


		});

	}



	}






