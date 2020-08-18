package supercoder79.mapfromimage.biome;

import java.util.Random;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeKeys;

public class BeachGen implements BiomeGen {
	public static final BeachGen INSTANCE = new BeachGen();

	@Override
	public double baseFactor() {
		return 2;
	}

	@Override
	public double baseHeight() {
		return 51;
	}

	@Override
	public double detailFactor() {
		return 2.5;
	}

	@Override
	public int treeAmt(Random random) {
		return 0;
	}

	@Override
	public int grassAmt(Random random) {
		return 0;
	}

	@Override
	public BlockState topState() {
		return Blocks.SAND.getDefaultState();
	}

	@Override
	public BlockState underState() {
		return Blocks.SANDSTONE.getDefaultState();
	}

	@Override
	public RegistryKey<Biome> getFakingBiome() {
		return BiomeKeys.BEACH;
	}
}
