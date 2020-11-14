package software.bigbade.fractioncalculator.math.values;

import java.math.BigDecimal;

public class MixedNumberValue implements IValue {
    private NumberValue number;
    private FractionValue fraction;

    public MixedNumberValue(NumberValue number, FractionValue fraction) {
        this.number = number;
        this.fraction = fraction;
    }

    @Override
    public BigDecimal getDecimalValue() {
        return number.getDecimalValue().add(fraction.getDecimalValue());
    }

    @Override
    public String getValue() {
        return number.getValue() + "_" + fraction.getValue();
    }

    @Override
    public IValue add(IValue other) {
        if(other instanceof NumberValue) {
            number = (NumberValue) number.add(other);
        } else if(other instanceof FractionValue) {
            fraction = (FractionValue) fraction.add(other);
        } else if(other instanceof MixedNumberValue) {
            number = (NumberValue) number.add(other);
            fraction = (FractionValue) fraction.add(other);
        } else {
            throw new IllegalArgumentException(NumberValue.UNIMPLEMENTED_OPERATION);
        }

        simplify();
        return this;
    }

    @Override
    public IValue subtract(IValue other) {
        if(other instanceof NumberValue) {
            number = (NumberValue) number.subtract(other);
        } else if(other instanceof FractionValue) {
            fraction = (FractionValue) fraction.subtract(other);
        } else if(other instanceof MixedNumberValue) {
            number = (NumberValue) number.subtract(other);
            fraction = (FractionValue) fraction.subtract(other);
        } else {
            throw new IllegalArgumentException(NumberValue.UNIMPLEMENTED_OPERATION);
        }
        simplify();
        return this;
    }

    @Override
    public IValue multiply(IValue other) {
        if(other instanceof NumberValue) {
            fraction.getNumerator().add(fraction.getDenominator().multiply(number).multiply(other));
        } else if(other instanceof FractionValue) {
            fraction = (FractionValue) fraction.multiply(other);
        } else if(other instanceof MixedNumberValue) {
            fraction = new FractionValue(fraction.getNumerator().add(fraction.getDenominator().multiply(number).multiply(other)),
                    fraction.getDenominator(), false);
        } else {
            throw new IllegalArgumentException(NumberValue.UNIMPLEMENTED_OPERATION);
        }
        simplify();
        return this;
    }

    @Override
    public IValue divide(IValue other) {
        return null;
    }

    private void simplify() {

    }
}
