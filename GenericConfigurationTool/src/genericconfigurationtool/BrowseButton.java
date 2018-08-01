/*
Copyright (c) 2018 William H. Beebe, Jr.

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
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
