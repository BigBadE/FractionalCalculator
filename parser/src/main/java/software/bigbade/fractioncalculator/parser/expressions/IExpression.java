package software.bigbade.fractioncalculator.parser.expressions;

import software.bigbade.fractioncalculator.parser.values.IValue;

import java.util.Optional;

public interface IExpression {
    int getPriority();

    void setFirst(IValue value);

    void setSecond(IValue value);

    IValue operate();

    Optional<IExpression> getParent();
}
