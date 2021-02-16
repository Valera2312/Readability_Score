import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String a = scanner.nextLine();
        int one = 0;
        int two = 0;

        for (int i = 0; i < a.length(); i++ ){

           int u = Character.getNumericValue(a.charAt(i));

            if(i < a.length() / 2){
               one += u;
            }else{
                two += u;
            }

        }
        if(one == two){
            System.out.println("Lucky");
        }else{
            System.out.println("Regular");
        }
    }
}