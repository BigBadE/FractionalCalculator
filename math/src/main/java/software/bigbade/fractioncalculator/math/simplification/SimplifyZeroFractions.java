package software.bigbade.fractioncalculator.math.simplification;

import software.bigbade.fractioncalculator.math.AnswerConsumer;
import software.bigbade.fractioncalculator.math.values.FractionValue;
import software.bigbade.fractioncalculator.math.values.IValue;
import software.bigbade.fractioncalculator.math.values.MixedNumberValue;
import software.bigbade.fractioncalculator.math.values.NumberValue;

import java.math.BigDecimal;

public class SimplifyZeroFractions implements ISimplifier {
    public static final NumberValue ZERO = new NumberValue(BigDecimal.ZERO);

    /**
     * @return true if the numerator of the fraction is 0
     */
    @Override
    public boolean matches(IValue value) {
        if(value instanceof MixedNumberValue) {
            MixedNumberValue mixedNumber = (MixedNumberValue) value;
            return mixedNumber.getNumber().compare(ZERO) == 0;
        } else if(value instanceof FractionValue) {
            FractionValue fractionValue = (FractionValue) value;
            return fractionValue.getNumerator().compare(ZERO) == 0;
        }
        return false;
    }

    /**
     * @return Returns 0
     */
    @Override
    public IValue simplify(IValue value, AnswerConsumer consumer) {
        consumer.printText("Simplify " + value.toString() + " to 0");
        return ZERO;
    }
}
