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
}
