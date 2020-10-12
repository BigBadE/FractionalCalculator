package software.bigbade.fractioncalculator;

import javafx.beans.value.ChangeListener;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import software.bigbade.fractioncalculator.parser.FractionCalculatorParser;

import java.util.ArrayList;
import java.util.List;

public class Controller {
    @FXML
    private TextField equation;
    @FXML
    private TextArea output;
    @FXML
    private TextArea currentEquation;

    private List<String> previousOutputs = new ArrayList<>();

    private int current = 0;

    @FXML
    public void initialize() {
        assert output != null;
        output.textProperty().addListener((ChangeListener<Object>) (observable, oldValue, newValue) -> output.setScrollTop(Double.MAX_VALUE));
        equation.setPromptText("Enter an equation");
    }

    public void goToBack() {
        if(!previousOutputs.isEmpty()) {
            current = 1;
            updateCurrentEquation(1);
        }
    }

    public void goBackOne() {
        if(current > 1) {
            current--;
            updateCurrentEquation(current);
        }
    }

    public void goForwardOne() {
        if(current < previousOutputs.size()) {
            current++;
            updateCurrentEquation(current);
        }
    }

    public void goToFront() {
        current = previousOutputs.size();
        updateCurrentEquation(previousOutputs.size());
    }

    public void onEnter() {
        output.clear();
        FractionCalculatorParser parser = new FractionCalculatorParser(FractionCalculator.getLogger());
        String outputString = parser.calculate(equation.getText());
        FractionCalculator.getLogger().info(equation.getText() + " to "
                + outputString.substring(outputString.lastIndexOf('\n')+1));
        outputString += '\n';
        previousOutputs.add(outputString);
        output.appendText(outputString);
        current = previousOutputs.size();
        updateCurrentEquation(previousOutputs.size());
    }

    private void updateCurrentEquation(int current) {
        currentEquation.setText("(" + current + "/" + previousOutputs.size() + ")");
        output.clear();
        output.appendText(previousOutputs.get(current-1));
    }
}
