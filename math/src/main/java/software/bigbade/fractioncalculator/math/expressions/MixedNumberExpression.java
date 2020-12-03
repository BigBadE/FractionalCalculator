package software.bigbade.fractioncalculator.math.expressions;

import lombok.Getter;
import lombok.Setter;
import software.bigbade.fractioncalculator.math.AnswerConsumer;
import software.bigbade.fractioncalculator.math.exception.ParseException;
import software.bigbade.fractioncalculator.math.graphics.IText;
import software.bigbade.fractioncalculator.math.values.FractionValue;
import software.bigbade.fractioncalculator.math.values.IValue;
import software.bigbade.fractioncalculator.math.values.MixedNumberValue;
import software.bigbade.fractioncalculator.math.values.NumberValue;

import java.util.List;

public class MixedNumberExpression implements IExpression {
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

    public MixedNumberExpression(List<IValue> values, int index, int parentheses, boolean isParenthesized) {
        this.values = values;
        this.parentheses = isParenthesized;
        this.index = index;
        this.priority = (parentheses >> 25 << 1) + 524291 + (index & 524287);
    }

    @Override
    public int getUsedValues() {
        return 3;
    }

    @Override
    public IValue operate(AnswerConsumer consumer) {
        IValue firstValue = values.get(valueIndex);
        IValue secondValue = values.get(valueIndex+1);
        IValue thirdValue = values.get(valueIndex+2);
        if (!(firstValue instanceof NumberValue) || !(secondValue instanceof NumberValue) || !(thirdValue instanceof NumberValue)) {
            throw new ParseException("Illegal mixed number! " + firstValue.getValue() + "_" + secondValue.getValue()
                    + "/" + thirdValue.getValue());
        }
        return new MixedNumberValue((NumberValue) firstValue, new FractionValue(secondValue, thirdValue, false), parentheses);
    }

    @Override
    public String toString(List<IValue> values) {
        IValue firstValue = values.get(valueIndex);
        IValue secondValue = values.get(valueIndex+1);
        IValue thirdValue = values.get(valueIndex+2);
        StringBuilder builder = new StringBuilder(parentheses ? "(" : "");
        builder.append(firstValue.getValue()).append('_').append(secondValue.getValue()).append('/').append(thirdValue.getValue());
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
        consumer.drawText("/");
        if(texts.size() != valueIndex+1) {
            texts.get(valueIndex + 1).render(consumer);
        }
    }
}
