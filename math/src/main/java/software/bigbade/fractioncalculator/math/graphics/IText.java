package software.bigbade.fractioncalculator.math.graphics;

import software.bigbade.fractioncalculator.math.AnswerConsumer;

public interface IText {
    boolean isDrawn();

    void render(AnswerConsumer consumer);
}
