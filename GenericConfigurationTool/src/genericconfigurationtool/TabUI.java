/*
 *
 */
package genericconfigurationtool;

import javafx.stage.Stage;

/**
 *
 * @author wbeebe
 */
public interface TabUI {
    void init(Stage stage, AppControl control);
    void reset();
    String tabName();
}
