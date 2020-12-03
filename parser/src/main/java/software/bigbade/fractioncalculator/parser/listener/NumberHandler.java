package software.bigbade.fractioncalculator.parser.listener;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import software.bigbade.fractioncalculator.math.values.IValue;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class NumberHandler {
    @Getter
    private final List<IValue> values = new ArrayList<>();

    @Getter
    private int index = 0;

    public void addValue(IValue value) {
        values.add(value);
        index++;
    }

    public void setValue(IValue value, int index) {
        values.set(index, value);
    }
}
