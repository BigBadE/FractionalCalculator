package software.bigbade.fractioncalculator.math;

import software.bigbade.fractioncalculator.math.expressions.BasicExpression;
import software.bigbade.fractioncalculator.math.values.IValue;

import java.util.List;
import java.util.Set;

/**
 * Prints the output strings to the output area.
 */
public interface AnswerConsumer {
    /**
     * Prints the text to the output
     * @param text Text to print
     */
    void printText(String text);

    /**
     * Prints the equation to the output
     * @param expressions Set of expressions in PEMDAS order
     * @param values List of values
     */
    void printEquation(Set<BasicExpression> expressions, List<IValue> values);

    /**
     * Prints the answer and returns it from the method
     * @param expressions Set of expressions in PEMDAS order
     * @param values List of values
     */
    void printAnswer(Set<BasicExpression> expressions, List<IValue> values);
}
