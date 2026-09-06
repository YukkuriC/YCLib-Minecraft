package io.yukkuric.yclib.forge;

import io.yukkuric.yclib.YCLib;
import net.minecraftforge.fml.common.Mod;

@Mod(YCLib.MOD_ID)
public final class YCLibForge {
    public YCLibForge() {
        // Run our common setup.
        YCLib.init();
    }
}
