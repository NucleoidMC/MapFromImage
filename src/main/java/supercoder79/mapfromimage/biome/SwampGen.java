package supercoder79.mapfromimage.biome;

import java.util.Random;

import supercoder79.mapfromimage.feature.SprucePoplarTreeGen;
import supercoder79.mapfromimage.feature.SwampTreeGen;
import xyz.nucleoid.plasmid.game.gen.MapGen;

import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeKeys;

public class SwampGen implements BiomeGen {
	public static final SwampGen INSTANCE = new SwampGen();

	@Override
	public double baseFactor() {
		return 1;
	}

	@Override
	public double baseHeight() {
		return 48.65;
	}

	@Override
	public double detailFactor() {
		return 1.75;
	}

	@Override
	public int treeAmt(Random random) {
		return 1 + random.nextInt(2);
	}

	@Override
	public int grassAmt(Random random) {
		return 2 + random.nextInt(2);
	}

	@Override
	public MapGen tree(int x, int z, Random random) {
		return SwampTreeGen.INSTANCE;
	}

	@Override
	public int shrubAmt(Random random) {
		return random.nextInt(2);
	}

	@Override
	public boolean generateDisks() {
		return false;
	}

	@Override
	public RegistryKey<Biome> getFakingBiome() {
		return BiomeKeys.SWAMP;
	}
}
