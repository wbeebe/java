# java

A "sketchbook" collection of Java projects originally created in NetBeans. These are not complete applications,
but a experiments trying out various capabilities and features of Java and JavaFX. They'll start and they
illustrate some feature or collection of features.

+ BorderPaneExample - JavaFX application. Creates a resizable canvas that is drawn upon and updates as
the Pane parent is resized.

+ GenericConfigurationTool - JavaFX application. Various controls organized into a tabbed interface.
CSS is used to style the controls. The first tab, About, shows how to display OS type, memory, and Java location and version.

+ VisualOrchestrator - JavaFX application. Attempt to create a drag-and-drop visual task orchestration tool. That is,
you drag and drop what were supposed to be structures that represented a task to perform. All that's been achieved so
far is the skeleton idea for the visual look and feel, complete with curved lines with arrowheads (edges) to connect
the nodes. The Start circle on the far left will accept a click to turn red with a "Stop". Except for moving it here
and porting it to Java 10, nothing has been done on it for years. In addition, it has a bug with how the canvas is drawn.
That's why, in part I wrote BorderPaneExample, to try and fix my canvas problem.

+ TabbedTable - Standard Java JFC application. One of the first of my experimental applications. I wrote it with the idea
it would be a database event browsing tool. One of my programs captured every RTI event that flowed within the HLA simulation
so that it could be mined later. This was the foundation for that tool. It would present results in an Excel-like table, and
the user could click on a single line and have it appear on the same tab to the right in expanded form. From there, you could,
if the data supported it, click on another item and continue until you either found what you were looking for, or gave up.
All of that is there including my special 'X' close button. I have it here for historical reasons, with the idea to port it
to JavaFX. When run it uses the platform look-and-feel, which on macOS is quite ugly.

+ TabbedTable_JFX - Just what it appears to be. A complete rewrite of TabbedTable but in JavaFX. Same functionality, but a lot
less code. I thought I was being clever in TabbedTable, but writing this over a weekend proved me wrong.
