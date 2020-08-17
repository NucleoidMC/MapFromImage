package supercoder79.mapfromimage.gen;

import net.minecraft.client.world.GeneratorType;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.chunk.ChunkGeneratorSettings;

public class FromImageWorldType extends GeneratorType {
    public FromImageWorldType() {
        super("from_image");
        GeneratorType.VALUES.add(this);
    }

    @Override
    protected ChunkGenerator getChunkGenerator(Registry<Biome> biomeRegistry, Registry<ChunkGeneratorSettings> chunkGeneratorSettingsRegistry, long seed) {
        return new FromImageChunkGenerator(new FromImageBiomeSource(biomeRegistry, seed), seed);
    }
}