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
package tabbedtables;

import java.awt.Color;
import java.util.Enumeration;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.UIDefaults;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 *
 * @author William
 */
public class TabbedTables {

    private static void dumpLookAndFeelDefaults() {
        UIDefaults defaults = UIManager.getLookAndFeelDefaults();
        Enumeration enums = UIManager.getLookAndFeelDefaults().keys();
        while (enums.hasMoreElements()) {
            Object key = enums.nextElement();
            Object val = defaults.get(key);
            System.out.println(key.toString() + " = " + (val != null ? val.toString() : "NULL"));
        }
    }

    private static void createGUI() {
        try {
            // Set system Java L&F (Windows for Windows, Gnome for Java...)
            //
            UIManager.setLookAndFeel(
                    UIManager.getSystemLookAndFeelClassName());
        } 
        catch (UnsupportedLookAndFeelException | 
                ClassNotFoundException | 
                InstantiationException | 
                IllegalAccessException exception) {
            // silently handle exception
        }

        // Turn off the dashed line around the active tab. Make it transparent.
        //
        UIManager.put("TabbedPane.focus", new Color(0,0,0,0));

        //dumpLookAndFeelDefaults();

        TabbedContainer tabbedContainer = new TabbedContainer();
        tabbedContainer.addTable("Tab 1", new CustomTable(new StaticTestData()));
        tabbedContainer.addTable("Tab 2", new CustomTable(new StaticTestData()));
        tabbedContainer.addTable("Tab 3", new CustomTable(new StaticTestData()));
        tabbedContainer.addTable("Tab 4", new CustomTable(new StaticTestData()));

        JFrame frame = new JFrame("Test tabs and tables");

        frame.setContentPane(tabbedContainer);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setVisible(true);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            createGUI();
        });
    }
}
