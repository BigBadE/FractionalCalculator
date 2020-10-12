package software.bigbade.fractioncalculator.math.expressions;

import software.bigbade.fractioncalculator.math.values.IValue;

import java.util.List;

public interface IExpression {
    int getPriority();

    int getIndex();

    void setFirst(int index);

    int getFirst();

    void setSecond(int index);

    int getSecond();

    boolean isFinished();

    IValue operate();

    String toString(List<IValue> values);
}
