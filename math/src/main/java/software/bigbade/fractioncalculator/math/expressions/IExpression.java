package software.bigbade.fractioncalculator.math.expressions;

import software.bigbade.fractioncalculator.math.AnswerConsumer;
import software.bigbade.fractioncalculator.math.graphics.IText;
import software.bigbade.fractioncalculator.math.values.IValue;

import java.util.List;

public interface IExpression {
    int getIndex();

    int getPriority();

    default int getUsedValues() {
        return 2;
    }

    void setValueIndex(int index);

    int getValueIndex();

    IValue operate(AnswerConsumer consumer);

    String toString(List<IValue> values);

    boolean shouldDrawEquation();

    void draw(List<IText> texts, AnswerConsumer consumer);
}
