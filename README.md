# java

A "sketchbook" collection of Java projects originally created in NetBeans. These are not complete applications,
but coding experiments trying out various capabilities and features of Java 8 and JavaFX. They'll start and they
illustrate some feature or collection of features, but they're not all that useful in and of themselves.

Note that as of August 2018 all the applications have been migrated to Java 10.0.2.
Note that as of December 2018 all applications have been migrated back to Java 8, 1.8.0_192 OpenJDK
I have moved back to Amazon Corretto Preview 2, https://aws.amazon.com/corretto/

## Available Applications

+ BorderPaneExample - JavaFX application. Creates a resizable canvas that is drawn upon and updates as
the Pane parent is resized.
[[https://github.com/wbeebe/java/blob/master/Images/BorderPaneExample/MainScreen.png]]

+ GenericConfigurationTool - JavaFX application. Various controls organized into a tabbed interface.
CSS is used to style the controls. The first tab, About, shows how to display OS type, memory, and Java location and version.

+ VisualOrchestrator - JavaFX application. Attempted to create a drag-and-drop visual task orchestration tool. 
You were meant to drag and drop what were supposed to be structures that represented a task to perform. All that's been achieved so
far is the skeleton idea for the visual look and feel, complete with curved lines with arrowheads (edges) to connect
the nodes. The Start circle on the far left will accept a click to turn red with a "Stop". Except for moving it here
and porting it to Java 10, nothing has been done on it for years. In addition, it has a bug with how the canvas is drawn.
That's why, in part I wrote BorderPaneExample, to try and fix my canvas problem.

  + UPDATE Sept 2018 - The nodes can now be moved around. The arrows are currently gone (commented out for now).
I'm about ready to move the arrows (edges) to using round endpoints. The look I'm after is from Blender's node editor.

+ TabbedTable - Standard Java JFC application. One of the first of my experimental applications. I wrote it with the idea
it would be a database event browsing tool. One of my programs captured every RTI event that flowed within an HLA simulation
so that it could be mined later. This was the visual foundation for that tool. It would present results in an Excel-like table, and
the user could click on a single line and have it appear on the same tab to the right in expanded form. From there, you could,
if the data supported it, click on another item and continue until you either found what you were looking for, or gave up.
All of that is there including my special 'X' close button. I have it here for historical reasons, with the idea to port it
to JavaFX. When run it uses the platform look-and-feel, which on macOS is quite ugly.

+ TabbedTable_JFX - Just what it appears to be. A complete rewrite of TabbedTable but in JavaFX. Same functionality, but a lot
less code. I thought I was being clever in TabbedTable, but writing this over a weekend proved me wrong.

+ Images - A directory of screen captures of the various applications as they execute. This should give you some idea of what
these applications can do and what they look like.

## License

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
