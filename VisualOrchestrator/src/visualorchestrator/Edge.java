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
 *
 * A Edge contains a CubicCurve and an Arrowhead, as well as
 * a direction.
 */
package visualorchestrator;

import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.CubicCurve;
import javafx.scene.shape.Rectangle;

/**
 *
 * @author wbeebe
 */
public class Edge extends CubicCurve implements Control {
    double startX, startY, width, height;
    Rectangle srcRect, destRect;
    Circle srcCirc, destCirc;

    public Edge(StackPane start, StackPane end, Color color, double stroke) {

        if (start.getChildren().get(0) instanceof Rectangle ) {
            srcRect = (Rectangle)start.getChildren().get(0);
            destRect = (Rectangle)end.getChildren().get(0);
            width = srcRect.getWidth();
            height = srcRect.getHeight();
        }
        else {
            srcCirc = (Circle)start.getChildren().get(0);
            destRect = (Rectangle)end.getChildren().get(0);
            width = height = srcCirc.getRadius() * 2;
        }

        if (start.getLayoutX() + height >= end.getLayoutY()) {
            startX = start.getLayoutX() + height / 2.0;
            startY = start.getLayoutY() + height / 2.0;
        }
        else {
            startX = start.getLayoutX() + width / 2;
            startY = start.getLayoutY() + height;
        }

        double endX = end.getLayoutX();
        double endY = end.getLayoutY() + destRect.getHeight() / 2.0;

        double controlX1, controlX2, controlY1, controlY2;

        if (start.getLayoutY() + height >= end.getLayoutY()) {
            controlX1 = endX;
            controlX2 = startX;
            controlY1 = startY;
            controlY2 = endY;
        }
        else {
            controlX1 = startX;
            controlY1 = endY;
            controlX2 = startX;
            controlY2 = endY;
        }

        setStartX(startX);
        setStartY(startY);
        setControlX1(controlX1);
        setControlY1(controlY1);
        setControlX2(controlX1);
        setControlY2(controlY2);
        setEndX(endX);
        setEndY(endY);
        setStroke(color);
        setStrokeWidth(stroke);
        setFill(null);
    }

    @Override
    public void setAsActive() {
        
    }

    @Override
    public void setAsInactive() {
        
    }
}
