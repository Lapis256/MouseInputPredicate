package io.github.laps256.mouse_input.predicate;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import io.github.laps256.mouse_input.util.MouseInput;

import java.util.Optional;


public record MouseInputPredicate(Optional<Boolean> right, Optional<Boolean> left, Optional<Boolean> middle) {
    public static final Codec<MouseInputPredicate> CODEC = RecordCodecBuilder.create(
            instance -> instance.group(
                            Codec.BOOL.optionalFieldOf("right").forGetter(MouseInputPredicate::right),
                            Codec.BOOL.optionalFieldOf("left").forGetter(MouseInputPredicate::left),
                            Codec.BOOL.optionalFieldOf("middle").forGetter(MouseInputPredicate::middle)
                    )
                    .apply(instance, MouseInputPredicate::new)
    );

    public boolean matches(MouseInput mouseInput) {
        return this.right.map(right -> right == mouseInput.rightClick()).orElse(true) &&
               this.left.map(left -> left == mouseInput.leftClick()).orElse(true) &&
               this.middle.map(middle -> middle == mouseInput.middleClick()).orElse(true);
    }
}
