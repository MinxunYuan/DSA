
/**
 * 用数组封装成一个Stack类
 */
public class Stack<E> {
    public static final int DEFAULT_MAX_SIZE = 5;
    private int size; // 栈中现在有的元素的个数，new出来之后默认是0
    private int capacity; // 数组的容量，万一到时候是动态的
    private E[] arr; // 栈的底层数组
    private int top = size-1;
    // top指着栈顶位置的元素（栈没元素的时候指向-1，但是Java中无法指向arr[-1]，进栈top+1,出栈top-1

    public Stack(int capacity) {
        // 用户传来的capacity要是小于默认大小就用默认的，否则用用户的
        this.capacity = (capacity < DEFAULT_MAX_SIZE) ? DEFAULT_MAX_SIZE : capacity;
        arr = (E[]) new Object[this.capacity];
    }

    public Stack() {
        this(DEFAULT_MAX_SIZE);
    }

    /*
    栈的底层数组扩容：
    每次扩容为原来capacity的2倍
    记得更新一下capacity，要么再次进入扩容的时候，拿的capacity是最初的capacity
    把原来的数组元素copy到新数组
    最后让this.arr引用我们新new出来的数组
     */
    protected E[] enlarge(){
        int newCapacity = this.capacity<<1;
        E[] newArr = (E[])new Object[newCapacity];
        this.capacity = newCapacity;
        for (int i = 0; i < getSize(); i++) {
            newArr[i] = arr[i];
        }
        return newArr;
    }

    /*
    底层数组的缩容
    每次缩小到原来的1.5倍,即right shift然后自增
     */
    public E[] shrink(){
        int newCapacity = getSize()>>1;
        E[] newArr = (E[])new Object[newCapacity];
        this.capacity = newCapacity;
        for (int i = 0; i<getSize(); i++){
            newArr[i] = arr[i];
        }
        return newArr;
    }

    // 栈中元素的个数
    public int getSize() {
        return size;
    }

    // 看看栈是不是空的
    public boolean isEmpty() {
        return top==-1;
    }

    // 看看栈是不是满的
    public boolean isFull() {
        // return size==0;
        return top+1 == capacity;
    }

    // 看看栈顶元素（不让它出来）
    public E getTop() {
        if(isEmpty())
            throw new IndexOutOfBoundsException("栈为空");
        else
            return arr[top];
    }

    // 将栈顶元素出栈并返回其值,注意下溢的问题
    public E pop() {
//        if(isEmpty())
//            throw new IndexOutOfBoundsException("栈为空");
//        如果size是capacity的1/4就缩容
        if(size<=1/4*this.capacity){
            arr = shrink();
        }

        top--;
        return arr[--size];
    }

    // 将元素压入栈，注意维护size和上溢
    public void push(E e) {
        // 如果栈（的底层数组）不是满的，就入栈，但是这里有动态扩容之后，就不涉及到满不满的问题了
//        if (isFull())
//            throw new IndexOutOfBoundsException("栈已满," + e + " push失败");

        // 先看看size，如果size==capacity，就扩容
        if(size==capacity)
            this.arr = enlarge();

        arr[size++] = e;
        top++;
    }

    // 把栈设为null
    public void setNull() {
        size=0;
        /*
        这里把size弄成0，那么top就是-1，元素确实还在内存里面
        但是此时我们想push，pop，会不满足isEmpty()或者isFull()的条件，抛出异常
        而没有必要新开一个引用
         */
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append("Stack: "+"size="+size+", capacity="+capacity+" ");
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

    /*
    以LIFO的形式打印栈（就是最后进来的先打印……）
     */
    public void reverseStack() {
        // 用迭代器也可以
        for(int i = arr.length-1; i>=0; i--){
            System.out.print(arr[i]+" ");
        }
        System.out.println();
    }
}
