package software.bigbade.fractioncalculator.parser.values;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class NumberValue implements IValue {
    @Getter
    private final Number numericValue;

    @Override
    public String getValue() {
        //Round non-integers really close to a whole numbers to a whole number. It should always be whole,
        //but just in case round it.
        if(!(numericValue instanceof Integer) && numericValue.doubleValue()%1==0) {
            return (int) Math.round(numericValue.doubleValue()) + "";
        }
        return numericValue.toString();
    }
}
