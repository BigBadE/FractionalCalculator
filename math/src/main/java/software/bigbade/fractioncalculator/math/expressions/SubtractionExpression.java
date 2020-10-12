package software.bigbade.fractioncalculator.math.expressions;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import software.bigbade.fractioncalculator.math.ExpressionLogger;
import software.bigbade.fractioncalculator.math.values.IValue;

import java.util.List;

@RequiredArgsConstructor
public final class SubtractionExpression implements IExpression {
    private final List<IValue> values;

    @Getter
    private final int index;

    @Getter
    private final boolean parentheses;

    @Getter
    private boolean finished = false;

    @Getter
    @Setter
    private int first;
    @Getter
    @Setter
    private int second;

    @Override
    public int getPriority() {
        return parentheses ? 3 : 0;
    }

    @Override
    public IValue operate() {
        return subtractNumbers(values.get(first), values.get(second));
    }

    @Override
    public String toString(List<IValue> values) {
        return (parentheses ? "(" : "") + values.get(first).getValue(values) + " - " + values.get(second).getValue(values) + (parentheses ? ")" : "");
    }

    public IValue subtractNumbers(IValue first, IValue second) {
        ExpressionLogger.getLogger().addLine("Subtract " + first.getValue(values) + " from " + second.getValue(values) + ":");
        finished = true;
        return first.subtract(second);
    }
}
