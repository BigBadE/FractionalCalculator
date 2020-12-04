package software.bigbade.fractioncalculator.math.simplification;

import software.bigbade.fractioncalculator.math.AnswerConsumer;
import software.bigbade.fractioncalculator.math.values.FractionValue;
import software.bigbade.fractioncalculator.math.values.IValue;
import software.bigbade.fractioncalculator.math.values.MixedNumberValue;
import software.bigbade.fractioncalculator.math.values.NumberValue;

import java.math.BigDecimal;

public class SimplifyImproperFractions implements ISimplifier {
    @Override
    public boolean matches(IValue value) {
        FractionValue fractionValue;
        if(value instanceof FractionValue) {
            fractionValue = (FractionValue) value;
        } else if(value instanceof MixedNumberValue) {
            fractionValue = ((MixedNumberValue) value).getFraction();
        } else {
            return false;
        }
        if(fractionValue.getNumerator().compare(SimplifyZeroFractions.ZERO) < 0) {
            fractionValue = new FractionValue(fractionValue.getNumerator().multiply(DividingNegativesIsPositive.NEGATIVE_ONE),
                    fractionValue.getDenominator(), fractionValue.isParenthesis());
        }
        if(fractionValue.getDenominator().compare(SimplifyZeroFractions.ZERO) < 0) {
            fractionValue = new FractionValue(fractionValue.getNumerator(),
                    fractionValue.getDenominator().multiply(DividingNegativesIsPositive.NEGATIVE_ONE),
                    fractionValue.isParenthesis());
        }
        return fractionValue.getNumerator().compare(fractionValue.getDenominator()) >= 0;
    }

    @Override
    public IValue simplify(IValue value, AnswerConsumer consumer) {
        FractionValue fractionValue;
        if(value instanceof FractionValue) {
            fractionValue = (FractionValue) value;
        } else {
            fractionValue = MixedNumberValue.convertToImproperFraction((MixedNumberValue) value);
        }
        BigDecimal[] output = fractionValue.getNumerator().getDecimalValue()
                .divideAndRemainder(fractionValue.getDenominator().getDecimalValue());
        if(output[1].compareTo(BigDecimal.ZERO) == 0) {
            consumer.printText("Simplify " + value.getValue() + " to " + output[0].toString());
            return new NumberValue(output[0]);
        } else {
            if(fractionValue.getDenominator().compare(SimplifyZeroFractions.ZERO) < 0) {
                fractionValue = new FractionValue(fractionValue.getNumerator(),
                        fractionValue.getDenominator().multiply(DividingNegativesIsPositive.NEGATIVE_ONE), false);
            }
            consumer.printText("Convert " + value.getValue() + " to a mixed number");
            consumer.printText("Divide " + fractionValue.getNumerator().getValue() + " by "
                    + fractionValue.getDenominator().getValue()
                    + ", setting the dividend to the mixed number, and remainder to the numerator.");
            MixedNumberValue mixedValue = new MixedNumberValue(new NumberValue(output[0]),
                    new FractionValue(new NumberValue(output[1].abs()), fractionValue.getDenominator(), false), false);
            consumer.printText("Get the mixed number " + mixedValue.getValue());
            return mixedValue;
        }
    }
}
