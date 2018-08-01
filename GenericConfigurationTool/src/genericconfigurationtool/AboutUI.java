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

import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.DecimalFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Stream;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 * @author wbeebe
 */
public class AboutUI extends GridPane implements TabUI {

    private String format(long num) {
        double val = (double)num;
        String suffix = "";

        if (val > 1024.0) {
            suffix = "Kb";
            val /= 1024.0;
        }
        if (val > 1024.0) {
            suffix = "Mb";
            val /= 1024.0;
        }
        if (val > 1024.0) {
            suffix = "Gb";
            val /= 1024.0;
        }
        
        DecimalFormat format = new DecimalFormat("#,###.##");
        return format.format(val) + suffix;
    }

    private String getQuotedValue(Stream<String> data, String key) {
        String value = "";
        if (data != null) {
            String[] items = data.toArray(String[]::new);

            for (String item: items) {
                if (item.contains(key)) {
                    String[] values = item.split("=");

                    if (values.length == 2) {
                        value = values[1].replace('"', ' ').trim();
                    }

                    break;
                }
            }
        }

        return value;
    }

    @Override
    public void init(Stage stage, AppControl control) {
        setHgap(5);
        setVgap(5);
        setPadding(new Insets(0, 5, 0, 10));
        
        add(new Label("Product:"), 0, 1);
        add(new Label("Generic Configuration Tool"), 1, 1);
        add(new Label("Release:"), 0, 2);
        add(new Label("..."), 1, 2);

        add(new Label("Runtime Environment"), 0, 5, 2, 1);
        TextArea textArea1 = new TextArea();
        textArea1.setEditable(false);
        textArea1.setMinSize(32, 12);
        add(textArea1, 0, 6, 8, 9);
        textArea1.clear();
        
        //String work = System.getProperty("java.version");
        //textArea1.appendText("Java Version: " + work + "\n");
        textArea1.appendText("Java Version: " + Runtime.version().toString() + "\n");
        String work = System.getProperty("java.home");
        textArea1.appendText("Java Home: " + work + "\n\n");
        work = System.getProperty("os.name");

        if (work.contains("Linux")) {
            String osName = "OS: Linux";
            // Look to see if it's RHEL or a RHEL derivative.
            //
            Path rhelPath = FileSystems.getDefault().getPath("/etc/system-release");

            // Look to see if it's Mint Linux
            //
            Path mintPath = FileSystems.getDefault().getPath("/etc/linuxmint/info");
            
            // Look to see if it's Ubuntu & others
            //
            Path uPath = FileSystems.getDefault().getPath("/etc/lsb-release");

            Stream<String> info = null;
            String srName = "";

            if (Files.isReadable(rhelPath)) {
                try {
                    srName = new String(Files.readAllBytes(rhelPath));
                } catch (IOException ex) {
                    Logger.getLogger(AboutUI.class.getName()).log(Level.SEVERE, null, ex);
                }

                if (srName.length() > 0) {
                    osName = "OS: " + srName.trim();
                }
            }
            else if (Files.isReadable(mintPath)) {
                try {
                    info = Files.lines(mintPath);
                } catch (IOException ex) {
                    Logger.getLogger(AboutUI.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                srName = getQuotedValue(info, "DESCRIPTION");
                if (srName.length() > 0) {
                    osName = "OS: " + srName;
                }
            }
            else if (Files.isReadable(uPath)) {
                try {
                    info = Files.lines(uPath);
                } catch (IOException ex) {
                    Logger.getLogger(AboutUI.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                srName = getQuotedValue(info, "DISTRIB_DESCRIPTION");
                if (srName.length() > 0) {
                    osName = "OS: " + srName;
                }
            }
            
            textArea1.appendText(osName);
            work = System.getProperty("os.version");
            textArea1.appendText("\nKernel " + work + "\n\n");
        }
        else {
            textArea1.appendText("OS: " + work);
            work = System.getProperty("os.version");
            textArea1.appendText(" Version " + work + "\n\n");
        }
        
        /*
         * WARNING: Manipulation of this bean deep within Java environment.
         */
        com.sun.management.OperatingSystemMXBean bean =
            (com.sun.management.OperatingSystemMXBean)ManagementFactory.getOperatingSystemMXBean();

        Runtime runtime = Runtime.getRuntime();

        textArea1.appendText("Total system memory: " + format(bean.getTotalPhysicalMemorySize()) + "\n");
        textArea1.appendText("Total memory available to JVM: " + format(runtime.maxMemory()) + "\n");
        textArea1.appendText("Total used by JVM: " + format(runtime.totalMemory()) + "\n");
        textArea1.appendText("Free memory in JVM: " + format(runtime.freeMemory()));
        
        textArea1.positionCaret(0);
    }

    AboutUI() {
    }

    @Override
    public void reset() {
    }

    @Override
    public String tabName() {
        return "About";
    }
}
