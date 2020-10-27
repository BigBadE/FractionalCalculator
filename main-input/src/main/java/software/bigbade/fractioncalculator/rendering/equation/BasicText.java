package software.bigbade.fractioncalculator.rendering.equation;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import software.bigbade.fractioncalculator.math.AnswerConsumer;
import software.bigbade.fractioncalculator.math.graphics.IText;

@Getter
@RequiredArgsConstructor
public class BasicText implements IText {
    private final String text;

    @Getter
    private boolean drawn = false;

    @Override
    public void render(AnswerConsumer consumer) {
        drawn = true;
        consumer.drawText(text);
    }
}
