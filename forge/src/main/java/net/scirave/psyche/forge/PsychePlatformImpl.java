package net.scirave.psyche.forge;

import net.scirave.psyche.PsychePlatform;
import net.minecraftforge.fml.loading.FMLPaths;

import java.nio.file.Path;

public class PsychePlatformImpl {
    /**
     * This is our actual method to {@link PsychePlatform#getConfigDirectory()}.
     */
    public static Path getConfigDirectory() {
        return FMLPaths.CONFIGDIR.get();
    }
}
