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
