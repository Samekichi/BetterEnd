package ru.betterend.interfaces;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import net.minecraft.client.MinecraftClient;
import net.minecraft.resource.ResourceManager;
import net.minecraft.util.Identifier;
import ru.betterend.BetterEnd;

public interface Patterned {
	//Blockstates
	public final static Identifier STATE_SIMPLE = BetterEnd.makeID("patterns/blockstate/pattern_block.json");
	public final static Identifier STATE_SLAB = BetterEnd.makeID("patterns/blockstate/pattern_slab.json");
	public final static Identifier STATE_STAIRS = BetterEnd.makeID("patterns/blockstate/pattern_stairs.json");
	public final static Identifier STATE_WALL = BetterEnd.makeID("patterns/blockstate/pattern_wall.json");
	public final static Identifier STATE_FENCE = BetterEnd.makeID("patterns/blockstate/pattern_fence.json");
	public final static Identifier STATE_BUTTON = BetterEnd.makeID("patterns/blockstate/pattern_button.json");
	public final static Identifier STATE_PILLAR = BetterEnd.makeID("patterns/blockstate/pattern_pillar.json");
	public final static Identifier STATE_PLATE = BetterEnd.makeID("patterns/blockstate/pattern_pressure_plate.json");
	public final static Identifier STATE_DOOR = BetterEnd.makeID("patterns/blockstate/pattern_door.json");
	public final static Identifier STATE_SAPLING = BetterEnd.makeID("patterns/blockstate/pattern_sapling.json");
	public final static Identifier STATE_GATE = BetterEnd.makeID("patterns/blockstate/pattern_fence_gate.json");
	public final static Identifier STATE_TRAPDOOR = BetterEnd.makeID("patterns/blockstate/pattern_trapdoor.json");
	public final static Identifier STATE_LADDER = BetterEnd.makeID("patterns/blockstate/pattern_ladder.json");
	public final static Identifier STATE_BARREL = BetterEnd.makeID("patterns/blockstate/pattern_barrel.json");
	public final static Identifier STATE_PEDESTAL = BetterEnd.makeID("patterns/blockstate/pattern_pedestal.json");
	public final static Identifier STATE_STONE_LANTERN = BetterEnd.makeID("patterns/blockstate/stone_lantern.json");
	
	//Models Block
	public final static Identifier BLOCK_EMPTY = BetterEnd.makeID("patterns/block/pattern_empty.json");
	public final static Identifier BLOCK_BASE = BetterEnd.makeID("patterns/block/pattern_block.json");
	public final static Identifier BLOCK_SIDED = BetterEnd.makeID("patterns/block/pattern_block_sided.json");
	public final static Identifier BLOCK_BOTTOM_TOP = BetterEnd.makeID("patterns/block/pattern_block_bottom_top.json");
	public final static Identifier BLOCK_SLAB = BetterEnd.makeID("patterns/block/pattern_slab.json");
	public final static Identifier BLOCK_STAIR = BetterEnd.makeID("patterns/block/pattern_stairs.json");
	public final static Identifier BLOCK_STAIR_INNER = BetterEnd.makeID("patterns/block/pattern_inner_stairs.json");
	public final static Identifier BLOCK_STAIR_OUTER = BetterEnd.makeID("patterns/block/pattern_outer_stairs.json");
	public final static Identifier BLOCK_WALL_POST = BetterEnd.makeID("patterns/block/pattern_wall_post.json");
	public final static Identifier BLOCK_WALL_SIDE = BetterEnd.makeID("patterns/block/pattern_wall_side.json");
	public final static Identifier BLOCK_WALL_SIDE_TALL = BetterEnd.makeID("patterns/block/pattern_wall_side_tall.json");
	public final static Identifier BLOCK_FENCE_POST = BetterEnd.makeID("patterns/block/pattern_fence_post.json");
	public final static Identifier BLOCK_FENCE_SIDE = BetterEnd.makeID("patterns/block/pattern_fence_side.json");
	public final static Identifier BLOCK_BUTTON = BetterEnd.makeID("patterns/block/pattern_button.json");
	public final static Identifier BLOCK_BUTTON_PRESSED = BetterEnd.makeID("patterns/block/pattern_button_pressed.json");
	public final static Identifier BLOCK_PILLAR = BetterEnd.makeID("patterns/block/pattern_pillar.json");
	public final static Identifier BLOCK_PLATE_UP = BetterEnd.makeID("patterns/block/pattern_pressure_plate_up.json");
	public final static Identifier BLOCK_PLATE_DOWN = BetterEnd.makeID("patterns/block/pattern_pressure_plate_down.json");
	public final static Identifier BLOCK_DOOR_TOP = BetterEnd.makeID("patterns/block/pattern_door_top.json");
	public final static Identifier BLOCK_DOOR_TOP_HINGE = BetterEnd.makeID("patterns/block/pattern_door_top_hinge.json");
	public final static Identifier BLOCK_DOOR_BOTTOM = BetterEnd.makeID("patterns/block/pattern_door_bottom.json");
	public final static Identifier BLOCK_DOOR_BOTTOM_HINGE = BetterEnd.makeID("patterns/block/pattern_door_bottom_hinge.json");
	public final static Identifier BLOCK_SAPLING = BetterEnd.makeID("patterns/block/pattern_sapling.json");
	public final static Identifier BLOCK_GATE_CLOSED = BetterEnd.makeID("patterns/block/pattern_fence_gate_closed.json");
	public final static Identifier BLOCK_GATE_CLOSED_WALL = BetterEnd.makeID("patterns/block/pattern_wall_gate_closed.json");
	public final static Identifier BLOCK_GATE_OPEN = BetterEnd.makeID("patterns/block/pattern_fence_gate_open.json");
	public final static Identifier BLOCK_GATE_OPEN_WALL = BetterEnd.makeID("patterns/block/pattern_wall_gate_open.json");
	public final static Identifier BLOCK_TRAPDOOR = BetterEnd.makeID("patterns/block/pattern_trapdoor.json");
	public final static Identifier BLOCK_LADDER = BetterEnd.makeID("patterns/block/pattern_ladder.json");
	public final static Identifier BLOCK_BARREL_OPEN = BetterEnd.makeID("patterns/block/pattern_barrel_open.json");
	public final static Identifier BLOCK_PEDESTAL_DEFAULT = BetterEnd.makeID("patterns/block/pattern_pedestal_default.json");
	public final static Identifier BLOKC_PEDESTAL_COLUMN = BetterEnd.makeID("patterns/block/pattern_pedestal_column.json");
	public final static Identifier BLOCK_PEDESTAL_COLUMN_TOP = BetterEnd.makeID("patterns/block/pattern_pedestal_column_top.json");
	public final static Identifier BLOCK_PEDESTAL_TOP = BetterEnd.makeID("patterns/block/pattern_pedestal_top.json");
	public final static Identifier BLOCK_PEDESTAL_BOTTOM = BetterEnd.makeID("patterns/block/pattern_pedestal_bottom.json");
	public final static Identifier BLOCK_PEDESTAL_PILLAR = BetterEnd.makeID("patterns/block/pattern_pedestal_pillar.json");
	public final static Identifier BLOCK_BOOKSHELF = BetterEnd.makeID("patterns/block/bookshelf.json");
	public final static Identifier BLOCK_STONE_LANTERN_CEIL = BetterEnd.makeID("patterns/block/stone_lantern_ceil.json");
	public final static Identifier BLOCK_STONE_LANTERN_FLOOR = BetterEnd.makeID("patterns/block/stone_lantern_floor.json");
	
	//Models Item
	public final static Identifier ITEM_WALL = BetterEnd.makeID("patterns/item/pattern_wall.json");
	public final static Identifier ITEM_FENCE = BetterEnd.makeID("patterns/item/pattern_fence.json");
	public final static Identifier ITEM_BUTTON = BetterEnd.makeID("patterns/item/pattern_button.json");
	public final static Identifier ITEM_CHEST = BetterEnd.makeID("patterns/item/pattern_chest.json");
	public final static Identifier ITEM_BLOCK = BetterEnd.makeID("patterns/item/pattern_block_item.json");
	public final static Identifier ITEM_MODEL = BetterEnd.makeID("patterns/item/pattern_item.json");

	default String getStatesPattern(Reader data) {
		return null;
	}
	
	default String getModelPattern(String name) {
		return null;
	}
	
	default Identifier statePatternId() {
		return null;
	}
	
	default Identifier modelPatternId() {
		return null;
	}
	
	public static String createJson(Reader data, Identifier parent, String name) {
		try (BufferedReader buffer = new BufferedReader(data)) {
			return buffer.lines().collect(Collectors.joining())
					.replace("%parent%", parent.getPath())
					.replace("%block%", name);
		} catch (Exception ex) {
			return null;
		}
	}
	
	public static String createJson(Identifier patternId, Identifier parent, String block) {
		ResourceManager resourceManager = MinecraftClient.getInstance().getResourceManager();
		try (InputStream input = resourceManager.getResource(patternId).getInputStream()) {
			return new BufferedReader(new InputStreamReader(input, StandardCharsets.UTF_8))
					.lines().collect(Collectors.joining())
						.replace("%parent%", parent.getPath())
						.replace("%block%", block);
		} catch (Exception ex) {
			return null;
		}
	}
	
	public static String createJson(Identifier patternId, String texture) {
		ResourceManager resourceManager = MinecraftClient.getInstance().getResourceManager();
		try (InputStream input = resourceManager.getResource(patternId).getInputStream()) {
			return new BufferedReader(new InputStreamReader(input, StandardCharsets.UTF_8))
					.lines().collect(Collectors.joining())
					.replace("%texture%", texture);
		} catch (Exception ex) {
			return null;
		}
	}
	
	public static String createJson(Identifier patternId, Map<String, String> textures) {
		ResourceManager resourceManager = MinecraftClient.getInstance().getResourceManager();
		try (InputStream input = resourceManager.getResource(patternId).getInputStream()) {
			String json = new BufferedReader(new InputStreamReader(input, StandardCharsets.UTF_8))
					.lines().collect(Collectors.joining());
			for (Entry<String, String> texture : textures.entrySet()) {
				json = json.replace(texture.getKey(), texture.getValue());
			}
			return json;
		} catch (Exception ex) {
			return null;
		}
	}
}
