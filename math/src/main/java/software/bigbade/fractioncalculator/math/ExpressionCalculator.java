package software.bigbade.fractioncalculator.math;

import lombok.RequiredArgsConstructor;
import software.bigbade.fractioncalculator.math.expressions.IExpression;
import software.bigbade.fractioncalculator.math.values.IValue;

import java.util.List;
import java.util.Set;

@RequiredArgsConstructor
public class ExpressionCalculator {
    private final List<IValue> values;
    private final Set<IExpression> expressions;

    public void calculate() {

    }
}
