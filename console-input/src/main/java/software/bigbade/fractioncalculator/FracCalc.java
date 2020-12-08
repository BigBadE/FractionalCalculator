package software.bigbade.fractioncalculator;

import software.bigbade.fractioncalculator.parser.FractionCalculator;

import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class FracCalc {
    /**
     * Entry point of the program
     * @param args Not used
     */
    public static void main(String[] args) {
        try(Scanner scanner = new Scanner(System.in, StandardCharsets.UTF_8.name())) {
            System.out.print("Enter your equation: ");
            System.out.println(produceAnswer(scanner.nextLine()));
        }
    }

    /**
     * Produces an answer to a equation
     * @param equation Equation to answer
     * @return Answer
     */
    public static String produceAnswer(String equation) {
        FractionCalculator parser = new FractionCalculator();
        parser.parse(equation);
        TextAnswerConsumer consumer = new TextAnswerConsumer();
        parser.calculate(consumer);
        return consumer.getAnswer();
    }
}
