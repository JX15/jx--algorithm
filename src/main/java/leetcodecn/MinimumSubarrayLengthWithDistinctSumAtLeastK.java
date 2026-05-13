package leetcodecn;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.function.BiFunction;

class MinimumSubarrayLengthWithDistinctSumAtLeastK {

    
//IMPORTANT!! Submit Code Region Begin(Do not remove this line)
static class Solution {
    public int minLength(int[] nums, int k) {
        int ans = nums.length + 1;
        int sum = 0;
        int left = 0;
        Map<Integer,Integer> map = new HashMap();
        for(int i = 0; i < nums.length; i++){
            int num = nums[i];

            if(!map.containsKey(num)){
                sum += num;
            }

            map.merge(num, 1, Integer::sum);

            while(sum >= k){
                ans = Math.min(ans, i - left + 1);

                int leftNum = nums[left];
                map.merge(leftNum, -1, (num1, num2) -> {
                    if(num1 + num2 == 0){
                        return null;
                    }
                    return num1 + num2;
                });

                if(!map.containsKey(leftNum)){
                    sum -= leftNum;
                }

                left++;
            }
            
  

        }
        
        return ans <= nums.length ? ans : -1;
    }
}
//IMPORTANT!! Submit Code Region End(Do not remove this line)

public static void main(String[] args) {
    // add your test code
//    Solution solution = new Solution();
//    int[] nums = new int[]{2,2,3,1};
//    int i = solution.minLength(nums, 4);
//    System.out.println("i = " + i);
}
}