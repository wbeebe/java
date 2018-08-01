/*
 * 
 */
package genericconfigurationtool;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

/**
 *
 * @author wbeebe
 */
public class DatabaseUI extends GridPane implements TabUI {
    private final DirectoryChooser directoryChooser = new DirectoryChooser();
    private EnhancedTextField databaseLocation;
    private EnhancedTextField databaseName;
    private EnhancedTextField databaseSchemaName;

    @Override
    public void init(Stage stage, AppControl control) {
        setHgap(5);
        setVgap(5);
        setPadding(new Insets(0, 5, 0, 10));
        
        add(new Label("Local Database Location"), 0, 1);
        databaseLocation = new EnhancedTextField(control);
        databaseLocation.setPrefColumnCount(24);
        add(databaseLocation, 0, 2);
        Button findOracleLocation = new BrowseButton(stage, directoryChooser, databaseLocation);
        add(findOracleLocation, 1, 2);
        
        add(new Label("Database Name"), 0, 3);
        databaseName = new EnhancedTextField(control);
        databaseName.setPrefColumnCount(24);
        add(databaseName, 0, 4);
        
        add(new Label("Database Schema Root"), 0, 6);
        databaseSchemaName = new EnhancedTextField(control);
        add(databaseSchemaName, 0, 7);
    }
    
    public DatabaseUI() {
    }

    @Override
    public void reset() {
        databaseLocation.clearEditedState();
        databaseName.clearEditedState();
        databaseSchemaName.clearEditedState();
    }

    @Override
    public String tabName() {
        return "Database";
    }
}