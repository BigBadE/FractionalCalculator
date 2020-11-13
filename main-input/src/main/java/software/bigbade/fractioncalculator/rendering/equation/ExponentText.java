package software.bigbade.fractioncalculator.rendering.equation;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import software.bigbade.fractioncalculator.math.AnswerConsumer;
import software.bigbade.fractioncalculator.math.graphics.IText;

import javax.annotation.Nullable;

@Getter
@RequiredArgsConstructor
public class ExponentText implements IText {
    @Nullable
    private final IText base;
    @Nullable
    private final IText exponent;

    @Getter
    private boolean drawn = false;

    @Override
    public void render(AnswerConsumer consumer) {
        if(drawn) {
            return;
        }

        drawn = true;

        if(base != null) {
            base.render(consumer);
        }

        if(exponent != null) {
            double textSize = consumer.getTextSize();
            consumer.setTextSize(textSize * 0.3);
            exponent.render(consumer);
            consumer.setTextSize(textSize);
        }
    }
}
