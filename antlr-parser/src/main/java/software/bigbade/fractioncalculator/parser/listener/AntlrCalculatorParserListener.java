package software.bigbade.fractioncalculator.parser.listener;

import lombok.Getter;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.TerminalNode;
import software.bigbade.fractioncalculator.generated.FractionParser;
import software.bigbade.fractioncalculator.generated.FractionParserBaseListener;
import software.bigbade.fractioncalculator.math.expressions.AdditionExpression;
import software.bigbade.fractioncalculator.math.expressions.DivisionExpression;
import software.bigbade.fractioncalculator.math.expressions.IExpression;
import software.bigbade.fractioncalculator.math.expressions.MultiplicationExpression;
import software.bigbade.fractioncalculator.math.expressions.SubtractionExpression;
import software.bigbade.fractioncalculator.math.values.FractionValue;
import software.bigbade.fractioncalculator.math.values.IValue;
import software.bigbade.fractioncalculator.math.values.MixedNumberValue;
import software.bigbade.fractioncalculator.math.values.NumberValue;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

public class AntlrCalculatorParserListener extends FractionParserBaseListener {
    @Getter
    private final List<IValue> values = new ArrayList<>();

    @Getter
    private final SortedSet<IExpression> expressions = new TreeSet<>((first, second) ->
            compare(second.getPriority(), first.getPriority()));

    private int parentheses = 0;
    private int index = 0;
    private int expressionIndex = 0;

    private IExpression current;

    @SuppressWarnings("UseCompareMethod")
    private static int compare(int first, int second) {
        if (first > second) {
            return 1;
        } else if (second > first) {
            return -1;
        } else {
            return 0;
        }
    }

    private static boolean isParenthesis(ParserRuleContext context) {
        while ((context = context.getParent()) != null) {
            if (context instanceof FractionParser.ParenthesisContext) {
                return true;
            }
        }
        return false;
    }

    public void setValue(IValue value, int index) {
        values.set(index, value);
    }

    public void removeValue(int index) {
        values.remove(index);
        for (IExpression expression : expressions) {
            if (expression.getValueIndex() >= index) {
                expression.setValueIndex(expression.getValueIndex() - 1);
            }
        }
    }

    @Override
    public void enterAddition(FractionParser.AdditionContext ctx) {
        if (current != null) {
            expressions.add(current);
        }

        current = new AdditionExpression(values, expressionIndex++, parentheses, isParenthesis(ctx));
    }

    @Override
    public void enterSubtraction(FractionParser.SubtractionContext ctx) {
        if (current != null) {
            expressions.add(current);
        }

        current = new SubtractionExpression(values, expressionIndex++, parentheses, isParenthesis(ctx));
    }

    @Override
    public void enterMultiplication(FractionParser.MultiplicationContext ctx) {
        if (current != null) {
            expressions.add(current);
        }

        current = new MultiplicationExpression(values, expressionIndex++, parentheses, isParenthesis(ctx));
    }

    @Override
    public void enterDivision(FractionParser.DivisionContext ctx) {
        if (current != null) {
            expressions.add(current);
        }

        current = new DivisionExpression(values, expressionIndex++, parentheses, isParenthesis(ctx));
    }

    @Override
    public void enterNumbValue(FractionParser.NumbValueContext ctx) {
        values.add(new NumberValue(new BigDecimal(ctx.getText())));
        updateValue();
    }

    @Override
    public void exitMixedNumber(FractionParser.MixedNumberContext ctx) {
        if (current != null) {
            expressions.add(current);
        }

        values.add(new MixedNumberValue(getNumberValue(ctx.NUMBER(), ctx.SUBTRACTION()),
                new FractionValue(values.get(index-1), values.get(index), false), false));

        updateValue();
    }

    private static NumberValue getNumberValue(TerminalNode number, TerminalNode subtraction) {
        BigDecimal value = new BigDecimal(number.getText());
        if(subtraction != null) {
            value = value.negate();
        }

        return new NumberValue(value);
    }

    private void updateValue() {
        if (current == null) {
            return;
        }
        System.out.println("Updated " + current + " with " + index);
        if (current.getValueIndex() == -1) {
            current.setValueIndex(index++);
        }
    }

    @Override
    public void exitOperation(FractionParser.OperationContext ctx) {
        if (current != null) {
            expressions.add(current);
        }
    }
}
