package software.bigbade.fractioncalculator.math.expressions;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import software.bigbade.fractioncalculator.math.AnswerConsumer;
import software.bigbade.fractioncalculator.math.graphics.IText;
import software.bigbade.fractioncalculator.math.values.IValue;

import java.util.List;

@RequiredArgsConstructor
public class ExponentExpression implements IExpression {
    private final List<IValue> values;

    @Getter
    private final int index;

    @Getter
    private final boolean parenthesis;

    @Getter
    private boolean finished = false;

    @Getter
    @Setter
    private int valueIndex = -1;

    @Override
    public int getPriority() {
        return parenthesis ? 3 : 1;
    }

    @Override
    public IValue operate(AnswerConsumer consumer) {
        finished = true;
        IValue firstValue = values.get(valueIndex);
        IValue secondValue = values.get(valueIndex+1);
        consumer.printText(firstValue.getValue(values) + " to the power of "
                + secondValue.getValue(values) + ":");
        return firstValue.divide(secondValue);
    }

    @Override
    public String toString(List<IValue> values) {
        IValue firstValue = values.get(valueIndex);
        IValue secondValue = values.get(valueIndex+1);
        StringBuilder builder = new StringBuilder(parenthesis ? "(" : "");
        builder.append(firstValue.getValue(values)).append("^").append(secondValue.getValue(values));
        if(parenthesis) {
            builder.append(')');
        }
        return builder.toString();
    }

    @Override
    public void draw(List<IText> texts, AnswerConsumer consumer) {
        throw new IllegalStateException("All division expressions should be ExpressionValues or NumberValues");
    }
}
