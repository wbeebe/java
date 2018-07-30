/*
 * 
 */
package genericconfigurationtool;

import javafx.geometry.Insets;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 *
 * @author wbeebe
 */
public class SecurityUI extends GridPane implements TabUI {

    @Override
    public void init(Stage stage, AppControl control) {
        setHgap(5);
        setVgap(5);
        setPadding(new Insets(0, 5, 0, 10));
        
        CheckBox enableAuditLogging = new CheckBox("Enable Audit Logging");
        add(enableAuditLogging, 0, 2);
        CheckBox enableDigitalSignatures = new CheckBox("Enable Digital Signatures");
        add(enableDigitalSignatures, 0, 3);
    }    

    public SecurityUI() {
    }

    @Override
    public void reset() {
    }

    @Override
    public String tabName() {
        return "Security";
    }
}
