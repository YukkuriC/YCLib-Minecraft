package io.yukkuric.yclib.fabric.mixin;

import io.yukkuric.yclib.YCLib;
import io.yukkuric.yclib.fabric.YCLibFabric;
import net.fabricmc.loader.api.FabricLoader;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.nio.file.Path;

@Mixin(YCLib.class)
public class YCLibFabricAPI {
    @Inject(method = "modLoaded", at = @At("HEAD"), cancellable = true, remap = false)
    private static void hookModLoaded(String id, CallbackInfoReturnable<Boolean> cir) {
        cir.setReturnValue(FabricLoader.getInstance().isModLoaded(id));
    }
    @Inject(method = "isPhysicalClient", at = @At("HEAD"), cancellable = true, remap = false)
    private static void hookPhysicalClient(CallbackInfoReturnable<Boolean> cir) {
        cir.setReturnValue(YCLibFabric.IsPhisicalClient());
    }
    @Inject(method = "modFilePath", at = @At("HEAD"), cancellable = true)
    private static void hookModFilePath(String id, CallbackInfoReturnable<Path> cir) {
        var container = FabricLoader.getInstance().getModContainer(id).orElse(null);
        if (container == null) {
            cir.setReturnValue(null);
            return;
        }
        var paths = container.getOrigin().getPaths();
        cir.setReturnValue(paths.isEmpty() ? null : paths.get(0));
    }
}
