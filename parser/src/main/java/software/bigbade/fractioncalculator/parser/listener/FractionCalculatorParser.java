package software.bigbade.fractioncalculator.parser.listener;

import lombok.Getter;
import software.bigbade.fractioncalculator.math.exception.ParseException;
import software.bigbade.fractioncalculator.math.expressions.BasicExpression;
import software.bigbade.fractioncalculator.math.expressions.MixedNumberExpression;
import software.bigbade.fractioncalculator.math.values.IValue;
import software.bigbade.fractioncalculator.math.values.NumberValue;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class FractionCalculatorParser {
    //All IValues in the equation
    @Getter
    private final List<IValue> values = new ArrayList<>();

    //Handles expressions and any changes to value indexes
    @Getter
    private final ExpressionHandler expressionHandler = new ExpressionHandler(values);

    //Set of expressions, sorted in the order of PEMDAS
    public Set<BasicExpression> getExpressions() {
        return expressionHandler.getExpressions();
    }

    /**
     * Parses an equation into a list of values and a set of expressions
     * @param line Equation to parse
     */
    public void parse(String line) {
        int parenthesis = 0;
        int expression = 1;
        boolean inNumber = true;
        StringBuilder current = new StringBuilder();
        for (int i = 0; i < line.length(); i++) {
            char character = line.charAt(i);
            if(checkNumber(current, character, expression)) {
                inNumber = true;
                continue;
            }
            if (inNumber) {
                expression--;
                inNumber = false;
                values.add(new NumberValue(new BigDecimal(current.toString())));
                current = new StringBuilder();
                updateExpression(expression);
            }
            int change = parseCharacter(((i == 0) ? line.charAt(i+1) : line.charAt(i-1)) != ' ',
                    character, expression, parenthesis);
            if(expressionHandler.getCurrent() != null && expression == 0) {
                expression = expressionHandler.getCurrent().getUsedValues() - 1;
            }
            parenthesis += change;
        }
        expression--;
        if(current.length() != 0) {
            values.add(new NumberValue(new BigDecimal(current.toString())));
            if (expression < 0) {
                throw new ParseException("Number outside of expression!");
            } else if (expression == 0) {
                expressionHandler.endExpression();
            }
        }
        while (expressionHandler.getCurrent() != null) {
            expressionHandler.endExpression();
        }
    }

    /**
     * Checks if a character is a valid number, if so adds it to the string builder
     * @param current StringBuilder that recieves the number
     * @param character Character to check
     * @param expression Remaining numbers in the expression
     * @return If the character was added
     */
    private static boolean checkNumber(StringBuilder current, char character, int expression) {
        if (character >= '0' && character <= '9' || character == '-' && expression > 0) {
            current.append(character);
            return true;
        }
        return false;
    }

    /**
     * Safely finishes the current expression
     * @param expression Remaining numbers in the expression
     */
    private void updateExpression(int expression) {
        if (expression < 0) {
            throw new ParseException("Number outside of expression!");
        } else if (expression == 0) {
            expressionHandler.endExpression();
        }
    }

    /**
     * Parses a character and calls the appropriate method with it
     * @param special If the character isn't surrounded by spaces, means division/mixed numbers are called instantly
     * @param character Character to parse
     * @param expression Remaining numbers in the expression
     * @param parenthesis Parenthesis depth
     * @return Change in parenthesis depth caused by the character
     */
    private int parseCharacter(boolean special, char character, int expression, int parenthesis) {
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
                    expressionHandler.startExpression(special, character, parenthesis);
                    return 0;
                }
                return 0;
            default:
                throw new IllegalStateException("Unknown character: " + character);
        }
    }
}
