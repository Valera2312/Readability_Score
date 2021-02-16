import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String size = scanner.nextLine();
        String arr = scanner.nextLine();
        String result = arr.replaceAll("\\s", "");
        int count = 0;
        String n = scanner.nextLine();
        for (int i = 0; i < result.length(); i++) {
            if(result.charAt(i) == n.charAt(0)){
                count++;
            }
        }
        System.out.println(count);
    }

}