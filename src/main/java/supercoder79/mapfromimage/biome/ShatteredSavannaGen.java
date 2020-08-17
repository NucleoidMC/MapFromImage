package supercoder79.mapfromimage.biome;

public class ShatteredSavannaGen extends SavannaGen {
	public static final ShatteredSavannaGen INSTANCE = new ShatteredSavannaGen();

	@Override
	public double baseFactor() {
		return 6;
	}

	@Override
	public double baseHeight() {
		return 62;
	}

	@Override
	public double detailFactor() {
		return 6;
	}
}
