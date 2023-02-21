package net.scirave.psyche.fabric;

import net.scirave.psyche.fabriclike.PsycheFabricLike;
import net.fabricmc.api.ModInitializer;

public class PsycheFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        PsycheFabricLike.init();
    }
}
