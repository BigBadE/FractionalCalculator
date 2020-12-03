package software.bigbade.fractioncalculator.math.expressions;

import lombok.Getter;
import lombok.Setter;
import software.bigbade.fractioncalculator.math.AnswerConsumer;
import software.bigbade.fractioncalculator.math.graphics.IText;
import software.bigbade.fractioncalculator.math.values.IValue;

import java.util.List;

public final class AdditionExpression implements IExpression {
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

    public AdditionExpression(List<IValue> values, int index, int parentheses, boolean isParenthesized) {
        this.values = values;
        this.parentheses = isParenthesized;
        this.index = index;
        this.priority = (parentheses >> 25 << 1) + 524288 + (index & 524287);
    }

    @Override
    public IValue operate(AnswerConsumer consumer) {
        consumer.printText("Add " + values.get(valueIndex).getValue() + " to "
                + values.get(valueIndex+1).getValue() + ":");
        return values.get(valueIndex).add(values.get(valueIndex+1));
    }

    @Override
    public String toString(List<IValue> values) {
        return (parentheses ? "(" : "") + values.get(valueIndex).getValue() + " + " + values.get(valueIndex+1).getValue() + (parentheses ? ")" : "");
    }

    @Override
    public boolean shouldDrawEquation() {
        return true;
    }

    @Override
    public void draw(List<IText> texts, AnswerConsumer consumer) {
        texts.get(valueIndex).render(consumer);
        consumer.drawText("+");
        if(texts.size() != valueIndex+1) {
            texts.get(valueIndex+1).render(consumer);
        }
    }
}
