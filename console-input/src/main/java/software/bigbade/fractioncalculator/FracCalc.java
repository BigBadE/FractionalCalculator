package software.bigbade.fractioncalculator;

import software.bigbade.fractioncalculator.parser.FractionCalculatorParser;

import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class FracCalc {
    public static void main(String[] args) {
        try(Scanner scanner = new Scanner(System.in, StandardCharsets.UTF_8.name())) {
            System.out.print("Enter your equation: ");
            System.out.println(produceAnswer(scanner.nextLine()));
        }
    }

    public static String produceAnswer(String equation) {
        FractionCalculatorParser parser = new FractionCalculatorParser();
        parser.parse(equation);
        TextAnswerConsumer consumer = new TextAnswerConsumer();
        parser.calculate(consumer);
        return consumer.getAnswer();
    }
}
