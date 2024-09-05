package io.github.laps256.mouse_input.util;

import net.minecraft.network.PacketByteBuf;
import net.minecraft.network.codec.PacketCodec;


public record MouseInput(boolean rightClick, boolean leftClick, boolean middleClick) {
    private static final byte RIGHT_CLICK = 0b0001;
    private static final byte LEFT_CLICK = 0b0010;
    private static final byte MIDDLE_CLICK = 0b0100;

    public static final PacketCodec<PacketByteBuf, MouseInput> PACKET_CODEC = new PacketCodec<PacketByteBuf, MouseInput>() {
        @Override
        public void encode(PacketByteBuf buf, MouseInput value) {
            byte flags = 0;
            if (value.rightClick()) {
                flags |= RIGHT_CLICK;
            }
            if (value.leftClick()) {
                flags |= LEFT_CLICK;
            }
            if (value.middleClick()) {
                flags |= MIDDLE_CLICK;
            }
            buf.writeByte(flags);
        }

        @Override
        public MouseInput decode(PacketByteBuf buf) {
            byte flags = buf.readByte();
            return new MouseInput((flags & RIGHT_CLICK) != 0, (flags & LEFT_CLICK) != 0, (flags & MIDDLE_CLICK) != 0);
        }
    };

    public static final MouseInput DEFAULT = new MouseInput(false, false, false);
}
