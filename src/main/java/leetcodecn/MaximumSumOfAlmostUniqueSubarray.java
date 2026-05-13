package leetcodecn;

import com.alibaba.fastjson2.JSON;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

class MaximumSumOfAlmostUniqueSubarray {

    
//IMPORTANT!! Submit Code Region Begin(Do not remove this line)
static class Solution {
    public long maxSum(List<Integer> nums, int m, int k) {
        //目标：计算 几乎唯一 字数组的 最大和
        //限制 几乎唯一：数组内有m个互不相同的元素
        //决策：定长滑动窗口
        //关键变量：窗口内的不同元素个数；窗口内已经出现的元素；最大和。

        int ans = 0;
        Map<Integer, Integer> dif = new HashMap();
        int subTotal = 0;
        for (int i = 0; i < nums.size(); i++) {

            int num = nums.get(i);

            subTotal += num;

            if (dif.containsKey(num)) {
                dif.put(num, dif.get(num) + 1);
            } else {
                dif.put(num, 1);
            }

            int left = i - k + 1;
            if (left < 0) {
                continue;
            }

            if (dif.keySet().size() >= m) {
                ans = Math.max(ans, subTotal);
            }

            int leftNum = nums.get(left);

            subTotal -= leftNum;

            int leftNumCount = dif.get(leftNum);
            if (leftNumCount == 1) {
                dif.remove(leftNum);
            } else {
                dif.put(leftNum, leftNumCount - 1);
            }
        }

        return ans;
    }
}
//IMPORTANT!! Submit Code Region End(Do not remove this line)

public static void main(String[] args) {
    // add your test code
//    String json = "[996021492,996021492,973489433,66259330,554129007,713784351,646092981,328987029,469368828,685679486,66259330,165954500,731567840,595800464,552439059,14673238,157622945,521321042,386913607,733723177,330475939,462727944,69696035,958945846,648914457,627088742,363551051,50748590,400980660,674779765,439950964,613843311,385212079,725525766,813504429,385212079,66563232,578031878,935017574,554725813,456892672,245308625,626768145,270964388,554725813,768296675,676923124,939689721,115905765,625193590,717796816,27972217,277242430,768296675,480860474,659230631,570682291,601689140,955632265,767424000,251696645,675750691,767424000,251696645,767424000,675750691,675750691,251696645]";
//    List<Integer> list = JSON.parseArray(json, Integer.class);
//    Solution solution = new Solution();
//    long l = solution.maxSum(list, 8, 8);
//    System.out.println("l = " + l);
    
    String s= " ";
    Set<Character> cSet = new HashSet<>();
    if (!cSet.contains(s.charAt(0))){
        cSet.add(s.charAt(0));
    }
    System.out.println("res = " + Math.max(0, cSet.size()));
    
}
}