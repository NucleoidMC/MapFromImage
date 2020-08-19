package supercoder79.mapfromimage.biome;

import java.util.Random;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeKeys;

public class MesaGen implements BiomeGen {
	public static final MesaGen INSTANCE = new MesaGen();

	@Override
	public double baseFactor() {
		return 4;
	}

	@Override
	public double baseHeight() {
		return 68;
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
	public BlockState topState(Random random) {
		return Blocks.TERRACOTTA.getDefaultState();
	}

	@Override
	public BlockState underState() {
		return Blocks.TERRACOTTA.getDefaultState();
	}

	@Override
	public int shrubAmt(Random random) {
		return 1 + random.nextInt(3);
	}

	@Override
	public RegistryKey<Biome> getFakingBiome() {
		return BiomeKeys.BADLANDS;
	}
}
