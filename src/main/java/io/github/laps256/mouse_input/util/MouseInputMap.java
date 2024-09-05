package io.github.laps256.mouse_input.util;

import net.minecraft.server.network.ServerPlayerEntity;

import java.util.HashMap;
import java.util.Map;


public class MouseInputMap {
    private final Map<Integer, MouseInput> map = new HashMap<>();

    public void put(ServerPlayerEntity player, MouseInput mouseInput) {
        map.put(player.hashCode(), mouseInput);
    }

    public MouseInput get(ServerPlayerEntity player) {
        return map.get(player.hashCode());
    }
}
