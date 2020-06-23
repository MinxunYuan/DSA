
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        // 作业1测试数据：
//        Stack<Integer> stack = new Stack<>(15);
//        for (int i = 0; i < 20; i++) {
//            stack.push(i);
//            System.out.println(stack);
//        }
//        for (int i = 0; i < 16; i++) {
//            stack.pop();
//            System.out.println(stack);
//        }
//        stack.setNull();
//        System.out.println(stack);

//        playStack();
//        System.out.println(isSymmetricalStr("abcecba"));
        int[] arr = {123,45,132,65,324,564,1243,25,11111};
        selectionSort(arr);
    }

    public static void selectionSort(int[] arr){
//        外层(最后一次end=1进入循环，然后end=1，begin=1，满足条件进入循环，比较begin[0]和begin[1])最后一次比较
        for(int end = arr.length-1; end>0;end--){
            // 内层，找到那个最大的对应的index
            int maxIndex = 0;
            for(int begin = 1;begin<=end;begin++){
//            选出最大的，默认arr[begin-1]是最大的
                if(arr[begin]>arr[maxIndex]){
                    maxIndex = begin;
                }
            }
            int temp = arr[maxIndex];
            arr[maxIndex] = arr[end];
            arr[end] = temp;
        }
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i]+" ");
        }
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
