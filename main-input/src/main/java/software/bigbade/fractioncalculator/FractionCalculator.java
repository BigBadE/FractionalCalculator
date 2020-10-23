package software.bigbade.fractioncalculator;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import software.bigbade.fractioncalculator.input.Controller;

import java.io.IOException;

public class FractionCalculator extends Application {
    @Getter
    private static final Logger logger = LoggerFactory.getLogger(FractionCalculator.class);

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        Controller controller = new Controller();
        loader.setController(controller);
        loader.setLocation(getClass().getResource("/basic_input.fxml"));
        Parent root = loader.load();

        Scene scene = new Scene(root);

        stage.setTitle("Fraction Calculator");
        stage.setScene(scene);
        stage.show();
    }
}
