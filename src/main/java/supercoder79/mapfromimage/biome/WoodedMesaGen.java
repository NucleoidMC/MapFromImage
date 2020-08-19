package supercoder79.mapfromimage.biome;

import java.util.Random;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;

public class WoodedMesaGen extends MesaGen {
	public static final WoodedMesaGen INSTANCE = new WoodedMesaGen();

	@Override
	public double detailFactor() {
		return 2.75;
	}

	@Override
	public int treeAmt(Random random) {
		return 1 + random.nextInt(3);
	}

	@Override
	public BlockState topState(Random random) {
		return random.nextInt(5) == 0 ? Blocks.GRASS_BLOCK.getDefaultState() : Blocks.TERRACOTTA.getDefaultState();
	}
}
