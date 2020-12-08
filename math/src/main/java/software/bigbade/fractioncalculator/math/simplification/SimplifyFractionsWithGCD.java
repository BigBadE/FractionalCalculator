package software.bigbade.fractioncalculator.math.simplification;

import software.bigbade.fractioncalculator.math.AnswerConsumer;
import software.bigbade.fractioncalculator.math.values.FractionValue;
import software.bigbade.fractioncalculator.math.values.IValue;

public class SimplifyFractionsWithGCD implements ISimplifier {
    /**
     * @return true if the value is a fraction and has a GDC of !=1 in the numerator or denominator
     */
    @Override
    public boolean matches(IValue value) {
        if(!(value instanceof FractionValue)) {
            return false;
        }

        return ((FractionValue) value).getNumerator().gcd(((FractionValue) value).getDenominator())
                .compare(FractionValue.ONE) != 0;
    }

    /**
     * @return fraction of the numerator and denominator divided by the found GDC.
     */
    @Override
    public IValue simplify(IValue value, AnswerConsumer consumer) {
        FractionValue fractionValue = (FractionValue) value;
        IValue gcd = fractionValue.getNumerator().gcd(fractionValue.getDenominator());
        return new FractionValue(fractionValue.getNumerator().divide(gcd, false),
                fractionValue.getDenominator().divide(gcd, false));
    }
}
