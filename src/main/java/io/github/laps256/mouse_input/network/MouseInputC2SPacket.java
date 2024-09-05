package io.github.laps256.mouse_input.network;

import io.github.laps256.mouse_input.MouseInputMod;
import io.github.laps256.mouse_input.util.MouseInput;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.packet.CustomPayload;


public record MouseInputC2SPacket(MouseInput mouseInput) implements CustomPayload {
    public static final CustomPayload.Id<MouseInputC2SPacket> ID = new CustomPayload.Id<>(MouseInputMod.id("mouse_input"));
    public static final PacketCodec<PacketByteBuf, MouseInputC2SPacket> CODEC = PacketCodec.tuple(
            MouseInput.PACKET_CODEC, MouseInputC2SPacket::mouseInput, MouseInputC2SPacket::new
    );

    @Override
    public Id<? extends CustomPayload> getId() {
        return ID;
    }
}
