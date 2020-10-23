package software.bigbade.fractioncalculator.parser;

import lombok.Getter;
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
import software.bigbade.fractioncalculator.math.values.IValue;
import software.bigbade.fractioncalculator.parser.listener.CalculatorParserListener;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

@RequiredArgsConstructor
public class FractionCalculatorParser {
    private final Logger logger;

    @Getter
    private List<IValue> values;
    @Getter
    private Set<IExpression> expressions;

    private final CalculatorParserListener listener = new CalculatorParserListener();

    public void parse(String line) {
        FractionLexer lexer = new FractionLexer(CharStreams.fromString(line));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        FractionParser parser = new FractionParser(tokens);
        for(ParseTree tree : parser.input().children) {
            ParseTreeWalker.DEFAULT.walk(listener, tree);
        }

        for(Map.Entry<String, Exception> error : ExpressionLogger.getLogger().getExceptions().entrySet()) {
            logger.error(error.getKey(), error.getValue());
        }
    }

    public String calculate() {
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

        expressions = listener.getExpressions();
        values = listener.getValues();


        ExpressionLogger.getLogger().addLine("Answer: ");
        ExpressionLogger.getLogger().printCurrentEquation(listener.getExpressions(), listener.getValues());

        String output = ExpressionLogger.getLogger().getOutput();
        ExpressionLogger.getLogger().clear();
        return output.substring(0, output.length()-1);
    }
}
