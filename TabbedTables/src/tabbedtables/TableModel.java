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

import javax.swing.table.AbstractTableModel;

/**
 *
 */
public class TableModel extends AbstractTableModel {
    private final String[] columnNames;
    private Object[][] data = null;

    public TableModel(final String[] columnNames, final Object[][] initialData) {
        this.columnNames = columnNames;
        data = initialData;
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public int getRowCount() {
        return data != null ? data.length : 0 ;
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }

    @Override
    public Object getValueAt(int row, int col) {
        return data != null ? data[row][col] : null ;
    }

    @Override
    public Class getColumnClass(int col) {
        return getValueAt(0, col).getClass();
    }

    /**
     * We want to make the tables read-only. We'll use a complex dialog to 
     * do any editing.
     * 
     * @param row
     * @param col
     * @return 
     */
    @Override
    public boolean isCellEditable(int row, int col) {
        return false;
    }

    @Override
    public void setValueAt(Object value, int row, int col) {
        if (data != null) {
            data[row][col] = value;
            fireTableCellUpdated(row, col);
        }
    }
}
