package software.bigbade.fractioncalculator.input;

import javafx.fxml.FXML;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.chart.LineChart;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
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
    private StackPane equation;
    @FXML
    private StackPane output;
    @FXML
    private TextArea currentEquation;
    @FXML
    private LineChart<Double, Double> lineGraph;

    private GraphHandler graphHandler;
    private LatexCanvas canvas;
    private LatexCanvas outputCanvas;

    @Getter
    private int current = 0;

    @FXML
    public void initialize() {
        graphHandler = new GraphHandler(lineGraph);
        canvas = new LatexCanvas(this, equation, true);
        equation.getChildren().add(canvas);
        outputCanvas = new LatexCanvas(this, output, false);
        output.getChildren().add(outputCanvas);
        outputCanvas.widthProperty().bind(output.widthProperty());
        outputCanvas.heightProperty().bind(output.heightProperty());

        GraphicsContext context = outputCanvas.getGraphicsContext2D();
        context.setFill(Color.WHITE);
        context.fillRect(0, 0, outputCanvas.getWidth(), outputCanvas.getHeight());

        canvas.widthProperty().bind(equation.widthProperty());
        canvas.heightProperty().bind(equation.heightProperty());
    }

    public void onClick(MouseEvent event) {
        if(!canvas.isFocused()) {
            canvas.setEffect(null);
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

        FractionCalculatorParser parser = new FractionCalculatorParser(FractionCalculator.getLogger());

        FractionCalculator.getLogger().info("Calculating " + canvas.getText());
        parser.parse(previousOutputs.get(current - 1));
        LatexDrawing drawing = new LatexDrawing(outputCanvas.getGraphicsContext2D(), outputCanvas.getWidth(), outputCanvas.getHeight());
        drawing.clear();
        parser.calculate(drawing);

        //graphHandler.updateGraph(found);
    }
}
