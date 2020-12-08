package software.bigbade.fractioncalculator.math.simplification;

import software.bigbade.fractioncalculator.math.AnswerConsumer;
import software.bigbade.fractioncalculator.math.values.FractionValue;
import software.bigbade.fractioncalculator.math.values.IValue;
import software.bigbade.fractioncalculator.math.values.MixedNumberValue;

public class SimplifyMixedNumberFractions implements ISimplifier {
    /**
     * @return true if there is a mixed number inside a fraction
     */
    @Override
    public boolean matches(IValue value) {
        return value instanceof FractionValue && (((FractionValue) value).getNumerator() instanceof MixedNumberValue ||
                ((FractionValue) value).getDenominator() instanceof MixedNumberValue);
    }

    /**
     * @return a fraction with fractions instead of mixed numbers in the numerator/denominator
     */
    @Override
    public IValue simplify(IValue value, AnswerConsumer consumer) {
        FractionValue fractionValue = (FractionValue) value;
        IValue numerator = fractionValue.getNumerator();
        IValue denominator = fractionValue.getDenominator();
        if(numerator instanceof MixedNumberValue) {
            consumer.printText("Simplify " + numerator.toString() + " to a fraction");
            numerator = MixedNumberValue.convertToImproperFraction((MixedNumberValue) numerator);
        }
        if(denominator instanceof MixedNumberValue) {
            consumer.printText("Simplify " + denominator.toString() + " to a fraction");
            denominator = MixedNumberValue.convertToImproperFraction((MixedNumberValue) denominator);
        }
        return new FractionValue(numerator, denominator);
    }
}
