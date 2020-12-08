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
    public String toString() {
        return numericValue.toString();
    }

    /**
     * @return This number plus the other, or calls the same on the other
     * if it isn't a number.
     */
    @Override
    public IValue add(IValue other) {
        if (other instanceof NumberValue) {
            return new NumberValue(numericValue.add(((NumberValue) other).numericValue));
        } else if (other instanceof FractionValue || other instanceof MixedNumberValue) {
            return other.add(this);
        }
        throw new IllegalArgumentException(UNIMPLEMENTED_OPERATION);
    }

    /**
     * @return This number minus the other, or calls the same flipped on the other
     * if it isn't a number.
     */
    @Override
    public IValue subtract(IValue other, boolean flipped) {
        if (other instanceof NumberValue) {
            if(flipped) {
                return new NumberValue(((NumberValue) other).numericValue.subtract(numericValue));
            } else {
                return new NumberValue(numericValue.subtract(((NumberValue) other).numericValue));
            }
        } else if(other instanceof FractionValue || other instanceof MixedNumberValue) {
            return other.subtract(this, !flipped);
        }
        throw new IllegalArgumentException(UNIMPLEMENTED_OPERATION);
    }

    /**
     * @return This number times the other, or calls the same on the other
     * if it isn't a number.
     */
    @Override
    public IValue multiply(IValue other) {
        if (other instanceof NumberValue) {
            return new NumberValue(numericValue.multiply(((NumberValue) other).numericValue));
        } else if (other instanceof FractionValue || other instanceof MixedNumberValue) {
            return other.multiply(this);
        }
        throw new IllegalArgumentException(UNIMPLEMENTED_OPERATION);
    }

    /**
     * @return This number divided by the other, or calls the same flipped on the other
     * if it isn't a number.
     */
    @Override
    public IValue divide(IValue other, boolean flipped) {
        if (other instanceof NumberValue) {
            NumberValue otherNumber = (NumberValue) other;
            if(flipped) {
                if(getDecimalValue().compareTo(BigDecimal.ZERO) == 0) {
                    throw new IllegalArgumentException("Can't divide by 0!");
                }
                return new NumberValue(otherNumber.numericValue.divide(numericValue, MathContext.DECIMAL32));
            } else {
                if(otherNumber.getDecimalValue().compareTo(BigDecimal.ZERO) == 0) {
                    throw new IllegalArgumentException("Can't divide by 0!");
                }
                return new NumberValue(numericValue.divide(otherNumber.numericValue, MathContext.DECIMAL32));
            }
        } else if (other instanceof FractionValue || other instanceof MixedNumberValue) {
            return other.divide(this, !flipped);
        }
        throw new IllegalArgumentException(UNIMPLEMENTED_OPERATION);
    }

    /**
     * @return This number modulo the other, or calls the same flipped on the other
     * if it isn't a number.
     */
    @Override
    public IValue modulo(IValue other, boolean flipped) {
        if (other instanceof NumberValue) {
            NumberValue otherNumber = (NumberValue) other;
            return new NumberValue(flipped ?
                    otherNumber.numericValue.remainder(numericValue, MathContext.DECIMAL32)
                    : numericValue.remainder(otherNumber.numericValue, MathContext.DECIMAL32));
        } else if (other instanceof FractionValue || other instanceof MixedNumberValue) {
            return other.modulo(this, !flipped);
        }
        throw new IllegalArgumentException(UNIMPLEMENTED_OPERATION);
    }

    /**
     * @return Numeric value rounded towards 0
     */
    @Override
    public IValue roundTowardsZero() {
        return new NumberValue(numericValue.setScale(0, RoundingMode.DOWN));
    }

    /**
     * @return This number to the other power, or calls the same flipped on the other
     * if it isn't a number.
     */
    @Override
    public IValue exponent(IValue other, boolean flipped) {
        if (other instanceof NumberValue) {
            NumberValue otherNumber = (NumberValue) other;
            if((flipped ? numericValue.abs() : otherNumber.numericValue.abs()).compareTo(MAX_POW) > 0) {
                throw new ParseException("Cannot have a power over " + MAX_POW.toString());
            }
            return new NumberValue(flipped ? otherNumber.numericValue.pow(numericValue.intValue(), MathContext.DECIMAL32)
                    : numericValue.pow(otherNumber.numericValue.intValue(), MathContext.DECIMAL32));
        } else if (other instanceof FractionValue || other instanceof MixedNumberValue) {
            return other.exponent(this, !flipped);
        }
        throw new IllegalArgumentException(UNIMPLEMENTED_OPERATION);
    }

    /**
     * @return Absolute of the number
     */
    @Override
    public IValue abs() {
        return new NumberValue(numericValue.abs());
    }

    /**
     * While other != 0, keeps GCDing this % other
     * @return GCD of the two numbers
     */
    @Override
    public IValue gcd(IValue other) {
        if(other.compare(SimplifyZeroFractions.ZERO) == 0) {
            return this;
        }
        return other.gcd(this.modulo(other, false));
    }

    /**
     * Finds abs(this*other)/gcd(this, other)
     * @return Least common multiple of both
     */
    @Override
    public IValue lcm(IValue other) {
        return multiply(other).abs().divide(gcd(other), false);
    }

    /**
     * Compares two numbers, if other is a fraction return the comparison of this and the other flipped
     * @return -1, 0, or 1 depending on the comparison
     * @see Integer#compare(int, int)
     */
    @Override
    public int compare(IValue other) {
        if (other instanceof NumberValue) {
            NumberValue otherNumber = (NumberValue) other;
            return numericValue.compareTo(otherNumber.getDecimalValue());
        } else if (other instanceof FractionValue || other instanceof MixedNumberValue) {
            return other.compare(this)*-1;
        }
        throw new IllegalArgumentException(UNIMPLEMENTED_OPERATION);
    }
}
