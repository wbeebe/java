/*
 *
 */
package genericconfigurationtool;

import javafx.css.PseudoClass;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

/**
 *
 * @author wbeebe
 */
public class EnhancedTextField extends TextField {
    private String backup = new String();
    private boolean modified = false;
    private final AppControl control;
    private final PseudoClass modClass =
            PseudoClass.getPseudoClass("modifiedtext");

    private void init() {
        textProperty().addListener((observable, oldValue, newValue) -> {
            // An empty call, for something I thought I wanted at one time,
            // and that I might want to add back in in the future.
            // I don't want to have to hunt this down again.
        });

        addEventFilter(KeyEvent.KEY_PRESSED, event -> {
            if (event.getCode() == KeyCode.ESCAPE) {
                setText(backup);
            }
        });

        focusedProperty().addListener((observable, oldValue, newValue) -> {
            checkIfModified(!newValue);
        });
    }

    /*
    * Called if focus changes. Since it can be called both when the control
    * goes into focus as well as out, we need to pass a true boolean for when
    * we loose focus (goes out of focus). That's when to check for any
    * modifications and to act accordingly.
    */
    private void checkIfModified(boolean outOfFocus) {
        if (! outOfFocus) return;

        if (getText().length() > 0 && !getText().equals(backup)) {
            backup = getText();
            modified = true;
            pseudoClassStateChanged(modClass, true);
            control.wasEdited();
        }
    }

    public void clearEditedState() {
        modified = false;
        pseudoClassStateChanged(modClass, false);
    }

    public EnhancedTextField(AppControl control) {
        this.control = control;
        init();
    }

    public EnhancedTextField(AppControl control, String initData) {
        setText(initData);
        this.control = control;
        this.backup = initData;
        init();
    }
}
