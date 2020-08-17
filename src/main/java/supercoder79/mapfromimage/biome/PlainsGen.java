package supercoder79.mapfromimage.biome;

import java.util.Random;

import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeKeys;

public class PlainsGen implements BiomeGen {
	public static final PlainsGen INSTANCE = new PlainsGen();

	@Override
	public double baseFactor() {
		return 6;
	}

	@Override
	public double baseHeight() {
		return 56;
	}

	@Override
	public double detailFactor() {
		return 3;
	}

	@Override
	public int treeAmt(Random random) {
		return random.nextInt(2);
	}

	@Override
	public int grassAmt(Random random) {
		return 4 + random.nextInt(4);
	}

	@Override
	public int shrubAmt(Random random) {
		return random.nextInt(2);
	}

	@Override
	public RegistryKey<Biome> getFakingBiome() {
		return BiomeKeys.PLAINS;
	}
}
