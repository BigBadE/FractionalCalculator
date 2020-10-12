package software.bigbade.fractioncalculator.math.values;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import software.bigbade.fractioncalculator.math.expressions.MultiplicationExpression;

import java.util.List;

@RequiredArgsConstructor
public class NumberValue implements IValue {
    public static final String UNIMPLEMENTED_OPERATION = "Unimplemented operation";

    @Getter
    private final Number numericValue;

    @Override
    public String getValue(List<IValue> valueList) {
        //Round non-integers really close to a whole numbers to a whole number. It should always be whole,
        //but just in case round it.
        if (!(numericValue instanceof Integer) && numericValue.doubleValue() % 1 == 0) {
            return (int) Math.round(numericValue.doubleValue()) + "";
        }
        return numericValue.toString();
    }

    @Override
    public IValue add(IValue other) {
        if (other instanceof NumberValue) {
            return new NumberValue(numericValue.doubleValue()
                    + ((NumberValue) other).numericValue.doubleValue());
        } else if (other instanceof FractionValue) {
            other.add(this);
        }
        throw new IllegalArgumentException(UNIMPLEMENTED_OPERATION);
    }

    @Override
    public IValue subtract(IValue other) {
        if (other instanceof NumberValue) {
            return new NumberValue(numericValue.doubleValue()
                    - ((NumberValue) other).numericValue.doubleValue());
        }
        throw new IllegalArgumentException(UNIMPLEMENTED_OPERATION);
    }

    @Override
    public IValue multiply(IValue other) {
        if (other instanceof NumberValue) {
            return new NumberValue(numericValue.doubleValue()
                    * ((NumberValue) other).numericValue.doubleValue());
        } else if (other instanceof FractionValue) {
            return other.multiply(this);
        }
        throw new IllegalArgumentException(UNIMPLEMENTED_OPERATION);
    }

    @Override
    public IValue divide(IValue other) {
        if (other instanceof NumberValue) {
            return new NumberValue(numericValue.doubleValue()
                    / ((NumberValue) other).numericValue.doubleValue());
        } else if (other instanceof FractionValue) {

        }
        throw new IllegalArgumentException(UNIMPLEMENTED_OPERATION);
    }
}
