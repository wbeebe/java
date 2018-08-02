/*
Copyright (c) 2018 William H. Beebe, Jr.

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
*/
package visualorchestrator;

import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * A background that draws a grid. It is bound to the application's Scene
 * width and height, such that if the full application is resized, the
 * background is properly resized to fully fill the background.
 * 
 * @author wbeebe
 */
public class Background extends Canvas {
    private double width, height;
    
    public Background (Scene scene) {
        widthProperty().bind(scene.widthProperty());
        heightProperty().bind(scene.heightProperty());
        widthProperty().addListener(event -> draw());
        heightProperty().addListener(event -> draw());
        draw();
    }
    
    private void draw() {
        width = getWidth();
        height = getHeight();
        GraphicsContext gc = getGraphicsContext2D();
        // lighter than LIGHTGRAY
        gc.setStroke(Color.rgb(230, 230, 230));
        gc.setLineWidth(1);

        // Create all the vertical lines.
        //
        for (double i = 0; i <= width; i += 10) {
            if (i % 100 == 0) {
                gc.setLineWidth(2);
            }
            else {
                gc.setLineWidth(1);
            }
            gc.strokeLine(i, 0, i, height);
        }

        // Create all the horizontal lines.
        //
        for (double j = 0; j <= height; j += 10) {
            if (j % 100 == 0) {
                gc.setLineWidth(2);
            }
            else {
                gc.setLineWidth(1);
            }
            gc.strokeLine(0, j, width, j);
        }
    }
    
    @Override
    public boolean isResizable() { return true; }

    /**
     * Inherited from javafx.scene.Node.
     * 
     * The height value is ignored in the method call but is required to
     * match the overridden parent method.
     * 
     * @param height - the height that should be used if preferred width depends on it
     * @return 
     */
    @Override
    public double prefWidth(double height) {
        return getWidth();
    }

    /**
     * Inherited from javafx.scene.Node.
     * 
     * The width value is ignored in the method call but is required to
     * match the overridden parent method.
     *
     * @param width - the width that should be used if preferred height depends on it
     * @return 
     */
    @Override
    public double prefHeight(double width) {
        return getHeight();
    }
}
