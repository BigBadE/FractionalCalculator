package software.bigbade.fractioncalculator.parser;

import lombok.SneakyThrows;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import software.bigbade.fractioncalculator.generated.FractionLexer;
import software.bigbade.fractioncalculator.generated.FractionParser;
import software.bigbade.fractioncalculator.parser.listener.CalculatorParserListener;

public class FractionCalculatorParser {
    @SneakyThrows
    public String calculate() {
        FractionLexer lexer = new FractionLexer(CharStreams.fromString("1+2(1/2)^5"));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        FractionParser parser = new FractionParser(tokens);
        CalculatorParserListener listener = new CalculatorParserListener();
        for(ParseTree tree : parser.equation().children) {
            ParseTreeWalker.DEFAULT.walk(listener, tree);
        }
        return "";
    }
}
