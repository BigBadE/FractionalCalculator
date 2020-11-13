package software.bigbade.fractioncalculator.math;

import software.bigbade.fractioncalculator.math.expressions.IExpression;
import software.bigbade.fractioncalculator.math.values.IValue;

import java.util.List;
import java.util.Set;

public interface AnswerConsumer {
    void printText(String text);

    void drawText(String text);

    void drawText(String text, int xOffset, int yOffset);

    void drawTextNoOffset(String text, int xOffset, int yOffset);

    double getTextSize();

    double getTextWidth(String text);

    double getXOffset();

    void setXOffset(double x);

    double getYOffset();

    void setYOffset(double y);

    void setTextSize(double size);

    void drawLine(double x1, double y1, double x2, double y2);

    void printEquation(Set<IExpression> expressions, List<IValue> values);
}
