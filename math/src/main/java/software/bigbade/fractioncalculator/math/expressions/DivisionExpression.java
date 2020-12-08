package software.bigbade.fractioncalculator.math.expressions;

import software.bigbade.fractioncalculator.math.AnswerConsumer;
import software.bigbade.fractioncalculator.math.values.FractionValue;
import software.bigbade.fractioncalculator.math.values.IValue;

import java.util.List;

/**
 * Implementation of division
 */
public class DivisionExpression extends BasicExpression {
    private static final char[] CHARACTERS = new char[] { '/' };

    public DivisionExpression(int index, int parentheses) {
        super(index, parentheses);
    }

    @Override
    int getOperationPriority() {
        return 1;
    }

    @Override
    public IValue operate(AnswerConsumer consumer, List<IValue> values) {
        return new FractionValue(values.get(getValueIndex()), values.get(getValueIndex() + 1));
    }

    @Override
    char[] getCharacters() {
        return CHARACTERS;
    }

    @Override
    public boolean shouldPrint() {
        return false;
    }
}
