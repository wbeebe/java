/*
 * Copyright (c) 2018 William H. Beebe, Jr.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package tabbedtables_jfx;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author wbeebe
 */
public class TabbedTables extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Tabbed Tables");
        StackPane root = new StackPane();
        Scene scene = new Scene(root, 800, 600);
        BorderPane borderPane = new BorderPane();
        borderPane.setTop(new TopMenu(primaryStage));
        borderPane.prefHeightProperty().bind(scene.heightProperty());
        borderPane.prefWidthProperty().bind(scene.widthProperty());
        TabPane tabPane = new TabPane();

        for (int i = 1; i < 5; i++) {
            Tab tab = new Tab();
            tab.setText(String.format("Tab %d", i));
            tab.setContent(new CustomTable());
            tabPane.getTabs().add(tab);
        }

        borderPane.setCenter(tabPane);
        root.getChildren().add(borderPane);
        primaryStage.setScene(scene);
        primaryStage.show();

        primaryStage.setOnCloseRequest(event -> {
            closeApp();
            event.consume();
        });
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    public void closeApp() {
        Platform.exit();
        System.exit(0);
    }
}
