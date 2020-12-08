package software.bigbade.fractioncalculator;

import lombok.Getter;
import software.bigbade.fractioncalculator.math.AnswerConsumer;
import software.bigbade.fractioncalculator.math.expressions.BasicExpression;
import software.bigbade.fractioncalculator.math.values.IValue;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Set;

public class TextAnswerConsumer implements AnswerConsumer {
    @Getter
    private String answer;

    @Override
    public void printText(String text) {
        System.out.println(text);
    }

    @Override
    public void printEquation(Set<BasicExpression> expressions, List<IValue> values) {
        System.out.println(getEquationAsText(expressions, values));
    }

    @Override
    public void printAnswer(Set<BasicExpression> expressions, List<IValue> values) {
        answer = getEquationAsText(expressions, values);
        System.out.println(answer);
    }

    private static String getEquationAsText(Set<BasicExpression> expressions, List<IValue> values) {
        List<BasicExpression> sortedExpressions = new ArrayList<>(expressions);
        sortedExpressions.sort(Comparator.comparingInt(BasicExpression::getIndex));
        if(values.size() == 1) {
            return values.get(0).toString();
        }

        //Adding strings compile to a StringBuilder anyways.
        StringBuilder builder = new StringBuilder();

        for(BasicExpression expression : sortedExpressions) {
            builder.append(expression.toString(sortedExpressions, values));
        }
        return builder.toString();
    }
}
