package borderpaneexample;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 *
 * @author wbeebe
 */
public class GridLayer implements CanvasLayer {

    @Override
    public void draw(GraphicsContext gc, double width, double height) {
        gc.clearRect(0, 0, width, height);
        // lighter than LIGHTGRAY
        gc.setStroke(Color.rgb(230, 230, 230));

        // Create all the vertical lines.
        //
        for (double i = 0; i <= width; i += 10) {
            gc.setLineWidth((i % 100) == 0 ? 2 : 1);
            gc.strokeLine(i, 0, i, height-2);
        }

        // Create all the horizontal lines.
        //
        for (double j = 0; j <= height; j += 10) {
            gc.setLineWidth((j % 100) == 0 ? 2 : 1);
            gc.strokeLine(0, j, width-2, j);
        }
    }
}
