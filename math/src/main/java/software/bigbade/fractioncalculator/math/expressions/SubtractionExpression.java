package software.bigbade.fractioncalculator.math.expressions;

import software.bigbade.fractioncalculator.math.AnswerConsumer;
import software.bigbade.fractioncalculator.math.values.IValue;

import java.util.List;

/**
 * Implementation of subtraction
 */
public final class SubtractionExpression extends BasicExpression {
    private static final char[] CHARACTERS = new char[] { '-' };

    public SubtractionExpression(int index, int parentheses) {
        super(index, parentheses);
    }

    @Override
    int getOperationPriority() {
        return 0;
    }

    @Override
    public IValue operate(AnswerConsumer consumer, List<IValue> values) {
        consumer.printText("Subtract " + values.get(getValueIndex()).toString() + " from "
                + values.get(getValueIndex() + 1).toString() + ":");
        return values.get(getValueIndex()).subtract(values.get(getValueIndex() + 1), false);
    }

    @Override
    char[] getCharacters() {
        return CHARACTERS;
    }
}
