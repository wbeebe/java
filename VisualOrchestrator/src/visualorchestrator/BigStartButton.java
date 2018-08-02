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

import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

/**
 *
 * @author wbeebe
 */
public class BigStartButton extends StackPane implements Control {
    final Circle circle = new Circle();
    final Label label = new Label("Start");
    boolean isActive = false;
    Control controlled;

    public BigStartButton(double x, double y, double r) {
        circle.setRadius(r);
        circle.setFill(Color.GREEN);
        circle.setStroke(Color.BLACK);
        circle.setStrokeWidth(2);
        circle.setOnMouseClicked((MouseEvent event) -> {
            if (! isActive) {
                isActive = true;
                setAsActive();
                if (controlled != null) controlled.setAsActive();
            }
            else {
                isActive = false;
                setAsInactive();
                if (controlled != null) controlled.setAsInactive();
            }
        });

        label.setStyle("-fx-font-size:16;-fx-font-weight:bold;");
        label.setTextFill(Color.WHITE);
        label.setMouseTransparent(true);

        getChildren().addAll(circle, label);
        setLayoutX(x);
        setLayoutY(y);
    }
    
    @Override
    public void setAsActive() {
        isActive = true;
        circle.setFill(Color.RED);
        label.setText("Stop");
    }

    @Override
    public void setAsInactive() {
        isActive = false;
        circle.setFill(Color.GREEN);
        label.setText("Start");
    }

    public void setControlled(Control controlled) {
        this.controlled = controlled;
    }
}
