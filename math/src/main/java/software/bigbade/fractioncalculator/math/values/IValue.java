package software.bigbade.fractioncalculator.math.values;

import java.util.List;

/**
 * A value, such as a fraction, number, or variable
 */
public interface IValue {
    String getValue(List<IValue> values);

    IValue add(IValue other);

    IValue subtract(IValue other);

    IValue multiply(IValue other);

    IValue divide(IValue other);
}
