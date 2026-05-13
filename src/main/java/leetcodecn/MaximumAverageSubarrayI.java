package leetcodecn;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONArray;

import java.util.HashMap;
import java.util.List;

class MaximumAverageSubarrayI {

    
//IMPORTANT!! Submit Code Region Begin(Do not remove this line)
static class Solution {
    public double findMaxAverage(int[] nums, int k) {
        //目标：找出子数组的最大平均数
        //限制：子数组长度必须为k
        //决策：定长窗口
        //关键变量：窗口左右端点

        if(nums.length == 1){
            return nums[0];
        }

        double ans = - Double.MAX_VALUE;
        double subTotal = 0;
        for(int right = 0; right < nums.length; right++){

            //右入
            subTotal += nums[right];

            int left = right - k + 1;
            if(left < 0){
                continue;
            }

            System.out.println("subTotal = " + subTotal);
            
            //更新
            ans = Math.max(ans, subTotal / k);

            //左出
            subTotal = subTotal - nums[left];
            
        }
        return ans;
    }
}
//IMPORTANT!! Submit Code Region End(Do not remove this line)

public static void main(String[] args) {
    // add your test code
    Solution solution = new Solution();
    String json = "[8860,-853,6534,4477,-4589,8646,-6155,-5577,-1656,-5779,-2619,-8604,-1358,-8009,4983,7063,3104,-1560,4080,2763,5616,-2375,2848,1394,-7173,-5225,-8244,-809,8025,-4072,-4391,-9579,1407,6700,2421,-6685,5481,-1732,-8892,-6645,3077,3287,-4149,8701,-4393,-9070,-1777,2237,-3253,-506,-4931,-7366,-8132,5406,-6300,-275,-1908,67,3569,1433,-7262,-437,8303,4498,-379,3054,-6285,4203,6908,4433,3077,2288,9733,-8067,3007,9725,9669,1362,-2561,-4225,5442,-9006,-429,160,-9234,-4444,3586,-5711,-9506,-79,-4418,-4348,-5891]";
    List<Integer> list = JSON.parseArray(json, Integer.class);
    int[] nums = list.stream().mapToInt(Integer::intValue).toArray();
    System.out.println("nums.length = " + nums.length);
    double maxAverage = solution.findMaxAverage(nums, 93);
    System.out.println("maxAverage = " + maxAverage);
}
}