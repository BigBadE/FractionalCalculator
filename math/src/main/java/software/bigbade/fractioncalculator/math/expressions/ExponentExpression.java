package software.bigbade.fractioncalculator.math.expressions;

import software.bigbade.fractioncalculator.math.AnswerConsumer;
import software.bigbade.fractioncalculator.math.values.IValue;

import java.util.List;

/**
 * Implementation of exponents
 */
public class ExponentExpression extends BasicExpression {
    private static final char[] CHARACTERS = new char[] { '^' };

    public ExponentExpression(int index, int parentheses) {
        super(index, parentheses);
    }

    @Override
    int getOperationPriority() {
        return 2;
    }

    @Override
    public IValue operate(AnswerConsumer consumer, List<IValue> values) {
        consumer.printText(values.get(getValueIndex()).toString() + " to the power of "
                + values.get(getValueIndex() + 1).toString() + ":");
        return values.get(getValueIndex()).exponent(values.get(getValueIndex() + 1), false);
    }

    @Override
    char[] getCharacters() {
        return CHARACTERS;
    }
}
