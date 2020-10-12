package software.bigbade.fractioncalculator.parser;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.slf4j.Logger;
import software.bigbade.fractioncalculator.generated.FractionLexer;
import software.bigbade.fractioncalculator.generated.FractionParser;
import software.bigbade.fractioncalculator.math.ExpressionLogger;
import software.bigbade.fractioncalculator.math.expressions.IExpression;
import software.bigbade.fractioncalculator.parser.listener.CalculatorParserListener;

import java.util.Iterator;
import java.util.Map;

@RequiredArgsConstructor
public class FractionCalculatorParser {
    private final Logger logger;

    @SneakyThrows
    public String calculate(String line) {
        FractionLexer lexer = new FractionLexer(CharStreams.fromString(line));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        FractionParser parser = new FractionParser(tokens);
        CalculatorParserListener listener = new CalculatorParserListener();
        for(ParseTree tree : parser.input().children) {
            ParseTreeWalker.DEFAULT.walk(listener, tree);
        }

        Iterator<IExpression> iterator = listener.getExpressions().iterator();
        while (iterator.hasNext()) {
            IExpression expression = iterator.next();
            listener.setValue(expression.operate(), expression.getFirst());
            listener.removeValue(expression.getSecond());
            if(expression.isFinished()) {
                iterator.remove();
            }
            ExpressionLogger.getLogger().printCurrentEquation(listener.getExpressions(), listener.getValues());
        }

        for(Map.Entry<String, Exception> error : ExpressionLogger.getLogger().getExceptions().entrySet()) {
            logger.error(error.getKey(), error.getValue());
        }

        ExpressionLogger.getLogger().addLine("Answer: ");
        ExpressionLogger.getLogger().printCurrentEquation(listener.getExpressions(), listener.getValues());

        String output = ExpressionLogger.getLogger().getOutput();
        ExpressionLogger.getLogger().clear();
        return output.substring(0, output.length()-1);
    }
}
