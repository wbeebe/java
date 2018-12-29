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

import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.DecimalFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Stream;
import javafx.scene.control.TextArea;

/**
 *
 * @author wbeebe
 */
public class RuntimeEnvironment extends TextArea {
    public RuntimeEnvironment() {
        init();
    }
    
    private void init() {
        setEditable(false);
        setMinSize(32, 24);
        clear();
        appendText("Java Version " + System.clearProperty("java.version")+ "\n");
        String work = System.getProperty("java.home");
        appendText("Java Home: " + work + "\n\n");
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
                    Logger.getLogger(
                        RuntimeEnvironment.class.getName()).log(Level.SEVERE,
                        null, ex
                        );
                }

                if (srName.length() > 0) {
                    osName = "OS: " + srName.trim();
                }
            }
            else if (Files.isReadable(mintPath)) {
                try {
                    info = Files.lines(mintPath);
                } catch (IOException ex) {
                    Logger.getLogger(
                        RuntimeEnvironment.class.getName()).log(Level.SEVERE,
                        null, ex
                        );
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
                    Logger.getLogger(
                        RuntimeEnvironment.class.getName()).log(Level.SEVERE,
                        null, ex
                        );
                }
                
                srName = getQuotedValue(info, "DISTRIB_DESCRIPTION");
                if (srName.length() > 0) {
                    osName = "OS: " + srName;
                }
            }
            
            appendText(osName);
            work = System.getProperty("os.version");
            appendText("\nKernel " + work + "\n\n");
        }
        else {
            appendText("OS: " + work);
            work = System.getProperty("os.version");
            appendText(" Version " + work + "\n\n");
        }

        /*
         * WARNING: Manipulation of this bean deep within Java environment.
         */
        com.sun.management.OperatingSystemMXBean bean =
            (com.sun.management.OperatingSystemMXBean)ManagementFactory.getOperatingSystemMXBean();

        Runtime runtime = Runtime.getRuntime();

        appendText("Total system memory: " + format(bean.getTotalPhysicalMemorySize()) + "\n");
        appendText("Total memory available to JVM: " + format(runtime.maxMemory()) + "\n");
        appendText("Total used by JVM: " + format(runtime.totalMemory()) + "\n");
        appendText("Free memory in JVM: " + format(runtime.freeMemory()));

        positionCaret(0);
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
}
