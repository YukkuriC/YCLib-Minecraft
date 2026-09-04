package io.yukkuric.yclib.fabric.client;

import io.yukkuric.yclib.fabric.YCLibFabric;
import net.fabricmc.api.ClientModInitializer;

public final class YCLibFabricClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        YCLibFabric._markPhisicalClient();
    }
}
