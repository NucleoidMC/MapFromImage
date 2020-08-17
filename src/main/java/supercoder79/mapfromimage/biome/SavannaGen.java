package supercoder79.mapfromimage.biome;

import java.util.Random;

import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeKeys;

public class SavannaGen implements BiomeGen {
	public static final SavannaGen INSTANCE = new SavannaGen();

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
		return 1.5;
	}

	@Override
	public int treeAmt(Random random) {
		return 0;
	}

	@Override
	public int grassAmt(Random random) {
		return 6 + random.nextInt(6);
	}

	@Override
	public int shrubAmt(Random random) {
		return 1 + random.nextInt(3);
	}

	@Override
	public RegistryKey<Biome> getFakingBiome() {
		return BiomeKeys.SAVANNA;
	}
}
