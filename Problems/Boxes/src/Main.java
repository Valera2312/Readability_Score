import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[] a = new int[3];
        int[] b = new int[3];
        int countA = 0;
        int countB = 0;
        for(int i = 0; i < 3; i++){
            a[i] = scanner.nextInt();
        }
        for(int i = 0; i < 3; i++){
            b[i] = scanner.nextInt();
        }
        Arrays.sort(a);
        Arrays.sort(b);

        for(int i = 0 ; i < 3; i++){
            if(a[i] > b[i]){
                countA++;
            }
            else if(a[i] < b[i]){
                countB++;
            }
        }
        if(countA == 3){
            System.out.println("Box 1 > Box 2");
        }
        else if(countB == 3){
            System.out.println("Box 1 < Box 2");
        }
        else {
            System.out.println("Incompatible");
        }
    }
}