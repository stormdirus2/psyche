package net.scirave.psyche.fabric;

import net.scirave.psyche.PsychePlatform;
import org.quiltmc.loader.api.QuiltLoader;

import java.nio.file.Path;

public class PsychePlatformImpl {
    /**
     * This is our actual method to {@link PsychePlatform#getConfigDirectory()}.
     */
    public static Path getConfigDirectory() {
        return QuiltLoader.getConfigDir();
    }
}
