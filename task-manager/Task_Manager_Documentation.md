# Task Manager Project - Complete Architecture & Implementation Guide

This document explains the complete architecture of the `task-manager` project, demonstrating how various core Java concepts, object-oriented principles, and Java 8+ features are implemented throughout the codebase.

## 1. Project Motivation and Architecture

The `task-manager` is a console-based application designed to manage a list of tasks. The architecture follows a simple domain-service-repository pattern:

- **Domain Models**: `Task.java`, `DeadlineTask.java`, `Priority.java`. These represent the core entities of the application.
- **Service Layer**: `TaskService.java`. Encapsulates the business logic, stream manipulations, threading, and data processing.
- **Data Layer (Simulated)**: `Repository.java` (Generic storage) and standard Java I/O built into `TaskService` (Serialization).
- **Presentation/Entry Point**: `Main.java`. Serves as the driver class to demonstrate all functionalities.

## 2. Core Java Basics (Day 2)

- **Syntax, Data Types, Operators**: Demonstrated extensively across all classes. `int` for IDs, `String` for titles, `boolean` for completion tracking. Standard operators are used for iteration and condition checks.
- **JVM Architecture**: The JVM interprets the compiled `.class` files. When we run `java Main`, the classloader loads `Main.class` and all referenced dependencies into memory, the bytecode verifier ensures safety, and the execution engine runs the program.

## 3. Static Keywords, Packages & Access Modifiers (Day 3)

- **Access Modifiers**: `private` (e.g., `tasks` list in `TaskService`) ensures encapsulation. `public` methods expose the API. `protected` isn't explicitly needed here but could be applied if we split domains into different packages.
- **Static**: `private static final long serialVersionUID` in `Task` and `DeadlineTask` ensures proper versioning during serialization. `TaskBuilder` is a `public static class`, meaning it doesn't require an instance of `Task` to be instantiated.

## 4. Arrays & Strings (Day 4)

- **Strings, StringBuilder, StringBuffer**: The `generateReport()` method in `TaskService.java` demonstrates both `StringBuilder` (faster, non-thread-safe) and `StringBuffer` (thread-safe, synchronized) to construct complex string outputs efficiently instead of continuous string concatenation.
- **1D Arrays**: `getTaskTitlesArray()` uses Streams to map a list of objects down to an array of just their string titles `String[]`.
- **2D Arrays**: `getTaskMatrix()` constructs a `String[][]` containing IDs and Title/Priority mappings, simulating tabular data mapping.

## 5. OOPs Fundamentals (Day 1)

- **Encapsulation**: State fields in `Task` (`id`, `title`, `priority`) are heavily encapsulated using `private` access modifiers and retrieved via public getters.
- **Abstraction**: `Repository<T>` abstractly defines a storage medium without worrying about specific data types until instantiated.
- **Inheritance & Polymorphism**: `DeadlineTask` inherits from `Task` using the `extends` keyword. It overrides `toString()`, showing runtime polymorphism.
- **Interfaces**: `Schedulable.java` acts as a contract. `DeadlineTask` `implements` it to define functionality like `delay()` and `isOverdue()`.

## 6. Java Collections & Generics (Day 5)

- **Generics**: `Repository<T>` allows saving any object type. In `TaskService`, `Function<Task, R>` maps a `Task` to any generic return type `R`.
- **Collections**: `List<Task>` handles ordered data, `Set<Integer>` manages unique completed IDs automatically preventing duplicates, and `Map<Priority, List<Task>>` creates groupings of objects based on an Enum key.

## 7. Exception Handling (Day 11)

- **Custom Exceptions**: `InvalidTaskException.java` is a checked exception extending `Exception`. It is explicitly caught in `Main.java` when attempting to complete task `ID 99`.
- **Try/Catch**: Utilized for File I/O operations and intercepting custom business logic exceptions safely to prevent application crashes.

## 8. Enums & Annotations (Day 12)

- **Enums**: `Priority.java` (HIGH, MEDIUM, LOW) restricts task urgency to predefined constants.
- **Annotations**: 
  - *Built-in*: `@SuppressWarnings("deprecation")`, `@Override`, `@Deprecated`.
  - *Custom*: `@LogExecution` is a retention policy `RUNTIME` annotation accessed via Reflection in `Main.java` to dynamically print metadata about executing methods.

## 9. Java I/O (Day 12)

- **Serialization**: `loadFromFile` and `saveToFile` in `TaskService` use `ObjectInputStream` and `ObjectOutputStream` to write the full state of `tasks` and `completedTasks` continuously to a binary file `tasks.ser`.

## 10. Java 8 Features (Day 13)

Implementation found natively in `TaskService` and called dynamically in `Main.java`:
- **Predicate**: Evaluating conditions (`t -> t.getPriority() == Priority.HIGH`).
- **Function**: Transforming object states (`t -> t.getTitle().toUpperCase()`).
- **Consumer**: Iterating actions over sequences (`t -> System.out.println(...)`).
- **Supplier**: Providing default values lazily for isolated `Task` lookups.
- **Streams Pipeline**: Heavy use of `.stream()`, `.filter()`, `.map()`, `.reduce()`, and `.collect()` to streamline data processing.

## 11. Threading Basics (Day 14)

- **Thread & Runnable**: `BackupDaemon` implements `Runnable` to do background work isolated from the main flow.
- **Synchronization**: `TaskService` uses `synchronized (lock)` surrounding critical sections.
- **Wait and Notify**: When `addTask` is called, it triggers `lock.notifyAll()`. `BackupDaemon` pauses via `lock.wait()` entirely efficiently until new tasks arrive to simulate a background data sink.

## 12. Inner Classes (Day 15)

Found explicitly across `Task` and `TaskService`:
1. **Static Nested**: `TaskBuilder` (Builder pattern).
2. **Member Inner**: `TaskLogger` (Accesses parent fields securely).
3. **Local Inner**: `TaskSummary` (Confined to function scope).
4. **Anonymous Inner**: Inline `Comparator` for sorting data visually on-the-fly.

## 13. JVM Internals & Memory Management (Day 21 & Day 22)

- **Classloader**: Loads classes dynamically (`Task`, `TaskService`, `Main`) into the runtime environment.
- **JIT Compiler**: Translates `TaskService` repetitive Stream pipelines down into highly optimized machine code during runtime execution.
- **Bytecode**: All `.java` files are compiled into platform-agnostic `.class` files.
- **Memory Tracking**: 
  - **Stack**: Stores primitive types (e.g., local loop integrators `i`, primitive `boolean` values for completed matching) and method frames for `filterTasks`, `mapTasks`, etc.
  - **Heap**: Long-lived objects like `tasks` `ArrayList`, `Task` instances, and static structures reside here. When `Task` records are removed or replaced over a session's extent, the **Garbage Collector** seamlessly clears up unused memory objects automatically. 
