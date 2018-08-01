/*
Copyright (c) 2018 William H. Beebe, Jr.

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
 */
package genericconfigurationtool;

import javafx.geometry.Insets;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 * @author wbeebe
 */
public class RoleabilityUI extends GridPane implements TabUI {

    @Override
    public void init(Stage stage, AppControl control) {
        setHgap(5);
        setVgap(5);
        setPadding(new Insets(0, 5, 0, 10));
    }
    
    public RoleabilityUI() {
    }

    @Override
    public void reset() {
    }

    @Override
    public String tabName() {
        return "Roleability";
    }
}
