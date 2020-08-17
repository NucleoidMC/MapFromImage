package supercoder79.mapfromimage.feature;

import java.util.Random;

import xyz.nucleoid.plasmid.game.gen.GenHelper;
import xyz.nucleoid.plasmid.game.gen.MapGen;

import net.minecraft.block.Blocks;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.ServerWorldAccess;

public final class AspenTreeGen implements MapGen {
	public static final AspenTreeGen INSTANCE = new AspenTreeGen();

	@Override
	public void generate(ServerWorldAccess world, BlockPos pos, Random random) {

		double maxRadius = 2 + ((random.nextDouble() - 0.5) * 0.2);
		int leafDistance = random.nextInt(4) + 3;
		BlockPos.Mutable mutable = pos.mutableCopy();

		for (int y = 0; y < 8; y++) {
			world.setBlockState(mutable, Blocks.BIRCH_LOG.getDefaultState(), 3);
			//add branch blocks
			if (maxRadius * radius(y / 7.f) > 2.1) {
				Direction.Axis axis = getAxis(random);
				world.setBlockState(mutable.offset(getDirection(axis, random)).up(leafDistance), Blocks.BIRCH_LOG.getDefaultState().with(Properties.AXIS, axis), 3);
			}
			mutable.move(Direction.UP);
		}
		mutable = pos.mutableCopy();
		mutable.move(Direction.UP, leafDistance);
		for (int y = 0; y < 8; y++) {
			GenHelper.circle(mutable.mutableCopy(), maxRadius * radius(y / 7.f), leafPos -> {
				if (world.getBlockState(leafPos).isAir()) {
					world.setBlockState(leafPos, Blocks.BIRCH_LEAVES.getDefaultState(), 3);
				}
			});
			mutable.move(Direction.UP);
		}
	}

	private double radius(double x) {
		return -Math.pow(((1.4 * x) - 0.3), 2) + 1.2;
	}

	private Direction.Axis getAxis(Random random) {
		return random.nextBoolean() ? Direction.Axis.X : Direction.Axis.Z;
	}

	private Direction getDirection(Direction.Axis axis, Random random) {
		if (axis == Direction.Axis.X) {
			return random.nextBoolean() ? Direction.EAST : Direction.WEST;
		} else {
			return random.nextBoolean() ? Direction.NORTH : Direction.SOUTH;
		}
	}
}