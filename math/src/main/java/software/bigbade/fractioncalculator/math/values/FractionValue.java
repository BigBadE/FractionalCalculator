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

    @Getter
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
    public IValue add(IValue other) {
        if (other instanceof NumberValue) {
            return new FractionValue(numerator.add(denominator.multiply(other)), denominator, parenthesis);
        } else if (other instanceof FractionValue) {
            FractionValue otherFraction = (FractionValue) other;
            return new FractionValue(numerator.multiply(otherFraction.denominator)
                    .add(otherFraction.numerator.multiply(denominator)),
                    denominator.multiply(otherFraction.denominator), parenthesis);
        } else if (other instanceof MixedNumberValue) {
            return other.add(this);
        }
        throw new IllegalArgumentException(NumberValue.UNIMPLEMENTED_OPERATION);
    }

    @Override
    public IValue subtract(IValue other) {
        if(other instanceof NumberValue) {
            return new FractionValue(numerator.subtract(denominator.multiply(other)), denominator, parenthesis);
        } else if(other instanceof FractionValue) {
            FractionValue otherFraction = (FractionValue) other;
            return new FractionValue(numerator.multiply(otherFraction.denominator)
                    .subtract(otherFraction.numerator.multiply(denominator)),
                    denominator.multiply(otherFraction.denominator), parenthesis);
        } else if(other instanceof MixedNumberValue) {
            return other.subtract(this);
        }
        throw new IllegalArgumentException(NumberValue.UNIMPLEMENTED_OPERATION);
    }

    @Override
    public IValue multiply(IValue other) {
        if (other instanceof NumberValue) {
            return new FractionValue(numerator.multiply(other), denominator, parenthesis);
        } else if (other instanceof FractionValue) {
            FractionValue second = (FractionValue) other;
            return new FractionValue(numerator.multiply(second.numerator),
                    denominator.multiply(second.denominator), parenthesis);
        } else if (other instanceof MixedNumberValue) {
            return other.multiply(this);
        }
        throw new IllegalArgumentException(NumberValue.UNIMPLEMENTED_OPERATION);
    }

    @Override
    public IValue divide(IValue other) {
        if(other instanceof NumberValue) {
            return new FractionValue(getDenominator().multiply(other), getNumerator(), false);
        } else if(other instanceof FractionValue) {
            FractionValue fractionValue = (FractionValue) other;
            return new FractionValue(getNumerator().multiply(fractionValue.getDenominator()),
                    getDenominator().multiply(fractionValue.getNumerator()), false);
        } else if(other instanceof MixedNumberValue) {
            return other.divide(this);
        }
        throw new IllegalArgumentException(NumberValue.UNIMPLEMENTED_OPERATION);
    }

    @Override
    public IValue modulo(IValue other) {
        return other.subtract(multiply(divide(other).floor()));
    }

    @Override
    public IValue floor() {
        IValue current = ONE;
        int change = compare(SimplifyZeroFractions.ZERO);
        IValue temp;
        while ((temp = current.multiply(denominator)).compare(numerator) == change) {
            current = temp;
        }
        return current;
    }

    @Override
    public IValue exponent(IValue other) {
        return new FractionValue(numerator.exponent(other), denominator.exponent(other), parenthesis);
    }

    @Override
    public IValue abs() {
        return new FractionValue(numerator.abs(), denominator.abs(), parenthesis);
    }

    @Override
    public IValue gcd(IValue other) {
        FractionValue fractionValue;
        if(other instanceof NumberValue) {
            fractionValue = new FractionValue(other, ONE, false);
        } else if(other instanceof FractionValue) {
            fractionValue = (FractionValue) other;
        } else {
            return other.gcd(this);
        }
        return numerator.gcd(fractionValue.getNumerator()).divide(denominator.lcm(fractionValue.getDenominator()));
    }

    @Override
    public IValue lcm(IValue other) {
        FractionValue fractionValue;
        if(other instanceof NumberValue) {
            fractionValue = new FractionValue(other, ONE, false);
        } else if(other instanceof FractionValue) {
            fractionValue = (FractionValue) other;
        } else {
            return other.gcd(this);
        }
        return numerator.lcm(fractionValue.getNumerator()).divide(denominator.gcd(fractionValue.getDenominator()));
    }

    @Override
    public int compare(IValue other) {
        if(other instanceof NumberValue) {
            return denominator.compare(other.multiply(numerator));
        } else if(other instanceof FractionValue) {
            FractionValue fractionValue = (FractionValue) other;
            return numerator.multiply(fractionValue.getDenominator())
                    .compare(denominator.multiply(fractionValue.getNumerator()));
        } else if(other instanceof MixedNumberValue) {
            return other.compare(this);
        }
        throw new IllegalArgumentException(NumberValue.UNIMPLEMENTED_OPERATION);
    }
}
