package com.jiangxin.demo.algorithm.leetcode;

/**
 * @author: xin.jiang.cn
 * @date: 2022年12月01日 10:37:48
 * @description: https://leetcode.cn/problems/find-nearest-point-that-has-the-same-x-or-y-coordinate
 */
public class LeetCode1779 {

    public int nearestValidPoint(int x, int y, int[][] points) {
        if (points == null){
            return -1;
        }

        int minDis = Integer.MAX_VALUE;
        int minDisIndex = -1;
        for (int i = 0; i < points.length; i++) {
            int[] pointi = points[i];
            int xi = pointi[0];
            int yi = pointi[1];
            if (xi == x || yi == y){
                int manhattanDistance = getManhattanDistance(x, y, xi, yi);
                if (manhattanDistance < minDis){
                    minDis = manhattanDistance;
                    minDisIndex = i;
                }
            }

        }
        return minDisIndex;

    }

    public int getManhattanDistance(int x,int y,int xi,int yi){
        return Math.abs(x - xi) + Math.abs(y - yi);
    }
}
