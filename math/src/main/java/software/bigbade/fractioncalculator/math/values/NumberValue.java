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
        } else if (other instanceof FractionValue || other instanceof MixedNumberValue) {
            return other.add(this);
        }
        throw new IllegalArgumentException(UNIMPLEMENTED_OPERATION);
    }

    @Override
    public IValue subtract(IValue other) {
        if (other instanceof NumberValue) {
            return new NumberValue(numericValue.subtract(((NumberValue) other).numericValue));
        } else if(other instanceof FractionValue || other instanceof MixedNumberValue) {
            return other.add(this);
        }
        throw new IllegalArgumentException(UNIMPLEMENTED_OPERATION);
    }

    @Override
    public IValue multiply(IValue other) {
        if (other instanceof NumberValue) {
            return new NumberValue(numericValue.multiply(((NumberValue) other).numericValue));
        } else if (other instanceof FractionValue || other instanceof MixedNumberValue) {
            return other.multiply(this);
        }
        throw new IllegalArgumentException(UNIMPLEMENTED_OPERATION);
    }

    @Override
    public IValue divide(IValue other) {
        if (other instanceof NumberValue) {
            NumberValue otherNumber = (NumberValue) other;
            if(otherNumber.getDecimalValue().compareTo(BigDecimal.ZERO) == 0) {
                throw new IllegalArgumentException("Can't divide by 0!");
            }
            return new NumberValue(numericValue.divide(otherNumber.numericValue, MathContext.DECIMAL32));
        } else if (other instanceof FractionValue || other instanceof MixedNumberValue) {
            return other.divide(this);
        }
        throw new IllegalArgumentException(UNIMPLEMENTED_OPERATION);
    }

    @Override
    public IValue exponent(IValue other) {
        //TODO
        throw new IllegalArgumentException(UNIMPLEMENTED_OPERATION);
    }

    @Override
    public int compare(IValue other) {
        if (other instanceof NumberValue) {
            NumberValue otherNumber = (NumberValue) other;
            return numericValue.compareTo(otherNumber.getDecimalValue());
        } else if (other instanceof FractionValue || other instanceof MixedNumberValue) {
            return other.compare(this);
        }
        throw new IllegalArgumentException(UNIMPLEMENTED_OPERATION);
    }
}
