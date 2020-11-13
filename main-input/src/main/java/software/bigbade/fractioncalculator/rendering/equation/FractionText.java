package software.bigbade.fractioncalculator.rendering.equation;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import software.bigbade.fractioncalculator.math.AnswerConsumer;
import software.bigbade.fractioncalculator.math.graphics.IText;

import javax.annotation.Nullable;

@Getter
@RequiredArgsConstructor
public class FractionText implements IText {
    @Nullable
    private final IText numerator;
    @Nullable
    private final IText denominator;

    @Getter
    private boolean drawn = false;

    @Override
    public void render(AnswerConsumer consumer) {
        if(drawn) {
            return;
        }

        drawn = true;
        consumer.setTextSize(consumer.getTextSize()/2);
        double currentXOffset = consumer.getXOffset();
        double currentYOffset = consumer.getYOffset();
        if(numerator != null) {
            numerator.render(consumer);
        }
        double offset = consumer.getXOffset();
        consumer.setXOffset(currentXOffset);
        consumer.setYOffset((int) (currentYOffset+consumer.getTextSize()/2));
        if(denominator != null) {
            denominator.render(consumer);
        }
        offset = Math.max(offset, consumer.getXOffset());
        consumer.setYOffset(currentYOffset);
        consumer.setXOffset(offset);
        consumer.drawLine(currentXOffset, currentYOffset, offset, currentYOffset);
    }
}
