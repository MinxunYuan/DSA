import java.util.Random;

public class QuickSort {
    private QuickSort() {
    }

    public static <E extends Comparable<E>> void sort(E[] arr) {
        sort(arr, 0, arr.length - 1);
    }

    private static <E extends Comparable<E>> void sort(E[] arr, int l, int r) {
        if (l >= r) return;
        int p = partition(arr, l, r);
        sort(arr, l, p - 1);
        sort(arr, l + 1, r);
    }

    /**
     * 将arr[l]设为标定点的快排，i在[l+1,r]
     * （所以说如果r是数组长度，然后选arr[r-1]作为标定点，那么i范围就是[l, r-1)
     * 保证arr[l..j] < v，arr[j+1..i] >= v
     *
     * @return 返回pivot的索引
     */
    private static <E extends Comparable<E>> int partition(E[] arr, int l, int r) {
        int p = (new Random()).nextInt(r - l + 1);
        swap(arr, l, p);
        // arr[l..j]<v ; arr[j+1..i]>v
        int j = l;
        for (int i = l + 1; i <= r; i++) {
            if (arr[i].compareTo(arr[l]) < 0) {
                // arr[i]比标定点小，让它跟中值swap一下，同时pivot右移
                // 因为swap过后arr[j]是原来的arr[i]，必>arr[l]，直接++即可
                j++;
                swap(arr, i, j);
            }
        }
        // 最后把l位置和j位置元素交换，l j指针不换
        swap(arr, l, j);
        return j;
    }

    private static <E> void swap(E[] arr, int i, int j) {
        E tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }
}
