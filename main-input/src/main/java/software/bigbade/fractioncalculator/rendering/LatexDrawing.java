package software.bigbade.fractioncalculator.rendering;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import lombok.Getter;
import lombok.Setter;
import software.bigbade.fractioncalculator.math.AnswerConsumer;
import software.bigbade.fractioncalculator.math.expressions.IExpression;
import software.bigbade.fractioncalculator.math.graphics.IText;
import software.bigbade.fractioncalculator.math.values.FractionValue;
import software.bigbade.fractioncalculator.math.values.IValue;
import software.bigbade.fractioncalculator.rendering.equation.BasicText;
import software.bigbade.fractioncalculator.rendering.equation.FractionText;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Setter
public class LatexDrawing implements AnswerConsumer {
    private static final String FONT = "Droid Sans";

    private final GraphicsContext context;

    private double width;
    private double height;

    @Getter
    private int yOffset;
    @Getter
    private int xOffset;

    public LatexDrawing(GraphicsContext context, double width, double height) {
        context.setFont(Font.font(FONT, 15));

        this.context = context;
        this.width = width;
        this.height = height;

        clear();
    }

    public void clear() {
        context.setFill(Color.WHITE);
        context.fillRect(0, 0, width, height);
        yOffset = 14;
        xOffset = 4;
    }

    @Override
    public void printText(String text) {
        xOffset = 4;
        context.setFill(Color.BLACK);
        drawText(text);
        yOffset += getTextSize();
    }

    @Override
    public void drawText(String text) {
        context.setFill(Color.BLACK);
        context.fillText(text, xOffset, yOffset);
        xOffset += getTextWidth(text);
    }

    @Override
    public void drawText(String text, int xOffset, int yOffset) {
        context.setFill(Color.BLACK);
        context.fillText(text, xOffset, yOffset);
        this.xOffset += getTextWidth(text);
    }

    @Override
    public void drawTextNoOffset(String text, int xOffset, int yOffset) {
        context.setFill(Color.BLACK);
        context.fillText(text, xOffset, yOffset);
    }

    @Override
    public double getTextSize() {
        return context.getFont().getSize();
    }

    @Override
    public double getTextWidth(String text) {
        Text tempTest = new Text(text);
        tempTest.setFont(context.getFont());
        return tempTest.getLayoutBounds().getWidth();
    }

    @Override
    public void setTextSize(double size) {
        context.setFont(Font.font(FONT, size));
    }

    @Override
    public void drawLine(int x1, int y1, int x2, int y2) {
        context.setStroke(Color.BLACK);
        context.strokeLine(x1, y1, x2, y2);
    }

    @Override
    public void printEquation(Set<IExpression> expressions, List<IValue> values) {
        xOffset = 4;
        if(values.isEmpty()) {
            return;
        }

        if (values.size() == 1 && expressions.isEmpty()) {
            printText(values.get(0).getValue(values));
        } else {
            List<IText> text = new ArrayList<>();
            for (IValue value : values) {
                text.add(parseValue(values, value));
            }
            for (IExpression expression : expressions) {
                if(expression.getPriority() == 3) {
                    drawText("(");
                }
                expression.draw(text, this);
                if(expression.getPriority() == 3) {
                    drawText(")");
                }
            }
        }
        yOffset += context.getFont().getSize() * 2;
    }

    public IText parseValue(List<IValue> values, IValue value) {
        if (value instanceof FractionValue) {
            FractionValue fractionValue = (FractionValue) value;
            return new FractionText(parseValue(values, fractionValue.getNumerator()),
                    parseValue(values, fractionValue.getDenominator()));
        } else {
            return value == null ? null : new BasicText(value.getValue(values));
        }
    }
}
