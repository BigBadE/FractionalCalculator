package software.bigbade.fractioncalculator.parser.listener;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import software.bigbade.fractioncalculator.math.expressions.AdditionExpression;
import software.bigbade.fractioncalculator.math.expressions.DivisionExpression;
import software.bigbade.fractioncalculator.math.expressions.ExponentExpression;
import software.bigbade.fractioncalculator.math.expressions.IExpression;
import software.bigbade.fractioncalculator.math.expressions.MixedNumberExpression;
import software.bigbade.fractioncalculator.math.expressions.MultiplicationExpression;
import software.bigbade.fractioncalculator.math.expressions.SubtractionExpression;

import java.util.ArrayList;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

@RequiredArgsConstructor
public class ExpressionHandler {
    @Getter
    private final SortedSet<IExpression> expressions = new TreeSet<>(ExpressionHandler::compare);

    private final NumberHandler numberHandler;

    private final List<IExpression> hierarchy = new ArrayList<>();
    @Getter
    private IExpression current;

    private int expressionIndex = 0;

    private static int compare(IExpression first, IExpression second) {
        return Integer.compare(first.getPriority(), second.getPriority())*-1;
    }

    public void removeValue(int index) {
        numberHandler.getValues().remove(index);
        for (IExpression expression : expressions) {
            if (expression.getValueIndex() >= index) {
                expression.setValueIndex(expression.getValueIndex() - 1);
            }
        }
    }

    /**
     * Starts an expression
     * @param character Operation character
     * @param parenthesis Parenthesis depth
     * @param inParenthesis If the expression is in parenthesis
     */
    public void startExpression(char character, int parenthesis, boolean inParenthesis) {
        hierarchy.add(current);
        switch (character) {
            case '+':
                current = new AdditionExpression(numberHandler.getValues(), expressionIndex,
                        parenthesis, inParenthesis);
                current.setValueIndex(numberHandler.getIndex()-1);
                return;
            case '-':
                current = new SubtractionExpression(numberHandler.getValues(), expressionIndex,
                        parenthesis, inParenthesis);
                current.setValueIndex(numberHandler.getIndex()-1);
                return;
            case '*':
                current = new MultiplicationExpression(numberHandler.getValues(), expressionIndex,
                        parenthesis, inParenthesis);
                current.setValueIndex(numberHandler.getIndex()-1);
                return;
            case '/':
                current = new DivisionExpression(numberHandler.getValues(), expressionIndex,
                        parenthesis, inParenthesis);
                current.setValueIndex(numberHandler.getIndex()-1);
                return;
            case '^':
                current = new ExponentExpression(numberHandler.getValues(), expressionIndex,
                        parenthesis, inParenthesis);
                current.setValueIndex(numberHandler.getIndex()-1);
                return;
            case '_':
                current = new MixedNumberExpression(numberHandler.getValues(), expressionIndex,
                        parenthesis, inParenthesis);
                current.setValueIndex(numberHandler.getIndex()-1);
                return;
            default:
                throw new IllegalStateException("Unknown expression: " + character);
        }
    }

    public void endExpression() {
        if(current == null) {
            return;
        }
        expressionIndex++;
        expressions.add(current);
        hierarchy.remove(current);
        current = hierarchy.get(hierarchy.size()-1);
    }
}
