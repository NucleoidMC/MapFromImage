package supercoder79.mapfromimage.biome;

import java.util.Random;

import supercoder79.mapfromimage.feature.SprucePoplarTreeGen;
import xyz.nucleoid.plasmid.game.gen.MapGen;

import net.minecraft.util.math.BlockPos;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.Heightmap;
import net.minecraft.world.ServerWorldAccess;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeKeys;
import net.minecraft.world.gen.feature.ConfiguredFeatures;

public class MegaTaigaGen extends TaigaGen {
	public static final MegaTaigaGen INSTANCE = new MegaTaigaGen();

	@Override
	public void generate(ServerWorldAccess world, BlockPos pos, Random random) {
		for (int i = 0; i < 4; i++) {
			int x = pos.getX() + random.nextInt(16);
			int z = pos.getZ() + random.nextInt(16);
			int y = world.getTopY(Heightmap.Type.WORLD_SURFACE_WG, x, z);

			ConfiguredFeatures.MEGA_SPRUCE.generate((StructureWorldAccess) world, null, random, new BlockPos(x, y, z));
		}
	}
}
