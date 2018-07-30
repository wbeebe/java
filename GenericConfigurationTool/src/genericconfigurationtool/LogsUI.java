/*
 *
 */
package genericconfigurationtool;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

/**
 *
 * @author wbeebe
 */
public class LogsUI extends GridPane implements TabUI {
    private final DirectoryChooser directoryChooser = new DirectoryChooser();
    private EnhancedTextField loggingFilesLocation;

    @Override
    public void init(Stage stage, AppControl control) {
        setHgap(5);
        setVgap(5);
        setPadding(new Insets(0, 5, 0, 10));
        String home = System.getProperty("user.home");
        
        add(new Label("Logging Files Location"), 0, 1);
        loggingFilesLocation = new EnhancedTextField(control, home);
        loggingFilesLocation.setPrefColumnCount(16);
        add(loggingFilesLocation, 0, 2);
        Button findLfLocation = new BrowseButton(stage, directoryChooser, loggingFilesLocation);
        add(findLfLocation, 1, 2);

        add(new Label("Logging level"), 0, 4);
        ToggleGroup loggingLevel = new ToggleGroup();
        RadioButton disabled = new RadioButton("Disabled");
        disabled.setToggleGroup(loggingLevel);
        RadioButton debug = new RadioButton("Debug");
        debug.setToggleGroup(loggingLevel);
        RadioButton info = new RadioButton("Info");
        info.setToggleGroup(loggingLevel);
        info.setSelected(true);
        RadioButton warning = new RadioButton("Warning");
        warning.setToggleGroup(loggingLevel);
        RadioButton error = new RadioButton("Error");
        error.setToggleGroup(loggingLevel);
        VBox vbox = new VBox(disabled, debug, info, warning, error);
        add(vbox, 0, 5);
    }

    public LogsUI() {
    }

    @Override
    public void reset() {
        loggingFilesLocation.clearEditedState();
    }

    @Override
    public String tabName() {
        return "Logging";
    }
}
