/*
 *
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
