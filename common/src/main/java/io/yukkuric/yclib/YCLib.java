package io.yukkuric.yclib;

import com.mojang.logging.LogUtils;
import net.minecraft.locale.Language;
import org.apache.commons.lang3.NotImplementedException;
import org.slf4j.Logger;

import java.nio.file.Path;

public final class YCLib {
    public static final String MOD_ID = "yclib";
    public static final Logger LOGGER = LogUtils.getLogger();

    public static void init() {
        // Write common init code here.
        LOGGER.info("YukkuriC's Lib loaded");
    }

    // platform-specific mixin handles
    public static boolean modLoaded(String id) {
        throw new NotImplementedException("MIXIN REQUIRED");
    }
    public static Path modFilePath(String id) {
        throw new NotImplementedException("MIXIN REQUIRED");
    }
    public static boolean isPhysicalClient() {
        throw new NotImplementedException("MIXIN REQUIRED");
    }

    // non-mixin common logic
    public static boolean tryLoadInterop(String modId, Runnable loadFunc) {
        if (!modLoaded(modId)) return false;
        try {
            loadFunc.run();
            return true;
        } catch (Throwable e) {
            LOGGER.error("error trying to load interop of {}", modId, e);
            return false;
        }
    }
    public static String doTranslate(String key, Object... args) {
        return Language.getInstance().getOrDefault(key).formatted(args);
    }
}
