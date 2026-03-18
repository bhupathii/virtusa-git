Task Manager Project - Complete Architecture & Implementation Guide

This document explains the complete architecture of the task-manager project, demonstrating how various core Java concepts, object-oriented principles, and Java 8+ features are implemented throughout the codebase.

1. Project Motivation and Architecture

The task-manager is a console-based application designed to manage a list of tasks. The architecture follows a simple domain-service-repository pattern:

- Domain Models: Priority.java, Task.java, Schedulable.java, DeadlineTask.java. These represent the core entities (the data) of the application.
- Service Layer: TaskService.java. The engine of the application. It encapsulates the business logic, stream manipulations, multithreading, and data processing.
- Data Layer (Simulated): Repository.java to act as generic storage, supplemented by standard Java I/O built directly into TaskService for saving data across sessions (Serialization).
- Presentation/Entry Point: Main.java. Serves as the driver class to instantiate services and demonstrate all functionalities.

2. Detailed Code Breakdown & Java Concepts

2.1. The Domain Models (The Data)

Priority.java (Enums)
This is a simple enum containing HIGH, MEDIUM, and LOW. Enums restrict variables to a predefined set of constants, ensuring type safety so priorities can't be accidentally defined as an invalid string.

Task.java (Encapsulation & Nested Classes)
This is the base class representing a generic task. State fields (id, title, priority) are declared private (Encapsulation) and can only be accessed via public getters.
It implements Serializable, allowing Java to convert a Task object into a byte stream to be saved to a file.
It contains a static nested class named TaskBuilder. This implements the Builder Design Pattern, allowing you to create a Task step-by-step cleanly (e.g., new Task.TaskBuilder().setId(1)...).

Schedulable.java (Interfaces)
This is a contract interface that defines two methods: isOverdue() and delay(int days). Any class that implements this interface is forced to provide the concrete logic for these methods.

DeadlineTask.java (Inheritance & Polymorphism)
This class uses the extends keyword to inherit all properties from Task, but adds a specific LocalDate deadline field.
It uses implements Schedulable to fulfill the interface contract.
Polymorphism is demonstrated here: It overrides the toString() method of the parent Task class. At runtime, if a Task collection contains a DeadlineTask, Java dynamically knows to call this specific, overridden version of toString() rather than the parent's generic version.

2.2. The Storage Layer

Repository.java (Generics)
This class is defined using Generic Typing: class Repository<T>. The <T> stands for Type. This allows the repository to hold a list of any object type without explicitly knowing what that object is at compile-time. We instantiate it in Main using Repository<Task>, locking that specific instance into only accepting Tasks securely.

2.3. The Service Layer (The Engine)

TaskService.java (Collections, Streams, I/O, Strings)
This class holds the core business logic.
- Collections: It uses an ArrayList to store tasks (maintaining insertion order) and a HashSet to store the IDs of completed tasks (ensuring ultra-fast O(1) lookups and naturally preventing duplicate completions).
- Custom Annotation Processing: Methods here are tagged with @LogExecution. While this doesn't change the execution natively, it allows us to inspect the method at runtime via Reflection in Main.java to read metadata about what the method does.
- Exception Handling: The markCompleted() method throws an InvalidTaskException (a custom checked exception) if you attempt to complete a task that doesn't exist or is already completed.
- Java 8 Streams: Methods like showCompleted() and filterTasks() use the Streams API to process collections declaratively. For example, instead of writing an if-statement inside a manual for-loop, it uses .stream().filter(t -> ...).forEach(...).
- Functional Interfaces: It accepts Predicate<Task>, Function<Task, R>, Consumer<Task>, and Supplier<Task> as arguments. This advanced technique allows the caller (Main.java) to pass custom inline behavior (lambda expressions) directly into the processing methods.
- File I/O: saveToFile() and loadFromFile() use ObjectOutputStream and ObjectInputStream to save (serialize) the entire tasks List and completedTasks Set into a binary file (tasks.ser). This restores state natively across restarts.
- Strings & Arrays: generateReport() uses StringBuilder and StringBuffer to efficiently piece together a long textual string report without creating hundreds of temporary, wasteful string objects in memory.

2.4. Background Processing

BackupDaemon.java (Threading Basics)
This class implements Runnable, meaning it defines a set of work that can be executed on a separate, concurrent Thread.
- Thread Synchronization (wait / notify): When the Daemon starts, it calls taskService.waitForNewTask(). Inside TaskService, this method hits a lock.wait() statement, putting the Daemon thread to sleep indefinitely and yielding the CPU.
- Notification: Whenever addTask() is called on the main thread, it executes lock.notifyAll() inside a synchronized block. This wakes up the sleeping Daemon thread automatically, allowing it to print a simulated backup message before looping back to sleep.

2.5. The Entry Point

Main.java (Execution)
This is where everything comes together:
1. It instantiates the TaskService and starts the BackupDaemon Thread.
2. It uses the Builder to construct standard tasks, and creates one DeadlineTask, demonstrating polymorphism as they are all pushed to the same Service method organically.
3. It intentionally tests error handling by attempting to complete a known invalid Task ID inside a try/catch block.
4. It demonstrates inner classes contextually by:
  - Creating a Member Inner Class instance (TaskLogger).
  - Calling a method that defines a Local Inner Class (TaskSummary) entirely inside its execution scope.
  - Creating an Anonymous Inner Class (Comparator) inline to quickly sort the list of tasks.
5. It invokes the Java 8 Functional methods and passes specific Lambda expressions (e.g., t -> t.getPriority() == Priority.HIGH) into the system.
6. Finally, it triggers the Java I/O framework to save the current dataset to tasks.ser and exits safely.

3. JVM Internals & Memory Management (Day 21 & Day 22)

- Classloader: Loads classes dynamically (Task, TaskService, Main) into the runtime environment upon launch.
- JIT Compiler: Translates the repetitive Stream pipelines found in TaskService down into highly optimized machine code while the application is running, drastically speeding up repeat execution.
- Bytecode: All .java source files are compiled tightly into platform-agnostic .class files via the javac command.
- Memory Tracking: 
  - Stack: Stores simple primitives (like standard loop integrators) and all active method invocation frames (when Main calls TaskService.filterTasks, a frame is added to the stack).
  - Heap: All long-lived objects like the tasks ArrayList, specific Task instances, and the BackupDaemon thread reside here. When Task records are removed or if the program runs continuously, the Java Garbage Collector seamlessly sweeps through the heap, clearing out any unreferenced, detached objects automatically to prevent memory leaks.
