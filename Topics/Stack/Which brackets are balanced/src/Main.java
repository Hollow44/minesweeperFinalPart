import javax.swing.plaf.IconUIResource;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Objects;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        // put your code here
        Deque<String> stack = new ArrayDeque<>();
        Scanner scanner = new Scanner(System.in);

        String input = scanner.nextLine();

        boolean decision = false;

        for (int i = 0; i < input.length(); i++) {
            if (Objects.equals(input.charAt(i), '(') || Objects.equals(input.charAt(i), '{') || Objects.equals(input.charAt(i), '[')) {
                stack.push(String.valueOf(input.charAt(i)));
            } else {
                if (stack.isEmpty()) {
                    decision = false;
                    break;
                }
                if (String.valueOf(input.charAt(i)).equals(")") && stack.peek().equals("(")) {
                    stack.pop();
                    decision = true;
                } else if (String.valueOf(input.charAt(i)).equals("}") && stack.peek().equals("{")) {
                    stack.pop();
                    decision = true;
                } else if (String.valueOf(input.charAt(i)).equals("]") && stack.peek().equals("[")) {
                    stack.pop();
                    decision = true;
                }
            }
        }

        if (input.length() < 2) {
            decision = false;
            System.out.println(decision);
        } else if (!stack.isEmpty()) {
            decision = false;
            System.out.println(decision);
        }
        else {
            System.out.println(decision);
        }
    }
}