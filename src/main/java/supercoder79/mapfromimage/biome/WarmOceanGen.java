package supercoder79.mapfromimage.biome;

import java.util.Random;

import net.minecraft.util.math.BlockPos;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.Heightmap;
import net.minecraft.world.ServerWorldAccess;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeKeys;
import net.minecraft.world.gen.feature.ConfiguredFeatures;

public class WarmOceanGen extends OceanGen {
	public static final WarmOceanGen INSTANCE = new WarmOceanGen();

	@Override
	public void generate(ServerWorldAccess world, BlockPos pos, Random random) {
		int x = pos.getX() + random.nextInt(16);
		int z = pos.getZ() + random.nextInt(16);
		int y = world.getTopY(Heightmap.Type.WORLD_SURFACE_WG, x, z);

		ConfiguredFeatures.WARM_OCEAN_VEGETATION.generate((StructureWorldAccess) world, null, random, new BlockPos(x, y, z));
	}

	@Override
	public RegistryKey<Biome> getFakingBiome() {
		return BiomeKeys.WARM_OCEAN;
	}
}
