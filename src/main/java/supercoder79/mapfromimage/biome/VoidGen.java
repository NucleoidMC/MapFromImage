package supercoder79.mapfromimage.biome;

import java.util.Random;

import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeKeys;

public class VoidGen implements BiomeGen {
	public static final VoidGen INSTANCE = new VoidGen();

	@Override
	public double baseFactor() {
		return 6;
	}

	@Override
	public double baseHeight() {
		return 54;
	}

	@Override
	public double detailFactor() {
		return 3;
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
		return BiomeKeys.THE_VOID;
	}
}
