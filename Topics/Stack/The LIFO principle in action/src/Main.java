import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        // put your code here
        Scanner scanner = new Scanner(System.in);

        int inputSize = scanner.nextInt();


        Deque<Integer> stack = new ArrayDeque<>();

        int counter = 0;
        while(counter < inputSize) {
            stack.push(scanner.nextInt());
            counter++;
        }

        while (!stack.isEmpty()) {
            System.out.println(stack.pop());
        }
    }
}