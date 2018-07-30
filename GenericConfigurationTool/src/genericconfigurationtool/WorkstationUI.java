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
public class WorkstationUI extends GridPane implements TabUI {
    private Stage stage;
    private AppControl control;
    private final DirectoryChooser directoryChooser = new DirectoryChooser();
    private EnhancedTextField messageDirectoryLocation;
    private EnhancedTextField mapDataLocation;
    private EnhancedTextField rasterMapLocation;
    private EnhancedTextField virtualControlLocation;
    private EnhancedTextField fedName;

    @Override
    public void init(Stage stage, AppControl control) {
        this.stage = stage;
        this.control = control;

        setHgap(5);
        setVgap(5);
        setPadding(new Insets(0, 5, 0, 10));
        int row = -1;

        add(new Label("Federate Name"), 0, row += 2);
        fedName = new EnhancedTextField(control, "REMOTE_WORKSTATION");
        fedName.setPrefColumnCount(20);
        add(fedName, 0, row += 1);

        add(new Label("Message Directory Cache"), 0, row += 2);
        messageDirectoryLocation = new EnhancedTextField(control);
        messageDirectoryLocation.setPrefColumnCount(40);
        add(messageDirectoryLocation, 0, row += 1);
        Button findMdLocation = new BrowseButton(stage, directoryChooser, messageDirectoryLocation);
        add(findMdLocation, 1, row);

        add(new Label("Map Data Cache"), 0, row += 2);
        mapDataLocation = new EnhancedTextField(control);
        mapDataLocation.setPrefColumnCount(40);
        add(mapDataLocation, 0, row += 1);
        Button findMapDataLocation = new BrowseButton(stage, directoryChooser, mapDataLocation);
        add(findMapDataLocation, 1, row);

        add(new Label("Raster Map Cache"), 0, row += 2);
        rasterMapLocation = new EnhancedTextField(control);
        rasterMapLocation.setPrefColumnCount(40);
        add(rasterMapLocation, 0, row += 1);
        Button findRmLocation = new BrowseButton(stage, directoryChooser, rasterMapLocation);
        add(findRmLocation, 1, row);

        add(new Label("Remote Control Location"), 0, row += 2);
        virtualControlLocation = new EnhancedTextField(control);
        virtualControlLocation.setPrefColumnCount(24);
        add(virtualControlLocation, 0, row += 1);
        Button findVcLocation = new BrowseButton(stage, directoryChooser, virtualControlLocation);
        add(findVcLocation, 1, row);
    }

    public WorkstationUI() {
    }

    @Override
    public void reset() {
        messageDirectoryLocation.clearEditedState();
        mapDataLocation.clearEditedState();
        rasterMapLocation.clearEditedState();
        virtualControlLocation.clearEditedState();
        fedName.clearEditedState();

    }

    @Override
    public String tabName() {
        return "Workstation";
    }
}
