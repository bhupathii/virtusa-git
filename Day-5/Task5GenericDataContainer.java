public class Task5GenericDataContainer {
    public static void main(String[] args) {
        DataContainer<Integer> intBox = new DataContainer<>();
        intBox.add(42);
        intBox.get();
        intBox.displayType();

        DataContainer<String> stringBox = new DataContainer<>();
        stringBox.add("hello");
        stringBox.get();
        stringBox.displayType();

        DataContainer<Student> studentBox = new DataContainer<>();
        studentBox.add(new Student("Asha", 92));
        studentBox.get();
        studentBox.displayType();
    }

    static class DataContainer<T> {
        private T item;

        void add(T item) {
            this.item = item;
        }

        T get() {
            return item;
        }

        void displayType() {
            System.out.println(item.getClass().getSimpleName());
        }
    }

    static class Student {
        private final String name;
        private final int marks;

        Student(String name, int marks) {
            this.name = name;
            this.marks = marks;
        }
    }
}
