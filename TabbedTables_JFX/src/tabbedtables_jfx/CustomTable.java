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

import javafx.geometry.Insets;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.BorderPane;

/**
 * A control that displays a split pane with a table on the left side and a text
 * area on the right. The control is set up with data rows in the table on the
 * left. When a user clicks any row of data, the table will create a detail text
 * area and display it on the right side of the split pane.
 * 
 * @author wbeebe
 */
public class CustomTable extends BorderPane {
    SplitPane splitPane;
    TableView tableView;
    BorderPane rightPane;

    CustomTable() {
        setPadding(new Insets(4,4,4,4));
        this.tableView = new TableView();
        tableView.setEditable(false);
        this.splitPane = new SplitPane();

        var leftPane = new BorderPane();
        var leftScroll = new ScrollPane();
        leftScroll.setFitToHeight(true);
        leftScroll.setFitToWidth(true);
        leftPane.setCenter(leftScroll);

        /*
        * Build up the table by first creating the column header.
        * Add the column title from the array of column names in StaticTestData.
        * Then associate the data identifier for each column by modifying the
        * column header name. For example:
        *
        * "Column 1" -> "column_1"
        *
        * This is what the line header.toLowerCase().replace(' ','_')) is doing.
        *
        * This is used to name the getters and setters in the data model class.
        * For example:
        *
        * "Column 1" -> "column_1" -> getColumn_1() and setColumn_1(...)
        */
        for ( String header : StaticTestData.columnNames) {
            var column = new TableColumn(header);
            column.setCellValueFactory(
                new PropertyValueFactory<>(header.toLowerCase().replace(' ', '_'))
            );
            tableView.getColumns().add(column);
            tableView.getSortOrder().add(column);
        }

        tableView.setItems(StaticTestData.getRowData());
        tableView.setRowFactory(tv -> {
            TableRow<RowData> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (!row.isEmpty() && event.getButton() == MouseButton.PRIMARY
                        && event.getClickCount() == 2) {
                    RowData clickedRow = row.getItem();
                    displayRowData(clickedRow);
                }
            });
            
            return row;
        });

        leftScroll.setContent(tableView);
        rightPane = new BorderPane();
        //var rightButton = new Button("right");
        //rightPane.setCenter(rightButton);

        splitPane.getItems().add(leftPane);
        splitPane.getItems().add(rightPane);
        splitPane.setDividerPositions(new double[]{1.0});

        setCenter(splitPane);
    }
    
    /*
    * Creates a text pane with the row data selected by a double click and places
    * it in the right side of the split pane. Resizes the split pane to show the
    * data.
    */
    private void displayRowData(RowData data) {
        var text = new TextArea();
        text.setEditable(false);
        text.appendText("Row clicked.\n\n");
        text.appendText(String.format(" Col 1 %s\n", data.getColumn_1()));
        text.appendText(String.format(" Col 2 %s\n", data.getColumn_2()));
        text.appendText(String.format(" Col 3 %s\n", data.getColumn_3()));
        text.appendText(String.format(" Col 4 %s\n", data.getColumn_4()));
        rightPane.setCenter(text);
        splitPane.setDividerPositions(new double[]{0.6, 0.4});
    }
}
