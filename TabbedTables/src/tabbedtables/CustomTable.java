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

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author William
 */
public class CustomTable extends JPanel {
    private JSplitPane splitPane;
    private JPanel rightDetail = null;
    private JLabel rightText;

    public CustomTable(TableDataProvider tableData) {
        super(new GridLayout(1,0,4,4));
        init(tableData);
    }

    private void init(TableDataProvider tableData) {
        JTable table = new JTable(new TableModel(tableData.getColumnNames(), tableData.getTableData())) {
            //
            // A simple renderer to render every odd row in the table as
            // a color other than white, or the default background color.
            //
            @Override
            public Component prepareRenderer(TableCellRenderer renderer, int row, int col) {
                Component component = super.prepareRenderer(renderer, row, col);
                //
                // Alternate row color
                //
                if (!isRowSelected(row))
                    component.setBackground(row % 2 == 0 ? getBackground() : Color.decode("0xEED8AE"));
                return component;
            }
        };

        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        table.setFillsViewportHeight(true);
        table.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        table.setAutoCreateRowSorter(true);
        table.getTableHeader().setReorderingAllowed(false);
        table.setGridColor(Color.decode("0xEED8AE"));

        // Look for a double mouse click to select a given cell in the table.
        //
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent event) {
                if (event.getClickCount() == 2) {
                    JTable table = (JTable)event.getSource();
                    int row = table.getSelectedRow();
                    int col = table.getSelectedColumn();

                    // If we haven't added the right detail pane yet, add it
                    // and make the split pane's divider wide enough to
                    // easily drag. This will honor the user's arbitrary
                    // width setting for any following detail clicks.
                    //
                    if (splitPane.getRightComponent() == null) {
                        splitPane.setRightComponent(rightDetail);
                        splitPane.setDividerSize(4);
                    }

                    // Simple detailed view of a given row.
                    // Don't do like I did the first time and get cell values
                    // from the table model, especially after a sort. 
                    // The table view is where you want to get detailed data.
                    //
                    rightText.setText("<html><h1>Detailed Information</h1>" +
                            "<h2>Row clicked:" + Integer.toString(row) + "</h2>" +
                            "<h2>Column clicked: " + Integer.toString(col) + "</h2>" +
                            "<h3>Column 1: " + table.getValueAt(row, 0) + "</h3>" +
                            "<h3>Column 2: " + table.getValueAt(row, 1) + "</h3>" +
                            "<h3>Column 3: " + table.getValueAt(row, 2) + "</h3>" +
                            "<h3>Column 4: " + table.getValueAt(row, 3) + "</h3>" + 
                            "</html>");
                }
            }
        });

        //JTableHeader tableHeader = table.getTableHeader();
        //tableHeader.setReorderingAllowed(false);

        // Ceate the split pane, but DON'T ADD the right detail panel. We'll
        // add it when someone first double-clicks on a table row.
        //
        splitPane = new JSplitPane(
                JSplitPane.HORIZONTAL_SPLIT, 
                new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED), null);
        splitPane.setDividerSize(0);
        Component add = add(splitPane);

        rightDetail = new JPanel(new BorderLayout());
        JPanel closeRightTop = new CloseControl("Placeholder text...", splitPane);
        rightDetail.add(closeRightTop, BorderLayout.PAGE_START);
        rightText = new JLabel();
        rightText.setVerticalAlignment(SwingConstants.TOP);
        rightDetail.add(rightText, BorderLayout.CENTER);
    }
}
