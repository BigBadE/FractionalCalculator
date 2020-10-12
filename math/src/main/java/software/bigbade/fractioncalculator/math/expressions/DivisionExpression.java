package software.bigbade.fractioncalculator.math.expressions;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import software.bigbade.fractioncalculator.math.ExpressionLogger;
import software.bigbade.fractioncalculator.math.values.IValue;

import java.util.List;

@RequiredArgsConstructor
public class DivisionExpression implements IExpression {
    private final List<IValue> values;

    @Getter
    private final int index;

    @Getter
    private final boolean parenthesis;

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
        return parenthesis ? 3 : 1;
    }

    @Override
    public IValue operate() {
        finished = true;
        IValue firstValue = values.get(this.first);
        IValue secondValue = values.get(this.second);
        ExpressionLogger.getLogger().addLine("Divide " + firstValue.getValue(values) + " by "
                + secondValue.getValue(values) + ":");
        return firstValue.divide(secondValue);
    }

    @Override
    public String toString(List<IValue> values) {
        IValue firstValue = values.get(first);
        IValue secondValue = values.get(second);
        StringBuilder builder = new StringBuilder(parenthesis ? "(" : "");
        builder.append(firstValue.getValue(values)).append("/").append(secondValue.getValue(values));
        if(parenthesis) {
            builder.append(')');
        }
        return builder.toString();
    }
}
