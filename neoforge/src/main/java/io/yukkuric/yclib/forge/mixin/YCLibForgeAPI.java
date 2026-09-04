package io.yukkuric.yclib.forge.mixin;

import io.yukkuric.yclib.YCLib;
import net.minecraft.locale.Language;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.fml.ModList;
import net.neoforged.fml.loading.FMLEnvironment;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.nio.file.Path;

@Mixin(YCLib.class)
public class YCLibForgeAPI {
    @Inject(method = "modLoaded", at = @At("HEAD"), cancellable = true, remap = false)
    private static void modLoadedFabric(String id, CallbackInfoReturnable<Boolean> cir) {
        cir.setReturnValue(ModList.get().isLoaded(id));
    }
    @Inject(method = "isPhysicalClient", at = @At("HEAD"), cancellable = true, remap = false)
    private static void hookPhysicalClient(CallbackInfoReturnable<Boolean> cir) {
        cir.setReturnValue(FMLEnvironment.dist == Dist.CLIENT);
    }
    @Inject(method = "modFilePath", at = @At("HEAD"), cancellable = true)
    private static void hookModFilePath(String id, CallbackInfoReturnable<Path> cir) {
        var file = ModList.get().getModFileById(id);
        if (file == null) {
            cir.setReturnValue(null);
            return;
        }
        cir.setReturnValue(file.getFile().getFilePath());
    }
    @Inject(method="doTranslate", at=@At("HEAD"), cancellable = true, remap = false)
    private static void hookDoTranslate(String key, Object[] args, CallbackInfoReturnable<String> cir){
        cir.setReturnValue(Language.getInstance().getOrDefault(key).formatted(args));
    }
}
