package software.bigbade.fractioncalculator.rendering;

import javafx.scene.canvas.GraphicsContext;
import lombok.Setter;

public class LatexDrawing {
    @Setter
    public int x;
    @Setter
    public int y;
    @Setter
    public double width;
    @Setter
    public double height;

    public LatexDrawing(int x, int y, double width, double height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public void draw(GraphicsContext context, String text) {
        StringBuilder builder = new StringBuilder();
        for(int i = 0; i < text.length(); i++) {

        }
    }
}
