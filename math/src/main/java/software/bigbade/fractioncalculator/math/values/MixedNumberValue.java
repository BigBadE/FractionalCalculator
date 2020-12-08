package software.bigbade.fractioncalculator.math.values;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import software.bigbade.fractioncalculator.math.simplification.DividingNegativesIsPositive;
import software.bigbade.fractioncalculator.math.simplification.SimplifyZeroFractions;

import java.math.BigDecimal;

@RequiredArgsConstructor
public class MixedNumberValue implements IValue {
    @Getter
    private final NumberValue number;
    @Getter
    private final FractionValue fraction;

    /**
     * @return number + fraction
     */
    @Override
    public BigDecimal getDecimalValue() {
        return number.getDecimalValue().add(fraction.getDecimalValue());
    }

    @Override
    public String toString() {
        return number.toString() + "_" + fraction.toString();
    }

    /**
     * @return For all math operations, convert to an improper fraction and run the operation on the fraction
     */
    @Override
    public IValue add(IValue other) {
        return convertToImproperFraction(this).add(other);
    }

    /**
     * @return For all math operations, convert to an improper fraction and run the operation on the fraction
     */
    @Override
    public IValue subtract(IValue other, boolean flipped) {
        return convertToImproperFraction(this).subtract(other, flipped);
    }

    /**
     * @return For all math operations, convert to an improper fraction and run the operation on the fraction
     */
    @Override
    public IValue multiply(IValue other) {
        return convertToImproperFraction(this).multiply(other);
    }

    /**
     * @return For all math operations, convert to an improper fraction and run the operation on the fraction
     */
    @Override
    public IValue divide(IValue other, boolean flipped) {
        return convertToImproperFraction(this).divide(other, flipped);
    }

    /**
     * @return For all math operations, convert to an improper fraction and run the operation on the fraction
     */
    @Override
    public IValue exponent(IValue other, boolean flipped) {
        return convertToImproperFraction(this).exponent(other, flipped);
    }

    /**
     * @return For all math operations, convert to an improper fraction and run the operation on the fraction
     */
    @Override
    public IValue abs() {
        return new MixedNumberValue((NumberValue) number.abs(), (FractionValue) fraction.abs());
    }

    /**
     * @return For all math operations, convert to an improper fraction and run the operation on the fraction
     */
    @Override
    public IValue gcd(IValue other) {
        return convertToImproperFraction(this).gcd(other);
    }

    /**
     * @return For all math operations, convert to an improper fraction and run the operation on the fraction
     */
    @Override
    public IValue lcm(IValue other) {
        return convertToImproperFraction(this).lcm(other);
    }

    /**
     * @return For all math operations, convert to an improper fraction and run the operation on the fraction
     */
    @Override
    public IValue modulo(IValue other, boolean flipped) {
        return convertToImproperFraction(this).modulo(other, flipped);
    }

    /**
     * @return For all math operations, convert to an improper fraction and run the operation on the fraction
     */
    @Override
    public IValue roundTowardsZero() {
        return convertToImproperFraction(this).roundTowardsZero();
    }

    /**
     * @return For all math operations, convert to an improper fraction and run the operation on the fraction
     */
    @Override
    public int compare(IValue other) {
        return convertToImproperFraction(this).compare(other);
    }

    /**
     * Converts the mixed value to a fraction.
     * Given a_b/c, returns (ac+b)/c
     * @param mixedValue Mixed value to convert to an improper fraction
     * @return Improper fraction equivalent to the mixedValue
     */
    public static FractionValue convertToImproperFraction(MixedNumberValue mixedValue) {
        boolean negative = mixedValue.getNumber().compare(SimplifyZeroFractions.ZERO) == -1;
        IValue positiveNumber = mixedValue.getNumber();
        if(negative) {
            positiveNumber = positiveNumber.multiply(DividingNegativesIsPositive.NEGATIVE_ONE);
        }
        FractionValue testValue = new FractionValue(positiveNumber.multiply(mixedValue.getFraction().getDenominator())
                .add(mixedValue.getFraction().getNumerator()),
                mixedValue.getFraction().getDenominator());
        if(negative) {
            testValue = (FractionValue) testValue.multiply(DividingNegativesIsPositive.NEGATIVE_ONE);
        }
        return testValue;
    }
}
