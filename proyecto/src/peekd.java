import java.util.Stack;

public class peekd {
    
    public static void main(String[] args) {
        Stack<Integer> hola = new Stack<Integer>(); 

        System.out.println(hola.size());

        hola.add(2); 

        System.out.println(hola.peek());
        System.out.println(hola.pop());
    }
}
