# Task Manager Project Documentation

This document explains the core Java concepts integrated into the Task Manager application: **Enums**, **Annotations**, **File Handling**, and **Serialization**. It also highlights where and how these concepts were applied to the codebase.

---

## 1. Enum Types and Usage

### What are Enums?
An `enum` (enumeration) is a special Java type used to define collections of constants. They are strongly typed, which means they provide compile-time safety and prevent invalid values from being assigned. 

### Changes in the Codebase
Previously, the `Priority` of a task was stored as a raw `String` (e.g., `"HIGH"`, `"MEDIUM"`, `"LOW"`), and we had to rely on `if-else` or manual exceptions to validate them. We replaced this with a dedicated Enum.

**1. Creating the Enum (`Priority.java`):**
```java
public enum Priority {
    HIGH, MEDIUM, LOW
}
```

**2. Integrating Enum into `Task.java`:**
The constructor no longer throws an `InvalidTaskException` for invalid priorities, because the Java compiler enforces that only `Priority.HIGH`, `Priority.MEDIUM`, or `Priority.LOW` can be passed.

```diff
- private String priority;
+ private Priority priority;

- public Task(int id, String title, String priority) throws InvalidTaskException {
-     if (!priority.equals("HIGH") && !priority.equals("MEDIUM") && !priority.equals("LOW")) {
-         throw new InvalidTaskException("Invalid priority");
-     }
+ public Task(int id, String title, Priority priority) {
      this.id = id;
      this.title = title;
      this.priority = priority;
  }
```

---

## 2. Built-in and Custom Annotations

### What are Annotations?
Annotations tag methods, classes, and fields with extra metadata. This metadata can be processed at compile-time (like `@Override`) or at runtime using Java Reflection (like custom tracing logs).

### Changes in the Codebase

**1. Custom Annotation (`LogExecution.java`):**
We created a custom runtime annotation which can be placed on methods to describe what they are executing.
```java
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME) // Available at runtime for Reflection
@Target(ElementType.METHOD)         // Only applicable to Methods
public @interface LogExecution {
    String value() default "Executing method...";
}
```

**2. Applying Annotations in `TaskService.java`:**
We decorated existing methods with built-in (`@Deprecated`, `@SuppressWarnings`) and custom (`@LogExecution`) annotations.

```java
    // Custom Annotation: Used to store metadata about the method's purpose
    @LogExecution("Adding a new task")
    public void addTask(Task task) {
        tasks.add(task);
    }

    // Built-in Annotation: Marks the method as outdated. The compiler will warn developers not to use it.
    @Deprecated
    public void showAll() {
        tasks.forEach(System.out::println);
    }

    // Built-in Annotation: Tells the compiler to ignore specific warnings (e.g., unchecked type casts during Deserialization)
    @SuppressWarnings("unchecked")
    @LogExecution("Loading tasks from file")
    public void loadFromFile(String filename) { ... }
```

**3. Parsing Annotations at Runtime (`Main.java`):**
We used **Java Reflection** to detect if a method has an annotation attached, and if so, printed its value.
```java
Method m = TaskService.class.getMethod("addTask", Task.class);
if (m.isAnnotationPresent(LogExecution.class)) {
    LogExecution log = m.getAnnotation(LogExecution.class);
    System.out.println("Method addTask() has @LogExecution: " + log.value());
}
```

---

## 3. Serialization and File Handling

### What is Serialization?
**Serialization** is the process of converting an object's state into a byte stream, so it can be saved to a file or sent over a network. **Deserialization** is the reverse process: reading the byte stream from a file to reconstruct the Java object.

### Changes in the Codebase

**1. Enabling Serialization (`Task.java`):**
To serialize instances of customized classes, they must implement the `java.io.Serializable` marker interface.
```diff
+ import java.io.Serializable;

- public class Task {
+ public class Task implements Serializable {
+    private static final long serialVersionUID = 1L;
     private int id;
     // ... rest of the code
```

**2. Implementing File Handling (`TaskService.java`):**
We used `FileOutputStream` and `ObjectOutputStream` to save our Collections (`List<Task>` and `Set<Integer>`) directly into a binary file (`tasks.ser`). We used `FileInputStream` and `ObjectInputStream` to read them back.

**Saving to a File:**
```java
    @LogExecution("Saving tasks to file")
    public void saveToFile(String filename) {
        // Try-with-resources automatically closes the streams
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            oos.writeObject(tasks);           // Serializes the Task List
            oos.writeObject(completedTasks);  // Serializes the Completed Task Set
            System.out.println("Tasks and completion states saved successfully to " + filename);
        } catch (IOException e) {
            System.out.println("Error saving tasks: " + e.getMessage());
        }
    }
```

**Loading from a File:**
```java
    @SuppressWarnings("unchecked")
    @LogExecution("Loading tasks from file")
    public void loadFromFile(String filename) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            tasks = (List<Task>) ois.readObject();           // Deserializes to Task List
            completedTasks = (Set<Integer>) ois.readObject();// Deserializes to Completed Task Set
            System.out.println("Tasks and completion states loaded successfully from " + filename);
        } catch (FileNotFoundException e) {
            System.out.println("Save file not found. Starting fresh.");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error loading tasks: " + e.getMessage());
        }
    }
```
