package software.bigbade.fractioncalculator.parser.expressions;

import lombok.RequiredArgsConstructor;
import lombok.Setter;
import software.bigbade.fractioncalculator.parser.values.ExpressionValue;
import software.bigbade.fractioncalculator.parser.values.FractionValue;
import software.bigbade.fractioncalculator.parser.values.IValue;
import software.bigbade.fractioncalculator.parser.values.NumberValue;

import java.util.Optional;

@RequiredArgsConstructor
public class MultiplicationExpression  implements IExpression {
    private final IExpression parent;

    @Setter
    private IValue first;
    @Setter
    private IValue second;

    @Override
    public int getPriority() {
        return 1;
    }

    @Override
    public IValue operate() {
        return multiplyNumbers(first, second);
    }

    public static IValue multiplyNumbers(IValue first, IValue second) {
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
                        * ((NumberValue) second).getNumericValue().doubleValue());
            }
            FractionValue secondFraction = (FractionValue) second;
            return new FractionValue(multiplyNumbers(secondFraction.getNumerator(), firstNumber),
                    secondFraction.getDenominator());
        } else if(first instanceof FractionValue) {
            FractionValue firstFraction = (FractionValue) first;
            if(second instanceof NumberValue) {
                return new FractionValue(multiplyNumbers(firstFraction.getNumerator(), second),
                        firstFraction.getDenominator());
            }
            FractionValue secondFraction = (FractionValue) second;
            return new FractionValue(multiplyNumbers(firstFraction.getNumerator(), secondFraction.getNumerator()),
                    multiplyNumbers(firstFraction.getDenominator(), secondFraction.getDenominator()));
        }
        throw new IllegalStateException("How did ya get here?");
    }

    @Override
    public Optional<IExpression> getParent() {
        return Optional.ofNullable(parent);
    }
}
