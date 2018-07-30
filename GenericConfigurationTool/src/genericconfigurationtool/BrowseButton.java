/*
 * 
 */
package genericconfigurationtool;

import java.io.File;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

/**
 * The BrowseButton class is a convenience class that wraps up common
 * functionality used to select a given directory.
 * 
 * @author wbeebe
 */
public class BrowseButton extends Button {
    private final Stage stage;
    private final DirectoryChooser directoryChooser;
    private final TextField tf;
    
    /**
     * 
     * @param stage - Application context stage
     * @param dc - Instance of DirectoryChooser to provide dialog
     * @param tf - Instance of unique TextField into which directory is placed.
     */
    public BrowseButton(Stage stage, DirectoryChooser dc, TextField tf) {
        this.stage = stage;
        this.directoryChooser = dc;
        this.tf = tf;
        setText("...");

        setOnAction((final ActionEvent ae) -> {
            File directory = directoryChooser.showDialog(stage);
            if (directory != null) {
                // System.out.println(directory.getAbsolutePath());
                tf.setText(directory.getAbsolutePath());
                tf.requestFocus();
            }
        });
    }    
}
