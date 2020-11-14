package software.bigbade.fractioncalculator.math.values;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;

@RequiredArgsConstructor
public class FractionValue implements IValue {
    @Getter
    private final IValue numerator;
    @Getter
    private final IValue denominator;

    private final boolean parenthesis;

    @Override
    public BigDecimal getDecimalValue() {
        return numerator.divide(denominator).getDecimalValue();
    }

    @Override
    public String getValue() {
        return (parenthesis ? "(" : "") + numerator.getValue() + "/" + denominator.getValue() + (parenthesis ? ")" : "");
    }

    @Override
    public IValue divide(IValue other) {
        return null;
    }

    @Override
    public IValue multiply(IValue other) {
        if (other instanceof NumberValue) {
            return new FractionValue(numerator.multiply(other), denominator, parenthesis);
        } else if (other instanceof FractionValue) {
            FractionValue second = (FractionValue) other;
            return new FractionValue(numerator.multiply(second.numerator),
                    denominator.multiply(second.denominator), parenthesis);
        }
        throw new IllegalArgumentException(NumberValue.UNIMPLEMENTED_OPERATION);
    }

    @Override
    public IValue subtract(IValue other) {
        return null;
    }

    @Override
    public IValue add(IValue other) {
        if (other instanceof NumberValue) {
            return new FractionValue(numerator.add(denominator.multiply(other)), denominator, parenthesis);
        } else if (other instanceof FractionValue) {
            FractionValue otherFraction = (FractionValue) other;
            return new FractionValue(numerator.multiply(otherFraction.denominator)
                    .add(otherFraction.numerator.multiply(denominator)),
                    denominator.multiply(otherFraction.denominator), parenthesis);
        }
        throw new IllegalArgumentException(NumberValue.UNIMPLEMENTED_OPERATION);
    }
}
