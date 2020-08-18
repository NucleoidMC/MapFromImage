package supercoder79.mapfromimage.biome;

import java.util.Random;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.Heightmap;
import net.minecraft.world.ServerWorldAccess;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.gen.feature.ConfiguredFeatures;

public class DesertGen extends BeachGen {
	public static final DesertGen INSTANCE = new DesertGen();

	@Override
	public double baseHeight() {
		return 56;
	}

	@Override
	public void generate(ServerWorldAccess world, BlockPos pos, Random random) {
		for (int i = 0; i < 2; i++) {
			int x = pos.getX() + random.nextInt(16);
			int z = pos.getZ() + random.nextInt(16);
			int y = world.getTopY(Heightmap.Type.WORLD_SURFACE_WG, x, z);

			ConfiguredFeatures.PATCH_CACTUS.generate((StructureWorldAccess) world, null, random, new BlockPos(x, y, z));
		}
	}
}
