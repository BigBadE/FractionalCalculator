package software.bigbade.fractioncalculator.math.simplification;

import software.bigbade.fractioncalculator.math.AnswerConsumer;
import software.bigbade.fractioncalculator.math.values.FractionValue;
import software.bigbade.fractioncalculator.math.values.IValue;
import software.bigbade.fractioncalculator.math.values.MixedNumberValue;
import software.bigbade.fractioncalculator.math.values.NumberValue;

import java.math.BigDecimal;

public class SimplifyZeroFractions implements ISimplifier {
    public static final NumberValue ZERO = new NumberValue(BigDecimal.ZERO);

    @Override
    public boolean matches(IValue value) {
        if(value instanceof MixedNumberValue) {
            MixedNumberValue mixedNumber = (MixedNumberValue) value;
            return mixedNumber.getNumber().getValue().equals("0");
        } else if(value instanceof FractionValue) {

            FractionValue fractionValue = (FractionValue) value;
            return fractionValue.getNumerator().compare(ZERO) == 0;
        }
        return false;
    }

    @Override
    public IValue simplify(IValue value, AnswerConsumer consumer) {
        consumer.printText("Simplify " + value.getValue() + " to 0");
        return ZERO;
    }
}
