package software.bigbade.fractioncalculator.parser;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import software.bigbade.fractioncalculator.generated.FractionLexer;
import software.bigbade.fractioncalculator.generated.FractionParser;
import software.bigbade.fractioncalculator.math.AnswerConsumer;
import software.bigbade.fractioncalculator.math.expressions.IExpression;
import software.bigbade.fractioncalculator.math.simplification.ISimplifier;
import software.bigbade.fractioncalculator.math.simplification.SimplifyImproperFractions;
import software.bigbade.fractioncalculator.math.values.IValue;
import software.bigbade.fractioncalculator.parser.listener.CalculatorParserListener;

import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

@RequiredArgsConstructor
public class FractionCalculatorParser {
    private final CalculatorParserListener listener = new CalculatorParserListener();
    @SuppressWarnings("ArraysAsListWithZeroOrOneArgument")
    private final List<ISimplifier> simplifiers = Arrays.asList(new SimplifyImproperFractions());

    @Getter
    private List<IValue> values = Collections.emptyList();
    @Getter
    private Set<IExpression> expressions = Collections.emptySet();

    public void parse(String line) {
        FractionLexer lexer = new FractionLexer(CharStreams.fromString(line));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        FractionParser parser = new FractionParser(tokens);
        walkTree(parser.input().children);
        if (parser.input().children == null) {
            return;
        }


        expressions = listener.getExpressions();
        values = listener.getValues();
    }

    private void walkTree(List<ParseTree> trees) {
        if (trees.isEmpty()) {
            return;
        }
        try {
            for (ParseTree tree : trees) {
                ParseTreeWalker.DEFAULT.walk(listener, tree);
            }
        } catch (Exception e) {
            //Ignore errors so we can have live parsing
        }
    }

    public void calculate(AnswerConsumer consumer) {
        for (IExpression expression : listener.getExpressions()) {
            if (expression.getValueIndex() == -1) {
                consumer.printText("Invalid input");
                return;
            }
        }

        Iterator<IExpression> iterator = listener.getExpressions().iterator();
        while (iterator.hasNext()) {
            System.out.println("Expression");
            IExpression expression = iterator.next();
            listener.setValue(expression.operate(consumer), expression.getValueIndex());
            listener.removeValue(expression.getValueIndex() + 1);
            iterator.remove();
            consumer.printEquation(listener.getExpressions(), listener.getValues());
        }

        values = listener.getValues();

        boolean found = true;
        while (found) {
            found = false;
            for(ISimplifier simplifier : simplifiers) {
                for(int i = 0; i < values.size(); i++) {
                    IValue value = values.get(i);
                    if(simplifier.matches(value)) {
                        values.set(i, simplifier.simplify(value, consumer));
                        found = true;
                    }
                }
            }
        }

        expressions = listener.getExpressions();

        consumer.printText("Answer: ");
        consumer.printEquation(listener.getExpressions(), listener.getValues());
    }
}
