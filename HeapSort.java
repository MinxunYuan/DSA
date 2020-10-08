
/**
 * @author YMX
 */
public class HeapSort {
    public static void heapSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        // 原地建堆
        for (int i = arr.length - 1; i >= 0; i--) {
            // arr.length即堆底层大小
            heapify(arr, i, arr.length);
        }
        int heapSize = arr.length;
        // 一开始建起来的时候肯定可以保证arr[0]是topPriority
        // 一个不断把arr[0]移到arr[len-1]然后范围缩小的一个过程
        swap(arr, 0, --heapSize);
        // arr的物理大小还是arr.length，只是逻辑上地只在0~heapSize-1这个范围内hpf
        while (heapSize > 0) {
            heapify(arr, 0, heapSize);
            swap(arr, 0, --heapSize);
        }
    }

    /**
     * 对arr[]的从index~heapSize范围做下沉操作
     * 如果下标从0开始拿i的左孩子就是i<<1|1 从1开始就是i<<1
     */
    public static void heapify(int[] arr, int index, int heapSize) {
        // index即某个子树的root,heapSize相当于是底层arr的长度
        // 默认先跟left比
        int left = index << 1 | 1;
        while (left < heapSize) {
            // 主函数传进来heapSize==arr.length, 如果heapSize==arr.length-1，那这里就是left<=heapSize
            // 先找大儿子
            int largest = left + 1 < heapSize && arr[left + 1] > arr[left] ? left + 1 : left;
            // 结果跟arr[index]比，看看要不要下沉
            largest = arr[index] > arr[largest] ? index : largest;
            // arr[index]最大那就别动了
            if (largest == index) {
                break;
            }
            // 下沉
            swap(arr, index, largest);
            // 只swap了值，没swap指针
            // 看看能否继续往下走
            index = largest;
            left = index << 1 | 1;
        }
    }

    private static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }
