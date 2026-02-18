import java.util.Scanner;
public class MaximumSumMatrix {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter the no of Rows: ");
        int r=sc.nextInt();
        System.out.println("Enter the no of Columns");
        int c=sc.nextInt();
        int [][] matrix = new int[r][c];
        System.out.println("Enter the elements: ");
        for (int i=0;i<r;i++){
            for (int j=0;j<c;j++){
                matrix[i][j]=sc.nextInt();
            }
        }
        int max=Integer.MIN_VALUE;
        int indx=-1;
        for (int i=0;i<r;i++){
            int sum=0;
            for (int j=0;j<c;j++){
                sum+=matrix[i][j];
            }
            if (sum>max){
                max=sum;
                indx=i;
            }
        }
        System.out.println("Maximum Sum ="+max);
        System.out.println("Index = "+indx);
        sc.close();
    }
}
