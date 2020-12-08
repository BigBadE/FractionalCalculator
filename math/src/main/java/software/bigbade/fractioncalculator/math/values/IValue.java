package software.bigbade.fractioncalculator.math.values;

import java.math.BigDecimal;

/**
 * A value, such as a fraction, number, or variable
 */
public interface IValue {
    /**
     * Decimal value of the value
     * @return Decimal
     */
    BigDecimal getDecimalValue();

    /**
     * Adds this with another
     * @param other Other value
     * @return Added value
     */
    IValue add(IValue other);

    /**
     * Subtracts this by another
     * @param other Other value
     * @param flipped If the subtraction should be flipped
     * @return Subtracted value
     */
    IValue subtract(IValue other, boolean flipped);

    /**
     * Multiplies this by another
     * @param other Other value
     * @return Multiplied value
     */
    IValue multiply(IValue other);

    /**
     * Divides this by another
     * @param other Other value
     * @param flipped If the division should be flipped
     * @return Divided value
     */
    IValue divide(IValue other, boolean flipped);

    /**
     * Exponents this by another
     * @param other Power value
     * @return Exponent of both values
     */
    IValue exponent(IValue other, boolean flipped);

    /**
     * Returns the absolute value of the value
     * @return abs of this value
     */
    IValue abs();

    /**
     * Finds the greatest common divisor of two values
     * @param other Other value to compare against
     * @return GCD of the two values
     */
    IValue gcd(IValue other);

    /**
     * Finds the least common multiple of two values
     * @param other Other value to compare against
     * @return LCM of the two values
     */
    IValue lcm(IValue other);

    /**
     * Modulo's two values
     * @param other Number to divide by
     * @param flipped If the modulo should be flipped
     * @return Remainer of this/other
     */
    IValue modulo(IValue other, boolean flipped);

    /**
     * Floors the value
     * @return Floored value
     */
    IValue roundTowardsZero();

    /**
     * Compares two values
     * @see Integer#compare(int, int)
     * @param other Other value
     * @return -1, 0, or 1.
     */
    int compare(IValue other);
}
