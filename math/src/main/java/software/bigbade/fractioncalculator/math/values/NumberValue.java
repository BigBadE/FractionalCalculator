package software.bigbade.fractioncalculator.math.values;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.math.MathContext;

@RequiredArgsConstructor
public class NumberValue implements IValue {
    public static final String UNIMPLEMENTED_OPERATION = "Unimplemented operation";

    @Getter
    private final BigDecimal numericValue;

    @Override
    public BigDecimal getDecimalValue() {
        return numericValue;
    }

    @Override
    public String getValue() {
        return numericValue.toString();
    }

    @Override
    public IValue add(IValue other) {
        if (other instanceof NumberValue) {
            return new NumberValue(numericValue.add(((NumberValue) other).numericValue));
        } else if (other instanceof FractionValue) {
            other.add(this);
        }
        throw new IllegalArgumentException(UNIMPLEMENTED_OPERATION);
    }

    @Override
    public IValue subtract(IValue other) {
        if (other instanceof NumberValue) {
            return new NumberValue(numericValue.subtract(((NumberValue) other).numericValue));
        }
        throw new IllegalArgumentException(UNIMPLEMENTED_OPERATION);
    }

    @Override
    public IValue multiply(IValue other) {
        if (other instanceof NumberValue) {
            return new NumberValue(numericValue.multiply(((NumberValue) other).numericValue));
        } else if (other instanceof FractionValue) {
            return other.multiply(this);
        }
        throw new IllegalArgumentException(UNIMPLEMENTED_OPERATION);
    }

    @Override
    public IValue divide(IValue other) {
        if (other instanceof NumberValue) {
            return new NumberValue(numericValue.divide(((NumberValue) other).numericValue, MathContext.DECIMAL32));
        } else if (other instanceof FractionValue) {
            FractionValue otherFraction = (FractionValue) other;
            return new FractionValue(otherFraction.getDenominator().multiply(this), otherFraction.getNumerator(), false);
        }
        throw new IllegalArgumentException(UNIMPLEMENTED_OPERATION);
    }
}
