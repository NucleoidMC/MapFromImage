package supercoder79.mapfromimage.biome;

import java.util.Random;

import supercoder79.mapfromimage.feature.ShrubGen;

import net.minecraft.util.math.BlockPos;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.Heightmap;
import net.minecraft.world.ServerWorldAccess;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeKeys;
import net.minecraft.world.gen.feature.ConfiguredFeatures;
import net.minecraft.world.gen.feature.DefaultBiomeFeatures;
import net.minecraft.world.gen.feature.Feature;

public class JungleGen implements BiomeGen {
	public static final JungleGen INSTANCE = new JungleGen();

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
		return 0;
	}

	@Override
	public int grassAmt(Random random) {
		return 8 + random.nextInt(4);
	}

	@Override
	public int shrubAmt(Random random) {
		return random.nextInt(3);
	}

	@Override
	public RegistryKey<Biome> getFakingBiome() {
		return BiomeKeys.JUNGLE;
	}

	@Override
	public void generate(ServerWorldAccess world, BlockPos pos, Random random) {
		for (int i = 0; i < 3; i++) {
			int x = pos.getX() + random.nextInt(16);
			int z = pos.getZ() + random.nextInt(16);
			int y = world.getTopY(Heightmap.Type.WORLD_SURFACE_WG, x, z);

			ConfiguredFeatures.MEGA_JUNGLE_TREE.generate((StructureWorldAccess) world, null, random, new BlockPos(x, y, z));
		}
	}
}
