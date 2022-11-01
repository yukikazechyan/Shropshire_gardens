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
import net.fabricmc.shropshire.block.FurnitureBlock;
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






	private static final Identifier GRAVEL_LOOT_TABLE_ID = Blocks.GRAVEL.getLootTableId();//关于劫持战利品表的喵
	private static final Identifier TALL_GRASS_LOOT_TABLE_ID = Blocks.TALL_GRASS.getLootTableId();//RICE_CROP_LOOT_TABLE_ID是这个东西的名字，Blocks.GOLD_ORE是被劫持的
	private static final Identifier SWEET_BERRY_BUSH_LOOT_TABLE_ID = Blocks.SWEET_BERRY_BUSH.getLootTableId();
	private static final Identifier WHEAT_LOOT_TABLE_ID = Blocks.GRAVEL.getLootTableId();
	private static final Identifier GRASS_BLOCK_LOOT_TABLE_ID = Blocks.GRASS_BLOCK.getLootTableId();
	private static final Identifier BIRCH_LEAVES_LOOT_TABLE_ID = Blocks.BIRCH_LEAVES.getLootTableId();
	private static final Identifier NETHER_WART_LOOT_TABLE_ID = Blocks.NETHER_WART.getLootTableId();
	private static final Identifier MANGROVE_LEAVES_LOOT_TABLE_ID = Blocks.MANGROVE_LEAVES.getLootTableId();
	private static final Identifier STONE_LOOT_TABLE_ID = Blocks.STONE.getLootTableId();
	private static final Identifier FERN_LOOT_TABLE_ID = Blocks.FERN.getLootTableId();
	private static final Identifier GRASS_LOOT_TABLE_ID = Blocks.GRASS.getLootTableId();




	public static final Item CUSTOM_ITEM = new Item(new FabricItemSettings().group(ItemGroup.MISC));//此处为一个物品实例
	//public static final Item test_c = new Item(new FabricItemSettings().group(ItemGroup.MISC));

	/*public static final ItemGroup ITEM_GROUP = FabricItemGroupBuilder.build(//此处为分组相关
			new Identifier("shropshire", "general"),
			() -> new ItemStack(Blocks.COBBLESTONE));
			//这部分为另一个分组，但妾身并不会使用，已经注释掉了
	 */
	public static final ItemGroup ITEM_GROUP = FabricItemGroupBuilder.create(//食物类
					new Identifier("shropshire", "dishes"))
			.icon(() -> new ItemStack(shropshire.TOAST))
			.appendItems(stacks -> {
				stacks.add(new ItemStack(shropshire.TOAST));
				stacks.add(new ItemStack(shropshire.SALAMI_PIZZA));
				stacks.add(new ItemStack(shropshire.BORSCHT));
				stacks.add(new ItemStack(shropshire.NAVY_BAKED_BEANS));
				stacks.add(new ItemStack(shropshire.APPLE_PIE));
				stacks.add(new ItemStack(shropshire.FISH_AND_CHIPS));
				stacks.add(new ItemStack(shropshire.THURINGIAN_SAUSAGE_BEER));
				stacks.add(new ItemStack(shropshire.WHITE_SAUSAGE_BAGELS));
				stacks.add(new ItemStack(shropshire.NAVY_CURRY));
				stacks.add(new ItemStack(shropshire.MARSEILLE_FISH_SOUP));
				stacks.add(new ItemStack(shropshire.MAPO_TOFU));
				stacks.add(new ItemStack(shropshire.LOOKING_UP_AT_THE_STARS));
				stacks.add(new ItemStack(shropshire.ROYAL_NAVY_CORNED_BEEF));
				stacks.add(new ItemStack(shropshire.TORPEDO_JUICE));
				stacks.add(new ItemStack(shropshire.CANNED_HERRING));
				stacks.add(new ItemStack(shropshire.BLACK_FOREST_CAKE));
				stacks.add(new ItemStack(shropshire.MUSSOLINI_IS_ASS));
				stacks.add(new ItemStack(shropshire.YOKAN));
				stacks.add(new ItemStack(shropshire.FRIED_SHRIMP_TEMPURA));
				stacks.add(new ItemStack(shropshire.MACARON));
				stacks.add(new ItemStack(shropshire.HELEBA));
				stacks.add(new ItemStack(shropshire.SPICY_STRIPS));
				stacks.add(new ItemStack(shropshire.ASSORTED_CHAR_SIEW_FRIED_RICE));
				stacks.add(new ItemStack(shropshire.GOLD_INLAID_JADE_BUNS));
				stacks.add(new ItemStack(shropshire.SWEET_TOFU_BRAIN));
				stacks.add(new ItemStack(shropshire.SALTY_TOFU_BRAIN));
				stacks.add(new ItemStack(shropshire.AMERICAN_BURGER));
				stacks.add(new ItemStack(shropshire.BLACK_TEA_SANDWICH));
				stacks.add(new ItemStack(shropshire.ROAST_PORK_KNUCKLE));
				stacks.add(new ItemStack(shropshire.SWEET_DUMPLING));
				stacks.add(new ItemStack(shropshire.TOMATO_BOLOGNESE_LASAGNA));
				stacks.add(new ItemStack(shropshire.KVASS));
				stacks.add(new ItemStack(shropshire.EGG_BENEDICT));
				stacks.add(new ItemStack(shropshire.RYE_BREAD_LENTIL_SOUP));
				stacks.add(new ItemStack(shropshire.BLACK_TEA_SCONES));
				stacks.add(new ItemStack(shropshire.GRILLED_FISH_AND_MISO_SOUP));
				stacks.add(new ItemStack(shropshire.HOT_DOG));
				stacks.add(new ItemStack(shropshire.TARTAN_APPLE_TART));
				stacks.add(new ItemStack(shropshire.TEMPURA_SOBA));
				stacks.add(new ItemStack(shropshire.YORKSHIRE_PUDDING));
				stacks.add(new ItemStack(shropshire.DONGPO_PORK));
				stacks.add(new ItemStack(shropshire.RUSSIAN_DUMPLING));
				stacks.add(new ItemStack(shropshire.RUSSIAN_ASPIC));
				stacks.add(new ItemStack(shropshire.VENETIAN_CUTTLEFISH_NOODLES));


			})
			.build();



				public static final ItemGroup OTHER_GROUP = FabricItemGroupBuilder.create(//此处为分组相关
					new Identifier("shropshire", "other"))
			.icon(() -> new ItemStack(shropshire.CUSTOM_ITEM))
			.appendItems(stacks -> {
				stacks.add(new ItemStack(CUSTOM_ITEM));//此处填写要添加在此分类的物品
				stacks.add(new ItemStack(shropshire.SALT));
				stacks.add(new ItemStack(shropshire.MINCED_MEAT));
				stacks.add(new ItemStack(shropshire.ALCOHOL));
				stacks.add(new ItemStack(shropshire.TOFU));
				stacks.add(new ItemStack(shropshire.SOY_SAUCE));
				stacks.add(new ItemStack(shropshire.BUTTER));
				stacks.add(new ItemStack(shropshire.SHRIMP));
				stacks.add(new ItemStack(shropshire.CHEESE));
				stacks.add(new ItemStack(shropshire.BACON));
				stacks.add(new ItemStack(shropshire.SAUSAGE));
				stacks.add(new ItemStack(shropshire.MK14_TORPEDO));
				stacks.add(new ItemStack(shropshire.ONION));
				stacks.add(new ItemStack(shropshire.FLOUR));
				stacks.add(new ItemStack(shropshire.SOYBEAN));
				stacks.add(new ItemStack(shropshire.CABBAGE));
				stacks.add(new ItemStack(shropshire.TOMATO));
				stacks.add(new ItemStack(shropshire.CURRY));
				stacks.add(new ItemStack(shropshire.RICE));
				stacks.add(new ItemStack(shropshire.GARLIC));
				stacks.add(new ItemStack(shropshire.PINEAPPLE));
				stacks.add(new ItemStack(shropshire.CHERRY));
				stacks.add(new ItemStack(shropshire.RED_BEANS));
				stacks.add(new ItemStack(shropshire.PLASTER));
				stacks.add(new ItemStack(shropshire.STICKY_RICE));
				stacks.add(new ItemStack(shropshire.BLACK_TEA));
				stacks.add(new ItemStack(shropshire.SESAME));
				stacks.add(new ItemStack(shropshire.STARCH));
				stacks.add(new ItemStack(shropshire.GINGER));
				stacks.add(new ItemStack(shropshire.LENTILS));
				stacks.add(new ItemStack(shropshire.LETTUCE));



			})
			.build();

	public static final ItemGroup GILDED_NETHERITE_GROUP = FabricItemGroupBuilder.create(//新建了盔甲这个分类
					new Identifier("shropshire", "gilded_netherite_group"))
			.icon(() -> new ItemStack(shropshire.confidential_cargo))
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
	public static final FurnitureBlock confidential_cargo = new FurnitureBlock(Block.Settings.of(Material.TNT).hardness(1.0f));//public static final ExampleBlock 此处一般直接使用此物品id = new ExampleBlock(Block.Settings.of(Material.此处填写物品材质，决定了使用何种工具能够较快挖掘).hardness(硬度，抗爆能力f));
	//public static final ExampleBlock dressing_table = new ExampleBlock(Block.Settings.of(Material.STONE).hardness(4.0f));
	public static final ExampleBlock italian_dish_kit = new ExampleBlock(Block.Settings.of(Material.STONE).hardness(4.0f));
	public static final ExampleBlock railgun = new ExampleBlock(Block.Settings.of(Material.STONE).hardness(4.0f));
	public static final loot LOOT_EFFECT = new loot();//此处关于药水
	public static final Potion LOOT = new Potion(new StatusEffectInstance(shropshire.LOOT_EFFECT, 2000));//此药水效果时间为100s，但此效果应是妾身新增的效果，可能并无用处
	public static final Potion OIL = new Potion(new StatusEffectInstance(shropshire.LOOT_EFFECT, 1));
	public static final Potion SOY_MILK = new Potion(new StatusEffectInstance(shropshire.LOOT_EFFECT, 1));


	public static final Item TEST_FOOD = new Item(new Item.Settings().group(ItemGroup.FOOD).food(FoodComponents.TEST_FOOOD));//新增食物
	public static final Block RICE_CROP = new rice(FabricBlockSettings.copyOf(Blocks.WHEAT));//测试作物
	public static final Item RICE_SEEDS = new AliasedBlockItem(shropshire.RICE_CROP, new Item.Settings().group(ItemGroup.MISC));
	//public static final Item RICE_PRODUC = new Item(new FabricItemSettings().group(ItemGroup.MISC));//水稻产物
	//public static final Item TAIHO_UMBRELLA = new

	public static final Item TOAST = new Item(new Item.Settings().group(ItemGroup.FOOD).food(FoodComponents.TOAST));
	public static final Item SALAMI_PIZZA = new Item(new Item.Settings().group(ItemGroup.FOOD).food(FoodComponents.SALAMI_PIZZA));
	public static final Item BORSCHT = new Item(new Item.Settings().group(ItemGroup.FOOD).food(FoodComponents.BORSCHT));
	public static final Item NAVY_BAKED_BEANS = new Item(new Item.Settings().group(ItemGroup.FOOD).food(FoodComponents.NAVY_BAKED_BEANS));
	public static final Item APPLE_PIE = new Item(new Item.Settings().group(ItemGroup.FOOD).food(FoodComponents.APPLE_PIE));
	public static final Item FISH_AND_CHIPS = new Item(new Item.Settings().group(ItemGroup.FOOD).food(FoodComponents.FISH_AND_CHIPS));
	public static final Item THURINGIAN_SAUSAGE_BEER = new Item(new Item.Settings().group(ItemGroup.FOOD).food(FoodComponents.THURINGIAN_SAUSAGE_BEER));
	public static final Item WHITE_SAUSAGE_BAGELS = new Item(new Item.Settings().group(ItemGroup.FOOD).food(FoodComponents.WHITE_SAUSAGE_BAGELS));
	public static final Item NAVY_CURRY = new Item(new Item.Settings().group(ItemGroup.FOOD).food(FoodComponents.NAVY_CURRY));
	public static final Item MARSEILLE_FISH_SOUP = new Item(new Item.Settings().group(ItemGroup.FOOD).food(FoodComponents.MARSEILLE_FISH_SOUP));
	public static final Item MAPO_TOFU = new Item(new Item.Settings().group(ItemGroup.FOOD).food(FoodComponents.MAPO_TOFU));
	public static final Item LOOKING_UP_AT_THE_STARS = new Item(new Item.Settings().group(ItemGroup.FOOD).food(FoodComponents.LOOKING_UP_AT_THE_STARS));
	public static final Item ROYAL_NAVY_CORNED_BEEF = new Item(new Item.Settings().group(ItemGroup.FOOD).food(FoodComponents.ROYAL_NAVY_CORNED_BEEF));
	public static final Item TORPEDO_JUICE = new Item(new Item.Settings().group(ItemGroup.FOOD).food(FoodComponents.TORPEDO_JUICE));
	public static final Item CANNED_HERRING = new Item(new Item.Settings().group(ItemGroup.FOOD).food(FoodComponents.CANNED_HERRING));
	public static final Item BLACK_FOREST_CAKE = new Item(new Item.Settings().group(ItemGroup.FOOD).food(FoodComponents.BLACK_FOREST_CAKE));
	public static final Item MUSSOLINI_IS_ASS = new Item(new Item.Settings().group(ItemGroup.FOOD).food(FoodComponents.MUSSOLINI_IS_ASS));
	public static final Item YOKAN = new Item(new Item.Settings().group(ItemGroup.FOOD).food(FoodComponents.YOKAN));
	public static final Item FRIED_SHRIMP_TEMPURA = new Item(new Item.Settings().group(ItemGroup.FOOD).food(FoodComponents.FRIED_SHRIMP_TEMPURA));

	public static final Item MACARON = new Item(new Item.Settings().group(ItemGroup.FOOD).food(FoodComponents.MACARON));

	public static final Item HELEBA = new Item(new Item.Settings().group(ItemGroup.FOOD).food(FoodComponents.HELEBA));


	public static final Item SPICY_STRIPS = new Item(new Item.Settings().group(ItemGroup.FOOD).food(FoodComponents.SPICY_STRIPS));

	public static final Item ASSORTED_CHAR_SIEW_FRIED_RICE = new Item(new Item.Settings().group(ItemGroup.FOOD).food(FoodComponents.ASSORTED_CHAR_SIEW_FRIED_RICE));

	public static final Item GOLD_INLAID_JADE_BUNS = new Item(new Item.Settings().group(ItemGroup.FOOD).food(FoodComponents.GOLD_INLAID_JADE_BUNS));

	public static final Item SWEET_TOFU_BRAIN = new Item(new Item.Settings().group(ItemGroup.FOOD).food(FoodComponents.SWEET_TOFU_BRAIN));

	public static final Item SALTY_TOFU_BRAIN = new Item(new Item.Settings().group(ItemGroup.FOOD).food(FoodComponents.SALTY_TOFU_BRAIN));

	public static final Item AMERICAN_BURGER = new Item(new Item.Settings().group(ItemGroup.FOOD).food(FoodComponents.AMERICAN_BURGER));

	public static final Item BLACK_TEA_SANDWICH = new Item(new Item.Settings().group(ItemGroup.FOOD).food(FoodComponents.BLACK_TEA_SANDWICH));

	public static final Item ROAST_PORK_KNUCKLE = new Item(new Item.Settings().group(ItemGroup.FOOD).food(FoodComponents.ROAST_PORK_KNUCKLE));

	public static final Item LONGTIAN_YAKI = new Item(new Item.Settings().group(ItemGroup.FOOD).food(FoodComponents.LONGTIAN_YAKI));

	public static final Item SWEET_DUMPLING = new Item(new Item.Settings().group(ItemGroup.FOOD).food(FoodComponents.SWEET_DUMPLING));

	public static final Item TOMATO_BOLOGNESE_LASAGNA = new Item(new Item.Settings().group(ItemGroup.FOOD).food(FoodComponents.TOMATO_BOLOGNESE_LASAGNA));
	public static final Item KVASS = new Item(new Item.Settings().group(ItemGroup.FOOD).food(FoodComponents.KVASS));

	public static final Item EGG_BENEDICT = new Item(new Item.Settings().group(ItemGroup.FOOD).food(FoodComponents.EGG_BENEDICT));

	public static final Item RYE_BREAD_LENTIL_SOUP = new Item(new Item.Settings().group(ItemGroup.FOOD).food(FoodComponents.RYE_BREAD_LENTIL_SOUP));

	public static final Item BLACK_TEA_SCONES = new Item(new Item.Settings().group(ItemGroup.FOOD).food(FoodComponents.BLACK_TEA_SCONES));

	public static final Item GRILLED_FISH_AND_MISO_SOUP = new Item(new Item.Settings().group(ItemGroup.FOOD).food(FoodComponents.GRILLED_FISH_AND_MISO_SOUP));

	public static final Item HOT_DOG = new Item(new Item.Settings().group(ItemGroup.FOOD).food(FoodComponents.HOT_DOG));

	public static final Item TARTAN_APPLE_TART = new Item(new Item.Settings().group(ItemGroup.FOOD).food(FoodComponents.TARTAN_APPLE_TART));

	public static final Item TEMPURA_SOBA = new Item(new Item.Settings().group(ItemGroup.FOOD).food(FoodComponents.TEMPURA_SOBA));

	public static final Item YORKSHIRE_PUDDING = new Item(new Item.Settings().group(ItemGroup.FOOD).food(FoodComponents.YORKSHIRE_PUDDING));
	public static final Item BARBECUE = new Item(new Item.Settings().group(ItemGroup.FOOD).food(FoodComponents.BARBECUE));

	public static final Item DONGPO_PORK = new Item(new Item.Settings().group(ItemGroup.FOOD).food(FoodComponents.DONGPO_PORK));

	public static final Item RUSSIAN_DUMPLING = new Item(new Item.Settings().group(ItemGroup.FOOD).food(FoodComponents.RUSSIAN_DUMPLING));

	public static final Item RUSSIAN_ASPIC = new Item(new Item.Settings().group(ItemGroup.FOOD).food(FoodComponents.RUSSIAN_ASPIC));

	public static final Item VENETIAN_CUTTLEFISH_NOODLES = new Item(new Item.Settings().group(ItemGroup.FOOD).food(FoodComponents.VENETIAN_CUTTLEFISH_NOODLES));


	public static final Item SALT = new Item(new FabricItemSettings().group(ItemGroup.MISC));
	public static final Item MINCED_MEAT = new Item(new FabricItemSettings().group(ItemGroup.MISC));
	public static final Item ALCOHOL = new Item(new FabricItemSettings().group(ItemGroup.MISC));
	//public static final Item OIL = new Item(new FabricItemSettings().group(ItemGroup.MISC));

	//public static final Item SOY_MILK = new Item(new FabricItemSettings().group(ItemGroup.MISC));
	public static final Item TOFU = new Item(new FabricItemSettings().group(ItemGroup.MISC));

	public static final Item SOY_SAUCE = new Item(new FabricItemSettings().group(ItemGroup.MISC));
	public static final Item RYE = new Item(new FabricItemSettings().group(ItemGroup.MISC));

	public static final Item BUTTER = new Item(new FabricItemSettings().group(ItemGroup.MISC));

	public static final Item SHRIMP = new Item(new FabricItemSettings().group(ItemGroup.MISC));
	public static final Item CHEESE = new Item(new FabricItemSettings().group(ItemGroup.MISC));
	public static final Item BACON = new Item(new FabricItemSettings().group(ItemGroup.MISC));
	public static final Item SAUSAGE = new Item(new FabricItemSettings().group(ItemGroup.MISC));
	public static final Item MK14_TORPEDO = new Item(new FabricItemSettings().group(ItemGroup.MISC));
	public static final Item ONION = new Item(new FabricItemSettings().group(ItemGroup.MISC));
	public static final Item FLOUR = new Item(new FabricItemSettings().group(ItemGroup.MISC));
	public static final Item SOYBEAN = new Item(new FabricItemSettings().group(ItemGroup.MISC));
	public static final Item CABBAGE = new Item(new FabricItemSettings().group(ItemGroup.MISC));
	public static final Item TOMATO = new Item(new FabricItemSettings().group(ItemGroup.MISC));
	public static final Item CURRY = new Item(new FabricItemSettings().group(ItemGroup.MISC));

	public static final Item RICE = new Item(new FabricItemSettings().group(ItemGroup.MISC));

	public static final Item GARLIC = new Item(new FabricItemSettings().group(ItemGroup.MISC));

	public static final Item PINEAPPLE = new Item(new FabricItemSettings().group(ItemGroup.MISC));

	public static final Item CHERRY = new Item(new FabricItemSettings().group(ItemGroup.MISC));

	public static final Item RED_BEANS = new Item(new FabricItemSettings().group(ItemGroup.MISC));
	public static final Item PLASTER = new Item(new FabricItemSettings().group(ItemGroup.MISC));

	public static final Item STICKY_RICE = new Item(new FabricItemSettings().group(ItemGroup.MISC));
	public static final Item BLACK_TEA = new Item(new FabricItemSettings().group(ItemGroup.MISC));

	public static final Item SESAME = new Item(new FabricItemSettings().group(ItemGroup.MISC));
	public static final Item STARCH = new Item(new FabricItemSettings().group(ItemGroup.MISC));
	public static final Item GINGER = new Item(new FabricItemSettings().group(ItemGroup.MISC));

	public static final Item LENTILS = new Item(new FabricItemSettings().group(ItemGroup.MISC));

	public static final Item LETTUCE = new Item(new FabricItemSettings().group(ItemGroup.MISC));












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

		Registry.register(Registry.STATUS_EFFECT, new Identifier("shropshire", "loot"), LOOT_EFFECT);//此处为buff注册
		Registry.register(Registry.POTION, new Identifier("shropshire", "loot"), LOOT);//此处为药水注册
		Registry.register(Registry.POTION, new Identifier("shropshire", "oil"), OIL);
		Registry.register(Registry.POTION, new Identifier("shropshire", "soy_milk"), SOY_MILK);



		Registry.register(Registry.ITEM, new Identifier("shropshire", "test_food"), TEST_FOOD);//测试食物
		Registry.register(Registry.ITEM, new Identifier("shropshire", "toast"), TOAST);
		Registry.register(Registry.ITEM, new Identifier("shropshire", "salami_pizza"), SALAMI_PIZZA);
		Registry.register(Registry.ITEM, new Identifier("shropshire", "borscht"), BORSCHT);
		Registry.register(Registry.ITEM, new Identifier("shropshire", "navy_baked_beans"), NAVY_BAKED_BEANS);
		Registry.register(Registry.ITEM, new Identifier("shropshire", "apple_pie"), APPLE_PIE);
		Registry.register(Registry.ITEM, new Identifier("shropshire", "fish_and_chips"), FISH_AND_CHIPS);
		Registry.register(Registry.ITEM, new Identifier("shropshire", "thuringian_sausage_beer"), THURINGIAN_SAUSAGE_BEER);
		Registry.register(Registry.ITEM, new Identifier("shropshire", "white_sausage_bagels"), WHITE_SAUSAGE_BAGELS);
		Registry.register(Registry.ITEM, new Identifier("shropshire", "navy_curry"), NAVY_CURRY);
		Registry.register(Registry.ITEM, new Identifier("shropshire", "marseille_fish_soup"), MARSEILLE_FISH_SOUP);
		Registry.register(Registry.ITEM, new Identifier("shropshire", "mapo_tofu"), MAPO_TOFU);
		Registry.register(Registry.ITEM, new Identifier("shropshire", "looking_up_at_the_stars"), LOOKING_UP_AT_THE_STARS);
		Registry.register(Registry.ITEM, new Identifier("shropshire", "royal_navy_corned_beef"), ROYAL_NAVY_CORNED_BEEF);
		Registry.register(Registry.ITEM, new Identifier("shropshire", "torpedo_juice"), TORPEDO_JUICE);
		Registry.register(Registry.ITEM, new Identifier("shropshire", "canned_herring"), CANNED_HERRING);
		Registry.register(Registry.ITEM, new Identifier("shropshire", "black_forest_cake"), BLACK_FOREST_CAKE);
		Registry.register(Registry.ITEM, new Identifier("shropshire", "mussolini_is_ass"), MUSSOLINI_IS_ASS);
		Registry.register(Registry.ITEM, new Identifier("shropshire", "yokan"), YOKAN);
		Registry.register(Registry.ITEM, new Identifier("shropshire", "fried_shrimp_tempura"), FRIED_SHRIMP_TEMPURA);
		Registry.register(Registry.ITEM, new Identifier("shropshire", "macaron"), MACARON);
		Registry.register(Registry.ITEM, new Identifier("shropshire", "heleba"), HELEBA);

		Registry.register(Registry.ITEM, new Identifier("shropshire", "spicy_strips"), SPICY_STRIPS);

		Registry.register(Registry.ITEM, new Identifier("shropshire", "assorted_char_siew_fried_rice"), ASSORTED_CHAR_SIEW_FRIED_RICE);

		Registry.register(Registry.ITEM, new Identifier("shropshire", "gold_inlaid_jade_buns"), GOLD_INLAID_JADE_BUNS);

		Registry.register(Registry.ITEM, new Identifier("shropshire", "sweet_tofu_brain"), SWEET_TOFU_BRAIN);

		Registry.register(Registry.ITEM, new Identifier("shropshire", "salty_tofu_brain"), SALTY_TOFU_BRAIN);
		Registry.register(Registry.ITEM, new Identifier("shropshire", "american_burger"), AMERICAN_BURGER);

		Registry.register(Registry.ITEM, new Identifier("shropshire", "black_tea_sandwich"), BLACK_TEA_SANDWICH);

		Registry.register(Registry.ITEM, new Identifier("shropshire", "roast_pork_knuckle"), ROAST_PORK_KNUCKLE);

		Registry.register(Registry.ITEM, new Identifier("shropshire", "longtian_yaki"), LONGTIAN_YAKI);

		Registry.register(Registry.ITEM, new Identifier("shropshire", "sweet_dumpling"), SWEET_DUMPLING);

		Registry.register(Registry.ITEM, new Identifier("shropshire", "tomato_bolognese_lasagna"), TOMATO_BOLOGNESE_LASAGNA);

		Registry.register(Registry.ITEM, new Identifier("shropshire", "kvass"), KVASS);

		Registry.register(Registry.ITEM, new Identifier("shropshire", "egg_benedict"), EGG_BENEDICT);

		Registry.register(Registry.ITEM, new Identifier("shropshire", "rye_bread_lentil_soup"), RYE_BREAD_LENTIL_SOUP);

		Registry.register(Registry.ITEM, new Identifier("shropshire", "black_tea_scones"), BLACK_TEA_SCONES);

		Registry.register(Registry.ITEM, new Identifier("shropshire", "grilled_fish_and_miso_soup"), GRILLED_FISH_AND_MISO_SOUP);
		Registry.register(Registry.ITEM, new Identifier("shropshire", "hot_dog"), HOT_DOG);

		Registry.register(Registry.ITEM, new Identifier("shropshire", "tartan_apple_tart"), TARTAN_APPLE_TART);

		Registry.register(Registry.ITEM, new Identifier("shropshire", "tempura_soba"), TEMPURA_SOBA);

		Registry.register(Registry.ITEM, new Identifier("shropshire", "yorkshire_pudding"), YORKSHIRE_PUDDING);

		Registry.register(Registry.ITEM, new Identifier("shropshire", "barbecue"), BARBECUE);

		Registry.register(Registry.ITEM, new Identifier("shropshire", "dongpo_pork"), DONGPO_PORK);

		Registry.register(Registry.ITEM, new Identifier("shropshire", "russian_dumpling"), RUSSIAN_DUMPLING);

		Registry.register(Registry.ITEM, new Identifier("shropshire", "russian_aspic"), RUSSIAN_ASPIC);
		Registry.register(Registry.ITEM, new Identifier("shropshire", "venetian_cuttlefish_noodles"), VENETIAN_CUTTLEFISH_NOODLES);




		Registry.register(Registry.ITEM, new Identifier("shropshire", "salt"), SALT);
		Registry.register(Registry.ITEM, new Identifier("shropshire", "minced_meat"), MINCED_MEAT);
		Registry.register(Registry.ITEM, new Identifier("shropshire", "alcohol"), ALCOHOL);

		//Registry.register(Registry.STATUS_EFFECT, new Identifier("shropshire", "oil"), OIL);
		//Registry.register(Registry.ITEM, new Identifier("shropshire", "soy_milk"), SOY_MILK);
		Registry.register(Registry.ITEM, new Identifier("shropshire", "tofu"), TOFU);
		Registry.register(Registry.ITEM, new Identifier("shropshire", "soy_sauce"), SOY_SAUCE);
		Registry.register(Registry.ITEM, new Identifier("shropshire", "rye"), RYE);
		Registry.register(Registry.ITEM, new Identifier("shropshire", "butter"), BUTTER);
		Registry.register(Registry.ITEM, new Identifier("shropshire", "shrimp"), SHRIMP);
		Registry.register(Registry.ITEM, new Identifier("shropshire", "cheese"), CHEESE);
		Registry.register(Registry.ITEM, new Identifier("shropshire", "bacon"), BACON);
		Registry.register(Registry.ITEM, new Identifier("shropshire", "sausage"), SAUSAGE);
		Registry.register(Registry.ITEM, new Identifier("shropshire", "mk14_torpedo"), MK14_TORPEDO);
		Registry.register(Registry.ITEM, new Identifier("shropshire", "onion"), ONION);
		Registry.register(Registry.ITEM, new Identifier("shropshire", "flour"), FLOUR);
		Registry.register(Registry.ITEM, new Identifier("shropshire", "soybean"), SOYBEAN);
		Registry.register(Registry.ITEM, new Identifier("shropshire", "cabbage"), CABBAGE);
		Registry.register(Registry.ITEM, new Identifier("shropshire", "tomato"), TOMATO);
		Registry.register(Registry.ITEM, new Identifier("shropshire", "curry"), CURRY);
		Registry.register(Registry.ITEM, new Identifier("shropshire", "rice"), RICE);
		Registry.register(Registry.ITEM, new Identifier("shropshire", "garlic"), GARLIC);
		Registry.register(Registry.ITEM, new Identifier("shropshire", "pineapple"), PINEAPPLE);
		Registry.register(Registry.ITEM, new Identifier("shropshire", "cherry"), CHERRY);
		Registry.register(Registry.ITEM, new Identifier("shropshire", "red_beans"), RED_BEANS);
		Registry.register(Registry.ITEM, new Identifier("shropshire", "plaster"), PLASTER);
		Registry.register(Registry.ITEM, new Identifier("shropshire", "sticky_rice"), STICKY_RICE);
		Registry.register(Registry.ITEM, new Identifier("shropshire", "black_tea"), BLACK_TEA);
		Registry.register(Registry.ITEM, new Identifier("shropshire", "sesame"), SESAME);
		Registry.register(Registry.ITEM, new Identifier("shropshire", "starch"), STARCH);
		Registry.register(Registry.ITEM, new Identifier("shropshire", "ginger"), GINGER);
		Registry.register(Registry.ITEM, new Identifier("shropshire", "lentils"), LENTILS);
		Registry.register(Registry.ITEM, new Identifier("shropshire", "lettuce"), LETTUCE);












		//BlockRenderLayerMap.INSTANCE.putBlock(TEST_CROP, RenderLayer.getCutout());//测试作物的方块
		Registry.register(Registry.BLOCK, new Identifier("shropshire", "rice_crop"), RICE_CROP);//
		BlockRenderLayerMap.INSTANCE.putBlocks(RenderLayer.getCutout(), RICE_CROP);//作物方块的背景透明，后面的在这里加就行
		Registry.register(Registry.ITEM, new Identifier("shropshire", "rice_seeds"), RICE_SEEDS);//测试种子
		//Registry.register(Registry.ITEM, new Identifier( "shropshire", "rice_product"), RICE_PRODUC);



		RegisterItems.register();//盔甲部分注册




		LootTableEvents.MODIFY.register((resourceManager, lootManager, id, tableBuilder, source) -> {//劫持战利品表的
			if (source.isBuiltin() && GRAVEL_LOOT_TABLE_ID.equals(id)) {
				LootPool.Builder poolBuilder = LootPool.builder()
						.with(ItemEntry.builder(shropshire.SALT));//掉落物
				tableBuilder.pool(poolBuilder);}
			if (source.isBuiltin() && TALL_GRASS_LOOT_TABLE_ID.equals(id)) {//这里的RICE_CROP_LOOT_TABLE_ID和上面一致的喵
				LootPool.Builder poolBuilder = LootPool.builder()
						.with(ItemEntry.builder(shropshire.CABBAGE))//然后在这里加上添加的东西
						.with(ItemEntry.builder(shropshire.LETTUCE));

				tableBuilder.pool(poolBuilder);}
			if (source.isBuiltin() && SWEET_BERRY_BUSH_LOOT_TABLE_ID.equals(id)) {
				LootPool.Builder poolBuilder = LootPool.builder()
						.with(ItemEntry.builder(shropshire.CABBAGE));
				tableBuilder.pool(poolBuilder);}
			if (source.isBuiltin() && GRASS_BLOCK_LOOT_TABLE_ID.equals(id)) {
				LootPool.Builder poolBuilder = LootPool.builder()
						.with(ItemEntry.builder(shropshire.GARLIC))
						.with(ItemEntry.builder(shropshire.GINGER));
				tableBuilder.pool(poolBuilder);}
			if (source.isBuiltin() && BIRCH_LEAVES_LOOT_TABLE_ID.equals(id)) {
				LootPool.Builder poolBuilder = LootPool.builder()
						.with(ItemEntry.builder(shropshire.PINEAPPLE));
				tableBuilder.pool(poolBuilder);}
			if (source.isBuiltin() && NETHER_WART_LOOT_TABLE_ID.equals(id)) {
				LootPool.Builder poolBuilder = LootPool.builder()
						.with(ItemEntry.builder(shropshire.CHERRY));
				tableBuilder.pool(poolBuilder);}

			if (source.isBuiltin() && MANGROVE_LEAVES_LOOT_TABLE_ID.equals(id)) {
				LootPool.Builder poolBuilder = LootPool.builder()
						.with(ItemEntry.builder(shropshire.RED_BEANS));
				tableBuilder.pool(poolBuilder);}
			if (source.isBuiltin() && STONE_LOOT_TABLE_ID.equals(id)) {
				LootPool.Builder poolBuilder = LootPool.builder()
						.with(ItemEntry.builder(shropshire.PLASTER));
				tableBuilder.pool(poolBuilder);}
			if (source.isBuiltin() && FERN_LOOT_TABLE_ID.equals(id)) {
				LootPool.Builder poolBuilder = LootPool.builder()
						.with(ItemEntry.builder(shropshire.SESAME));
				tableBuilder.pool(poolBuilder);}
			if (source.isBuiltin() && GRASS_BLOCK_LOOT_TABLE_ID.equals(id)) {
				LootPool.Builder poolBuilder = LootPool.builder()
						.with(ItemEntry.builder(shropshire.GINGER));
				tableBuilder.pool(poolBuilder);}

			if (source.isBuiltin() && GRASS_LOOT_TABLE_ID.equals(id)) {
				LootPool.Builder poolBuilder = LootPool.builder()
						.with(ItemEntry.builder(shropshire.LENTILS));
				tableBuilder.pool(poolBuilder);}

		});

	}



	}






