package software.bigbade.fractioncalculator.math.values;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import software.bigbade.fractioncalculator.math.exception.ParseException;
import software.bigbade.fractioncalculator.math.simplification.SimplifyZeroFractions;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

@RequiredArgsConstructor
public class NumberValue implements IValue {
    private static final BigDecimal MAX_POW = new BigDecimal(999999999);

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
    public IValue modulo(IValue other) {
        if (other instanceof NumberValue) {
            NumberValue otherNumber = (NumberValue) other;
            return new NumberValue(numericValue.remainder(otherNumber.numericValue, MathContext.DECIMAL32));
        } else if (other instanceof FractionValue || other instanceof MixedNumberValue) {
            return other.modulo(this);
        }
        throw new IllegalArgumentException(UNIMPLEMENTED_OPERATION);
    }

    @Override
    public IValue floor() {
        return new NumberValue(numericValue.setScale(0, RoundingMode.FLOOR));
    }

    @Override
    public IValue exponent(IValue other) {
        if (other instanceof NumberValue) {
            NumberValue otherNumber = (NumberValue) other;
            if(otherNumber.numericValue.abs().compareTo(MAX_POW) > 0) {
                throw new ParseException("Cannot have a power over " + MAX_POW.toString());
            }
            return new NumberValue(numericValue.pow(otherNumber.numericValue.intValue(), MathContext.DECIMAL32));
        } else if (other instanceof FractionValue || other instanceof MixedNumberValue) {
            return other.divide(this);
        }
        throw new IllegalArgumentException(UNIMPLEMENTED_OPERATION);
    }

    @Override
    public IValue abs() {
        return new NumberValue(numericValue.abs());
    }

    @Override
    public IValue gcd(IValue other) {
        if(other.compare(SimplifyZeroFractions.ZERO) == 0) {
            return this;
        }
        return other.gcd(this.modulo(other));
    }

    @Override
    public IValue lcm(IValue other) {
        return multiply(other).abs().divide(gcd(other));
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
