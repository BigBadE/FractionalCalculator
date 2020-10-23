package software.bigbade.fractioncalculator.input;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;
import lombok.Setter;
import software.bigbade.fractioncalculator.FractionCalculator;
import software.bigbade.fractioncalculator.parser.FractionCalculatorParser;

import java.util.Map;
import java.util.WeakHashMap;

@SuppressWarnings("unused")
public class Controller {
    //Prevents crashes from lack of memory if too many equations were entered.
    private final Map<Integer, String> previousOutputs = new WeakHashMap<>();
    @FXML
    private StackPane equation;
    @FXML
    private TextArea output;
    @FXML
    private TextArea currentEquation;
    @FXML
    private LineChart<Double, Double> lineGraph;

    private GraphHandler graphHandler;
    private LatexCanvas canvas;

    private int current = 0;

    @FXML
    public void initialize() {
        graphHandler = new GraphHandler(lineGraph);
        canvas = new LatexCanvas(this, equation);
        equation.getChildren().add(canvas);

        canvas.widthProperty().bind(equation.widthProperty());
        canvas.heightProperty().bind(equation.heightProperty());
    }

    public void onKeyPressed(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            onEnter();
        } else {
            canvas.onKeyPressed(event);
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
        FractionCalculatorParser parser = new FractionCalculatorParser(FractionCalculator.getLogger());

        FractionCalculator.getLogger().info("Calculating " + canvas.getText());
        parser.parse(canvas.getText().toString());
        String outputString = parser.calculate();

        FractionCalculator.getLogger().info("Output: " + outputString.substring(outputString.lastIndexOf('\n') + 1));

        if (outputString.isEmpty()) {
            outputString = "Invalid/Unrecognised function!";
        }
        previousOutputs.put(current, outputString);
        //graphHandler.updateGraph(found);

        current = previousOutputs.size();
        updateCurrentEquation(previousOutputs.size());
    }

    private void updateCurrentEquation(int current) {
        currentEquation.setText("(" + current + "/" + previousOutputs.size() + ")");
        output.clear();
        output.setText(previousOutputs.get(current - 1));

        //graphHandler.updateGraph(found);
    }
}
