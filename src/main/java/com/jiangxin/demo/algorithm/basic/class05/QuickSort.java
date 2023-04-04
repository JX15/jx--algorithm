package com.jiangxin.demo.algorithm.basic.class05;

import com.jiangxin.demo.algorithm.utils.MyArrayUtil;

import java.util.Stack;

/**
 * @author: xin.jiang.cn
 * @date: 2023年04月04日 20:19:05
 * @description: 快速排序递归版&迭代版
 */
public class QuickSort {

    private static void swap(int[] arr,int i,int j){
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }


    /**
     * 荷兰国旗问题
     *  选择数组中最右边的数作为参考值，小于参考值的数放左边，等于的放中间，大于的放右边
     * 思路
     *  1.划分一个小于区右边界，开始位置在 L - 1
     *  2.划分一个大于区左边界，开始位置在 R
     *  3.让指针指向数组0位置，从0开始遍历数组,终止条件为指针和大于区左边界相等
     *      3.1.如果这个数小于参考值，当前值和 小于区右边界+1 位置的值交换，小于区右边界+1，指针+1
     *      3.2.如果这个数等于参考值，指针+1
     *      3.3.如果这个数大于参考值，当前值和 大于区左边界-1 位置的值交换，大于区左边界-1，指针不动
     *  4.将R位置的参考值和大于区左边界的值交换
     *  5.返回相等区左右边界数组
     */
    public static int[] netherLandsFlag(int[] arr,int L,int R){
        if (L > R){
            return new int[]{-1,-1};
        }
        if (L == R){
            return new int[]{L,L};
        }
        int less = L - 1;
        int more = R;
        int index = L;
        while (index < more){
            if (arr[index] < arr[R]){
                swap(arr, index++, ++less);
            }else if (arr[index] == arr[R]){
                index++;
            }else{
                swap(arr, index, --more);
            }
        }
        swap(arr, more, R);
        return new int[]{less + 1,more};
    }

    /**
     * 快速排序：递归版本
     *  1.递归终止条件：L >= R
     *  2.递归步骤：
     *      2.0.随机从[L,R]取一个数作为参考值，放在数组最右边,来达到最优期望时间复杂度O(N * logN)
     *      2.1.对当前数组做荷兰国旗划分
     *      2.2.对小于区[L,相等区左边界 - 1]重复当前操作
     *      2.3.对大于区[相等区右边界 + 1，R]重复当前操作
     * 扩展：快速排序的额外空间复杂度 O(logN)
     */
    public static void quickSortRecursion(int[] arr){
        if (arr == null || arr.length < 2){
            return;
        }
        process(arr,0,arr.length - 1);
    }

    private static void process(int[] arr, int L, int R) {
        //存在荷兰国旗划分完区域后，小于区数的数量<=1/大于区数的数量<=1，所以终止条件是L>=R
        if (L >= R){
            return;
        }
        //随机从[L,R]取一个数作为参考值，放在数组最右边
        swap(arr, L + (int)(Math.random() * (R - L + 1)), R);
        int[] equals = netherLandsFlag(arr, L, R);
        process(arr, L,equals[0] - 1);
        process(arr, equals[1] + 1,R);
    }


    /**
     * 快速排序：非递归版本
     *  1.先对整个数组做一次荷兰国旗划分
     *  2.需要进行排序的小于区和大于区入栈
     *  3.只要栈不为空，则仍然存在小于区/大于区
     *  4.每次处理时新增的小于区和大于区都要入栈
     */
    public static void quickSortIteration(int[] arr){
        if (arr == null || arr.length < 2){
            return;
        }
        int N = arr.length;
        swap(arr, (int)(Math.random() * N), N - 1);
        int[] equals = netherLandsFlag(arr, 0, N - 1);
        Stack<Op> stack = new Stack<>();
        stack.push(new Op(0, equals[0] - 1));
        stack.push(new Op(equals[1]+1,N - 1));
        while (!stack.empty()){
            Op op = stack.pop();
            if (op.L < op.R){
                swap(arr, op.L + (int)(Math.random() * (op.R - op.L + 1)), op.R);
                equals = netherLandsFlag(arr, op.L, op.R);
                stack.push(new Op(op.L,equals[0] - 1));
                stack.push(new Op(equals[1] + 1, op.R));
            }
        }
    }

    /**
     * 辅助类，表示要处理的是什么范围上的排序
     */
    public static class Op{
        public int L;
        public int R;
        public Op(int left,int right){
            L = left;
            R = right;
        }
    }

    public static void main(String[] args) {
        int maxTime = 500000;
        for (int i = 0; i < maxTime; i++) {
            System.out.println("当前正在进行的轮次：" + i);
            int[] randomArray = MyArrayUtil.getRandomArray(100, 100);
            int[] copyArray = MyArrayUtil.copyArray(randomArray);
            quickSortRecursion(randomArray);
            quickSortIteration(copyArray);
            for (int j = 0; j < randomArray.length; j++) {
                if (randomArray[j] != copyArray[j]){
                    System.out.println("出错啦！！！");
                    MyArrayUtil.printArray(randomArray);
                    MyArrayUtil.printArray(copyArray);
                    return;
                }
            }
        }
        System.out.println("成功！");
    }
}
