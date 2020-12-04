package software.bigbade.fractioncalculator.math.simplification;

import software.bigbade.fractioncalculator.math.AnswerConsumer;
import software.bigbade.fractioncalculator.math.values.FractionValue;
import software.bigbade.fractioncalculator.math.values.IValue;
import software.bigbade.fractioncalculator.math.values.MixedNumberValue;

public class SimplifyMixedNumberFractions implements ISimplifier {
    @Override
    public boolean matches(IValue value) {
        return value instanceof FractionValue && (((FractionValue) value).getNumerator() instanceof MixedNumberValue ||
                ((FractionValue) value).getDenominator() instanceof MixedNumberValue);
    }

    @Override
    public IValue simplify(IValue value, AnswerConsumer consumer) {
        FractionValue fractionValue = (FractionValue) value;
        IValue numerator = fractionValue.getNumerator();
        IValue denominator = fractionValue.getDenominator();
        if(numerator instanceof MixedNumberValue) {
            consumer.printText("Simplify " + numerator.getValue() + " to a fraction");
            numerator = MixedNumberValue.convertToImproperFraction((MixedNumberValue) numerator);
        }
        if(denominator instanceof MixedNumberValue) {
            consumer.printText("Simplify " + denominator.getValue() + " to a fraction");
            denominator = MixedNumberValue.convertToImproperFraction((MixedNumberValue) denominator);
        }
        return new FractionValue(numerator, denominator, false);
    }
}
