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

    int getXOffset();

    void setXOffset(int x);

    int getYOffset();

    void setYOffset(int y);

    void setTextSize(double size);

    void drawLine(int x1, int y1, int x2, int y2);

    void printEquation(Set<IExpression> expressions, List<IValue> values);
}
