

class Problem {
    public static void main(String[] args) {
        if (args[0].equals("mode")) {
            System.out.print(args[1]);
        } else if (args[2].equals("mode")) {
            System.out.print(args[3]);
        } else {
            System.out.print("default");
        }
    }
}