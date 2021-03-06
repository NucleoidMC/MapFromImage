package supercoder79.mapfromimage.feature;

import java.util.Random;

import xyz.nucleoid.plasmid.game.gen.MapGen;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.collection.WeightedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ServerWorldAccess;

public final class ImprovedDiskGen implements MapGen {
	public static final ImprovedDiskGen INSTANCE = new ImprovedDiskGen();

	private static final WeightedList<BlockState> STATES = new WeightedList<BlockState>()
			.add(Blocks.SAND.getDefaultState(), 1)
			.add(Blocks.GRAVEL.getDefaultState(), 1);

	@Override
	public void generate(ServerWorldAccess world, BlockPos pos, Random random) {

		int radius = random.nextInt(5) + 2;
		int radiusSquared = radius * radius;

		BlockPos.Mutable mutable = new BlockPos.Mutable();
		BlockState state = STATES.pickRandom(random);

		for(int x = pos.getX() - radius; x <= pos.getX() + radius; ++x) {
			for (int z = pos.getZ() - radius; z <= pos.getZ() + radius; ++z) {
				int localX = x - pos.getX();
				int localZ = z - pos.getZ();
				if (localX * localX + localZ * localZ <= radiusSquared) {
					for(int y = pos.getY() - 2; y <= pos.getY() + 2; ++y) {
						mutable.set(x, y, z);

						if (world.getBlockState(mutable).isOf(Blocks.DIRT) || world.getBlockState(mutable).isOf(Blocks.GRASS_BLOCK)) {
							world.setBlockState(mutable, state, 3);

							if (!world.getBlockState(mutable.up()).canPlaceAt(world, mutable) && !world.getBlockState(mutable.up()).isOf(Blocks.GRASS_PATH)) {
								world.setBlockState(mutable.up(), Blocks.AIR.getDefaultState(), 3);
							}
						}
					}
				}
			}
		}
	}
}