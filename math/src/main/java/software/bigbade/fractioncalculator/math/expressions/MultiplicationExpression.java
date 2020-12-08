package software.bigbade.fractioncalculator.math.expressions;

import software.bigbade.fractioncalculator.math.AnswerConsumer;
import software.bigbade.fractioncalculator.math.values.IValue;

import java.util.List;

/**
 * Implementation of multiplication
 */
public class MultiplicationExpression extends BasicExpression {
    private static final char[] CHARACTERS = new char[]{'*'};

    public MultiplicationExpression(int index, int parentheses) {
        super(index, parentheses);
    }

    @Override
    int getOperationPriority() {
        return 1;
    }

    @Override
    public IValue operate(AnswerConsumer consumer, List<IValue> values) {
        consumer.printText(values.get(getValueIndex()).toString() + " times "
                + values.get(getValueIndex() + 1).toString() + ":");
        return values.get(getValueIndex()).multiply(values.get(getValueIndex() + 1));
    }

    @Override
    char[] getCharacters() {
        return CHARACTERS;
    }
}
