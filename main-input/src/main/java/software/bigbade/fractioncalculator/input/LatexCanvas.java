package software.bigbade.fractioncalculator.input;

import javafx.animation.AnimationTimer;
import javafx.geometry.Insets;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import lombok.Getter;
import software.bigbade.fractioncalculator.FractionCalculator;
import software.bigbade.fractioncalculator.parser.FractionCalculatorParser;
import software.bigbade.fractioncalculator.rendering.LatexDrawing;

/**
 * Takes in text and renders it in LaTeX.
 */
public class LatexCanvas extends Canvas {
    private final ScrollPane pane;
    private final Controller controller;
    private final LatexDrawing drawing;

    private final AnimationTimer cursor = new AnimationTimer() {
        int frame = 0;

        @Override
        public void handle(long now) {
            GraphicsContext context = getGraphicsContext2D();
            if (frame % 40 == 1) {
                context.setFill(Color.BLACK);
                context.fillRect(drawing.getXOffset(), drawing.getYOffset() - drawing.getTextSize() * .75,
                        1, drawing.getTextSize());
            } else if (frame % 40 == 21) {
                context.setFill(Color.WHITE);
                context.fillRect(drawing.getXOffset(), drawing.getYOffset() - drawing.getTextSize() * .75 - 1,
                        1, drawing.getTextSize() + 2);
            }
            frame++;
        }
    };

    @Getter
    private StringBuilder text = new StringBuilder();

    public LatexCanvas(Controller controller, ScrollPane equation, boolean input) {
        if(input) {
            drawing = new LatexDrawing(this, equation.getPrefWidth(), equation.getPrefHeight());
            heightProperty().bind(equation.heightProperty());
        } else {
            drawing = new LatexDrawing(this, equation.getPrefWidth(), equation.getPrefHeight());
            setHeight(equation.getPrefHeight());
        }
        setWidth(equation.getPrefWidth());
        this.controller = controller;
        this.pane = equation;
    }

    public void startCursor() {
        cursor.start();
    }

    public void stopCursor() {
        cursor.stop();
        getGraphicsContext2D().setFill(Color.WHITE);
        getGraphicsContext2D().fillRect(drawing.getXOffset(), drawing.getYOffset() - drawing.getTextSize() * .75,
                1, drawing.getTextSize() + 2);
    }

    public void onKeyPressed(KeyEvent event) {
        if (event.getCode() == KeyCode.BACK_SPACE) {
            onBackspace();
        } else if (event.getCode() == KeyCode.ENTER) {
            controller.onEnter();
            text = new StringBuilder();
        }
        update();
    }

    public void onKeyTyped(KeyEvent event) {
        char character = event.getCharacter().charAt(0);
        if (character >= '!' && character <= '~') {
            text.append(event.getCharacter());
            update();
        }
    }

    public void onBackspace() {
        if (text.length() > 0) {
            text.deleteCharAt(text.length() - 1);
            update();
        }
    }

    public void update() {
        drawing.clear();
        pane.setContent(this);
        if(text.length() != 0) {
            FractionCalculatorParser parser = new FractionCalculatorParser(FractionCalculator.getLogger());
            parser.parse(text.toString());
            drawing.printEquation(parser.getExpressions(), parser.getValues());
        }
        pane.setContent(this);
    }
}
