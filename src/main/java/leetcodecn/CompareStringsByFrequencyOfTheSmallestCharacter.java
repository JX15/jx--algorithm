package leetcodecn;

import java.util.Arrays;
import java.util.HashMap;

class CompareStringsByFrequencyOfTheSmallestCharacter {

    
//IMPORTANT!! Submit Code Region Begin(Do not remove this line)
static class Solution {
    public int[] numSmallerByFrequency(String[] queries, String[] words) {
        int[] wCnt = new int[words.length];
        for(int i = 0; i < words.length; i++){
            char[] arr = words[i].toCharArray();
            Arrays.sort(arr);

            int cnt = charLowerBound(arr, (char)(arr[0] + 1));
            wCnt[i] = cnt;
        }

        Arrays.sort(wCnt);

        int[] ans = new int[queries.length];
        for(int i = 0; i < queries.length; i++){
            char[] arr = queries[i].toCharArray();
            Arrays.sort(arr);

            int cnt = charLowerBound(arr, (char)(arr[0] + 1));

            ans[i] += wCnt.length - intLowerBound(wCnt, cnt + 1);
        }

        return ans;
    }

    //返回第一个 >= target 的元素的索引
    //如果所有元素都 < target 返回 arr.length
    //arr必须是非递减的
    private int charLowerBound(char[] arr, char target){
        int left = 0;
        int right = arr.length - 1;

        while(left <= right){
            int mid = left + (right - left) / 2;
            if(arr[mid] >= target){
                right = mid - 1;
            }else{
                left = mid + 1;
            }
        }

        return left;
    }

    private int intLowerBound(int[] arr, int target){
        int left = 0;
        int right = arr.length - 1;

        while(left <= right){
            int mid = left + (right - left) / 2;
            if(arr[mid] >= target){
                right = mid - 1;
            }else{
                left = mid + 1;
            }
        }

        return left;
    }
}
//IMPORTANT!! Submit Code Region End(Do not remove this line)

public static void main(String[] args) {
    // add your test code
    Solution solution = new Solution();
    String[] q = new String[]{"bba","abaaaaaa","aaaaaa","bbabbabaab","aba","aa","baab","bbbbbb","aab","bbabbaabb"};
    String[] w = new String[]{"aaabbb","aab","babbab","babbbb","b","bbbbbbbbab","a","bbbbbbbbbb","baaabbaab","aa"};
    int[] ints = solution.numSmallerByFrequency(q, w);
    System.out.println("ints = " + Arrays.toString(ints));
}
}