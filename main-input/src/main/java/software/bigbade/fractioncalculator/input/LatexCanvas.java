package software.bigbade.fractioncalculator.input;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import lombok.Getter;
import software.bigbade.fractioncalculator.rendering.LatexDrawing;

/**
 * Takes in text and renders it in LaTeX.
 * Credit to https://stackoverflow.com/questions/25027060/running-swing-application-in-javafx/25037747#25037747
 */
public class LatexCanvas extends Canvas {
    @Getter
    private final StringBuilder text = new StringBuilder();

    private final Controller controller;

    private final GraphicsContext context = getGraphicsContext2D();
    private final LatexDrawing drawing = new LatexDrawing(0, 0, getWidth(), getHeight());

    public LatexCanvas(Controller controller, StackPane equation) {
        this.controller = controller;

        //Required to hear key events
        setFocusTraversable(true);

        addEventFilter(MouseEvent.MOUSE_PRESSED, e -> requestFocus());

        addEventFilter(KeyEvent.KEY_TYPED, this::onKeyTyped);
        addEventFilter(KeyEvent.KEY_PRESSED, this::onKeyPressed);

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

    public void onKeyPressed(KeyEvent event) {
        if(event.getCode() == KeyCode.BACK_SPACE) {
            onBackspace();
        } else if(event.getCode() == KeyCode.ENTER) {
            controller.onEnter();
        }
        update();
    }

    public void onKeyTyped(KeyEvent event) {
        char character = event.getCharacter().charAt(0);
        if(character >= '!' && character <= '~') {
            text.append(event.getCharacter());
            update();
        }
    }

    public void onBackspace() {
        if(text.length() > 0) {
            text.deleteCharAt(text.length() - 1);
        }
    }

    private void update() {
        drawing.draw(context, text.toString());
    }
}
