package software.bigbade.fractioncalculator.parser;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import software.bigbade.fractioncalculator.math.AnswerConsumer;
import software.bigbade.fractioncalculator.math.expressions.IExpression;
import software.bigbade.fractioncalculator.math.simplification.DividingNegativesIsPositive;
import software.bigbade.fractioncalculator.math.simplification.ISimplifier;
import software.bigbade.fractioncalculator.math.simplification.SimplifyFractionsInFractions;
import software.bigbade.fractioncalculator.math.simplification.SimplifyFractionsWithLCD;
import software.bigbade.fractioncalculator.math.simplification.SimplifyImproperFractions;
import software.bigbade.fractioncalculator.math.simplification.SimplifyMixedNumberFractions;
import software.bigbade.fractioncalculator.math.simplification.SimplifyZeroFractions;
import software.bigbade.fractioncalculator.math.values.IValue;
import software.bigbade.fractioncalculator.parser.listener.CalculatorParserListener;

import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

@RequiredArgsConstructor
public class FractionCalculatorParser {
    private final CalculatorParserListener listener = new CalculatorParserListener();

    private final List<ISimplifier> simplifiers = Arrays.asList(new DividingNegativesIsPositive(),
            new SimplifyMixedNumberFractions(),
            new SimplifyFractionsInFractions(),
            new SimplifyFractionsWithLCD(),
            new SimplifyImproperFractions(),
            new SimplifyZeroFractions());

    @Getter
    private List<IValue> values = Collections.emptyList();
    @Getter
    private Set<IExpression> expressions = Collections.emptySet();

    public void parse(String line) {
        listener.parse(line);
        expressions = listener.getExpressions();
        values = listener.getValues();
    }

    public void calculate(AnswerConsumer consumer) {
        for (IExpression expression : listener.getExpressions()) {
            if (expression.getValueIndex() == -1) {
                consumer.printText("Invalid input for " + expression.getClass());
                return;
            }
        }

        values = listener.getValues();
        expressions = listener.getExpressions();

        consumer.printEquation(expressions, values);

        Iterator<IExpression> iterator = listener.getExpressions().iterator();
        while (iterator.hasNext()) {
            IExpression expression = iterator.next();
            listener.getNumberHandler().setValue(expression.operate(consumer), expression.getValueIndex());
            for (int i = expression.getUsedValues() - 1; i > 0; i--) {
                listener.getExpressionHandler().removeValue(expression.getValueIndex() + i);
            }
            iterator.remove();
            if (expression.shouldDrawEquation()) {
                consumer.printEquation(listener.getExpressions(), listener.getValues());
            }
        }

        simplify(consumer);

        consumer.printText("Answer: ");
        consumer.printAnswer(listener.getExpressions(), listener.getValues());
    }

    private void simplify(AnswerConsumer consumer) {
        int tries = 0;
        boolean found = true;
        while (found && tries < 5) {
            found = false;
            for (int i = 0; i < values.size(); i++) {
                if(simplifyValue(consumer, i)) {
                    tries++;
                    found = true;
                    break;
                }
            }
        }
    }

    private boolean simplifyValue(AnswerConsumer consumer, int i) {
        for (ISimplifier simplifier : simplifiers) {
            IValue value = values.get(i);
            if (simplifier.matches(value)) {
                values.set(i, simplifier.simplify(value, consumer));
                return true;
            }
        }
        return false;
    }
}
