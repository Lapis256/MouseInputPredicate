package io.github.laps256.mouse_input;

import io.github.laps256.mouse_input.network.MouseInputC2SPacket;
import io.github.laps256.mouse_input.util.MouseInputMap;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class MouseInputMod implements ModInitializer {
    public static final String MOD_ID = "mouse_input";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    public static final MouseInputMap mouseInputMap = new MouseInputMap();

    public static Identifier id(String path) {
        return Identifier.of(MOD_ID, path);
    }

    @Override
    public void onInitialize() {
        registerPackets();
    }

    private void registerPackets() {
        PayloadTypeRegistry.playC2S().register(MouseInputC2SPacket.ID, MouseInputC2SPacket.CODEC);

        ServerPlayNetworking.registerGlobalReceiver(MouseInputC2SPacket.ID, (pkt, ctx) -> {
            ctx.server().execute(() -> mouseInputMap.put(ctx.player(), pkt.mouseInput()));
        });
    }
}
