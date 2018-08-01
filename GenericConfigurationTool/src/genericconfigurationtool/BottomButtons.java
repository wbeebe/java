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

import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;

/**
 *
 * @author wbeebe
 */
public class BottomButtons extends HBox {
    private final AppControl app;
    private Button saveButton;

    private void init() {
        setPadding(new Insets(15, 12, 15, 12));
        setSpacing(10);

        saveButton = new Button("Save");
        saveButton.setPrefSize(100, 20);
        saveButton.setDisable(true);
        saveButton.setOnAction((final ActionEvent ae) -> {
           app.saveData();
        });

        Button closeButton = new Button("Close");
        closeButton.setPrefSize(100, 20);
        closeButton.setOnAction((final ActionEvent ae) -> {
            app.closeApp();
        });

        getChildren().addAll(saveButton, closeButton);
        setAlignment(Pos.BASELINE_RIGHT);        
    }

    public void setSaveEnabled(boolean flag) {
        saveButton.setDisable(!flag);
    }

    public BottomButtons(AppControl app) {
        this.app = app;
        init();
    }
}
