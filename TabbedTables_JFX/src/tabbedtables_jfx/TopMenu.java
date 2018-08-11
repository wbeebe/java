/*
 * Copyright (c) 2018 William H. Beebe, Jr.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package tabbedtables_jfx;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.stage.Stage;

/**
 * The application's top level menu bar. All actions and menus are instantiated
 * here. Default actions for everything are created, and there are helper
 * functions to set them to something custom based on initial need or context
 * changes.
 * 
 * The getters and setters for MenuAction are for (possible) context setting.
 * For example, the Exit menu action might be changed to reflect internal changes
 * that might need to be performed before the application fully exits. You can
 * always build in logic to test if something changed before exiting within a
 * single exit procedure, but then you'd have to have access to all application
 * resources. Replaceable MenuActions are for trying to have as much separation
 * as reasonably possible.
 * 
 * @author wbeebe
 */
public class TopMenu extends MenuBar {
    Stage stage;
    MenuAction exitAction;
    MenuAction aboutAction;

    public TopMenu(Stage stage) {
        this.stage = stage;
        this.exitAction = new DefaultExitAction();
        this.aboutAction = new DefaultAboutAction(this.stage);

        Menu fileMenu = new Menu("File");
        MenuItem exitMenuItem = new MenuItem("Exit");
        exitMenuItem.setAccelerator(new KeyCodeCombination(KeyCode.F4, KeyCombination.ALT_DOWN));
        exitMenuItem.setOnAction((ActionEvent ae) -> { exitAction.doAction(); });
        fileMenu.getItems().addAll(new SeparatorMenuItem(), exitMenuItem);

        Menu helpMenu = new Menu("Help");
        MenuItem aboutMenuItem = new MenuItem("About");
        aboutMenuItem.setOnAction((ActionEvent ae) -> { aboutAction.doAction(); });
        helpMenu.getItems().addAll(new SeparatorMenuItem(), aboutMenuItem);

        getMenus().addAll(fileMenu, helpMenu);
    }

    /**
     * Set the Exit menu action to do an action when invoked.
     * 
     * @param action - Custom Exit action instance derived from MenuAction.
     */
    public void setExitAction(MenuAction action) {
        if (action != null) {
            this.exitAction = action;
        }
    }

    public MenuAction getExitAction() { return this.exitAction; }

    /**
     * Set the About menu action to do an action when invoked.
     * 
     * @param action - Custom About action instance derived from MenuAction.
     */
    public void setAboutAction(MenuAction action) {
        if (action != null) {
            this.aboutAction = action;
        }
    }

    public MenuAction getAboutAction() { return this.aboutAction; }
}

/**
 * Default main menu exit action. Calls Platform Exit to exit application.
 * 
 * @author wbeebe
 */
class DefaultExitAction implements MenuAction {
    @Override
    public void doAction() {
        Platform.exit();
    }
}

/**
 * Default main menu About action. A wrapper around invoking an instance of
 * AboutDialog.
 * 
 * @author wbeebe
 */
class DefaultAboutAction implements MenuAction {
    Stage stage;

    public DefaultAboutAction(Stage st) {
        this.stage = st;

    }

    @Override
    public void doAction() {
        var dialog = 
            new AboutDialog(stage, "About Tabbed Tables", "Tabbed Tables V 0.1");
        dialog.showAndWait();
    }
}
