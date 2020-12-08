package software.bigbade.fractioncalculator.math.expressions;

import software.bigbade.fractioncalculator.math.AnswerConsumer;
import software.bigbade.fractioncalculator.math.values.IValue;

import java.util.List;

/**
 * Implementation of addition
 */
public final class AdditionExpression extends BasicExpression {
    private static final char[] CHARACTERS = new char[] { '+' };

    public AdditionExpression(int index, int parentheses) {
        super(index, parentheses);
    }

    @Override
    int getOperationPriority() {
        return 0;
    }

    @Override
    public IValue operate(AnswerConsumer consumer, List<IValue> values) {
        consumer.printText("Add " + values.get(getValueIndex()).toString() + " to "
                + values.get(getValueIndex() + 1).toString() + ":");
        return values.get(getValueIndex()).add(values.get(getValueIndex() + 1));
    }

    @Override
    char[] getCharacters() {
        return CHARACTERS;
    }
}
