package software.bigbade.fractioncalculator.math.simplification;

import software.bigbade.fractioncalculator.math.AnswerConsumer;
import software.bigbade.fractioncalculator.math.values.IValue;

public interface ISimplifier {
    boolean matches(IValue value);

    IValue simplify(IValue value, AnswerConsumer consumer);
}
