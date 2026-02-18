import java.util.Scanner;
public class SecondLargest {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the number of elements: ");
        int n=scanner.nextInt();
        int[] arr=new int[n];
        System.out.println("Enter the elements: ");
        for (int i=0;i<n;i++){
            arr[i]=scanner.nextInt();
        }
        int largest=Integer.MIN_VALUE;
        int SecondLargest=Integer.MIN_VALUE;
        for (int i=0;i<n;i++){
            if (arr[i]>largest){
                SecondLargest=largest;
                largest=arr[i];
            }
            else if (arr[i]>SecondLargest && arr[i]!=largest){
                SecondLargest=arr[i];
            }
        }
        System.out.println("Second Largest Element: "+SecondLargest);
        scanner.close();
    }
}