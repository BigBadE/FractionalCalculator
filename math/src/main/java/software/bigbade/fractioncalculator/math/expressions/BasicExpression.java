package software.bigbade.fractioncalculator.math.expressions;

import lombok.Getter;
import lombok.Setter;
import software.bigbade.fractioncalculator.math.AnswerConsumer;
import software.bigbade.fractioncalculator.math.values.IValue;

import java.util.List;

public abstract class BasicExpression {
    //Priority of the expression, with higher priorities called first
    @Getter
    private final int priority;

    //Numerical index of the expression
    @Getter
    @Setter
    private int index;

    //Numerical index of the first value of the expression. May be -1 before set.
    @Getter
    @Setter
    private int valueIndex = -1;

    /**
     * Constructs a basic expression given the index and parenthesis depth
     * @param index Index of the expression
     * @param parenthesis Parenthesis depth
     */
    BasicExpression(int index, int parenthesis) {
        //Combines parenthesis depth, operation priority, and index into one number so only
        //one comparison is needed.
        this.priority = (parenthesis << 24) + (getOperationPriority() << 22) + (0x7FFFF - index);
        this.index = index;
    }

    /**
     * Operation priority of the expression
     * @return Follows PEMDAS, 3 for mixed numbers/fractions, 2 for exponents,
     *  1 for division/multiplication, 0 for addition/subtraction
     */
    abstract int getOperationPriority();

    /**
     * Number of values used by the operation.
     * @return only 3 for mixed numbers, 2 for everything else
     */
    public int getUsedValues() {
        return 2;
    }

    /**
     * Calls the given operation
     * @param consumer Answer printer
     * @param values List of values
     * @return Output of the operation
     */
    public abstract IValue operate(AnswerConsumer consumer, List<IValue> values);

    /**
     * Converts the value to a printable string
     * @param expressions List of expressions (to determine where parenthesis should be used)
     * @param values Values
     * @return String version of the expression
     */
    public String toString(List<BasicExpression> expressions, List<IValue> values) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < getParenthesisChange(expressions, false); i++) {
            builder.append('(');
        }
        int offset = 0;
        for(char character : getCharacters()) {
            builder.append(values.get(valueIndex+(offset++)).toString()).append(character);
        }
        if (values.size() == valueIndex + offset + 1) {
            builder.append(values.get(valueIndex + offset).toString());
        }
        for (int i = 0; i < getParenthesisChange(expressions, true); i++) {
            builder.append(')');
        }
        return builder.toString();
    }

    /**
     * Gets the change in parenthesis between the current expression and the one before/after
     * @param expressions List of expressions
     * @param forward If the method should look forwards/backwards
     * @return Parenthesis difference between the two expressions
     */
    public int getParenthesisChange(List<BasicExpression> expressions, boolean forward) {
        if ((forward && (expressions.size() - 1) <= index) || (!forward && index == 0)) {
            return getParenthesis(priority);
        } else {
            int other = (forward) ? expressions.get(index + 1).getPriority() : expressions.get(index - 1).getPriority();
            return getParenthesis(priority) - getParenthesis(other);
        }
    }

    /**
     * If the expression should be printed
     * @return true for everything but division and mixed numbers
     */
    public boolean shouldPrint() {
        return true;
    }

    /**
     * Returns the parenthesis depth of the expression
     * @param priority Priority number
     * @return Parenthesis depth, 0 > x > 0xFF
     */
    public static int getParenthesis(int priority) {
        return priority >>> 24;
    }

    /**
     * List of separators between the operations
     * @return seperators, length of 1 for everything but mixed numbers
     */
    abstract char[] getCharacters();

    /**
     * Hash code
     * @return priority, because it must be unique for each expression
     */
    @Override
    public int hashCode() {
        return priority;
    }

    /**
     * Equals of two expressions
     * @param obj Other expression
     * @return if the priorities are equal
     */
    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof BasicExpression)) {
            return false;
        }
        return ((BasicExpression) obj).priority == priority;
    }
}
