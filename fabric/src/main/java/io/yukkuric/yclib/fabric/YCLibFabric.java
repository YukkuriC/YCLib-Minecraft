package io.yukkuric.yclib.fabric;

import io.yukkuric.yclib.YCLib;
import net.fabricmc.api.ModInitializer;

public final class YCLibFabric implements ModInitializer {
    private static boolean isPhisicalClient = false;

    @Override
    public void onInitialize() {
        // This code runs as soon as Minecraft is in a mod-load-ready state.
        // However, some things (like resources) may still be uninitialized.
        // Proceed with mild caution.

        // Run our common setup.
        YCLib.init();
    }

    public static boolean IsPhisicalClient() {
        return isPhisicalClient;
    }
    public static void _markPhisicalClient() {
        isPhisicalClient = true;
    }
}
