public class Polymorphism {
    public static void main(String[] args) {
        Calculator calc=new Calculator();
        System.out.println(calc.add(2,3));
        Animal a1=new Dog();
        Animal a2=new Cat();
        a1.sound();
        a2.sound();
    }
}
