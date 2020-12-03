package software.bigbade.fractioncalculator.math.values;

import lombok.Getter;
import software.bigbade.fractioncalculator.math.simplification.DividingNegativesIsPositive;
import software.bigbade.fractioncalculator.math.simplification.SimplifyZeroFractions;

import java.math.BigDecimal;

public class MixedNumberValue implements IValue {
    @Getter
    private final FractionValue fraction;
    @Getter
    private final NumberValue number;

    private final boolean parenthesis;

    public MixedNumberValue(NumberValue number, FractionValue fraction, boolean parenthesis) {
        this.number = number;
        this.fraction = fraction;
        this.parenthesis = parenthesis;
    }

    @Override
    public BigDecimal getDecimalValue() {
        return number.getDecimalValue().add(fraction.getDecimalValue());
    }

    @Override
    public String getValue() {
        return (parenthesis ? "(" : "") + number.getValue() + "_" + fraction.getValue() + (parenthesis ? ")" : "");
    }

    @Override
    public IValue add(IValue other) {
        return convertToImproperFraction(this).add(other);
    }

    @Override
    public IValue subtract(IValue other) {
        return convertToImproperFraction(this).subtract(other);
    }

    @Override
    public IValue multiply(IValue other) {
        return convertToImproperFraction(this).multiply(other);
    }

    @Override
    public IValue divide(IValue other) {
        return convertToImproperFraction(this).divide(other);
    }

    @Override
    public IValue exponent(IValue other) {
        return convertToImproperFraction(this).exponent(other);
    }

    @Override
    public int compare(IValue other) {
        return convertToImproperFraction(this).compare(other);
    }

    public static FractionValue convertToImproperFraction(MixedNumberValue mixedValue) {
        boolean negative = mixedValue.getNumber().compare(SimplifyZeroFractions.ZERO) == -1;
        IValue positiveNumber = mixedValue.getNumber();
        if(negative) {
            positiveNumber = positiveNumber.multiply(DividingNegativesIsPositive.NEGATIVE_ONE);
        }
        FractionValue testValue = new FractionValue(positiveNumber.multiply(mixedValue.getFraction().getDenominator())
                .add(mixedValue.getFraction().getNumerator()),
                mixedValue.getFraction().getDenominator(), false);
        if(negative) {
            testValue = (FractionValue) testValue.multiply(DividingNegativesIsPositive.NEGATIVE_ONE);
        }
        return testValue;
    }
}
