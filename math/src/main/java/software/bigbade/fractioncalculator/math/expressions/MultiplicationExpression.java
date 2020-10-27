package software.bigbade.fractioncalculator.math.expressions;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import software.bigbade.fractioncalculator.math.AnswerConsumer;
import software.bigbade.fractioncalculator.math.graphics.IText;
import software.bigbade.fractioncalculator.math.values.FractionValue;
import software.bigbade.fractioncalculator.math.values.IValue;
import software.bigbade.fractioncalculator.math.values.NumberValue;

import java.util.List;

@RequiredArgsConstructor
public class MultiplicationExpression implements IExpression {
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
        consumer.printText("Multiply " + firstValue.getValue(values) + " by "
                + secondValue.getValue(values) + ":");
        return firstValue.multiply(secondValue);
    }

    @Override
    public String toString(List<IValue> values) {
        IValue firstValue = values.get(valueIndex);
        IValue secondValue = values.get(valueIndex+1);
        StringBuilder builder = new StringBuilder(parenthesis ? "(" : "");
        if(firstValue instanceof NumberValue && secondValue instanceof NumberValue) {
            builder.append(firstValue.getValue(values)).append("×").append(secondValue.getValue(values));
        } else if(firstValue instanceof NumberValue && secondValue instanceof FractionValue) {
            builder.append(firstValue.getValue(values)).append('(').append(secondValue.getValue(values)).append(')');
        } else {
            builder.append(secondValue.getValue(values)).append('(').append(firstValue.getValue(values)).append(')');
        }
        if(parenthesis) {
            builder.append(')');
        }
        return builder.toString();
    }

    @Override
    public void draw(List<IText> texts, AnswerConsumer consumer) {
        texts.get(valueIndex).render(consumer);
        consumer.drawText("×");
        if(texts.size() != valueIndex+1) {
            texts.get(valueIndex + 1).render(consumer);
        }
    }
}
