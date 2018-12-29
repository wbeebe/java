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
package genericconfigurationtool;

import java.util.Optional;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 * @author wbeebe
 */
public class GenericConfigurationTool extends Application implements AppControl {
    private Stage stage;
    private boolean isEdited = false;
    private BottomButtons bottomButtons;

    private static final TabUI[] TAB_CONTENT = new TabUI[]{
            new AboutUI(),
            new WorkstationUI(),
            new SystemUI(),
            new DatabaseUI(),
            new CotsUI(),
            new SecurityUI(),
            new RemoteControlUI(),
            new LogsUI(),
    };

    @Override
    public void start(Stage primaryStage) {
        this.stage = primaryStage;

        stage.setTitle("Generic Configuration Tool");
        
        StackPane root = new StackPane();
        Scene scene = new Scene(root, 800, 500);
        scene.getStylesheets().add(
                getClass().getResource("GenericConfigurationTool.css").toExternalForm());

        TabPane tabPane = new TabPane();
        tabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);

        BorderPane borderPane = new BorderPane();
        
        for (TabUI tabContent : TAB_CONTENT) {
            Tab tab = new Tab();
            tab.setText(tabContent.tabName());
            tabContent.init(stage, this);
            tab.setContent((Node)tabContent);
            tabPane.getTabs().add(tab);
        }

        borderPane.prefHeightProperty().bind(scene.heightProperty());
        borderPane.prefWidthProperty().bind(scene.widthProperty());
        borderPane.setCenter(tabPane);
        // tabPane.getSelectionModel().select(8);
        bottomButtons = new BottomButtons(this);
        borderPane.setBottom(bottomButtons);

        root.getChildren().add(borderPane);
        stage.setScene(scene);
        stage.show();

        stage.setOnCloseRequest(event -> {
            closeApp();
            event.consume();
        });
    }

    @Override
    public void wasEdited() {
        isEdited = true;
        bottomButtons.setSaveEnabled(isEdited);
    }

    @Override
    public void closeApp() {
        if (isEdited) {
            ButtonType close = new ButtonType("Close", ButtonBar.ButtonData.OK_DONE);
            ButtonType stay = new ButtonType("Stay", ButtonBar.ButtonData.CANCEL_CLOSE);
            Alert alert = new Alert(
                    AlertType.CONFIRMATION,
                    "You have unsaved edits.\nReally close and exit?",
                    close, stay);
            DialogPane dialogPane = alert.getDialogPane();
            dialogPane.getStylesheets().add(
                getClass().getResource("GenericConfigurationTool.css").toExternalForm());
            alert.setTitle("Exit Confirmation");
            alert.initOwner(stage);
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get().getText().equalsIgnoreCase("Stay")) {
                return;
            }
        }

        Platform.exit();
        System.exit(0);
    }

    @Override
    public void saveData() {
        isEdited = false;
        bottomButtons.setSaveEnabled(isEdited);
        for (TabUI tabContent : TAB_CONTENT) {
            tabContent.reset();
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}
