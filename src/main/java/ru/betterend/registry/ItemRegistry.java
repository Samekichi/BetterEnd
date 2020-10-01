package ru.betterend.registry;

import java.util.List;

import com.google.common.collect.Lists;

import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.Items;
import net.minecraft.item.ShovelItem;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolItem;
import net.minecraft.util.registry.Registry;
import ru.betterend.BetterEnd;
import ru.betterend.item.EndArmorMaterial;
import ru.betterend.item.EndAxe;
import ru.betterend.item.EndHoe;
import ru.betterend.item.EndPickaxe;
import ru.betterend.item.EndToolMaterial;

public class ItemRegistry {
	private static final List<Item> MOD_BLOCKS = Lists.newArrayList();
	private static final List<Item> MOD_ITEMS = Lists.newArrayList();
	
	//Materials
	public final static Item ENDER_DUST = registerItem("ender_dust", new Item((new Item.Settings()).group(ItemGroup.MATERIALS)));
	public final static Item TERMINITE_INGOT = registerItem("terminite_ingot", new Item((new Item.Settings()).group(ItemGroup.MATERIALS)));
	public final static Item AETERNIUM_INGOT = registerItem("aeternium_ingot", new Item((new Item.Settings()).group(ItemGroup.MATERIALS)));
	
	//Armor
	public static final Item TERMINITE_HELMET = registerItem("terminite_helmet", new ArmorItem(EndArmorMaterial.TERMINITE, EquipmentSlot.HEAD,new Item.Settings().group(ItemGroup.COMBAT)));
	public static final Item TERMINITE_CHESTPLATE = registerItem("terminite_chestplate", new ArmorItem(EndArmorMaterial.TERMINITE, EquipmentSlot.CHEST, new Item.Settings().group(ItemGroup.COMBAT)));
	public static final Item TERMINITE_LEGGINGS = registerItem("terminite_leggings", new ArmorItem(EndArmorMaterial.TERMINITE, EquipmentSlot.LEGS, new Item.Settings().group(ItemGroup.COMBAT)));
	public static final Item TERMINITE_BOOTS = registerItem("terminite_boots", new ArmorItem(EndArmorMaterial.TERMINITE, EquipmentSlot.FEET, new Item.Settings().group(ItemGroup.COMBAT)));
	public static final Item AETERNIUM_HELMET = registerItem("aeternium_helmet", new ArmorItem(EndArmorMaterial.AETERNIUM, EquipmentSlot.HEAD,new Item.Settings().group(ItemGroup.COMBAT)));
	public static final Item AETERNIUM_CHESTPLATE = registerItem("aeternium_chestplate", new ArmorItem(EndArmorMaterial.AETERNIUM, EquipmentSlot.CHEST, new Item.Settings().group(ItemGroup.COMBAT)));
	public static final Item AETERNIUM_LEGGINGS = registerItem("aeternium_leggings", new ArmorItem(EndArmorMaterial.AETERNIUM, EquipmentSlot.LEGS, new Item.Settings().group(ItemGroup.COMBAT)));
	public static final Item AETERNIUM_BOOTS = registerItem("aeternium_boots", new ArmorItem(EndArmorMaterial.AETERNIUM, EquipmentSlot.FEET, new Item.Settings().group(ItemGroup.COMBAT)));
	
	//Tools
	public static ToolItem TERMINITE_SHOVEL = registerTool("terminite_shovel", new ShovelItem(EndToolMaterial.TERMINITE, 1.5F, -3.0F, new Item.Settings().group(ItemGroup.TOOLS)));
	public static ToolItem TERMINITE_SWORD = registerTool("terminite_sword", new SwordItem(EndToolMaterial.TERMINITE, 3, -2.4F, new Item.Settings().group(ItemGroup.COMBAT)));
	public static ToolItem TERMINITE_PICKAXE = registerTool("terminite_pickaxe", new EndPickaxe(EndToolMaterial.TERMINITE, 1, -2.8F, new Item.Settings().group(ItemGroup.TOOLS)));
	public static ToolItem TERMINITE_AXE = registerTool("terminite_axe", new EndAxe(EndToolMaterial.TERMINITE, 5.0F, -3.0F, new Item.Settings().group(ItemGroup.TOOLS)));
	public static ToolItem TERMINITE_HOE = registerTool("terminite_hoe", new EndHoe(EndToolMaterial.TERMINITE, -3, 0.0F, new Item.Settings().group(ItemGroup.TOOLS)));

	protected static Item registerItem(String name, Item item) {
		if (item != Items.AIR) {
			Registry.register(Registry.ITEM, BetterEnd.getResId(name), item);
			if (item instanceof BlockItem)
				MOD_BLOCKS.add(item);
			else
				MOD_ITEMS.add(item);
		}
		return item;
	}
	
	protected static ToolItem registerTool(String name, ToolItem item) {
		if (item != Items.AIR) {
			Registry.register(Registry.ITEM, BetterEnd.getResId(name), item);
			MOD_ITEMS.add(item);
		}
		return item;
	}

	public static void register() {}
	
	public static List<Item> getModBlocks() {
		return MOD_BLOCKS;
	}

	public static List<Item> getModItems() {
		return MOD_ITEMS;
	}
}
