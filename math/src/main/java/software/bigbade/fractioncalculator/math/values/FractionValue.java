package software.bigbade.fractioncalculator.math.values;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import software.bigbade.fractioncalculator.math.simplification.SimplifyZeroFractions;

import java.math.BigDecimal;

@RequiredArgsConstructor
public class FractionValue implements IValue {
    public static final IValue ONE = new NumberValue(BigDecimal.ONE);

    @Getter
    private final IValue numerator;
    @Getter
    private final IValue denominator;

    /**
     * Returns the decimal value of the numerator divided by the denominator
     */
    @Override
    public BigDecimal getDecimalValue() {
        return numerator.divide(denominator, false).getDecimalValue();
    }

    /**
     * Surrounds the numerator or denominator in parenthesis if they are complex to make
     * the problem more clear visually
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        if (!(numerator instanceof NumberValue)) {
            builder.append('(').append(numerator.toString()).append(')');
        } else {
            builder.append(numerator.toString());
        }
        builder.append('/');
        if (!(denominator instanceof NumberValue)) {
            builder.append('(').append(denominator.toString()).append(')');
        } else {
            builder.append(denominator.toString());
        }
        return builder.toString();
    }

    /**
     * @return If other is a number, multiply it by the denominator and add it to the numerator
     * If other is a fraction, given a/b+c/d, return (ad+bc)/bd
     */
    @Override
    public IValue add(IValue other) {
        if (other instanceof NumberValue) {
            return new FractionValue(numerator.add(denominator.multiply(other)), denominator);
        } else if (other instanceof FractionValue) {
            FractionValue otherFraction = (FractionValue) other;
            return new FractionValue(numerator.multiply(otherFraction.denominator)
                    .add(otherFraction.numerator.multiply(denominator)),
                    denominator.multiply(otherFraction.denominator));
        } else if (other instanceof MixedNumberValue) {
            return other.add(this);
        }
        throw new IllegalArgumentException(NumberValue.UNIMPLEMENTED_OPERATION);
    }

    /**
     * @return If other is a number, multiply it by the denominator and subtract it from the numerator
     * If other is a fraction, given a/b-c/d, return (ad-bc)/bd
     */
    @Override
    public IValue subtract(IValue other, boolean flipped) {
        if (other instanceof NumberValue) {
            return new FractionValue(numerator.subtract(denominator.multiply(other), flipped), denominator);
        } else if (other instanceof FractionValue) {
            FractionValue otherFraction = (FractionValue) other;
            return new FractionValue(numerator.multiply(otherFraction.denominator)
                    .subtract(otherFraction.numerator.multiply(denominator), flipped),
                    denominator.multiply(otherFraction.denominator));
        } else if (other instanceof MixedNumberValue) {
            return other.subtract(this, !flipped);
        }
        throw new IllegalArgumentException(NumberValue.UNIMPLEMENTED_OPERATION);
    }

    /**
     * @return If other is a number, multiply it by the denominator and multiply it by the numerator
     * If other is a fraction, given a/b*c/d, return ab/bd
     */
    @Override
    public IValue multiply(IValue other) {
        if (other instanceof NumberValue) {
            return new FractionValue(numerator.multiply(other), denominator);
        } else if (other instanceof FractionValue) {
            FractionValue second = (FractionValue) other;
            return new FractionValue(numerator.multiply(second.numerator),
                    denominator.multiply(second.denominator));
        } else if (other instanceof MixedNumberValue) {
            return other.multiply(this);
        }
        throw new IllegalArgumentException(NumberValue.UNIMPLEMENTED_OPERATION);
    }

    /**
     * @return If other is a number, given (a/b)*c return bc/a
     * If other is a fraction, given (a/b)/(c/d), return (ad)/bc
     */
    @Override
    public IValue divide(IValue other, boolean flipped) {
        if (other instanceof NumberValue) {
            if (flipped) {
                return new FractionValue(getDenominator().multiply(other), getNumerator());
            } else {
                return new FractionValue(getNumerator(), getDenominator().multiply(other));
            }
        } else if (other instanceof FractionValue) {
            FractionValue fractionValue = (FractionValue) other;
            if (flipped) {
                return new FractionValue(getDenominator().multiply(fractionValue.getNumerator()),
                        getNumerator().multiply(fractionValue.getDenominator()));
            } else {
                return new FractionValue(getNumerator().multiply(fractionValue.getDenominator()),
                        getDenominator().multiply(fractionValue.getNumerator()));
            }
        } else if (other instanceof MixedNumberValue) {
            return other.divide(this, !flipped);
        }
        throw new IllegalArgumentException(NumberValue.UNIMPLEMENTED_OPERATION);
    }

    /**
     * @return Given a%b, if a is a fraction return b-(a*[[a/b]])
     */
    @Override
    public IValue modulo(IValue other, boolean flipped) {
        return flipped ? subtract(other.multiply(other.divide(this, false).roundTowardsZero()), false)
                : other.subtract(multiply(divide(other, false).roundTowardsZero()), false);
    }

    /**
     * @return Continually compares the numerator by the denominator until the numerator is greater than the denominator,
     * then returns the last value
     */
    @Override
    public IValue roundTowardsZero() {
        IValue current = ONE;
        int change = compare(SimplifyZeroFractions.ZERO);
        IValue temp;
        while ((temp = current.multiply(denominator)).compare(numerator) == change) {
            current = temp;
        }
        return current;
    }

    /**
     * @return Given (a/b)^c, return a^c/b^c
     */
    @Override
    public IValue exponent(IValue other, boolean flipped) {
        if(flipped) {
            if(denominator.compare(ONE) != 0) {
                throw new UnsupportedOperationException("Square roots not supported yet!");
            }
            return other.exponent(numerator, true);
        } else {
            return new FractionValue(numerator.exponent(other, false), denominator.exponent(other, false));
        }
    }

    /**
     * @return Given abs(a/b), return abs(a)/abs(b)
     */
    @Override
    public IValue abs() {
        return new FractionValue(numerator.abs(), denominator.abs());
    }

    /**
     * @return Given gdc(a/b, c/d), return gcd(a, c)/lcm(b, d)
     */
    @Override
    public IValue gcd(IValue other) {
        FractionValue fractionValue;
        if (other instanceof NumberValue) {
            fractionValue = new FractionValue(other, ONE);
        } else if (other instanceof FractionValue) {
            fractionValue = (FractionValue) other;
        } else {
            return other.gcd(this);
        }
        return numerator.gcd(fractionValue.getNumerator()).divide(denominator.lcm(fractionValue.getDenominator()), false);
    }

    /**
     * @return Given lcm(a/b, c/d), return lcm(a, c)/gcd(b, d)
     */
    @Override
    public IValue lcm(IValue other) {
        FractionValue fractionValue;
        if (other instanceof NumberValue) {
            fractionValue = new FractionValue(other, ONE);
        } else if (other instanceof FractionValue) {
            fractionValue = (FractionValue) other;
        } else {
            return other.gcd(this);
        }
        return numerator.lcm(fractionValue.getNumerator()).divide(denominator.gcd(fractionValue.getDenominator()), false);
    }

    /**
     * @return Simplifies both values to a NumberValue, then compares them.
     */
    @Override
    public int compare(IValue other) {
        if (other instanceof NumberValue) {
            return denominator.compare(other.multiply(numerator));
        } else if (other instanceof FractionValue) {
            FractionValue fractionValue = (FractionValue) other;
            return numerator.multiply(fractionValue.getDenominator())
                    .compare(denominator.multiply(fractionValue.getNumerator()));
        } else if (other instanceof MixedNumberValue) {
            return other.compare(this);
        }
        throw new IllegalArgumentException(NumberValue.UNIMPLEMENTED_OPERATION);
    }
}
