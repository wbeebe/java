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

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 *
 * @author wbeebe
 */
public class VisualOrchestrator extends Application implements Control {
    ProjectFileManager projectFileManager = null;
    List<Activity> activities = new ArrayList<>();
    Background background;
    BigStartButton start;
    ActivityNode node1, node2, node3, node4, node5, node6;
    Edge connect0;
    Edge connect1, connect2, connect3, connect4, connect5, connect6;
    Arrowhead arrowEnd1, arrowEnd2, arrowEnd3, arrowEnd4, arrowEnd5, arrowEnd6;

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Visual Orchestrator");
        BorderPane borderPane = new BorderPane();
        borderPane.setTop(makeTopMenu(primaryStage));

        Scene scene = new Scene(borderPane, 1024, 800);
        background = new Background(scene);
        
        Group root = new Group();

        start = new BigStartButton(020,020,040);
        start.setControlled(this);
        node1 = new ActivityNode(110,010,80,80,"Action 1");
        node2 = new ActivityNode(250,010,80,80,"Action 2");
        node3 = new ActivityNode(390,010,80,80,"Action 3");
        node4 = new ActivityNode(390,110,80,80,"Action 4");
        node5 = new ActivityNode(390,210,80,80,"Action 5");
        node6 = new ActivityNode(530,010,80,80,"Action 6");

        // connect0 = new Edge(start, node1, Color.BLACK, 2);
        // connect1 = new Edge(node1, node2, Color.BLACK, 2);
        // connect2 = new Edge(node2, node3, Color.BLACK, 2);
        // connect3 = new Edge(node2, node4, Color.BLACK, 2);
        // connect4 = new Edge(node2, node5, Color.BLACK, 2);
        // connect5 = new Edge(node3, node6, Color.BLACK, 2);

        // arrowEnd1 = new Arrowhead(connect1);
        // arrowEnd2 = new Arrowhead(connect2);
        // arrowEnd3 = new Arrowhead(connect3);
        // arrowEnd4 = new Arrowhead(connect4);
        // arrowEnd5 = new Arrowhead(connect5);

        root.getChildren().add(background);
        // we lay down the connectors first so that everthing else overlays
        // their ends.
        //
        // root.getChildren().addAll( connect0, connect1, connect2, connect3, connect4, connect5 );
        root.getChildren().addAll( start, node1, node2, node3, node4, node5, node6 );
        // root.getChildren().addAll( arrowEnd1, arrowEnd2, arrowEnd3, arrowEnd4, arrowEnd5 );

        borderPane.setCenter(root);
        VBox vbox = makeVBox();
        vbox.getChildren().add(new Button("Right"));
        borderPane.setRight(vbox);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void setAsActive() {
        node1.setAsActive();
        if (connect1 != null) {
            connect1.setStroke(Color.RED);
            arrowEnd1.setFill(Color.RED);
        }
    }

    @Override
    public void setAsInactive() {
        node1.setAsInactive();
        if (connect1 != null) {
            connect1.setStroke(Color.BLACK);
            arrowEnd1.setFill(Color.BLACK);
        }
    }

    private MenuBar makeTopMenu(final Stage stage) {
        projectFileManager = new ProjectFileManager();
        MenuBar menuBar = new MenuBar();
        final FileChooser fileChooser = new FileChooser();

        Menu fileMenu = new Menu("File");
        MenuItem newMenuItem = new MenuItem("New");
        newMenuItem.setAccelerator(new KeyCodeCombination(KeyCode.N, KeyCombination.CONTROL_DOWN));
        MenuItem openMenuItem = new MenuItem("Open");
        openMenuItem.setOnAction((ActionEvent ae) -> {
            File file = fileChooser.showOpenDialog(stage);
        });
        openMenuItem.setAccelerator(new KeyCodeCombination(KeyCode.O, KeyCombination.CONTROL_DOWN));
        MenuItem saveMenuItem = new MenuItem("Save");
        saveMenuItem.setAccelerator(new KeyCodeCombination(KeyCode.S, KeyCombination.CONTROL_DOWN));
        MenuItem saveAsMenuItem = new MenuItem("Save As...");
        saveAsMenuItem.setAccelerator(new KeyCodeCombination(KeyCode.S, KeyCombination.CONTROL_DOWN,
            KeyCombination.ALT_DOWN));

        saveAsMenuItem.setOnAction((ActionEvent ae) -> {
            fileChooser.setInitialFileName("test_composition.xml");
            File file = fileChooser.showSaveDialog(stage);
        });

        MenuItem exitMenuItem = new MenuItem("Exit");
        exitMenuItem.setAccelerator(new KeyCodeCombination(KeyCode.F4, KeyCombination.ALT_DOWN));

        exitMenuItem.setOnAction((ActionEvent ae) -> {
            Platform.exit();
        });

        fileMenu.getItems().addAll(newMenuItem, openMenuItem, saveMenuItem,
                saveAsMenuItem, new SeparatorMenuItem(), exitMenuItem);
        /*
        Menu editMenu = new Menu("Edit");

        Menu searchMenu = new Menu("Search");
        MenuItem findMenuItem = new MenuItem("Find");
        findMenuItem.setAccelerator(new KeyCodeCombination(KeyCode.F, KeyCombination.CONTROL_DOWN));
        searchMenu.getItems().addAll(findMenuItem);

        Menu settingsMenu = new Menu("Settings");
        MenuItem preferencesMenuItem = new MenuItem("Preferences");
        settingsMenu.getItems().addAll(preferencesMenuItem);

        menuBar.getMenus().addAll(fileMenu, editMenu, searchMenu, settingsMenu);
        */
        menuBar.getMenus().addAll(fileMenu);
        return menuBar;
    }

    public HBox makeHBox() {
        HBox hbox = new HBox();
        hbox.setPadding(new Insets(5, 5, 5, 5));
        hbox.setSpacing(5);
        hbox.setStyle("-fx-background-color: #cccccc;");
        return hbox;
    }
    public VBox makeVBox() {
        VBox vbox = new VBox();
        vbox.setPadding(new Insets(5, 5, 5, 5));
        vbox.setSpacing(5);
        vbox.setStyle("-fx-background-color: #dddddd;");
        return vbox;
    }
}
