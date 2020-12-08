package software.bigbade.fractioncalculator.math.simplification;

import software.bigbade.fractioncalculator.math.AnswerConsumer;
import software.bigbade.fractioncalculator.math.exception.ParseException;
import software.bigbade.fractioncalculator.math.values.FractionValue;
import software.bigbade.fractioncalculator.math.values.IValue;
import software.bigbade.fractioncalculator.math.values.MixedNumberValue;
import software.bigbade.fractioncalculator.math.values.NumberValue;

public class SimplifyImproperFractions implements ISimplifier {
    /**
     * @return true if the numerator is greater than the denominator
     */
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
                    fractionValue.getDenominator());
        }
        if(fractionValue.getDenominator().compare(SimplifyZeroFractions.ZERO) < 0) {
            fractionValue = new FractionValue(fractionValue.getNumerator(),
                    fractionValue.getDenominator().multiply(DividingNegativesIsPositive.NEGATIVE_ONE));
        }
        return fractionValue.getNumerator().compare(fractionValue.getDenominator()) >= 0;
    }

    /**
     * @return A mixed number if the numerator is greater than the denominator, or a number if
     * 1 &lt; numerator/denominator &lt; 2
     */
    @Override
    public IValue simplify(IValue value, AnswerConsumer consumer) {
        FractionValue fractionValue;
        if(value instanceof FractionValue) {
            fractionValue = (FractionValue) value;
        } else {
            fractionValue = MixedNumberValue.convertToImproperFraction((MixedNumberValue) value);
        }
        if(fractionValue.getDenominator().compare(SimplifyZeroFractions.ZERO) == 0) {
            throw new ParseException("Cannot divide by zero!");
        }
        IValue divisor = fractionValue.getNumerator().divide(fractionValue.getDenominator(), false).roundTowardsZero();
        IValue remainder = fractionValue.getNumerator().modulo(fractionValue.getDenominator(), false);
        if(remainder.compare(SimplifyZeroFractions.ZERO) == 0) {
            consumer.printText("Simplify " + value.toString() + " to " + divisor.toString());
            return divisor;
        } else {
            if(fractionValue.getDenominator().compare(SimplifyZeroFractions.ZERO) < 0) {
                fractionValue = new FractionValue(fractionValue.getNumerator(),
                        fractionValue.getDenominator().multiply(DividingNegativesIsPositive.NEGATIVE_ONE));
            }
            consumer.printText("Convert " + value.toString() + " to a mixed number");
            consumer.printText("Divide " + fractionValue.getNumerator().toString() + " by "
                    + fractionValue.getDenominator().toString()
                    + ", setting the dividend to the mixed number, and remainder to the numerator.");
            MixedNumberValue mixedValue = new MixedNumberValue((NumberValue) divisor,
                    new FractionValue(remainder.abs(), fractionValue.getDenominator()));
            consumer.printText("Get the mixed number " + mixedValue.toString());
            return mixedValue;
        }
    }
}
