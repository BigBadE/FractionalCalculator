package software.bigbade.fractioncalculator.parser.expressions;

import javax.annotation.Nullable;
import java.util.function.Function;

public enum ExpressionType {
    ADDITION(AdditionExpression::new);

    private final Function<IExpression, IExpression> supplier;

    ExpressionType(Function<IExpression, IExpression> supplier) {
        this.supplier = supplier;
    }

    public IExpression getExpression(@Nullable IExpression parent) {
        return supplier.apply(parent);
    }
}
