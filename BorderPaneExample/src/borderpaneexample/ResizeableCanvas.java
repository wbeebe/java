package borderpaneexample;

import java.util.ArrayList;
import java.util.List;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Region;

/**
 *
 * @author wbeebe
 */

public class ResizeableCanvas extends Canvas {
    List<CanvasLayer>canvasLayerList;
    /**
     * ResizeableCanvas provides a resizeable Canvas.
     * 
     * Resizeable Canvas requires that it be wrapped in a Region.
 
 To use, follow these steps:
 1) Instantiate a wrap around Region instance, such as Pane;
 2) Instantiate a ResizeableCanvas with the Region class instance;
 3) Call the Region's getChildren().add(...) method to add the
    ResizeableCanvas instance to the Region instance.
 
 This is especially effective, and required, if you want a resizable
 Canvas in the center of a BorderPane and want to use any of the other
 BorderPane regions.
 
 Internally the Resizable Canvas is bound to the wrapper Region's
 width and heigh property; when the wrapper Region is resized, then
 the Resiable Canvas' resizeDraw() method is called, producing the effect of
 resizing through redrawing.
 
 For this to work, isResizable() must be overridden to return true,
 prefWidth() overridden to return the current width, and
 prefHeight() overridden to return the current height.
     * 
     * @param region 
     */
    public ResizeableCanvas(Region region) {
        this.canvasLayerList = new ArrayList<>();
        widthProperty().bind(region.widthProperty());
        heightProperty().bind(region.heightProperty());
        widthProperty().addListener(event -> resizeDraw() );
        heightProperty().addListener(event -> resizeDraw() );
    }

    /**
     * Basically shows how to use the GraphicsContext to resizeDraw into the 
     * Canvas.
     * Any graphic operation that can be supported by a Canvas can be performed
     * here on a resize event.
     */
    private void resizeDraw() {
        double width = getWidth();
        double height = getHeight();
        GraphicsContext gc = getGraphicsContext2D();

        canvasLayerList.forEach((canvasLayer) -> {
            canvasLayer.draw(gc, width, height);
        });
    }

    public void addLayer(CanvasLayer canvasLayer) {
        canvasLayerList.add(canvasLayer);
    }

    @Override
    public boolean isResizable() {
      return true;
    }
 
    @Override
    public double prefWidth(double height) {
      return getWidth();
    }
 
    @Override
    public double prefHeight(double width) {
      return getHeight();
    }
}
