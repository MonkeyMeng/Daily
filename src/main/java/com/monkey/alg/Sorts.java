package com.monkey.alg;

/**
 * @Author: yangsimeng
 * @Description: 各种排序算法实现
 * @Date: 14:45 2019-12-31
 **/
public class Sorts {

    public static void main(String[] args) {

        String data = "10,3,2,9,20,45,30,1,19,99,10";
        System.out.println("冒泡排序");
        print(bubble(toIntArray(data)));
        System.out.println("选择排序");
        print(selection(toIntArray(data)));
        System.out.println("插入排序");
        print(insertion(toIntArray(data)));

        System.out.println("希尔排序");
        print(shell(toIntArray(data)));

        System.out.println("快速排序");
        int[] quickSortArray = toIntArray(data);
        print(quickSort(quickSortArray, 0, quickSortArray.length - 1));

        System.out.println("归并排序");
        int[] mergeSortArray = toIntArray(data);
        print(mergeSort(mergeSortArray, new int[mergeSortArray.length], 0, mergeSortArray.length - 1));

        System.out.println("堆排序");
        print(heapSort(toIntArray(data)));

    }

    /**
     * 冒泡排序 O(n2)
     *
     * @param data
     * @return
     */
    private static int[] bubble(int[] data) {
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data.length - i - 1; j++) {
                if (data[j] > data[j + 1]) {
                    int temp = data[j + 1];
                    data[j + 1] = data[j];
                    data[j] = temp;
                }
            }
        }
        return data;

    }

    /**
     * 选择排序O(n2)
     *
     * @param data
     * @return
     */
    private static int[] selection(int[] data) {
        for (int i = 0; i < data.length; i++) {
            int minIndex = i;
            for (int j = i; j < data.length; j++) {
                if (data[j] < data[minIndex]) {
                    minIndex = j;
                }
            }
            if (i != minIndex) {
                swap(data, i, minIndex);
            }

        }
        return data;
    }

    /**
     * 插入排序O(n2) 将前i-1个元素看成是有序的 从i开始和有序数据比较并交换
     *
     * @param data
     * @return
     */
    private static int[] insertion(int[] data) {
        for (int i = 1; i < data.length; i++) {
            int j;
            if (data[i] < data[i - 1]) {
                int temp = data[i];
                for (j = i - 1; j >= 0 && temp < data[j]; j--) {
                    data[j + 1] = data[j];
                }

                data[j + 1] = temp;
            }
        }
        return data;
    }

    /**
     * 希尔排序 是插入排序的一种特殊变种
     * 先让各个组之间基本有序
     *
     * @param data
     * @return
     */
    private static int[] shell(int[] data) {
        int gap = data.length / 2;
        while (gap > 0) {
            for (int i = gap; i < data.length; i++) {
                if ((i - gap) >= 0 && data[i] < data[i - gap]) {
                    int temp = data[i];
                    int j;
                    for (j = i - gap; j >= 0 && temp < data[j]; j = j - gap) {
                        data[j + gap] = data[j];
                    }
                    data[j + gap] = temp;
                }
            }
            gap = gap / 2;
        }
        return data;
    }

    /**
     * 快速排序
     *
     * @param data
     * @param start
     * @param end
     * @return
     */
    private static int[] quickSort(int[] data, int start, int end) {

        int low = start;
        int high = end;
        int target = data[low];
        if (low < high) {
            while (low < high) {
                while (low < high && data[high] >= target) {
                    high--;
                }
                data[low] = data[high];
                while (low < high && data[low] < target) {
                    low++;
                }
                data[high] = data[low];
            }
            data[low] = target;
            quickSort(data, start, low - 1);
            quickSort(data, low + 1, end);
        }
        return data;
    }

    /**
     * 归并排序 先分治 再合并
     *
     * @param data
     * @return
     */
    private static int[] mergeSort(int[] data, int[] tmpArray, int start, int end) {
        if (start >= end) {
            return data;
        }
        int mid = (start + end) / 2;
        //一直分 递归分
        mergeSort(data, tmpArray, start, mid);
        mergeSort(data, tmpArray, mid + 1, end);
        // 从底下往上合并
        merge(data, tmpArray, start, mid, end);
        return data;
    }


    private static void merge(int[] data, int[] tempArray, int start, int mid, int end) {
        //通过mid分成两个数组
        int i = start;
        int j = mid + 1;
        int k = 0;
        while (i <= mid && j <= end) {
            if (data[i] <= data[j]) {
                tempArray[k++] = data[i++];

            } else {
                tempArray[k++] = data[j++];

            }
        }

        while (i <= mid) {
            tempArray[k++] = data[i++];
        }
        while (j <= end) {
            tempArray[k++] = data[j++];
        }
        //ti
        for (int l = 0; l < k; l++) {
            data[start + l] = tempArray[l];
        }
    }

    private static int [] heapSort(int[] data) {
        for (int i = data.length / 2 - 1; i >= 0; i--) {
            heapAdjust(data, i, data.length);
        }
        for (int i = data.length - 1; i >= 0; i--) {
            swap(data, 0, i);
            heapAdjust(data, 0, i);
        }
        return data;

    }

    //构造第i个元素
    private static void heapAdjust(int[] data, int i, int length) {
        int parent = i;
        int max = i;
        int leftChild = 2 * i + 1;
        int rightChild = 2 * i + 2;
        //找到左右两个孩子大值
        if (leftChild < length && data[leftChild]>data[max]){
            max = leftChild;
        }
        if(rightChild<length&&data[rightChild]>data[max]){
            max = rightChild;
        }
        if(max!=i){
            swap(data, max, i);
            heapAdjust(data, max,length);
        }


    }


    private static void print(int[] data) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < data.length; i++) {
            sb.append(data[i]).append(",");
        }
        System.out.println(sb);
    }

    private static int[] toIntArray(String data) {
        String[] datas = data.split(",");
        int[] result = new int[datas.length];
        for (int i = 0; i < datas.length; i++) {
            result[i] = Integer.parseInt(datas[i]);
        }
        return result;
    }


    private static void swap(int[] data, int i, int j) {
        int temp = data[i];
        data[i] = data[j];
        data[j] = temp;
    }

}
