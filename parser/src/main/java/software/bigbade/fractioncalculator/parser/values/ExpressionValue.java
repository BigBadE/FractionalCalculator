package software.bigbade.fractioncalculator.parser.values;

import lombok.RequiredArgsConstructor;
import software.bigbade.fractioncalculator.parser.expressions.ExpressionType;

@RequiredArgsConstructor
public class ExpressionValue<T extends IValue, E extends IValue> {
    private final T first;
    private final E second;
    private final ExpressionType type;

    public IValue calculate() {

    }
}
