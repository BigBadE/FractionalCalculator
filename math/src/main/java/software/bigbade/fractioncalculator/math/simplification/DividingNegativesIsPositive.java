package software.bigbade.fractioncalculator.math.simplification;

import software.bigbade.fractioncalculator.math.AnswerConsumer;
import software.bigbade.fractioncalculator.math.values.FractionValue;
import software.bigbade.fractioncalculator.math.values.IValue;
import software.bigbade.fractioncalculator.math.values.MixedNumberValue;
import software.bigbade.fractioncalculator.math.values.NumberValue;

import java.math.BigDecimal;

public class DividingNegativesIsPositive implements ISimplifier {
    public static final IValue NEGATIVE_ONE = new NumberValue(BigDecimal.valueOf(-1));

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

    @Override
    public IValue simplify(IValue value, AnswerConsumer consumer) {
        FractionValue fractionValue;
        if (value instanceof FractionValue) {
            fractionValue = (FractionValue) value;
        } else {
            fractionValue = ((MixedNumberValue) value).getFraction();
        }
        IValue answer = new FractionValue(fractionValue.getNumerator().multiply(NEGATIVE_ONE),
                fractionValue.getDenominator().multiply(NEGATIVE_ONE), fractionValue.isParenthesis());
        consumer.printText("Dividing negatives is same as dividing positives, so " + value.getValue()
                + " simplifies to " + answer.getValue());
        if(value instanceof MixedNumberValue) {
            MixedNumberValue mixedNumberValue = (MixedNumberValue) value;
            answer = new MixedNumberValue(mixedNumberValue.getNumber(), (FractionValue) answer, false);
        }
        return answer;
    }
}
