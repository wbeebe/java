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

/**
 *
 * @author William
 */
public class StaticTestData implements TableDataProvider {
    public static String[] columnNames = {
        "Column 1", "Column 2", "Column 3", "Column 4"
    };

    public static Object[][] sampleData =
    {
        { "sample 1", "sample e", "sample", "sample" },
        { "sample 2", "sample d", "sample", "sample" },
        { "sample 3", "sample c", "sample", "sample" },
        { "sample 4", "sample b", "sample", "sample" },
        { "sample 5", "sample a", "sample", "sample" },
        { "sample", "sample x", "sample", "sample" },
        { "sample", "sample y", "sample", "sample" },
        { "sample", "sample z", "sample", "sample" },
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
        { "sample", "sample", "sample", "sample" },
        { "sample", "sample", "sample", "sample" },
    };

    @Override
    public String[] getColumnNames() {
        return columnNames;
    }

    @Override
    public Object[][] getTableData() {
        return sampleData;
    }
    
}
