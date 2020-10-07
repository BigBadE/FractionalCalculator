package software.bigbade.fractioncalculator.parser.values;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class FractionValue implements IValue {
    @Getter
    private final IValue numerator;
    @Getter
    private final IValue denominator;

    @Override
    public String getValue() {
        return numerator.getValue() + "/" + denominator.getValue();
    }

}
