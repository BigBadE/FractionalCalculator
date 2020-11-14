package software.bigbade.fractioncalculator.parser.listener;

import lombok.Getter;
import org.antlr.v4.runtime.ParserRuleContext;
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
import java.util.Set;
import java.util.TreeSet;

public class CalculatorParserListener extends FractionParserBaseListener {
    @Getter
    private final List<IValue> values = new ArrayList<>();

    @Getter
    private final Set<IExpression> expressions = new TreeSet<>((first, second) -> {
        if (first.getPriority() == second.getPriority()) {
            return compare(first.getIndex(), second.getIndex());
        }
        return compare(second.getPriority(), first.getPriority());
    });

    private int index = 0;
    private int expressionIndex = 0;

    private IExpression current;

    @SuppressWarnings("UseCompareMethod")
    private static int compare(int first, int second) {
        if(first > second) {
            return 1;
        } else if(second > first) {
            return -1;
        } else {
            return 0;
        }
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
        if(current != null) {
            expressions.add(current);
        }

        current = new AdditionExpression(values, expressionIndex++, isParenthesis(ctx));
    }

    @Override
    public void enterSubtraction(FractionParser.SubtractionContext ctx) {
        if(current != null) {
            expressions.add(current);
        }

        current = new SubtractionExpression(values, expressionIndex++, isParenthesis(ctx));
    }

    @Override
    public void enterMultiplication(FractionParser.MultiplicationContext ctx) {
        if(current != null) {
            expressions.add(current);
        }

        current = new MultiplicationExpression(values, expressionIndex++, isParenthesis(ctx));
    }

    @Override
    public void enterDivision(FractionParser.DivisionContext ctx) {
        if(current != null) {
            expressions.add(current);
        }

        current = new DivisionExpression(values, expressionIndex++, isParenthesis(ctx));
    }

    private static boolean isParenthesis(ParserRuleContext context) {
        while ((context = context.getParent()) != null) {
            if (context instanceof FractionParser.ParenthesisContext) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void exitValue(FractionParser.ValueContext ctx) {
        System.out.println("TEST: " + ctx.getText());
        if (ctx.NUMBER() != null) {
            values.add(new NumberValue(new BigDecimal(ctx.getText())));
        }
        if (ctx.mixedNumber() != null) {
            BigDecimal number = new BigDecimal(ctx.mixedNumber().getText());
            values.add(new MixedNumberValue(new NumberValue(number),
                    (FractionValue) values.get(values.size()-1)));
            values.remove(values.size()-1);
        }
        if(current == null) {
            return;
        }
        ParserRuleContext context = ctx;
        while (!(context.getParent().getParent() instanceof FractionParser.OperationContext)) {
            context = context.getParent();
        }
        if(current.getValueIndex() == -1) {
            current.setValueIndex(index++);
        }
    }

    @Override
    public void exitOperation(FractionParser.OperationContext ctx) {
        if(current != null) {
            expressions.add(current);
        }
    }
}
