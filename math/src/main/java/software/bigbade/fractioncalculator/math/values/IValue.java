package software.bigbade.fractioncalculator.math.values;

import java.math.BigDecimal;

/**
 * A value, such as a fraction, number, or variable
 */
public interface IValue {
    BigDecimal getDecimalValue();

    String getValue();

    IValue add(IValue other);

    IValue subtract(IValue other);

    IValue multiply(IValue other);

    IValue divide(IValue other);

    IValue exponent(IValue other);

    IValue abs();

    IValue gcd(IValue other);

    IValue lcm(IValue other);

    IValue modulo(IValue other);

    IValue floor();

    /**
     * Functions like any other compare method
     * @see Integer#compare(int, int)
     * @param other Other value
     * @return -1, 0, or 1.
     */
    int compare(IValue other);
}
