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

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * This class began life providing the same data for the JDK Table models used
 * to create equivalent table views. Life was simpler then, in that all you
 * needed to do was create the first two data structures (columnNames and
 * rowData) and pass them into the Table methods to populate the table.
 * 
 * Now for JavaFX more work must be done. That's why we have the getRowData()
 * method. It takes that second double String array and puts it into an
 * ObservableList that is then used by the JavaFX table to populate the table
 * view with test data.
 * 
 * Note that everything is static. Also note that we check to see if rawData
 * is empty and if it is populate it then, but only once. All subsequent calls
 * simply return a reference to the now-filled rawData.
 * 
 * @author William
 */
public class StaticTestData {
    public static String[] columnNames = {
        "Column 1", "Column 2", "Column 3", "Column 4"
    };

    public static ObservableList<RowData> rowData = 
        FXCollections.observableArrayList();

    public static String[][] sampleData =
    {
        { "sample 1",  "sample e", "sample 8", "sample h" },
        { "sample 2",  "sample d", "sample 7", "sample i" },
        { "sample 3",  "sample c", "sample 6", "sample j" },
        { "sample 4",  "sample b", "sample 5", "sample k" },
        { "sample 5",  "sample e", "sample 4", "sample l" },
        { "sample 6",  "sample f", "sample 3", "sample m" },
        { "sample 7",  "sample g", "sample 2", "sample n" },
        { "sample 8",  "sample h", "sample 1", "sample o" },
        { "sample 10", "sample i", "sample 0", "sample p" },
        { "sample 11", "sample j", "sample",   "sample q" },
        { "sample", "sample", "sample", "sample" },
        { "sample", "sample", "sample", "sample" },
        { "sample", "sample", "sample", "sample" },
        { "sample", "sample", "sample", "sample" },
        { "sample", "sample", "sample", "sample" },
        { "sample", "sample", "sample", "sample" },
        { "sample", "sample", "sample", "sample" },
        { "sample", "sample", "sample", "sample" },
        { "sample", "sample", "sample", "sample" },
        { "sample", "sample", "sample", "sample" },
        { "sample", "sample", "sample", "sample" },
        { "sample", "sample", "sample", "sample" },
        { "sample", "sample", "sample", "sample" },
        { "sample", "sample", "sample", "sample" },
        { "sample", "sample", "sample", "sample" },
        { "sample", "sample", "sample", "sample" },
        { "sample", "sample", "sample", "sample" },
        { "sample", "sample", "sample", "sample" },
        { "sample", "sample", "sample", "sample" },
        { "sample", "sample", "sample", "sample" },
        { "sample", "sample", "sample", "sample" },
        { "sample", "sample", "sample", "sample" },
        { "sample", "sample", "sample", "sample" },
        { "sample", "sample", "sample", "sample" },
        { "sample", "sample", "sample", "sample" },
        { "sample", "sample", "sample", "sample" },
        { "sample", "sample", "sample", "sample" },
        { "sample", "sample", "sample", "sample" },
        { "sample", "sample", "sample", "sample" },
        { "sample", "sample", "sample", "sample" },
        { "last sample", "last sample", "last sample", "last sample" },
    };

    /**
     * Build up an ObservableList from the String[][] sampleData above. Build it
     * once if it's empty, else return it.
     * 
     * @return - ObservableList of sampleData
     */
    public static ObservableList getRowData() {
        if (rowData.isEmpty()) {
            for ( String[] data : sampleData ) {
                rowData.add(new RowData(data));
            }
        }
        
        return rowData;
    }
}
