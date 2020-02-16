
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        // 作业1测试数据：
        Stack<Integer> stack = new Stack<>(10);
        for (int i = 0; i < 10; i++) {
            stack.push(i);
            System.out.println(stack);
        }
        stack.setNull();
        System.out.println(stack);

        playStack();
        System.out.println(isSymmetricalStr("abcecba"));
    }

    // 作业3
    public static boolean isSymmetricalStr(String string) {
        // 空串返回false
        int strLength = string.length();
        if(strLength==0)
            return false;
        int halfLength = string.length() >> 1;

        Stack<Character> stack = new Stack<>(20);
        for (int i = 0; i < halfLength; i++) {
            stack.push(string.charAt(i));
        }
        // string.length()是奇数的话就+1
        for (int i = ((strLength & 1) == 0) ? halfLength : halfLength + 1; i < string.length(); i++) {
            if (stack.pop() != string.charAt(i))
                return false;
        }

        return true;
    }


    // 作业2
    public static void playStack() {
        System.out.println("请输入字符串：");
        boolean flag = true;
        Scanner sc = new Scanner(System.in);
        while (flag) {
            // 输入一个String
            Stack<Character> stringStack = new Stack<>(10);
            String str = sc.next();
            for (int i = 0; i < str.length(); i++) {
                char c = str.charAt(i);
                switch (c) {
                    case '\n':
                        stringStack.reverseStack();
                        break;
                    case '<':
                        stringStack.pop();
                        break;
                    case '@':
                        stringStack.setNull();
                        break;
                    case '#':
                        flag = false;
                        break;
                    default:
                        stringStack.push(c);
                }
            }
            stringStack.reverseStack();
        }
    }
}
