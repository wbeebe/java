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

import javax.swing.JComponent;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;

/**
 *
 * @author William
 */
public class TabbedContainer extends JTabbedPane {
    private int lastActiveTab = 0;

    public TabbedContainer() {
        init();
    }

    private void init() {
        addChangeListener((ChangeEvent event) -> {
            JTabbedPane source = (JTabbedPane)event.getSource();
            if (getTabCount() == 0) return;
            if (lastActiveTab >= getTabCount()) lastActiveTab = getTabCount() - 1;
            if (source.getTabComponentAt(lastActiveTab) == null) return;
            ((CloseControl)source.getTabComponentAt(lastActiveTab)).setButtonEnabled(false);
            lastActiveTab = source.getSelectedIndex();
            ((CloseControl)source.getTabComponentAt(lastActiveTab)).setButtonEnabled(true); 
        });
    }

    public void addTable(String tabTitle, JComponent table) {
        int lastTabCount = getTabCount();
        addTab(tabTitle, table);
        CloseControl ctc = new CloseControl(this);
        setTabComponentAt(getTabCount() - 1, ctc);
        setSelectedIndex(getTabCount() - 1);

        if (lastTabCount > 0) {
            lastActiveTab = lastTabCount;
        }
    }
}
