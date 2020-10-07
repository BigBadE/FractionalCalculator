package software.bigbade.fractioncalculator.parser.listener;

import software.bigbade.fractioncalculator.generated.FractionParser;
import software.bigbade.fractioncalculator.generated.FractionParserBaseListener;
import software.bigbade.fractioncalculator.parser.expressions.AdditionExpression;
import software.bigbade.fractioncalculator.parser.expressions.IExpression;
import software.bigbade.fractioncalculator.parser.expressions.MultiplicationExpression;

public class CalculatorParserListener extends FractionParserBaseListener {
    private IExpression current;

    @Override
    public void enterAddition(FractionParser.AdditionContext ctx) {
        current = new AdditionExpression(current);
        super.enterAddition(ctx);
    }

    @Override
    public void enterMultiplication(FractionParser.MultiplicationContext ctx) {
        current = new MultiplicationExpression(current);
        super.enterMultiplication(ctx);
    }

    @Override
    public void exitValue(FractionParser.ValueContext ctx) {

        super.exitValue(ctx);
    }

    @Override
    public void exitOperation(FractionParser.OperationContext ctx) {
        super.exitOperation(ctx);
    }
}
