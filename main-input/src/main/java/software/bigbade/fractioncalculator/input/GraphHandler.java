package software.bigbade.fractioncalculator.input;

import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import lombok.RequiredArgsConstructor;
import software.bigbade.fractioncalculator.math.values.FractionValue;
import software.bigbade.fractioncalculator.math.values.IValue;
import software.bigbade.fractioncalculator.math.values.NumberValue;

@RequiredArgsConstructor
public class GraphHandler {
    private static final int VALUES_PER_INCREMENT = 10;

    private final LineChart<Double, Double> lineChart;


    private double minX;
    private double xRange;
    private double minY;
    private double yRange;

    public void updateGraph() {
        //this.data = data;
        XYChart.Series<Double, Double> series = new XYChart.Series<>();
        for(double x = minX; x < minX+xRange; x+=(xRange/VALUES_PER_INCREMENT/20)) {
            series.getData().add(new XYChart.Data<>(x, getValue(x)));
        }
        lineChart.getData().add(series);
    }

    private double getValue(double x) {
        return 1 /*getNumberValue(data.getValues().get(0))*/;
    }

    private double getNumberValue(IValue value) {
        if(value instanceof FractionValue) {
            FractionValue fractionValue = (FractionValue) value;
            return getNumberValue(fractionValue.getNumerator())/getNumberValue(fractionValue.getDenominator());
        } else if(value instanceof NumberValue) {
            return ((NumberValue) value).getNumericValue().doubleValue();
        }
        throw new IllegalArgumentException("Unimplemented getNumberValue for class " + value.getClass());
    }
}
