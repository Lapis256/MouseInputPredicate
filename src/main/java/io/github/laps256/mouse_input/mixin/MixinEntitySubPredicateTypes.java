package io.github.laps256.mouse_input.mixin;

import io.github.laps256.mouse_input.init.MouseInputEntitySubPredicateTypes;
import net.minecraft.predicate.entity.EntitySubPredicateTypes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;


@Mixin(value = EntitySubPredicateTypes.class)
public class MixinEntitySubPredicateTypes {
    @Inject(method = "<clinit>", at = @At("RETURN"))
    private static void init(CallbackInfo ci) {
        MouseInputEntitySubPredicateTypes.init();
    }
}
