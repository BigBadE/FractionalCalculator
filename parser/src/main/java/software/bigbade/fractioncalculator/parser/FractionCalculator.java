package software.bigbade.fractioncalculator.parser;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import software.bigbade.fractioncalculator.math.AnswerConsumer;
import software.bigbade.fractioncalculator.math.expressions.BasicExpression;
import software.bigbade.fractioncalculator.math.simplification.DividingNegativesIsPositive;
import software.bigbade.fractioncalculator.math.simplification.ISimplifier;
import software.bigbade.fractioncalculator.math.simplification.SimplifyFractionsInFractions;
import software.bigbade.fractioncalculator.math.simplification.SimplifyFractionsWithGCD;
import software.bigbade.fractioncalculator.math.simplification.SimplifyImproperFractions;
import software.bigbade.fractioncalculator.math.simplification.SimplifyMixedNumberFractions;
import software.bigbade.fractioncalculator.math.simplification.SimplifyZeroFractions;
import software.bigbade.fractioncalculator.math.values.IValue;
import software.bigbade.fractioncalculator.parser.listener.FractionCalculatorParser;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

@RequiredArgsConstructor
public class FractionCalculator {
    //A parser without a lexer using a basic LL* impl
    private final FractionCalculatorParser listener = new FractionCalculatorParser();

    //Simplifiers applied to values in that order
    private final List<ISimplifier> simplifiers = Arrays.asList(new DividingNegativesIsPositive(),
            new SimplifyMixedNumberFractions(),
            new SimplifyFractionsInFractions(),
            new SimplifyFractionsWithGCD(),
            new SimplifyImproperFractions(),
            new SimplifyZeroFractions());

    //Reference to values
    @Getter
    private final List<IValue> values = listener.getValues();

    //Reference to expressions
    @Getter
    private final Set<BasicExpression> expressions = listener.getExpressions();

    /**
     * Parses an equation
     * @param line Equation to parse
     */
    public void parse(String line) {
        listener.parse(line);
    }

    /**
     * Solves the parsed equation
     * @param consumer Prints strings to given output (Console, GUI, etc...)
     */
    public void calculate(AnswerConsumer consumer) {
        for (BasicExpression expression : listener.getExpressions()) {
            if (expression.getValueIndex() == -1) {
                consumer.printText("Invalid input for " + expression.getClass());
                return;
            }
        }

        consumer.printEquation(expressions, values);

        Iterator<BasicExpression> iterator = listener.getExpressions().iterator();
        while (iterator.hasNext()) {
            BasicExpression expression = iterator.next();
            listener.getValues().set(expression.getValueIndex(), expression.operate(consumer, values));
            for (int i = expression.getUsedValues() - 1; i > 0; i--) {
                listener.getExpressionHandler().removeValue(expression.getValueIndex() + i);
            }
            iterator.remove();
            for(BasicExpression after : listener.getExpressions()) {
                if(after.getIndex() > expression.getIndex()) {
                    after.setIndex(after.getIndex() - 1);
                }
            }
            if (expression.shouldPrint()) {
                consumer.printEquation(listener.getExpressions(), listener.getValues());
            }
        }

        simplify(consumer);

        consumer.printText("Answer: ");
        consumer.printAnswer(listener.getExpressions(), listener.getValues());
    }

    /**
     * Simplifies the calculated value
     * @param consumer AnswerConsumer to print output strings
     */
    private void simplify(AnswerConsumer consumer) {
        boolean found = true;
        while (found) {
            found = false;
            for (int i = 0; i < values.size(); i++) {
                if(simplifyValue(consumer, i)) {
                    found = true;
                    break;
                }
            }
        }
    }

    /**
     * Simplifies a single value
     * @param consumer AnswerConsumer to print output strings
     * @param index Index of the value
     * @return If the value was simplified
     */
    private boolean simplifyValue(AnswerConsumer consumer, int index) {
        for (ISimplifier simplifier : simplifiers) {
            IValue value = values.get(index);
            if (simplifier.matches(value)) {
                values.set(index, simplifier.simplify(value, consumer));
                return true;
            }
        }
        return false;
    }
}
