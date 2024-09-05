package io.github.laps256.mouse_input.input;

import io.github.laps256.mouse_input.util.MouseInput;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.option.GameOptions;


@Environment(EnvType.CLIENT)
public class MouseInputHandler {
    private final GameOptions settings;
    public MouseInput mouseInput = MouseInput.DEFAULT;

    public MouseInputHandler(GameOptions settings) {
        this.settings = settings;
    }

    public void tick() {
        mouseInput = new MouseInput(settings.useKey.isPressed(), settings.attackKey.isPressed(), settings.pickItemKey.isPressed());
    }
}
