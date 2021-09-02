package mods.flammpfeil.slashblade.event;

import mods.flammpfeil.slashblade.SlashBlade;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementProgress;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.AnvilUpdateEvent;
import net.minecraftforge.event.entity.player.AnvilRepairEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class AnvilCrafting {
    private static final class SingletonHolder {
        private static final AnvilCrafting instance = new AnvilCrafting();
    }
    public static AnvilCrafting getInstance() {
        return SingletonHolder.instance;
    }
    private AnvilCrafting(){}
    public void register(){
        MinecraftForge.EVENT_BUS.register(this);
    }

    @SubscribeEvent
    public void onAnvilUpdateEvent(AnvilUpdateEvent event){
        ItemStack base = event.getLeft();
        ItemStack material = event.getRight();

        if(base.isEmpty()) return;
        if(material.isEmpty()) return;

        AnvilCraftingRecipe recipe = AnvilCraftingRecipe.getRecipe(material);
        if(recipe == null) return;

        if(!recipe.matches(base)) return;

        event.setMaterialCost(1);
        event.setCost(recipe.getLevel());
        event.setOutput(recipe.getResult(base));
    }

    static private final ResourceLocation REFORGE = new ResourceLocation(SlashBlade.modid, "tips/reforge");

    @SubscribeEvent
    public void onAnvilRepairEvent(AnvilRepairEvent event){

        if(!(event.getPlayer() instanceof ServerPlayerEntity)) return;


        ItemStack material = event.getIngredientInput();
        AnvilCraftingRecipe recipe = AnvilCraftingRecipe.getRecipe(material);
        if(recipe == null) return;

        ItemStack base = event.getItemInput();
        if(!recipe.matches(base)) return;

        grantCriterion((ServerPlayerEntity) event.getPlayer(), REFORGE);
    }

    private static void grantCriterion(ServerPlayerEntity player, ResourceLocation resourcelocation){
        Advancement adv = player.getServer().getAdvancementManager().getAdvancement(resourcelocation);
        if(adv == null) return;

        AdvancementProgress advancementprogress = player.getAdvancements().getProgress(adv);
        if (advancementprogress.isDone()) return;

        for(String s : advancementprogress.getRemaningCriteria()) {
            player.getAdvancements().grantCriterion(adv, s);
        }
    }

}
