package software.bigbade.fractioncalculator.math.expressions;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import software.bigbade.fractioncalculator.math.AnswerConsumer;
import software.bigbade.fractioncalculator.math.graphics.IText;
import software.bigbade.fractioncalculator.math.values.FractionValue;
import software.bigbade.fractioncalculator.math.values.IValue;

import java.util.List;

@RequiredArgsConstructor
public class DivisionExpression implements IExpression {
    private final List<IValue> values;

    @Getter
    private final int index;

    @Getter
    private final boolean parenthesis;

    @Getter
    @Setter
    private int valueIndex = -1;

    @Override
    public int getPriority() {
        return parenthesis ? 3 : 1;
    }

    @Override
    public IValue operate(AnswerConsumer consumer) {
        IValue firstValue = values.get(valueIndex);
        IValue secondValue = values.get(valueIndex+1);
        consumer.printText("Convert " + firstValue.getValue() + '/'
                + secondValue.getValue() + " to a fraction:");
        return new FractionValue(firstValue, secondValue, parenthesis);
    }

    @Override
    public String toString(List<IValue> values) {
        IValue firstValue = values.get(valueIndex);
        IValue secondValue = values.get(valueIndex+1);
        StringBuilder builder = new StringBuilder(parenthesis ? "(" : "");
        builder.append(firstValue.getValue()).append('/').append(secondValue.getValue());
        if(parenthesis) {
            builder.append(')');
        }
        return builder.toString();
    }

    @Override
    public void draw(List<IText> texts, AnswerConsumer consumer) {
        throw new IllegalStateException("All division expressions should be FractionValues or decimals");
    }
}
