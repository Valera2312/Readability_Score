import java.util.*;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int size = scanner.nextInt();
        int[] arr = new int[size];
        //int sum = 0;
        int count = 0;
        int[] ggg = new int[size];

        for(int i = 0; i < arr.length; i++) {
            arr[i] = scanner.nextInt();
        }
        for(int i = 0; arr.length - 1 > i ; i++){

            if(arr[i] < arr[i + 1]){

                count++;
            }
            else{
                count = 0;
            }

            ggg[i] = count;
        }
        OptionalInt max = Arrays.stream(ggg).max();

        System.out.println(max.getAsInt() + 1);

    }
}