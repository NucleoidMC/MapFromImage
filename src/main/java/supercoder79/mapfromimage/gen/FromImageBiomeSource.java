package supercoder79.mapfromimage.gen;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.google.common.collect.ImmutableList;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import supercoder79.mapfromimage.biome.*;

import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryLookupCodec;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.source.BiomeSource;

public class FromImageBiomeSource extends BiomeSource {
	public static final Codec<FromImageBiomeSource> CODEC = RecordCodecBuilder.create(instance ->
		instance.group(
				RegistryLookupCodec.of(Registry.BIOME_KEY).forGetter(source -> source.biomeRegistry),
				Codec.LONG.fieldOf("seed").stable().forGetter(source -> source.seed)
		).apply(instance, instance.stable(FromImageBiomeSource::new)));

	private final Registry<Biome> biomeRegistry;
	private final long seed;
	public final BufferedImage image;

	private final int sizeX;
	private final int sizeZ;

	public FromImageBiomeSource(Registry<Biome> biomeRegistry, long seed) {
		super(ImmutableList.of());
		this.biomeRegistry = biomeRegistry;
		this.seed = seed;

		BufferedImage temp;
		try {
			temp = ImageIO.read(new File("image.png"));
		} catch (IOException e) {
			temp = null;
			e.printStackTrace();
		}
		image = temp;

		sizeX = image.getWidth() / 2;
		sizeZ = image.getHeight() / 2;
	}

	@Override
	protected Codec<? extends BiomeSource> getCodec() {
		return CODEC;
	}

	@Override
	public BiomeSource withSeed(long seed) {
		return this;
	}

	@Override
	public Biome getBiomeForNoiseGen(int biomeX, int biomeY, int biomeZ) {
		return biomeRegistry.get(fromImage(biomeX << 2, biomeZ << 2).getFakingBiome());
	}

	public BiomeGen fromImage(int x, int z) {
		if (x >= -sizeX && z >= -sizeZ && x < sizeX && z < sizeZ) {
			int color = image.getRGB(x + sizeX, z + sizeZ);

			switch (color) {
				case 0xFF34CB26: return PlainsGen.INSTANCE;
				case 0xFF28CD65: return TaigaGen.INSTANCE;
				case 0xFFFAE527: return SavannaGen.INSTANCE;
				case 0xFF1B3704: return SwampGen.INSTANCE;
				case 0xFF78D96F: return BirchForestGen.INSTANCE;
				case 0xFF0000BC: return OceanGen.INSTANCE;
				case 0xFFAC9C07: return ShatteredSavannaGen.INSTANCE;
				case 0xFFE0FFFF: return MountainsGen.INSTANCE;
				case 0xFFBCFF29: return JungleGen.INSTANCE;
				case 0xFF00C3FF: return WarmOceanGen.INSTANCE;
				case 0xFF00C245: return MegaTaigaGen.INSTANCE;
				case 0xFFFFFF9D: return BeachGen.INSTANCE;
				case 0xFFFFD78E: return DesertGen.INSTANCE;
				case 0xFFFF850E: return MesaGen.INSTANCE;
				case 0xFFBB5100: return ShatteredMesaGen.INSTANCE;
				case 0xFFD29A0C: return WoodedMesaGen.INSTANCE;
			}
		}

		return VoidGen.INSTANCE;
	}
}
