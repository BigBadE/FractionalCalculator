package software.bigbade.fractioncalculator;

import lombok.Getter;
import software.bigbade.fractioncalculator.math.AnswerConsumer;
import software.bigbade.fractioncalculator.math.expressions.IExpression;
import software.bigbade.fractioncalculator.math.values.IValue;

import java.util.List;
import java.util.Set;

public class TextAnswerConsumer implements AnswerConsumer {
    @Getter
    private String answer;

    @Override
    public void printText(String text) {
        System.out.println(text);
    }

    @Override
    public void drawText(String text) {
        System.out.println(text);
    }

    @Override
    public void drawText(String text, int xOffset, int yOffset) {
        //Not used because of printEquation
    }

    @Override
    public void drawTextNoOffset(String text, int xOffset, int yOffset) {
        //Not used because of printEquation
    }

    @Override
    public double getTextSize() {
        //Not used because of printEquation
        return 0;
    }

    @Override
    public double getTextWidth(String text) {
        //Not used because of printEquation
        return 0;
    }

    @Override
    public double getXOffset() {
        //Not used because of printEquation
        return 0;
    }

    @Override
    public void setXOffset(double x) {
        //Not used because of printEquation
    }

    @Override
    public double getYOffset() {
        //Not used because of printEquation
        return 0;
    }

    @Override
    public void setYOffset(double y) {
        //Not used because of printEquation
    }

    @Override
    public void setTextSize(double size) {
        //Not used because of printEquation
    }

    @Override
    public void drawLine(double x1, double y1, double x2, double y2) {
        //Not used because of printEquation
    }

    @Override
    public void printEquation(Set<IExpression> expressions, List<IValue> values) {
        if(values.size() == 1) {
            System.out.println(values.get(0).getValue());
            return;
        }
        //Adding strings compile to a StringBuilder anyways.
        StringBuilder builder = new StringBuilder();
        int lastValue = -1;

        for(IExpression expression : expressions) {
            String adding = expression.toString(values);
            if(lastValue == expression.getValueIndex()) {
                adding = adding.substring(values.get(expression.getValueIndex()+1).getValue().length());
            }
            builder.append(adding);
            lastValue = expression.getValueIndex()+1;
        }
        answer = builder.toString();
        System.out.println(answer);
    }
}
