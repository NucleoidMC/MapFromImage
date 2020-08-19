package supercoder79.mapfromimage.biome;

import java.util.Random;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;

public class ShatteredMesaGen extends MesaGen {
	public static final ShatteredMesaGen INSTANCE = new ShatteredMesaGen();

	@Override
	public double baseHeight() {
		return 76;
	}

	@Override
	public double detailFactor() {
		return 16;
	}

	@Override
	public BlockState topState(Random random) {
		return random.nextInt(4) == 0 ? Blocks.STONE.getDefaultState() : Blocks.TERRACOTTA.getDefaultState();
	}
}
