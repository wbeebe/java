/*
 * 
 */
package genericconfigurationtool;

import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.scene.text.Font;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

/**
 *
 * @author wbeebe
 */
public class SystemUI extends GridPane implements TabUI {
    private EnhancedTextField gateway;
    private EnhancedTextField edsProd;
    private EnhancedTextField edsTest;
    private EnhancedTextField port;

    @Override
    public void init(Stage stage, AppControl control) {
        setHgap(5);
        setVgap(5);
        setPadding(new Insets(0, 5, 0, 10));

        Label title = new Label("Gateway Services");
        title.setFont(Font.font(null, FontWeight.BLACK, -1));
        add(title, 0, 1);

        add(new Label("Gateway Host Name/IP Address"), 0, 3);
        gateway = new EnhancedTextField(control);
        gateway.setPrefColumnCount(24);
        add(gateway, 0, 4);

        add(new Label("Exercise Data Server Host Name/IP Address"), 0, 6);
        edsProd = new EnhancedTextField(control);
        edsProd.setPrefColumnCount(24);
        add(edsProd, 0, 7);

        edsTest = new EnhancedTextField(control);
        edsTest.setPrefColumnCount(24);
        add(edsTest, 0, 8);

        add(new Label("Messaging Port"), 0, 10);
        port = new EnhancedTextField(control, "61616");
        port.setPrefColumnCount(16);
        add(port, 0, 11);

        // Enabling radio buttons for Production/Test EDS
        //
        ToggleGroup group = new ToggleGroup();
        RadioButton enableProd = new RadioButton("Production");
        enableProd.setToggleGroup(group);
        enableProd.setSelected(true);

        RadioButton enableTest = new RadioButton("Test");
        enableTest.setToggleGroup(group);

        add(enableProd, 1, 7);
        add(enableTest, 1, 8);
        
        group.selectedToggleProperty().addListener(
            (ObservableValue<? extends Toggle> ov, Toggle oldValue, Toggle newValue) -> {
            if (group.getSelectedToggle() != null) {
                System.out.println(group.getSelectedToggle().toString());
            }
        });
    }

    public SystemUI() {
    }

    @Override
    public void reset() {
        gateway.clearEditedState();
        edsProd.clearEditedState();
        edsTest.clearEditedState();
        port.clearEditedState();
    }

    @Override
    public String tabName() {
        return "Services";
    }
}
