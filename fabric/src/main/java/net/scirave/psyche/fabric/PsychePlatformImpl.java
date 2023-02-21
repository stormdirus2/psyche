package net.scirave.psyche.fabric;

import net.scirave.psyche.PsychePlatform;
import net.fabricmc.loader.api.FabricLoader;

import java.nio.file.Path;

public class PsychePlatformImpl {
    /**
     * This is our actual method to {@link PsychePlatform#getConfigDirectory()}.
     */
    public static Path getConfigDirectory() {
        return FabricLoader.getInstance().getConfigDir();
    }
}
