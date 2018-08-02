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
package visualorchestrator;

import java.beans.XMLEncoder;
import java.io.BufferedOutputStream;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author wbeebe
 */
public class ProjectFileManager {
    public void writeToFile(List<Activity> list, String file) {
        BufferedOutputStream outStream = null;
        XMLEncoder xmle;
        try {
            outStream = new BufferedOutputStream(new FileOutputStream(file));
            xmle = new XMLEncoder(outStream);
            list.forEach((activity) -> {
                xmle.writeObject(activity);
            });
        }
        catch (IOException ioe) {
            System.err.println("Visual Orchestrator: Error opening save file: " + file);
            System.err.println(ioe.toString());
        }
        catch (NoSuchElementException nse) {
            System.err.println("Visual Orchestrator: Invalid object to save.");
            System.err.println(nse.toString());
        }
        finally {
            try {
                if (outStream != null) outStream.close();
            }
            catch (IOException ioe) {
                System.err.println("Visual Orchestrator: Error closing file: " + file);
                System.err.println(ioe.toString());
            }
        }
    }

    public List<Activity> readFromFile(String file) {
        List<Activity> list = new ArrayList<>();
        ObjectInputStream inputStream = null;

        try {
            inputStream = new ObjectInputStream(new FileInputStream(file));
        } catch (FileNotFoundException ex) {
            System.err.println("File not found: " + file);
            Logger.getLogger(ProjectFileManager.class.getName()).log(Level.SEVERE, null, ex);
            return list;
        } catch (IOException ex) {
            System.err.println("Error opening file: " + file);
            Logger.getLogger(ProjectFileManager.class.getName()).log(Level.SEVERE, null, ex);
            return list;
        }

        boolean loading = true;

        while (loading) {
            try {
                Activity activity = (Activity) inputStream.readObject();
                list.add(activity);
            } catch (EOFException eof) {
                // Clumsy way to reach end of file.
                loading = false;
            } catch (ClassNotFoundException cnf) {
                System.err.println("Object creation failed.");
                Logger.getLogger(ProjectFileManager.class.getName()).log(Level.SEVERE, null, cnf);
                loading = false;
            } catch (IOException ioe) {
                System.err.println("Error reading file: " + file);
                Logger.getLogger(ProjectFileManager.class.getName()).log(Level.SEVERE, null, ioe);
                loading = false;
            } finally {
                try {
                    inputStream.close();
                } catch (IOException ioe) {
                    System.err.println("Error closing file: " + file);
                    Logger.getLogger(ProjectFileManager.class.getName()).log(Level.SEVERE, null, ioe);
                }
            }
        }

        return list;
    }
}
