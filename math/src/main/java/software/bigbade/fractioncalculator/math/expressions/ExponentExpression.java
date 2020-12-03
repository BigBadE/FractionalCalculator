package software.bigbade.fractioncalculator.math.expressions;

import lombok.Getter;
import lombok.Setter;
import software.bigbade.fractioncalculator.math.AnswerConsumer;
import software.bigbade.fractioncalculator.math.graphics.IText;
import software.bigbade.fractioncalculator.math.values.IValue;

import java.util.List;

public class ExponentExpression implements IExpression {
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

    public ExponentExpression(List<IValue> values, int index, int parentheses, boolean isParenthesized) {
        this.values = values;
        this.parentheses = isParenthesized;
        this.index = index;
        this.priority = (parentheses >> 25 << 1) + 524290 + (index & 524287);
    }

    @Override
    public IValue operate(AnswerConsumer consumer) {
        IValue firstValue = values.get(valueIndex);
        IValue secondValue = values.get(valueIndex+1);
        return firstValue.exponent(secondValue);
    }

    @Override
    public String toString(List<IValue> values) {
        IValue firstValue = values.get(valueIndex);
        IValue secondValue = values.get(valueIndex+1);
        StringBuilder builder = new StringBuilder(parentheses ? "(" : "");
        builder.append(firstValue.getValue()).append("^").append(secondValue.getValue());
        if(parentheses) {
            builder.append(')');
        }
        return builder.toString();
    }

    @Override
    public boolean shouldDrawEquation() {
        return false;
    }

    @Override
    public void draw(List<IText> texts, AnswerConsumer consumer) {
        texts.get(valueIndex).render(consumer);
        if(texts.size() != valueIndex+1) {
            double oldSize = consumer.getTextSize();
            consumer.setTextSize(oldSize*.3d);
            texts.get(valueIndex+1).render(consumer);
            consumer.setTextSize(oldSize);
        }
    }
}
