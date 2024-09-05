package io.github.laps256.mouse_input.predicate;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import io.github.laps256.mouse_input.MouseInputMod;
import io.github.laps256.mouse_input.init.MouseInputEntitySubPredicateTypes;
import net.minecraft.entity.Entity;
import net.minecraft.predicate.entity.EntitySubPredicate;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.Vec3d;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;


public record PlayerMouseInputPredicate(Optional<MouseInputPredicate> mouseInput) implements EntitySubPredicate {
    public static final MapCodec<PlayerMouseInputPredicate> CODEC = RecordCodecBuilder.mapCodec(
            instance -> instance.group(MouseInputPredicate.CODEC.optionalFieldOf("mouse_input").forGetter(PlayerMouseInputPredicate::mouseInput))
                        .apply(instance, PlayerMouseInputPredicate::new)
    );

    @Override
    public MapCodec<? extends EntitySubPredicate> getCodec() {
        return MouseInputEntitySubPredicateTypes.PLAYER_MOUSE_INPUT;
    }

    @Override
    public boolean test(Entity entity, ServerWorld world, @Nullable Vec3d pos) {
        if (!(entity instanceof ServerPlayerEntity serverPlayerEntity)) {
            return false;
        }
        return mouseInput.isEmpty() || mouseInput.get().matches(MouseInputMod.mouseInputMap.get(serverPlayerEntity));
    }
}
