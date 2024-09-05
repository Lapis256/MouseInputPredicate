package io.github.laps256.mouse_input.init;

import com.mojang.serialization.MapCodec;
import io.github.laps256.mouse_input.MouseInputMod;
import io.github.laps256.mouse_input.predicate.PlayerMouseInputPredicate;
import net.minecraft.predicate.entity.EntitySubPredicate;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;


public class MouseInputEntitySubPredicateTypes {
    public static final MapCodec<PlayerMouseInputPredicate> PLAYER_MOUSE_INPUT = register("player", PlayerMouseInputPredicate.CODEC);

    private static <T extends EntitySubPredicate> MapCodec<T> register(String path, MapCodec<T> codec) {
        return Registry.register(Registries.ENTITY_SUB_PREDICATE_TYPE, MouseInputMod.id(path), codec);
    }

    public static void init() {
    }
}
