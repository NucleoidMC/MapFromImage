package supercoder79.mapfromimage.biome;

import java.util.Random;

import supercoder79.mapfromimage.feature.AspenTreeGen;
import supercoder79.mapfromimage.feature.SprucePoplarTreeGen;
import xyz.nucleoid.plasmid.game.gen.MapGen;

import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeKeys;

public class BirchForestGen implements BiomeGen {
	public static final BirchForestGen INSTANCE = new BirchForestGen();

	@Override
	public double baseFactor() {
		return 4;
	}

	@Override
	public double baseHeight() {
		return 56;
	}

	@Override
	public double detailFactor() {
		return 2;
	}

	@Override
	public int treeAmt(Random random) {
		return 2 + random.nextInt(3);
	}

	@Override
	public int grassAmt(Random random) {
		return 4 + random.nextInt(4);
	}

	@Override
	public MapGen tree(int x, int z, Random random) {
		return AspenTreeGen.INSTANCE;
	}

	@Override
	public RegistryKey<Biome> getFakingBiome() {
		return BiomeKeys.BIRCH_FOREST;
	}
}
