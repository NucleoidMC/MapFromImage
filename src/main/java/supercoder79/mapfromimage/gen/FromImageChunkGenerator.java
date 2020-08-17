package supercoder79.mapfromimage.gen;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;
import java.util.function.Supplier;

import javax.imageio.ImageIO;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import kdotjpg.opensimplex.OpenSimplexNoise;
import supercoder79.mapfromimage.biome.BiomeGen;
import supercoder79.mapfromimage.feature.ImprovedDiskGen;
import supercoder79.mapfromimage.feature.ShrubGen;
import xyz.nucleoid.plasmid.game.gen.feature.GrassGen;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;
import net.minecraft.world.ChunkRegion;
import net.minecraft.world.Heightmap;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.biome.source.BiomeAccess;
import net.minecraft.world.biome.source.BiomeSource;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.StructureAccessor;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.chunk.StructuresConfig;

public class FromImageChunkGenerator extends ChunkGenerator {
	public static final Codec<FromImageChunkGenerator> CODEC = RecordCodecBuilder.create(instance ->
			instance.group(
					FromImageBiomeSource.CODEC.fieldOf("biome_source").forGetter(generator -> generator.biomeSource),
					Codec.LONG.fieldOf("seed").stable().forGetter(generator -> generator.seed)
			).apply(instance, instance.stable(FromImageChunkGenerator::new)));
	private final FromImageBiomeSource biomeSource;
	private final long seed;

	private final int sizeX;
	private final int sizeZ;
	private final OpenSimplexNoise baseNoise;
	private final OpenSimplexNoise detailNoise;


	public FromImageChunkGenerator(FromImageBiomeSource biomeSource, long seed) {
		super(biomeSource, new StructuresConfig(false));
		this.biomeSource = biomeSource;
		this.seed = seed;

		sizeX = biomeSource.image.getWidth() / 2;
		sizeZ = biomeSource.image.getHeight() / 2;

		Random random = new Random();
		baseNoise = new OpenSimplexNoise(random.nextLong());
		detailNoise = new OpenSimplexNoise(random.nextLong());
	}

	@Override
	protected Codec<? extends ChunkGenerator> getCodec() {
		return CODEC;
	}

	@Override
	public ChunkGenerator withSeed(long seed) {
		return this;
	}

	@Override
	public void populateNoise(WorldAccess world, StructureAccessor accessor, Chunk chunk) {
		int chunkX = chunk.getPos().x * 16;
		int chunkZ = chunk.getPos().z * 16;

		BlockPos.Mutable mutable = new BlockPos.Mutable();
		for (int x = chunkX; x < chunkX + 16; x++) {
		    for (int z = chunkZ; z < chunkZ + 16; z++) {
		    	// Ensure that we're in bounds of the image
		    	if (x >= -sizeX && z >= -sizeZ && x < sizeX && z < sizeZ) {

		    		double height = baseNoise.eval(x / 256.0, z / 256.0) * 6;
					height += detailNoise.eval(x / 16.0, z / 16.0) * 3;

					height += 54;

					int baseHeight = (int) height;
					int genHeight = (int) Math.max(height, 48);
					for (int y = 0; y <= genHeight; y++) {
						// Simple surface building
						BlockState state = Blocks.STONE.getDefaultState();
						if (y == baseHeight) {
							// If the height and the generation height are the same, it means that we're on land
							if (baseHeight == genHeight) {
								state = Blocks.GRASS_BLOCK.getDefaultState();
							} else {
								// height and genHeight are different, so we're under water. Place dirt instead of grass.
								state = Blocks.DIRT.getDefaultState();
							}
						} else if ((baseHeight - y) <= 3) { //TODO: biome controls under depth
							state = Blocks.DIRT.getDefaultState();
						} else if (y == 0) {
							state = Blocks.BEDROCK.getDefaultState();
						}

						// If the y is higher than the land height, then we must place water
						if (y > baseHeight) {
							state = Blocks.WATER.getDefaultState();
						}

						// Set the state here
						chunk.setBlockState(mutable.set(x, y, z), state, false);
					}
				}
		    }
		}
	}

	@Override
	public void carve(long seed, BiomeAccess access, Chunk chunk, GenerationStep.Carver carver) {

	}

	@Override
	public void buildSurface(ChunkRegion region, Chunk chunk) {

	}

	@Override
	public void generateFeatures(ChunkRegion region, StructureAccessor structures) {
		BlockPos.Mutable mutable = new BlockPos.Mutable();
		Random random = new Random();

		int chunkX = region.getCenterChunkX() * 16;
		int chunkZ = region.getCenterChunkZ() * 16;

		BiomeGen biome = biomeSource.fromImage(chunkX + 8, chunkZ + 8);

		int treeAmt = biome.treeAmt(random);
		for (int i = 0; i < treeAmt; i++) {
			int x = chunkX + random.nextInt(16);
			int z = chunkZ + random.nextInt(16);
			int y = region.getTopY(Heightmap.Type.WORLD_SURFACE_WG, x, z);

			biome.tree(x, z, random).generate(region, mutable.set(x, y, z).toImmutable(), random);
		}

		int shrubAmt = biome.shrubAmt(random);
		for (int i = 0; i < shrubAmt; i++) {
			int x = chunkX + random.nextInt(16);
			int z = chunkZ + random.nextInt(16);
			int y = region.getTopY(Heightmap.Type.WORLD_SURFACE_WG, x, z);

			ShrubGen.INSTANCE.generate(region, mutable.set(x, y, z).toImmutable(), random);
		}

		int grassAmt = biome.grassAmt(random);
		for (int i = 0; i < grassAmt; i++) {
			int x = chunkX + random.nextInt(16);
			int z = chunkZ + random.nextInt(16);
			int y = region.getTopY(Heightmap.Type.WORLD_SURFACE_WG, x, z);

			GrassGen.INSTANCE.generate(region, mutable.set(x, y, z).toImmutable(), random);
		}

		for (int i = 0; i < 4; i++) {
			int x = chunkX + random.nextInt(16);
			int z = chunkZ + random.nextInt(16);
			int y = region.getTopY(Heightmap.Type.OCEAN_FLOOR_WG, x, z);

			if (y <= 48) {
				ImprovedDiskGen.INSTANCE.generate(region, mutable.set(x, y, z).toImmutable(), random);
			}
		}
	}

	@Override
	public int getHeight(int x, int z, Heightmap.Type heightmapType) {
		return 0;
	}

	@Override
	public BlockView getColumnSample(int x, int z) {
		return null;
	}
}
