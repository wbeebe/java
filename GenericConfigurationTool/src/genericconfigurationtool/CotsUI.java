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
public class CotsUI extends GridPane implements TabUI {
    private final DirectoryChooser directoryChooser = new DirectoryChooser();
    private EnhancedTextField wordProcessorLocation;
    private EnhancedTextField spreadsheetLocation;
    private EnhancedTextField presentationLocation;

    @Override
    public void init(Stage stage, AppControl control) {
        setHgap(5);
        setVgap(5);
        setPadding(new Insets(0, 5, 0, 10));
        int row = -1;

        add(new Label("Local Word Processor Location"), 0, row += 2);
        wordProcessorLocation = new EnhancedTextField(control);
        wordProcessorLocation.setPrefColumnCount(24);
        add(wordProcessorLocation, 0, row += 1);
        Button findWordLocation = new BrowseButton(stage, directoryChooser, wordProcessorLocation);
        add(findWordLocation, 1, row);

        add(new Label("Local Spreadsheet Editor Location"), 0, row += 2);
        spreadsheetLocation = new EnhancedTextField(control);
        spreadsheetLocation.setPrefColumnCount(24);
        add(spreadsheetLocation, 0, row += 1);
        Button findExcelLocation = new BrowseButton(stage, directoryChooser, spreadsheetLocation);
        add(findExcelLocation, 1, row);

        add(new Label("Local Presentation Editor Location"), 0, row += 2);
        presentationLocation = new EnhancedTextField(control);
        presentationLocation.setPrefColumnCount(24);
        add(presentationLocation, 0, row += 1);
        Button findPpLocation = new BrowseButton(stage, directoryChooser, presentationLocation);
        add(findPpLocation, 1, row);
    }

    public CotsUI() {
    }

    @Override
    public void reset() {
        wordProcessorLocation.clearEditedState();
        spreadsheetLocation.clearEditedState();
        presentationLocation.clearEditedState();
    }

    @Override
    public String tabName() {
        return "Desktop Apps";
    }
}
