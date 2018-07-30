package borderpaneexample;

import javafx.scene.canvas.GraphicsContext;

/**
 *
 * @author wbeebe
 */
public interface CanvasLayer {
    void draw(GraphicsContext gc, double width, double height);
}
