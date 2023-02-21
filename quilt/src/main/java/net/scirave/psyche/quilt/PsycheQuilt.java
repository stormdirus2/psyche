package net.scirave.psyche.quilt;

import net.scirave.psyche.fabriclike.PsycheFabricLike;
import org.quiltmc.loader.api.ModContainer;
import org.quiltmc.qsl.base.api.entrypoint.ModInitializer;

public class PsycheQuilt implements ModInitializer {
    @Override
    public void onInitialize(ModContainer mod) {
        PsycheFabricLike.init();
    }
}
