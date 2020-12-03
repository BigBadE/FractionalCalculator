package software.bigbade.fractioncalculator.parser.listener;

import lombok.Getter;
import software.bigbade.fractioncalculator.math.exception.ParseException;
import software.bigbade.fractioncalculator.math.expressions.IExpression;
import software.bigbade.fractioncalculator.math.expressions.MixedNumberExpression;
import software.bigbade.fractioncalculator.math.values.IValue;
import software.bigbade.fractioncalculator.math.values.NumberValue;

import java.math.BigDecimal;
import java.util.List;
import java.util.SortedSet;

public class CalculatorParserListener {
    @Getter
    private final NumberHandler numberHandler = new NumberHandler();

    @Getter
    private final ExpressionHandler expressionHandler = new ExpressionHandler(numberHandler);

    public List<IValue> getValues() {
        return numberHandler.getValues();
    }

    public SortedSet<IExpression> getExpressions() {
        return expressionHandler.getExpressions();
    }

    public void parse(String line) {
        int parenthesis = 0;
        int expression = 1;
        boolean inParenthesis = false;
        boolean inNumber = true;
        StringBuilder current = new StringBuilder();
        for (char character : line.toCharArray()) {
            if (character >= '0' && character <= '9' || character == '-' && expression > 0) {
                if (!inNumber) {
                    inNumber = true;
                }
                current.append(character);
                continue;
            }
            if (inNumber) {
                expression--;
                inNumber = false;
                numberHandler.addValue(new NumberValue(new BigDecimal(current.toString())));
                current = new StringBuilder();
                updateExpression(expression);
            }
            int change = parseCharacter(inParenthesis, character, expression, parenthesis);
            expression = expressionHandler.getCurrent().getUsedValues()-1;
            parenthesis += change;
            inParenthesis = (change == 1);
        }
        expression--;
        numberHandler.addValue(new NumberValue(new BigDecimal(current.toString())));
        if (expression < 0) {
            throw new ParseException("Number outside of expression!");
        } else if (expression == 0) {
            expressionHandler.endExpression();
        }
    }

    private void updateExpression(int expression) {
        if (expression < 0) {
            throw new ParseException("Number outside of expression!");
        } else if (expression == 0) {
            expressionHandler.endExpression();
        }
    }

    private int parseCharacter(boolean inParenthesis, char character, int expression, int parenthesis) {
        switch (character) {
            case ' ':
                return 0;
            case '(':
                return 1;
            case ')':
                return -1;
            case '+':
            case '-':
            case '/':
            case '*':
            case '^':
            case '_':
                if (!(expressionHandler.getCurrent() instanceof MixedNumberExpression) || character != '/') {
                    if (expression > 0) {
                        throw new ParseException(expressionHandler.getCurrent().getClass().getSimpleName()
                                + " isn't finished!");
                    }
                    expressionHandler.startExpression(character, parenthesis, inParenthesis);
                    return 0;
                }
                return 0;
            default:
                throw new IllegalStateException("Unknown character: " + character);
        }
    }
}
