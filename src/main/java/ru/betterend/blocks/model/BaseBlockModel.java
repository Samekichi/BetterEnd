package ru.betterend.blocks.model;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.function.Function;
import java.util.function.Supplier;

import com.google.common.math.Quantiles;
import com.mojang.datafixers.util.Pair;

import net.fabricmc.fabric.api.renderer.v1.Renderer;
import net.fabricmc.fabric.api.renderer.v1.RendererAccess;
import net.fabricmc.fabric.api.renderer.v1.mesh.Mesh;
import net.fabricmc.fabric.api.renderer.v1.mesh.MeshBuilder;
import net.fabricmc.fabric.api.renderer.v1.mesh.MutableQuadView;
import net.fabricmc.fabric.api.renderer.v1.mesh.QuadEmitter;
import net.fabricmc.fabric.api.renderer.v1.model.FabricBakedModel;
import net.fabricmc.fabric.api.renderer.v1.render.RenderContext;

import net.minecraft.block.BlockState;
import net.minecraft.client.render.model.BakedModel;
import net.minecraft.client.render.model.BakedQuad;
import net.minecraft.client.render.model.ModelBakeSettings;
import net.minecraft.client.render.model.ModelLoader;
import net.minecraft.client.render.model.UnbakedModel;
import net.minecraft.client.render.model.json.JsonUnbakedModel;
import net.minecraft.client.render.model.json.ModelOverrideList;
import net.minecraft.client.render.model.json.ModelTransformation;
import net.minecraft.client.texture.Sprite;
import net.minecraft.client.texture.SpriteAtlasTexture;
import net.minecraft.client.util.SpriteIdentifier;
import net.minecraft.client.util.math.AffineTransformation;
import net.minecraft.client.util.math.Vector3f;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Matrix3f;
import net.minecraft.util.math.Quaternion;
import net.minecraft.world.BlockRenderView;

import ru.betterend.BetterEnd;

public class BaseBlockModel implements UnbakedModel, BakedModel, FabricBakedModel {

	private static final Identifier DEFAULT_BLOCK_MODEL = new Identifier("minecraft:block/block");
	
	private final SpriteIdentifier[] spritesIDs;
    private final Sprite[] sprites;
    private ModelTransformation transformation;
    private Mesh mesh;

	public BaseBlockModel(String... textures) {
		this.spritesIDs = new SpriteIdentifier[textures.length];
		this.sprites = new Sprite[textures.length];
		for (int i = 0; i < textures.length; i++) {
			this.spritesIDs[i] = new SpriteIdentifier(SpriteAtlasTexture.BLOCK_ATLAS_TEXTURE, BetterEnd.makeID(textures[i]));
		}
	}
    
    @Override
	public BakedModel bake(ModelLoader loader, Function<SpriteIdentifier, Sprite> textureGetter,
			ModelBakeSettings rotationContainer, Identifier modelId) {
		for(int i = 0; i < sprites.length; i++) {
			this.sprites[i] = textureGetter.apply(spritesIDs[i]);
        }
		
		JsonUnbakedModel jsonBlockModel = (JsonUnbakedModel) loader.getOrLoadModel(DEFAULT_BLOCK_MODEL);
		this.transformation = jsonBlockModel.getTransformations();
		
		Renderer renderer = RendererAccess.INSTANCE.getRenderer();
		MeshBuilder builder = renderer.meshBuilder();
		QuadEmitter emitter = builder.getEmitter();
		
		Vector3f rotation = AffineTransformation.getLinearTransformationAndTranslationFromAffine(rotationContainer.getRotation().getMatrix()).getSecond();
		
		System.out.println("=====");
		System.out.println(rotation);
		
		Direction[] directions = Direction.values();
		for (Direction direction : directions) {
			Direction rotate = Direction.transform(rotationContainer.getRotation().getMatrix(), direction);
			this.rotate(emitter, direction, rotate);
			switch (sprites.length) {
				case 1: {
					emitter.spriteBake(0, sprites[0], MutableQuadView.BAKE_LOCK_UV);
					break;
				}
				case 2: {
					switch (direction) {
						case DOWN:
						case UP: {
							emitter.spriteBake(0, sprites[0], MutableQuadView.BAKE_LOCK_UV);
							break;
						}
						default: {
							emitter.spriteBake(0, sprites[1], MutableQuadView.BAKE_LOCK_UV);
						}
					}
				}
				case 3: {
					switch (direction) {
						case DOWN:
						case UP: {
							emitter.spriteBake(0, sprites[0], MutableQuadView.BAKE_LOCK_UV);
							break;
						}
						case NORTH:
						case SOUTH: {
							emitter.spriteBake(0, sprites[1], MutableQuadView.BAKE_LOCK_UV);
							break;
						}
						default: {
							emitter.spriteBake(0, sprites[2], MutableQuadView.BAKE_LOCK_UV);
						}
					}
				}
				case 4: {
					switch (direction) {
						case DOWN:
						case UP: {
							emitter.spriteBake(0, sprites[0], MutableQuadView.BAKE_LOCK_UV);
							break;
						}
						case NORTH: {
							emitter.spriteBake(0, sprites[1], MutableQuadView.BAKE_LOCK_UV);
							break;
						}
						case SOUTH: {
							emitter.spriteBake(0, sprites[2], MutableQuadView.BAKE_LOCK_UV);
							break;
						}
						default: {
							emitter.spriteBake(0, sprites[3], MutableQuadView.BAKE_LOCK_UV);
						}
					}
				}
				case 5: {
					switch (direction) {
						case DOWN:
						case UP:
						case NORTH:
						case SOUTH: {
							emitter.spriteBake(0, sprites[direction.ordinal()], MutableQuadView.BAKE_LOCK_UV);
							break;
						}
						default: {
							emitter.spriteBake(0, sprites[4], MutableQuadView.BAKE_LOCK_UV);
						}
					}
				}
				default: {
					emitter.spriteBake(0, sprites[direction.ordinal()], MutableQuadView.BAKE_LOCK_UV);
				}
			}

			emitter.spriteColor(0, -1, -1, -1, -1);
			emitter.emit();
		}
		this.mesh = builder.build();
		
		return this;
	}
    
    private void rotate(QuadEmitter emitter, Direction direction, Direction rotation) {
    	switch (direction) {
			case NORTH: {
				switch (rotation) {
					case DOWN:	
					case SOUTH: {
						emitter.square(rotation, 1.0f, 1.0f, 0.0f, 0.0f, 0.0f);
						break;
					}
					case EAST: {
						emitter.square(rotation, 0.0f, 1.0f, 1.0f, 0.0f, 0.0f);
						break;
					}
					case WEST: {
						emitter.square(rotation, 1.0f, 0.0f, 0.0f, 1.0f, 0.0f);
						break;
					}
					default: {
						emitter.square(rotation, 0.0f, 0.0f, 1.0f, 1.0f, 0.0f);
					}
				}
				break;
			}
			case SOUTH: {
				switch (rotation) {
					case DOWN:
					case NORTH: {
						emitter.square(rotation, 1.0f, 1.0f, 0.0f, 0.0f, 0.0f);
						break;
					}
					case EAST: {
						emitter.square(rotation, 1.0f, 0.0f, 0.0f, 1.0f, 0.0f);
						break;
					}
					case WEST: {
						emitter.square(rotation, 0.0f, 1.0f, 1.0f, 0.0f, 0.0f);
						break;
					}
					default: {
						emitter.square(rotation, 0.0f, 0.0f, 1.0f, 1.0f, 0.0f);
					}
				}
				break;
			}
			case EAST: {
				switch (rotation) {
					case NORTH: {
						emitter.square(rotation, 0.0f, 1.0f, 1.0f, 0.0f, 0.0f);
						break;
					}
					case SOUTH: {
						emitter.square(rotation, 1.0f, 0.0f, 0.0f, 1.0f, 0.0f);
						break;
					}
					case DOWN:
					case WEST: {
						emitter.square(rotation, 1.0f, 1.0f, 0.0f, 0.0f, 0.0f);
						break;
					}
					default: {
						emitter.square(rotation, 0.0f, 0.0f, 1.0f, 1.0f, 0.0f);
					}
				}
				break;
			}
			case WEST: {
				switch (rotation) {
					case NORTH: {
						emitter.square(rotation, 0.0f, 0.0f, 1.0f, 1.0f, 0.0f);
						break;
					}
					case SOUTH: {
						emitter.square(rotation, 0.0f, 0.0f, 1.0f, 1.0f, 0.0f);
						break;
					}
					case DOWN:
					case EAST: {
						emitter.square(rotation, 1.0f, 1.0f, 0.0f, 0.0f, 0.0f);
						break;
					}
					default: {
						emitter.square(rotation, 0.0f, 0.0f, 1.0f, 1.0f, 0.0f);
					}
				}
				break;
			}
			case DOWN: {
				switch (rotation) {
					case EAST: {
						emitter.square(rotation, 1.0f, 0.0f, 0.0f, 1.0f, 0.0f);
						break;
					}
					case WEST: {
						emitter.square(rotation, 0.0f, 1.0f, 1.0f, 0.0f, 0.0f);
						break;
					}
					case SOUTH:
					case UP: {
						emitter.square(rotation, 1.0f, 1.0f, 0.0f, 0.0f, 0.0f);
						break;
					}
					default: {
						emitter.square(rotation, 0.0f, 0.0f, 1.0f, 1.0f, 0.0f);
					}
				}
				break;
			}
			case UP: {
				switch (rotation) {
					case EAST: {
						emitter.square(rotation, 0.0f, 1.0f, 1.0f, 0.0f, 0.0f);
						break;
					}
					case WEST: {
						emitter.square(rotation, 1.0f, 0.0f, 0.0f, 1.0f, 0.0f);
						break;
					}
					case SOUTH:
					case DOWN: {
						emitter.square(rotation, 1.0f, 1.0f, 0.0f, 0.0f, 0.0f);
						break;
					}
					default: {
						emitter.square(rotation, 0.0f, 0.0f, 1.0f, 1.0f, 0.0f);
					}
				}
				break;
			}
		}
	}
	
	@Override
	public boolean isVanillaAdapter() {
		return false;
	}

	@Override
	public void emitBlockQuads(BlockRenderView blockView, BlockState state, BlockPos pos,
			Supplier<Random> randomSupplier, RenderContext context) {
		context.meshConsumer().accept(mesh);
	}

	@Override
	public void emitItemQuads(ItemStack stack, Supplier<Random> randomSupplier, RenderContext context) {
		context.meshConsumer().accept(mesh);
	}

	@Override
	public List<BakedQuad> getQuads(BlockState state, Direction face, Random random) {
		return null;
	}

	@Override
	public boolean useAmbientOcclusion() {
		return false;
	}

	@Override
	public boolean hasDepth() {
		return false;
	}

	@Override
	public boolean isSideLit() {
		return true;
	}

	@Override
	public boolean isBuiltin() {
		return false;
	}

	@Override
	public Sprite getSprite() {
		return this.sprites[0];
	}

	@Override
	public ModelTransformation getTransformation() {
		return this.transformation;
	}

	@Override
	public ModelOverrideList getOverrides() {
		return ModelOverrideList.EMPTY;
	}

	@Override
	public Collection<Identifier> getModelDependencies() {
		return Arrays.asList(DEFAULT_BLOCK_MODEL);
	}

	@Override
	public Collection<SpriteIdentifier> getTextureDependencies(Function<Identifier, UnbakedModel> unbakedModelGetter,
			Set<Pair<String, String>> unresolvedTextureReferences) {
		return Arrays.asList(spritesIDs);
	}
}
