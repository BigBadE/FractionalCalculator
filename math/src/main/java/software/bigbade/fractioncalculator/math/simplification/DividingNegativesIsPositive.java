package software.bigbade.fractioncalculator.math.simplification;

import software.bigbade.fractioncalculator.math.AnswerConsumer;
import software.bigbade.fractioncalculator.math.values.FractionValue;
import software.bigbade.fractioncalculator.math.values.IValue;
import software.bigbade.fractioncalculator.math.values.MixedNumberValue;
import software.bigbade.fractioncalculator.math.values.NumberValue;

import java.math.BigDecimal;

public class DividingNegativesIsPositive implements ISimplifier {
    public static final IValue NEGATIVE_ONE = new NumberValue(BigDecimal.valueOf(-1));

    /**
     * @return true if the fraction (or mixed number) has a negative in the numerator and denominator.
     */
    @Override
    public boolean matches(IValue value) {
        FractionValue fractionValue;
        if (value instanceof FractionValue) {
            fractionValue = (FractionValue) value;
        } else if (value instanceof MixedNumberValue) {
            fractionValue = ((MixedNumberValue) value).getFraction();
        } else {
            return false;
        }
        return (fractionValue.getNumerator().compare(SimplifyZeroFractions.ZERO) < 0
                && fractionValue.getDenominator().compare(SimplifyZeroFractions.ZERO) < 0);
    }

    /**
     * @return absolute value of the input, because it is positive.
     */
    @Override
    public IValue simplify(IValue value, AnswerConsumer consumer) {
        IValue answer = value.abs();
        consumer.printText("Dividing negatives is the same as dividing positives, so " + value.toString()
                + " simplifies to " + answer.toString());
        return answer.abs();
    }
}
