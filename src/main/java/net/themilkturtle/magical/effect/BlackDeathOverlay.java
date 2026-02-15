package net.themilkturtle.magical.effect;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RenderGuiOverlayEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.themilkturtle.magical.Magical;


@Mod.EventBusSubscriber(modid = Magical.MOD_ID, value = Dist.CLIENT)
public class BlackDeathOverlay {

    @SuppressWarnings("removal")
    private static final ResourceLocation OVERLAY =
            new ResourceLocation(Magical.MOD_ID, "textures/gui/black_death_overlay.png");

    // Persistent fade value
    private static float overlayAlpha = 0f;

    // Tunable speeds
    private static final float FADE_IN = 0.03f;
    private static final float FADE_OUT = 1f;

    @SubscribeEvent
    public static void onRenderOverlay(RenderGuiOverlayEvent.Post event) {

        Minecraft mc = Minecraft.getInstance();
        if (mc.player == null || mc.level == null) {
            overlayAlpha = 0f;
            return;
        }

        boolean infected = mc.player.hasEffect(ModEffects.BLACK_DEATH.get());

        // Update fade
        if (infected)
            overlayAlpha = Math.min(1f, overlayAlpha + FADE_IN);
        else
            overlayAlpha = Math.max(0f, overlayAlpha - FADE_OUT);

        if (overlayAlpha <= 0.001f)
            return;

        renderOverlay(event.getGuiGraphics(),
                mc.getWindow().getGuiScaledWidth(),
                mc.getWindow().getGuiScaledHeight(),
                overlayAlpha);
    }

    private static void renderOverlay(GuiGraphics gui, int width, int height, float alpha) {

        RenderSystem.enableBlend();
        RenderSystem.defaultBlendFunc();
        RenderSystem.setShaderTexture(0, OVERLAY);

        // Slight pulse to make it feel alive
        float pulse = (float)Math.sin(System.currentTimeMillis() * 0.002) * 0.05f;
        float finalAlpha = Mth.clamp(alpha + pulse, 0f, 1f);

        gui.setColor(1f, 1f, 1f, finalAlpha);
        gui.blit(OVERLAY, 0, 0, 0, 0, width, height, width, height);
        gui.setColor(1f, 1f, 1f, 1f);

        RenderSystem.disableBlend();
    }
}
