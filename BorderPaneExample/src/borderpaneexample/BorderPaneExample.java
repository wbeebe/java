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

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author wbeebe
 */
public class BorderPaneExample extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        var borderPane = new BorderPane();

        var rightButton = new Button("Right");
        rightButton.setOnAction((ActionEvent event) -> {
            System.out.println("Right button");
        });

        var leftButton = new Button("Left");
        leftButton.setOnAction((ActionEvent event) -> {
            System.out.println("Left button");
        });

        var topButton = new Button("Top");
        topButton.setOnAction((ActionEvent event) -> {
            System.out.println("Top button");
        });

        var bottomButton = new Button("Bottom");
        bottomButton.setOnAction((ActionEvent event) -> {
            System.out.println("Bottom button");
        });
        
        var centerPane = new Pane();
        var resizeableCanvas = new ResizeableCanvas(centerPane);
        centerPane.getChildren().add(resizeableCanvas);
        centerPane.setStyle(
            "-fx-padding: 0;" +
            "-fx-border-style: solid inside;" +
            "-fx-border-width: 1;" +
            "-fx-border-insets: 0;" +
            "-fx-border-radius: 0;" +
            "-fx-border-color: #000;");
        resizeableCanvas.addLayer(new GridLayer());
        resizeableCanvas.addLayer(new TargetLayer());

        borderPane.setCenter(centerPane);
        borderPane.setRight(rightButton);
        BorderPane.setAlignment(rightButton, Pos.CENTER_RIGHT);
        VBox vbox = makeVBox();
        vbox.getChildren().add(leftButton);
        borderPane.setLeft(vbox);
        BorderPane.setAlignment(leftButton, Pos.CENTER_LEFT);
        HBox hbox = makeHBox();
        hbox.getChildren().add(topButton);
        borderPane.setTop(hbox);
        BorderPane.setAlignment(topButton, Pos.TOP_CENTER);
        borderPane.setBottom(bottomButton);
        BorderPane.setAlignment(bottomButton, Pos.BOTTOM_CENTER);
	borderPane.setStyle(
            "-fx-padding: 2;" +
            "-fx-border-style: solid inside;" +
            "-fx-border-width: 2;" +
            "-fx-border-insets: 2;" +
            "-fx-border-radius: 0;" +
            "-fx-border-color: #cccccc;");


        var scene = new Scene(borderPane, 800, 600);
        primaryStage.setTitle("BorderPane Example with Canvas");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    public HBox makeHBox() {
        var hbox = new HBox();
        hbox.setPadding(new Insets(5, 5, 5, 5));
        hbox.setSpacing(5);
        hbox.setStyle("-fx-background-color: #cccccc;");
        return hbox;
    }

    public VBox makeVBox() {
        var vbox = new VBox();
        vbox.setPadding(new Insets(5, 5, 5, 5));
        vbox.setSpacing(5);
        vbox.setStyle("-fx-background-color: #dddddd;");
        return vbox;
    }
}
