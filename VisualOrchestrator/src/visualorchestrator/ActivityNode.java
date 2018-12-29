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

import java.util.ArrayList;
import java.util.List;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Glow;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.CubicCurve;
import javafx.scene.shape.Rectangle;

/**
 * The visual representation of an activity to be performed.
 *
 * @author wbeebe
 */
public class ActivityNode extends StackPane implements Control {
    Rectangle rect;
    Label label;
    CubicCurve connector;
    Arrowhead arrowhead;
    double sceneX, sceneY;
    double translateX, translateY;

    ArrayList<ActivityNode> children = new ArrayList<>();

    public ActivityNode(double x, double y, double w, double h) {
        label = new Label();
        makePane(x, y, w, h);
    }

    public ActivityNode(double x, double y, double w, double h, String text) {
        label = new Label(text);
        label.setStyle("fx-background-color: red;");
        makePane(x, y, w, h);
    }

    @Override
    public void setAsActive() {
        rect.setFill(Colors.activeBackground);
        rect.setStroke(Colors.activeStroke);
        if (connector != null) connector.setStroke(Colors.activeStroke);
        if (arrowhead != null) arrowhead.setFill(Colors.activeStroke);
    }

    @Override
    public void setAsInactive() {
        rect.setFill(Colors.inactiveBackground);
        rect.setStroke(Colors.inactiveStroke);
        if (connector != null) connector.setStroke(Colors.inactiveStroke);
        if (arrowhead != null) arrowhead.setFill(Colors.inactiveStroke);
    }

    public CubicCurve getConnector() { return connector; }

    public Arrowhead getArrowhead() { return arrowhead; }

    public List<ActivityNode> getNodeChildren() { return children; }

    public void setLabel(String text) {
        label.setText(text);
    }

    public String getLabel() {
        return label.getText();
    }

    public void setConnector(CubicCurve con) {
        connector = con;
    }

    public void addChild(ActivityNode testingNode) {
        if (!children.contains(testingNode)) children.add(testingNode);
    }

    public void removeChild(ActivityNode testingNode) {
        if (children.contains(testingNode)) children.remove(testingNode);
    }

    private void makePane(double x, double y, double w, double h) {
        final Glow glow = new Glow();
        final DropShadow dropShadow = new DropShadow();
        dropShadow.setOffsetX(3.0);
        dropShadow.setOffsetY(3.0);
        dropShadow.setRadius(8.0);
        //dropShadow.setColor(Color.color(0.2, 0.2, 0.2));
        dropShadow.setColor(Color.rgb(128, 128, 128));

        rect = new Rectangle(0, 0, w, h);
        rect.setFill(Colors.inactiveBackground);
        rect.setStroke(Colors.inactiveStroke);
        rect.setStrokeWidth(2);
        rect.setArcWidth(0);
        rect.setArcHeight(0);

        label.setAlignment(Pos.TOP_CENTER);
        getChildren().addAll(rect, label);
        setPadding(new Insets(0,0,0,0));
        setLayoutX(x);
        setLayoutY(y);
        setEffect(dropShadow);

        setOnMousePressed((MouseEvent event) -> {
            sceneX = event.getSceneX();
            sceneY = event.getSceneY();
            translateX = ((ActivityNode)(event.getSource())).getTranslateX();
            translateY = ((ActivityNode)(event.getSource())).getTranslateY();
            toFront();
            System.out.println("Mouse pressed: " + label.getText());
            System.out.println(" x " + Double.toString(sceneX));
        });
 
        setOnMouseDragged((MouseEvent event) -> {
            double newTranslateX = translateX + (event.getSceneX() - sceneX);
            double newTranslateY = translateY + (event.getSceneY() - sceneY);

            if (event.getSceneX() > 20.) {
                ((ActivityNode)(event.getSource())).setTranslateX(newTranslateX);
            }
            ((ActivityNode)(event.getSource())).setTranslateY(newTranslateY);
        });

        setOnMouseReleased((MouseEvent event) -> {
            double newTranslateX = (double)((long)(((translateX + (event.getSceneX() - sceneX))/10)*10));
            double newTranslateY = (double)((long)(((translateY + (event.getSceneY() - sceneY))/10)*10));

            ((ActivityNode)(event.getSource())).setTranslateX(newTranslateX);
            ((ActivityNode)(event.getSource())).setTranslateY(newTranslateY);
            System.out.println("Mouse released: " + label.getText());
        });

        setOnDragDetected((MouseEvent event) -> {
            System.out.println("Drag detected: " + label.getText());
            Dragboard db = startDragAndDrop(TransferMode.ANY);
            event.consume();
        });

        setOnMouseEntered((MouseEvent event) -> {
            setCursor(Cursor.HAND);
            dropShadow.setInput(glow);
        });

        setOnMouseExited((MouseEvent event) -> {
            dropShadow.setInput(null);
        });
    }
}
