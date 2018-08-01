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
import javafx.scene.text.Font;

/**
 *
 * @author wbeebe
 */
public class TargetLayer implements CanvasLayer {

    @Override
    public void draw(GraphicsContext gc, double width, double height) {
        gc.fillOval((width - 40)/2.0, (height - 40)/2.0, 40, 40);

        Font font = gc.getFont();
        gc.fillText("Width: " + Double.toString(width), width/2 + 2, font.getSize());

        // Some interesting graphics context manipulation. Move to the left
        // edge and print the height, rotated 90 degrees parallel to the
        // left edge.
        //
        gc.save();
        gc.translate(font.getSize(), height/2);
        gc.rotate(-90);
        gc.fillText("Height: " + Double.toString(height), 2, 0);
        gc.restore();

        gc.setStroke(Color.RED);
        gc.setLineWidth(2);
        gc.strokeLine(0, 0, width, height);
        gc.strokeLine(0, height, width, 0);
        gc.strokeLine(width/2.0, 0, width/2.0, height);
        gc.strokeLine(0, height/2.0, width, height/2.0);
    }
}
