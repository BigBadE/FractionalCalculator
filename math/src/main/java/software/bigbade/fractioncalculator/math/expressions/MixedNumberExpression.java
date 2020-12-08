package software.bigbade.fractioncalculator.math.expressions;

import software.bigbade.fractioncalculator.math.AnswerConsumer;
import software.bigbade.fractioncalculator.math.exception.ParseException;
import software.bigbade.fractioncalculator.math.values.FractionValue;
import software.bigbade.fractioncalculator.math.values.IValue;
import software.bigbade.fractioncalculator.math.values.MixedNumberValue;
import software.bigbade.fractioncalculator.math.values.NumberValue;

import java.util.List;

/**
 * Implementation of mixed numbers
 */
public class MixedNumberExpression extends BasicExpression {
    private static final char[] CHARACTERS = new char[] { '_', '/' };

    public MixedNumberExpression(int index, int parentheses) {
        super(index, parentheses);
    }

    @Override
    int getOperationPriority() {
        return 3;
    }

    @Override
    public IValue operate(AnswerConsumer consumer, List<IValue> values) {
        IValue first = values.get(getValueIndex());
        IValue second = values.get(getValueIndex()+1);
        IValue third = values.get(getValueIndex()+2);
        if(!(first instanceof NumberValue)) {
            throw new ParseException("Illegal mixed number!");
        }
        return new MixedNumberValue((NumberValue) first, new FractionValue(second, third));
    }

    @Override
    public int getUsedValues() {
        return 3;
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
