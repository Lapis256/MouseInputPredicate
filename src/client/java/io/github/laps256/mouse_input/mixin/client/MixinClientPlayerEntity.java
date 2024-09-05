package io.github.laps256.mouse_input.mixin.client;

import io.github.laps256.mouse_input.input.MouseInputHandler;
import io.github.laps256.mouse_input.network.MouseInputC2SPacket;
import io.github.laps256.mouse_input.util.MouseInput;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;


@Mixin(ClientPlayerEntity.class)
public class MixinClientPlayerEntity {
    @Shadow
    @Final
    protected MinecraftClient client;

    @Unique
    private MouseInput mouseInput = MouseInput.DEFAULT;

    @Unique
    private MouseInputHandler mouseInputHandler;

    @Inject(method = "tick", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/network/ClientPlayerEntity;sendSneakingPacket()V", shift = At.Shift.AFTER))
    private void tickMouseInput(CallbackInfo ci) {
        if (this.client == null) {
            return;
        }

        if (mouseInputHandler == null) {
            mouseInputHandler = new MouseInputHandler(client.options);
        }

        if (!mouseInput.equals(mouseInputHandler.mouseInput)) {
            ClientPlayNetworking.send(new MouseInputC2SPacket(mouseInputHandler.mouseInput));
            mouseInput = mouseInputHandler.mouseInput;
        }

        mouseInputHandler.tick();
    }
}
