
/**
 * 用数组封装成一个Stack类
 */
public class Stack<E> {
    public static final int DEFAULT_MAX_SIZE = 10;
    private int size; // 栈中现在有的元素的个数，new出来之后默认是0
    private int capacity; // 数组的容量，万一到时候是动态的
    private E[] arr; // 栈的底层数组

    public Stack(int capacity) {
        // 用户传来的capacity要是小于默认大小就用默认的，否则用用户的
        this.capacity = (capacity < DEFAULT_MAX_SIZE) ? DEFAULT_MAX_SIZE : capacity;
        arr = (E[]) new Object[this.capacity];
    }

    public Stack() {
        this(DEFAULT_MAX_SIZE);
    }


    // 栈中元素的个数
    public int getSize() {
        return size;
    }

    // 栈顶元素对应的数组元素索引
    public int top() {
        return size - 1;
    }

    // 看看栈是不是空的
    public boolean isEmpty() {
        return size == 0;
    }

    // 看看栈是不是满的
    public boolean isFull() {
        // return top()-1 == capacity
        return size == capacity;
    }

    // 看看栈顶元素（不让它出来）
    public E getTop() {
        if(isEmpty())
            throw new IndexOutOfBoundsException("栈为空");
        return arr[top()];
    }

    // 将栈顶元素出栈并返回其值,注意下溢的问题
    public E pop() {
        if(isEmpty())
            throw new IndexOutOfBoundsException("栈为空");
        return arr[--size];
    }

    // 将元素压入栈，注意维护size和上溢
    public void push(E e) {
        // 如果栈（的底层数组）不是满的，就入栈
        if (isFull())
            throw new IndexOutOfBoundsException("栈已满," + e + " push失败");
        arr[size++] = e;
    }

    // 把栈设为null
    public void setNull() {
        size = 0;
        /*
        这里把size弄成0，那么top就是-1，元素确实还在内存里面
        但是此时我们想push，pop，会不满足isEmpty()或者isFull()的条件，抛出异常
        而没有必要新开一个引用
         */
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append("Stack: ");
        res.append('[');
        for (int i = 0; i < getSize(); i++) {
            res.append(arr[i]);
            // i如果不是指向最后一个元素
            if (i != getSize()-1) {
                res.append(", ");
            }
        }
        res.append("] <-top"); // 数组末尾是栈顶
        return res.toString();
    }

    public void reverseStack() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < getSize(); i++) {
            sb.append(arr[i]);
        }
        System.out.println(sb);
    }
}
