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

import javafx.beans.property.SimpleStringProperty;

/**
 * This is the data model class for creating row data in the TableView used
 * in CustomTable. The getter names correspond to the column text names as
 * defined in StaticTestData's String array of columnNames:
 * 
 *  "Column 1" -> "column_1" -> getColumn_1()
 * 
 * It has to be this way for the PropertyValueFactory to do its job of filling
 * all the rows with data. And if you want to do more work, then you'll use those
 * data identifiers again.
 *
 * @author wbeebe
 */
public class RowData {
    private final SimpleStringProperty col1;
    private final SimpleStringProperty col2;
    private final SimpleStringProperty col3;
    private final SimpleStringProperty col4;

    public RowData(String col1, String col2, String col3, String col4 ) {
        this.col1 = new SimpleStringProperty(col1);
        this.col2 = new SimpleStringProperty(col2);
        this.col3 = new SimpleStringProperty(col3);
        this.col4 = new SimpleStringProperty(col4);
    }

    public RowData(String[] colData) {
        this(colData[0], colData[1], colData[2], colData[3]);
    }

    public String getColumn_1() { return col1.get(); }
    public String getColumn_2() { return col2.get(); }
    public String getColumn_3() { return col3.get(); }
    public String getColumn_4() { return col4.get(); }
    
    @Override
    public String toString() {
        return String.format(
            " col1 %s, col2 %s, col3 %s, col4 %s",
                col1.get(), col2.get(), col3.get(), col4.get()
        );
    }
}
