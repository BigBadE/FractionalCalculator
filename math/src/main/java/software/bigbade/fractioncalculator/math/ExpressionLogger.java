package software.bigbade.fractioncalculator.math;

import lombok.Getter;
import software.bigbade.fractioncalculator.math.expressions.IExpression;
import software.bigbade.fractioncalculator.math.values.IValue;

import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ExpressionLogger {
    private static final ExpressionLogger logger = new ExpressionLogger();

    @Getter
    private final Map<String, Exception> exceptions = new LinkedHashMap<>();

    private StringBuilder output = new StringBuilder();

    public static ExpressionLogger getLogger() {
        return logger;
    }

    public void logException(String message, Exception exception) {
        exceptions.put(message, exception);
    }

    public void addLine(String line) {
        output.append(line).append('\n');
    }

    public void printCurrentEquation(Set<IExpression> expressions, List<IValue> values) {
        if(values.size() == 1) {
            output.append(values.get(0).getValue(values));
        } else {
            Set<Integer> printed = new HashSet<>();
            for(IExpression expression : expressions) {
                String outputString = expression.toString(values);
                if(printed.contains(expression.getFirst())) {
                    outputString = outputString.substring(outputString.indexOf(' '));
                } else {
                    printed.add(expression.getFirst());
                }
                if(printed.contains(expression.getSecond())) {
                    outputString = outputString.substring(0, outputString.lastIndexOf(' '));
                } else {
                    printed.add(expression.getSecond());
                }
                output.append(outputString);
            }
        }
        output.append('\n');
    }

    public String getOutput() {
        return output.toString();
    }

    public void clear() {
        output = new StringBuilder();
    }
}
