package software.bigbade.fractioncalculator.math.expressions;

import lombok.Getter;
import lombok.Setter;
import software.bigbade.fractioncalculator.math.AnswerConsumer;
import software.bigbade.fractioncalculator.math.graphics.IText;
import software.bigbade.fractioncalculator.math.values.FractionValue;
import software.bigbade.fractioncalculator.math.values.IValue;
import software.bigbade.fractioncalculator.math.values.NumberValue;

import java.util.List;

public class MultiplicationExpression implements IExpression {
    private final List<IValue> values;

    @Getter
    private final int priority;

    @Getter
    private final boolean parentheses;

    @Getter
    private final int index;

    @Getter
    @Setter
    private int valueIndex = -1;

    public MultiplicationExpression(List<IValue> values, int index, int parentheses, boolean isParenthesized) {
        this.values = values;
        this.parentheses = isParenthesized;
        this.index = index;
        this.priority = (parentheses >> 25 << 1) + 524289 + (index & 524287);
    }

    @Override
    public IValue operate(AnswerConsumer consumer) {
        IValue firstValue = values.get(valueIndex);
        IValue secondValue = values.get(valueIndex+1);
        consumer.printText("Multiply " + firstValue.getValue() + " by "
                + secondValue.getValue() + ":");
        return firstValue.multiply(secondValue);
    }

    @Override
    public String toString(List<IValue> values) {
        IValue firstValue = values.get(valueIndex);
        IValue secondValue = values.get(valueIndex+1);
        StringBuilder builder = new StringBuilder(parentheses ? "(" : "");
        if(firstValue instanceof NumberValue && secondValue instanceof NumberValue) {
            builder.append(firstValue.getValue()).append("×").append(secondValue.getValue());
        } else if(firstValue instanceof NumberValue && secondValue instanceof FractionValue) {
            builder.append(firstValue.getValue()).append('(').append(secondValue.getValue()).append(')');
        } else {
            builder.append(secondValue.getValue()).append('(').append(firstValue.getValue()).append(')');
        }
        if(parentheses) {
            builder.append(')');
        }
        return builder.toString();
    }

    @Override
    public boolean shouldDrawEquation() {
        return true;
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
