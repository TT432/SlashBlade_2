package mods.flammpfeil.slashblade.event;

import mods.flammpfeil.slashblade.item.ItemSlashBlade;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(Dist.CLIENT)
public class BlockPickCanceller {
    @SubscribeEvent
    public void onBlockPick(InputEvent.ClickInputEvent event){
        ClientPlayerEntity player = Minecraft.getInstance().player;
        if (player.getHeldItemMainhand().getCapability(ItemSlashBlade.BLADESTATE).isPresent()){
            event.setCanceled(true);
        }
    }
}
