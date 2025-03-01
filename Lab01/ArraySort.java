//Example 6.5
import java.util.Arrays;
import java.util.Scanner;
public class ArraySort {
    public static void main(String[] args) {
        Scanner scanner =new Scanner(System.in);
        System.out.print("Enter the size of the array: ");
        int N= scanner.nextInt();
        int[] Array=new int[N];
        int sum=0;
        for(int i=0;i<N;i++){
            Array[i]=scanner.nextInt();
            sum+= Array[i];
        }
        Arrays.sort(Array);
        double average = (double)sum/N;

        System.out.println("Sort array: "+ Arrays.toString(Array));
        System.out.println("Sum of arrays: "+sum);
        System.out.println("Average of arrays: "+average);
        scanner.close();
    }
}
