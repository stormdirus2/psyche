package net.scirave.psyche.forge;

import net.scirave.psyche.Psyche;
import net.minecraftforge.fml.common.Mod;

@Mod(Psyche.MOD_ID)
public class PscyheForge {
    public PscyheForge() {
        Psyche.init();
    }
}
