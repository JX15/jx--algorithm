package com.jiangxin.demo.algorithm.basic.class07;

import com.jiangxin.demo.algorithm.utils.MyArrayUtil;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * @author: xin.jiang.cn
 * @date: 2023年04月11日 20:35:01
 * @description:
 * 给定很多线段，每个线段都有两个数组[start, end]， 表示线段开始位置和结束位置，左右都是闭区间 规定：
 * 1）线段的开始和结束位置一定都是整数值
 * 2）线段重合区域的长度必须>=1
 * 返回线段最多重合区域中，包含了几条线段
 */
public class MaxSuperpositionLineCount {

    private static class Line{
        private int start;
        private int end;
        public Line(int start,int end){
            this.start = start;
            this.end = end;
        }
    }

    /**
     * 题解：
     *  1.先对线程按start从小到大排序
     *  2.准备一个小根堆，用于存储线段的end值
     *  3.遍历排序后的线段，如果小根堆中有小于该线段的start值，将该值弹出（循环）
     *  4.将该线段的end值加入小根堆，此时小根堆的size就是穿过该线段start位置重合线段的数量
     *  5.比较得出最大值
     */
    public static int calculate(Line[] lines){
        Arrays.sort(lines, Comparator.comparingInt(line -> line.start));
        Queue<Integer> minHeap = new PriorityQueue<>();
        int maxCount = 0;
        for (int i = 0; i < lines.length; i++) {
            while (!minHeap.isEmpty() && minHeap.peek() <= lines[i].start ){
                minHeap.poll();
            }
            minHeap.add(lines[i].end);
            maxCount = Math.max(maxCount, minHeap.size());
        }
        return maxCount;
    }

    /**
     * 暴力解法：
     * 1.求出所有线段最小的start和最大的end
     * 2.算出经过每个x.5的线段数，取最大值
     */
    public static int violentMethod(Line[] lines){
        int minValue = Integer.MAX_VALUE;
        int maxValue = Integer.MIN_VALUE;
        for (int i = 0; i < lines.length; i++) {
            minValue = Math.min(minValue, lines[i].start);
            maxValue = Math.max(maxValue, lines[i].end);
        }
        int maxCount = 0;
        for (float i = minValue + 0.5F;i < maxValue;i++){
            int tempCount = 0;
            for (int j = 0; j < lines.length; j++) {
                if (lines[j].start < i && lines[j].end >i){
                    tempCount++;
                }
            }
            maxCount = Math.max(maxCount, tempCount);
        }
        return maxCount;

    }

    private static Line[] generateLines(int maxLength,int minStart,int maxEnd){
        int length = (int)(Math.random() * maxLength) + 1;
        Line[] lines = new Line[length];
        for (int i = 0; i < length; i++) {
            int start = minStart + (int)(Math.random() * (maxEnd - minStart + 1));
            int end = minStart + (int)(Math.random() * (maxEnd - minStart + 1));
            if (start == end){
                end = start + 1;
            }
            lines[i] = new Line(Math.min(start, end), Math.max(start, end));
        }
        return lines;
    }

    public static void main(String[] args) {

        int maxTimes = 100000;
        int maxLength = 20;
        int minStart = 1;
        int maxEnd = 100;
        for (int i = 0; i < maxTimes; i++) {
            Line[] lines = generateLines(maxLength, minStart, maxEnd);
            Line[] copyLines = new Line[lines.length];
            System.arraycopy(lines, 0, copyLines, 0, lines.length);
            int result1 = calculate(lines);
            int result2 = violentMethod(copyLines);
            if (result1 != result2){
                System.out.println("出错啦！！");
            }
        }
        System.out.println("成功！！");
    }
}
