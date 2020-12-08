package software.bigbade.fractioncalculator.math.simplification;

import software.bigbade.fractioncalculator.math.AnswerConsumer;
import software.bigbade.fractioncalculator.math.values.IValue;

public interface ISimplifier {
    /**
     * Checks if the value can be simplified
     * @param value Value to check
     * @return If the value can be simplified
     */
    boolean matches(IValue value);

    /**
     * Simplifies the value
     * @param value Value to simplify
     * @param consumer Prints output strings to output
     * @return Simplified value
     */
    IValue simplify(IValue value, AnswerConsumer consumer);
}
