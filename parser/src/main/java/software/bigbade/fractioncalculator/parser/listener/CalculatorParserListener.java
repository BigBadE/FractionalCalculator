package software.bigbade.fractioncalculator.parser.listener;

import lombok.Getter;
import org.antlr.v4.runtime.ParserRuleContext;
import software.bigbade.fractioncalculator.generated.FractionParser;
import software.bigbade.fractioncalculator.generated.FractionParserBaseListener;
import software.bigbade.fractioncalculator.math.expressions.AdditionExpression;
import software.bigbade.fractioncalculator.math.expressions.IExpression;
import software.bigbade.fractioncalculator.math.expressions.MultiplicationExpression;
import software.bigbade.fractioncalculator.math.values.IValue;
import software.bigbade.fractioncalculator.math.values.NumberValue;

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
            return Integer.compare(first.getIndex(), second.getIndex());
        }
        return Integer.compare(second.getPriority(), first.getPriority());
    });

    private int index = 0;
    private int expressionIndex = 0;

    private IExpression current;

    public void setValue(IValue value, int index) {
        values.set(index, value);
    }

    public void removeValue(int index) {
        values.remove(index);
        for (IExpression expression : expressions) {
            if (expression.getFirst() >= index) {
                expression.setFirst(expression.getFirst() - 1);
            }
            if (expression.getSecond() >= index) {
                expression.setSecond(expression.getSecond() - 1);
            }
        }
    }

    @Override
    public void enterAddition(FractionParser.AdditionContext ctx) {
        current = new AdditionExpression(values, expressionIndex++, isParenthesis(ctx));
        super.enterAddition(ctx);
    }

    @Override
    public void enterMultiplication(FractionParser.MultiplicationContext ctx) {
        current = new MultiplicationExpression(values, expressionIndex++, isParenthesis(ctx));
        super.enterMultiplication(ctx);
    }

    private boolean isParenthesis(ParserRuleContext context) {
        while ((context = context.getParent()) != null) {
            if (context instanceof FractionParser.ParenthesisContext) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void exitValue(FractionParser.ValueContext ctx) {
        if (ctx.NUMBER() != null) {
            values.add(new NumberValue(Double.parseDouble(ctx.getText())));
        }
        ParserRuleContext context = ctx;
        while (!(context.getParent().getParent() instanceof FractionParser.OperationContext)) {
            context = context.getParent();
        }
        if(context.getParent().children.get(0).equals(context)) {
            current.setFirst(index++);
        } else {
            current.setSecond(index++);
        }
        super.exitValue(ctx);
    }

    @Override
    public void enterOperation(FractionParser.OperationContext ctx) {
        if(current != null) {
            if (current.getSecond() == 0) {
                current.setSecond(index);
            }
            expressions.add(current);
            super.exitOperation(ctx);
        }
    }

    @Override
    public void exitOperation(FractionParser.OperationContext ctx) {
        if(current.getSecond() == 0) {
            current.setSecond(index);
        }
        expressions.add(current);
        super.exitOperation(ctx);
    }
}
