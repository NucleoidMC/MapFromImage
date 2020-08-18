package supercoder79.mapfromimage.biome;

import java.util.Random;

import xyz.nucleoid.plasmid.game.gen.MapGen;
import xyz.nucleoid.plasmid.game.gen.feature.PoplarTreeGen;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.ServerWorldAccess;
import net.minecraft.world.biome.Biome;

public interface BiomeGen extends MapGen {
	double baseFactor();

	double baseHeight();

	double detailFactor();

	int treeAmt(Random random);

	int grassAmt(Random random);

	default int shrubAmt(Random random) {
		return 0;
	}

	default MapGen tree(int x, int z, Random random) {
		return PoplarTreeGen.INSTANCE;
	}

	default boolean generateDisks() {
		return true;
	}

	default BlockState topState() {
		return Blocks.GRASS_BLOCK.getDefaultState();
	}

	default BlockState underState() {
		return Blocks.DIRT.getDefaultState();
	}

	default BlockState underWaterState() {
		return Blocks.DIRT.getDefaultState();
	}

	RegistryKey<Biome> getFakingBiome();

	// Custom generation
	@Override
	default void generate(ServerWorldAccess serverWorldAccess, BlockPos blockPos, Random random) {

	}
}
