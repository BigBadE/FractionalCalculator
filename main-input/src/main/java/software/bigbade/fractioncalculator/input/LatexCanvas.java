package software.bigbade.fractioncalculator.input;

import javafx.animation.AnimationTimer;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.effect.BlurType;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Effect;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import lombok.Getter;
import software.bigbade.fractioncalculator.FractionCalculator;
import software.bigbade.fractioncalculator.parser.FractionCalculatorParser;
import software.bigbade.fractioncalculator.rendering.LatexDrawing;

/**
 * Takes in text and renders it in LaTeX.
 * Credit to https://stackoverflow.com/questions/25027060/running-swing-application-in-javafx/25037747#25037747
 */
public class LatexCanvas extends Canvas {
    private static final Effect FOCUS_EFFECT = new DropShadow(BlurType.GAUSSIAN, Color.SKYBLUE, 5, 0.75, 0, 0);
    private final Controller controller;
    private final LatexDrawing drawing = new LatexDrawing(getGraphicsContext2D(), getWidth(), getHeight());

    private final AnimationTimer cursor = new AnimationTimer() {
        int frame = 0;

        @Override
        public void handle(long now) {
            GraphicsContext context = getGraphicsContext2D();
            if (frame % 40 == 1) {
                context.setFill(Color.BLACK);
                context.fillRect(drawing.getXOffset(), drawing.getYOffset()-drawing.getTextSize()*.75,
                        1, drawing.getTextSize());
            } else if (frame % 40 == 21) {
                context.setFill(Color.WHITE);
                context.fillRect(drawing.getXOffset(), drawing.getYOffset()-drawing.getTextSize()*.75-1,
                        1, drawing.getTextSize()+2);
            }
            frame++;
        }
    };

    @Getter
    private StringBuilder text = new StringBuilder();

    public LatexCanvas(Controller controller, StackPane equation, boolean input) {
        this.controller = controller;

        if (input) {
            //Required to hear key events
            setFocusTraversable(true);

            addEventFilter(MouseEvent.MOUSE_CLICKED, e -> {
                requestFocus();
                cursor.start();
                setEffect(FOCUS_EFFECT);
            });

            addEventFilter(KeyEvent.KEY_TYPED, this::onKeyTyped);
            addEventFilter(KeyEvent.KEY_PRESSED, this::onKeyPressed);
        }

        setLayoutX(4.0);
        setLayoutY(4.0);
        setHeight(equation.getHeight());
        setWidth(equation.getWidth());

        // Redraw canvas when size changes.
        widthProperty().addListener(evt -> {
            drawing.setWidth(getWidth());
            update();
        });
        heightProperty().addListener(evt -> {
            drawing.setHeight(getHeight());
            update();
        });
    }

    public void stopCursor() {
        cursor.stop();
        getGraphicsContext2D().setFill(Color.WHITE);
        getGraphicsContext2D().fillRect(drawing.getXOffset(), drawing.getYOffset()-drawing.getTextSize()*.75,
                1, drawing.getTextSize()+2);
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
        }
    }

    public void update() {
        FractionCalculatorParser parser = new FractionCalculatorParser(FractionCalculator.getLogger());
        parser.parse(text.toString());
        drawing.clear();
        drawing.printEquation(parser.getExpressions(), parser.getValues());
    }
}
