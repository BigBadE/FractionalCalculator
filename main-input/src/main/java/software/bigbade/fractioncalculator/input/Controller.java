package software.bigbade.fractioncalculator.input;

import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import lombok.Getter;
import software.bigbade.fractioncalculator.FractionCalculator;
import software.bigbade.fractioncalculator.parser.FractionCalculatorParser;
import software.bigbade.fractioncalculator.rendering.LatexDrawing;

import java.util.Map;
import java.util.WeakHashMap;

@SuppressWarnings("unused")
public class Controller {
    //Prevents crashes from lack of memory if too many equations were entered.
    private final Map<Integer, String> previousOutputs = new WeakHashMap<>();

    @FXML
    private ScrollPane equation;
    @FXML
    private ScrollPane output;
    @FXML
    private TextArea currentEquation;
    @FXML
    private LineChart<Double, Double> lineGraph;

    private GraphHandler graphHandler;
    private LatexCanvas outputCanvas;
    private LatexCanvas canvas;

    @Getter
    private int current = 0;

    @FXML
    public void initialize() {
        graphHandler = new GraphHandler(lineGraph);
        canvas = new LatexCanvas(this, equation, true);

        equation.addEventFilter(MouseEvent.MOUSE_CLICKED, e -> {
            equation.requestFocus();
            canvas.startCursor();
        });
        equation.addEventFilter(KeyEvent.KEY_TYPED, canvas::onKeyTyped);
        equation.addEventFilter(KeyEvent.KEY_PRESSED, canvas::onKeyPressed);

        equation.hvalueProperty().addListener(observable -> canvas.update());

        canvas.update();
        equation.setContent(canvas);

        equation.setFocusTraversable(true);

        outputCanvas = new LatexCanvas(this, output, false);
        outputCanvas.update();
        output.hvalueProperty().addListener(observable -> outputCanvas.update());
        output.vvalueProperty().addListener(observable -> outputCanvas.update());
        output.setContent(outputCanvas);
    }

    public void onClick(MouseEvent event) {
        if(!equation.isFocused()) {
            canvas.stopCursor();
        }
    }

    public void onKeyPressed(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            onEnter();
        }
    }

    public void goToBack() {
        if (!previousOutputs.isEmpty()) {
            current = 1;
            updateCurrentEquation(1);
        }
    }

    public void goBackOne() {
        if (current > 1) {
            current--;
            updateCurrentEquation(current);
        }
    }

    public void goForwardOne() {
        if (current < previousOutputs.size()) {
            current++;
            updateCurrentEquation(current);
        }
    }

    public void goToFront() {
        current = previousOutputs.size();
        updateCurrentEquation(previousOutputs.size());
    }

    public void onEnter() {
        previousOutputs.put(current++, canvas.getText().toString());
        updateCurrentEquation(current);
    }

    public void updateCurrentEquation(int current) {
        currentEquation.setText("(" + current + "/" + previousOutputs.size() + ")");

        FractionCalculatorParser parser = new FractionCalculatorParser();

        FractionCalculator.getLogger().info("Calculating " + canvas.getText());
        parser.parse(previousOutputs.get(current - 1));
        LatexDrawing drawing = new LatexDrawing(outputCanvas, Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY);
        drawing.clear();
        parser.calculate(drawing);

        //graphHandler.updateGraph(found);
    }
}
