package leetcodecn;

import java.util.HashMap;
import java.util.Map;

class TwoSum {

    
//IMPORTANT!! Submit Code Region Begin(Do not remove this line)
class Solution {
    public int[] twoSum(int[] nums, int target) {
        Map<Integer,Integer> map = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            int temp = target - nums[i];
            if (map.containsKey(temp)){
                return new int[]{i, map.get(temp)};
            }else {
                map.put(nums[i],i);
            }
        }
        
        return new int[]{};
    }
}
//IMPORTANT!! Submit Code Region End(Do not remove this line)

public static void main(String[] args) {
    // add your test code
}
}