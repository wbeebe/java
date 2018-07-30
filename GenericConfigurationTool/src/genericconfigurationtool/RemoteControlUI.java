/*
 *
 */
package genericconfigurationtool;

import javafx.geometry.Insets;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 *
 * @author wbeebe
 */
public class RemoteControlUI extends GridPane implements TabUI {
    private EnhancedTextField processName;
    
    @Override
    public void init(Stage stage, AppControl control) {
        setHgap(5);
        setVgap(5);
        setPadding(new Insets(0, 5, 0, 10));
        int row = -1;

        CheckBox enableProcessRegistration = new CheckBox("Enable process registration for process name ");
        add(enableProcessRegistration, 0, 1, 2, 1);
        processName = new EnhancedTextField(control, "REMOTEWS");
        add(processName, 2, 1);

        CheckBox enableRemoteShutdown = new CheckBox("Enable remote shutdown");
        add(enableRemoteShutdown, 0, 2, 2, 1);

        Label label1 = new Label("Timeout, in minutes, before automatic logout");
        add(label1, 0, 3, 2, 1);
        ComboBox<String> minutesAutoLogout = new ComboBox<>();
        minutesAutoLogout.getItems().addAll(
                "Disabled",
                "15",
                "30",
                "60",
                "90",
                "120");
        minutesAutoLogout.setValue("Disabled");
        add(minutesAutoLogout, 2, 3);

        CheckBox saveChanges = new CheckBox("Save changes made by the user before logout");
        add(saveChanges, 0, 4, 2, 1);
    }

    public RemoteControlUI() {
    }

    @Override
    public void reset() {
        processName.clearEditedState();
    }

    @Override
    public String tabName() {
        return "Remote Control";
    }
}
