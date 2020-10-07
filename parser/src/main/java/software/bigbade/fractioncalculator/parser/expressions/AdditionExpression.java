package software.bigbade.fractioncalculator.parser.expressions;

import lombok.RequiredArgsConstructor;
import lombok.Setter;
import software.bigbade.fractioncalculator.parser.values.ExpressionValue;
import software.bigbade.fractioncalculator.parser.values.FractionValue;
import software.bigbade.fractioncalculator.parser.values.IValue;
import software.bigbade.fractioncalculator.parser.values.NumberValue;

import java.util.Optional;

@RequiredArgsConstructor
public final class AdditionExpression implements IExpression {
    private final IExpression parent;

    @Setter
    private IValue first;
    @Setter
    private IValue second;

    @Override
    public int getPriority() {
        return 0;
    }

    @Override
    public IValue operate() {
        return addNumbers(first, second);
    }

    public static IValue addNumbers(IValue first, IValue second) {
        while (first instanceof ExpressionValue) {
            first = ((ExpressionValue<?, ?>) first).calculate();
        }
        while (second instanceof ExpressionValue) {
            second = ((ExpressionValue<?, ?>) second).calculate();
        }
        if(first instanceof NumberValue) {
            NumberValue firstNumber = (NumberValue) first;
            if(second instanceof NumberValue) {
                return new NumberValue(firstNumber.getNumericValue().doubleValue()
                        + ((NumberValue) second).getNumericValue().doubleValue());
            }
            FractionValue secondFraction = (FractionValue) second;
            return new FractionValue(addNumbers(secondFraction.getNumerator(),
                    MultiplicationExpression.multiplyNumbers(firstNumber, secondFraction.getDenominator())),
                    secondFraction.getDenominator());
        } else if(first instanceof FractionValue) {
            FractionValue firstFraction = (FractionValue) first;
            if(second instanceof NumberValue) {
                return new FractionValue(addNumbers(firstFraction.getNumerator(),
                        MultiplicationExpression.multiplyNumbers(second, firstFraction.getDenominator())),
                        firstFraction.getDenominator());
            }
            FractionValue secondFraction = (FractionValue) second;
            return new FractionValue(addNumbers(
                    MultiplicationExpression.multiplyNumbers(firstFraction.getNumerator(), secondFraction.getDenominator()),
                    MultiplicationExpression.multiplyNumbers(secondFraction.getNumerator(), firstFraction.getDenominator())),
                    MultiplicationExpression.multiplyNumbers(firstFraction.getDenominator(), secondFraction.getDenominator()));
        }
        throw new IllegalStateException("How did ya get here?");
    }

    @Override
    public Optional<IExpression> getParent() {
        return Optional.ofNullable(parent);
    }
}
