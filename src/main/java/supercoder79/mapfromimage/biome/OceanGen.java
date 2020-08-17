package supercoder79.mapfromimage.biome;

import java.util.Random;

import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeKeys;

public class OceanGen implements BiomeGen {
	public static final OceanGen INSTANCE = new OceanGen();

	@Override
	public double baseFactor() {
		return 3;
	}

	@Override
	public double baseHeight() {
		return 36;
	}

	@Override
	public double detailFactor() {
		return 1.5;
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
	public RegistryKey<Biome> getFakingBiome() {
		return BiomeKeys.OCEAN;
	}
}
