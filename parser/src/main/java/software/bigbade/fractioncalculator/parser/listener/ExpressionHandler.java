package software.bigbade.fractioncalculator.parser.listener;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import software.bigbade.fractioncalculator.math.AnswerConsumer;
import software.bigbade.fractioncalculator.math.expressions.AdditionExpression;
import software.bigbade.fractioncalculator.math.expressions.DivisionExpression;
import software.bigbade.fractioncalculator.math.expressions.ExponentExpression;
import software.bigbade.fractioncalculator.math.expressions.BasicExpression;
import software.bigbade.fractioncalculator.math.expressions.MixedNumberExpression;
import software.bigbade.fractioncalculator.math.expressions.MultiplicationExpression;
import software.bigbade.fractioncalculator.math.expressions.SubtractionExpression;
import software.bigbade.fractioncalculator.math.values.IValue;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

@RequiredArgsConstructor
public class ExpressionHandler {
    //Blank answer consumer, so the AnswerConsumer param is never null on expressions when they must be called before usual.
    private static final BlankAnswerConsumer BLANK_ANSWER_CONSUMER = new BlankAnswerConsumer();

    //A TreeSet of expressions in PEMDAS order using the compare method
    @Getter
    private final Set<BasicExpression> expressions = new TreeSet<>(ExpressionHandler::compare);

    //Reference to the list of values
    private final List<IValue> numbers;

    //A hierarchy, so if you do 1+(2+3), 2+3 doesn't override 1+5
    private final List<BasicExpression> hierarchy = new ArrayList<>();

    //The current expression
    @Getter
    private BasicExpression current;

    //Index of the current expression
    private int expressionIndex = 0;

    //If, when closed, the next expression should be called instantly (mixed numbers/fractions)
    private boolean special = false;

    /**
     * Compares two BasicExpressions to figure out which goes first
     * @param first First expression
     * @param second Second expression
     * @return Comparison of the two priorities, negated for the proper order
     * @see BasicExpression#getPriority()
     * @see Integer#compare(int, int)
     */
    private static int compare(BasicExpression first, BasicExpression second) {
        return Integer.compare(first.getPriority(), second.getPriority())*-1;
    }

    /**
     * Removes the index from the values list, and subtracts one from the index of expressions using that value.
     * Called on the second/third param of expressions
     * @param index Index to remove
     */
    public void removeValue(int index) {
        numbers.remove(index);
        for (BasicExpression expression : expressions) {
            if (expression.getValueIndex() >= index) {
                expression.setValueIndex(expression.getValueIndex() - 1);
            }
        }
    }

    /**
     * Starts an expression
     * @param special If the operation should be called immediately
     * @param character Operation character
     * @param parenthesis Parenthesis depth
     */
    public void startExpression(boolean special, char character, int parenthesis) {
        hierarchy.add(current);
        switch (character) {
            case '+':
                current = new AdditionExpression(expressionIndex, parenthesis);
                current.setValueIndex(numbers.size()-1);
                return;
            case '-':
                current = new SubtractionExpression(expressionIndex, parenthesis);
                current.setValueIndex(numbers.size()-1);
                return;
            case '*':
                current = new MultiplicationExpression(expressionIndex, parenthesis);
                current.setValueIndex(numbers.size()-1);
                return;
            case '/':
                current = new DivisionExpression(expressionIndex, parenthesis);
                current.setValueIndex(numbers.size()-1);
                this.special = special;
                return;
            case '^':
                current = new ExponentExpression(expressionIndex, parenthesis);
                current.setValueIndex(numbers.size()-1);
                return;
            case '_':
                current = new MixedNumberExpression(expressionIndex, parenthesis);
                current.setValueIndex(numbers.size()-1);
                this.special = special;
                return;
            default:
                throw new IllegalStateException("Unknown expression: " + character);
        }
    }

    /**
     * Ends an expression, calling it immediately if it is special
     * Afterwards, sets the new current expression to the last one in the hierarchy (if applicable)
     */
    public void endExpression() {
        if(current == null) {
            return;
        }
        if(special) {
            numbers.set(current.getValueIndex(),
                    current.operate(BLANK_ANSWER_CONSUMER, numbers));
            for (int i = current.getUsedValues() - 1; i > 0; i--) {
                numbers.remove(numbers.size()-1);
            }
            for(BasicExpression after : expressions) {
                if(after.getIndex() > current.getIndex()) {
                    after.setIndex(after.getIndex() - 1);
                }
            }
            special = false;
            hierarchy.remove(current);
            current = (hierarchy.isEmpty()) ? null : hierarchy.get(hierarchy.size()-1);
            return;
        }
        expressionIndex++;
        expressions.add(current);
        hierarchy.remove(current);
        current = (hierarchy.isEmpty()) ? null : hierarchy.get(hierarchy.size()-1);
    }
}

/**
 * An AnswerConsumer that should not have any methods called, should
 * only be passed to non-drawn expressions
 * @see BasicExpression#shouldPrint()
 */
class BlankAnswerConsumer implements AnswerConsumer {
    @Override
    public void printText(String text) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void drawText(String text) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void drawText(String text, int xOffset, int yOffset) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void drawTextNoOffset(String text, int xOffset, int yOffset) {
        throw new UnsupportedOperationException();
    }

    @Override
    public double getTextSize() {
        throw new UnsupportedOperationException();
    }

    @Override
    public double getTextWidth(String text) {
        throw new UnsupportedOperationException();
    }

    @Override
    public double getXOffset() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void setXOffset(double x) {
        throw new UnsupportedOperationException();
    }

    @Override
    public double getYOffset() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void setYOffset(double y) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void setTextSize(double size) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void drawLine(double x1, double y1, double x2, double y2) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void printEquation(Set<BasicExpression> expressions, List<IValue> values) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void printAnswer(Set<BasicExpression> expressions, List<IValue> values) {
        throw new UnsupportedOperationException();
    }
}
