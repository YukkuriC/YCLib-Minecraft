package io.yukkuric.yclib.forge;

import io.yukkuric.yclib.YCLib;
import net.neoforged.fml.common.Mod;

@Mod(YCLib.MOD_ID)
public final class YCLibNeoForge {
    public YCLibNeoForge() {
        // Run our common setup.
        YCLib.init();
    }
}
