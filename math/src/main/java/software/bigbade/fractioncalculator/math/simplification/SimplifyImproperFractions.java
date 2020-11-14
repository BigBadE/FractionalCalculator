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
        if(!(value instanceof FractionValue)) {
            return false;
        }
        FractionValue fractionValue = (FractionValue) value;
        return fractionValue.getNumerator() instanceof NumberValue && fractionValue.getDenominator() instanceof NumberValue;
    }

    @Override
    public IValue simplify(IValue value, AnswerConsumer consumer) {
        FractionValue fractionValue = (FractionValue) value;
        BigDecimal[] output = fractionValue.getNumerator().getDecimalValue()
                .divideAndRemainder(fractionValue.getDenominator().getDecimalValue());
        MixedNumberValue mixedValue = new MixedNumberValue(new NumberValue(output[0]),
                new FractionValue(new NumberValue(output[1]), fractionValue.getDenominator(), false));
        consumer.printText("Convert " + value.getValue() + " to a mixed number");
        consumer.printText("Divide " + fractionValue.getNumerator().getValue() + " by "
                + fractionValue.getDenominator().getValue()
                + ", setting the dividend to the mixed number, and remainder to the numerator.");
        consumer.printText("Get the mixed number " + mixedValue.getValue());
        return mixedValue;
    }
}
