abstract class Device{
        abstract void start();
        void info(){
            System.out.println("This is a smart Device");
        }
    }
    class laptop extends Device{
        @Override
        void start(){
            System.out.println("This is a laptop");
        }
    }
    class mobile extends Device{
        @Override
        void start(){
            System.out.println("This is a Mobile");
        }
    }

public class SmartDevices {
    public static void main(String[] args) {
        Device d1=new laptop();
        Device d2=new mobile();
        d1.info();
        d1.start();
        System.out.println();
        d2.info();
        d2.start();

    }
    
    }

