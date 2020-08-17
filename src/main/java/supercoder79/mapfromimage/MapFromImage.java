package supercoder79.mapfromimage;

import supercoder79.mapfromimage.gen.FromImageBiomeSource;
import supercoder79.mapfromimage.gen.FromImageChunkGenerator;
import supercoder79.mapfromimage.gen.FromImageWorldType;

import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.loader.api.FabricLoader;

public class MapFromImage implements ModInitializer {
	@Override
	public void onInitialize() {
		Registry.register(Registry.BIOME_SOURCE, id("biome_source"), FromImageBiomeSource.CODEC);
		Registry.register(Registry.CHUNK_GENERATOR, id("chunk_generator"), FromImageChunkGenerator.CODEC);

		if (FabricLoader.getInstance().getEnvironmentType() == EnvType.CLIENT) {
			new FromImageWorldType();
		}
	}

	public static Identifier id(String path) {
		return new Identifier("map_from_image", path);
	}
}
