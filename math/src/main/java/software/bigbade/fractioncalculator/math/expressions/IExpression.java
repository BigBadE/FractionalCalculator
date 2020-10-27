package software.bigbade.fractioncalculator.math.expressions;

import software.bigbade.fractioncalculator.math.AnswerConsumer;
import software.bigbade.fractioncalculator.math.graphics.IText;
import software.bigbade.fractioncalculator.math.values.IValue;

import java.util.List;

public interface IExpression {
    int getPriority();

    int getIndex();

    void setValueIndex(int index);

    int getValueIndex();

    boolean isFinished();

    IValue operate(AnswerConsumer consumer);

    String toString(List<IValue> values);

    void draw(List<IText> texts, AnswerConsumer consumer);
}
