package leetcodecn;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

class ContinuousSubarrays {

    
//IMPORTANT!! Submit Code Region Begin(Do not remove this line)
static class Solution {
    public long continuousSubarrays(int[] nums) {
        int max = -1;
        int min = Integer.MAX_VALUE;
        int left = 0;

        Map<Integer, Integer> cnt = new HashMap();

        long ans = 0;
        for (int i = 0; i < nums.length; i++) {

            cnt.merge(nums[i], 1, Integer::sum);

            max = Math.max(max, nums[i]);
            min = Math.min(min, nums[i]);

            while (Math.abs(max - min) > 2) {
                int newCnt = cnt.merge(nums[left], -1,Integer::sum);
                if(newCnt == 0){
                    cnt.remove(nums[left]);
                }

                if (newCnt == 0 && nums[left] == min) {
                    for (int num : cnt.keySet()) {
                        min = Math.min(min, num);
                    }
                } else if (newCnt == 0 && nums[left] == max) {
                    for (int num : cnt.keySet()) {
                        max = Math.max(max, num);
                    }
                }

                left++;
            }

            ans += i - left + 1;
        }




        return ans;
    }

}
//IMPORTANT!! Submit Code Region End(Do not remove this line)

public static void main(String[] args) {
    // add your test code
    Solution solution = new Solution();
    int[] nums = new int[]{5,4,2,4};
    solution.continuousSubarrays(nums);
}
}