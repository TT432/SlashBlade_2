package mods.flammpfeil.slashblade.keybind;

import mods.flammpfeil.slashblade.SlashBlade;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.client.util.InputMappings;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.settings.KeyConflictContext;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import org.lwjgl.glfw.GLFW;

@Mod.EventBusSubscriber(value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class KeyBindHandler {
    public static final String SLASH_BLADE_CATEGORY = "key.category." + SlashBlade.modid;

    public static final KeyBinding SUMMONED_SWORD_KEY = new KeyBinding("key.summoned_sword", KeyConflictContext.IN_GAME,
            InputMappings.Type.MOUSE, GLFW.GLFW_MOUSE_BUTTON_3, SLASH_BLADE_CATEGORY);

    @SubscribeEvent
    public static void registerKey(final FMLClientSetupEvent event) {
        event.enqueueWork(() -> ClientRegistry.registerKeyBinding(SUMMONED_SWORD_KEY));
    }

    // @SubscribeEvent
    // public static void onKeyboardInput(InputEvent.KeyInputEvent event) {
    //     if (KEY.isKeyDown()) {

    //     }
    // }
}