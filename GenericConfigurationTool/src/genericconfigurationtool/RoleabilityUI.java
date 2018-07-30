/*
 *
 */
package genericconfigurationtool;

import javafx.geometry.Insets;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 *
 * @author wbeebe
 */
public class RoleabilityUI extends GridPane implements TabUI {

    @Override
    public void init(Stage stage, AppControl control) {
        setHgap(5);
        setVgap(5);
        setPadding(new Insets(0, 5, 0, 10));
    }
    
    public RoleabilityUI() {
    }

    @Override
    public void reset() {
    }

    @Override
    public String tabName() {
        return "Roleability";
    }
}
