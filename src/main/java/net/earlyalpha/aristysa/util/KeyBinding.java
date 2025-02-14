package net.earlyalpha.aristysa.util;

import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.KeyMapping;
import net.minecraftforge.client.settings.KeyConflictContext;
import org.lwjgl.glfw.GLFW;

public class KeyBinding {
    public static final String KEY_CATEGORY_ARISTYSA = "key.category.aristysa.aristysa";
    public static final String KEY_OPTICAL_CAMO_USE = "key.aristysa.optical_camo_use";
    public static final String KEY_ENDER_EYE_USE = "key.aristysa.ender_eye_use";
    public static final String KEY_CYBERLEG_USE = "key.aristysa.cyberleg_use";
    public static final String KEY_CYBER_IMPLANT_SCREEN_OPEN = "key.aristysa.cyber_implant_screen_open";

    public static final KeyMapping OPTICAL_CAMO_USE = new KeyMapping(KEY_OPTICAL_CAMO_USE, KeyConflictContext.IN_GAME,
            InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_KP_0, KEY_CATEGORY_ARISTYSA);
    public static final KeyMapping ENDER_EYE_USE = new KeyMapping(KEY_ENDER_EYE_USE, KeyConflictContext.IN_GAME,
            InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_KP_1, KEY_CATEGORY_ARISTYSA);
    public static final KeyMapping CYBERLEG_USE = new KeyMapping(KEY_CYBERLEG_USE, KeyConflictContext.IN_GAME,
            InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_KP_2, KEY_CATEGORY_ARISTYSA);
    public static final KeyMapping CYBER_IMPLANT_SCREEN_OPEN = new KeyMapping(KEY_CYBER_IMPLANT_SCREEN_OPEN, KeyConflictContext.IN_GAME,
            InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_KP_3, KEY_CATEGORY_ARISTYSA);
}
