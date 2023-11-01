import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        // put your code here
        StackWithMax s = new StackWithMax();

        Scanner scanner = new Scanner(System.in);

        int numOfOperations = scanner.nextInt();

        for (int i = 0; i < numOfOperations; i++) {
            String operation = scanner.next();
            if (operation.equals("push")) {
                int v = scanner.nextInt();
                s.push(v);
            } else if (operation.equals("pop")) {
                s.pop();
            } else if (operation.equals("max")) {
                System.out.println(s.getMax());
            }
        }
    }
}

class StackWithMax {
    Deque<Integer> stack = new ArrayDeque<>();
    Deque<Integer> trackStack = new ArrayDeque<>();

    void push(int x) {
        stack.push(x);
        if (stack.size() == 1) {
            trackStack.push(x);
            return;
        }

        if (x > trackStack.peek()) {
            trackStack.push(x);
        } else {
            trackStack.push(trackStack.peek());
        }
    }

    int getMax() {
        return trackStack.peek();
    }

    void pop() {
        stack.pop();
        trackStack.pop();
    }

}