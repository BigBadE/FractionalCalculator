package software.bigbade.fractioncalculator.math.expressions;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import software.bigbade.fractioncalculator.math.AnswerConsumer;
import software.bigbade.fractioncalculator.math.graphics.IText;
import software.bigbade.fractioncalculator.math.values.IValue;

import java.util.List;

@RequiredArgsConstructor
public final class SubtractionExpression implements IExpression {
    private final List<IValue> values;

    @Getter
    private final int index;

    @Getter
    private final boolean parentheses;

    @Getter
    @Setter
    private int valueIndex = -1;

    @Override
    public int getPriority() {
        return parentheses ? 3 : 0;
    }

    @Override
    public IValue operate(AnswerConsumer consumer) {
        consumer.printText("Subtract " + values.get(valueIndex+1).getValue() + " from "
                + values.get(valueIndex).getValue() + ":");
        return values.get(valueIndex).subtract(values.get(valueIndex+1));
    }

    @Override
    public String toString(List<IValue> values) {
        return (parentheses ? "(" : "") + values.get(valueIndex).getValue() + " - " + values.get(valueIndex+1).getValue() + (parentheses ? ")" : "");
    }

    @Override
    public void draw(List<IText> texts, AnswerConsumer consumer) {
        texts.get(valueIndex).render(consumer);
        consumer.drawText("-");
        if(texts.size() != valueIndex+1) {
            texts.get(valueIndex + 1).render(consumer);
        }
    }
}
