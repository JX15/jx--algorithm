package leetcodecn;

class MaximumScoreAfterSplittingAString {


    //IMPORTANT!! Submit Code Region Begin(Do not remove this line)
    class Solution {
        public int maxScore(String s) {
            //目标：找到字符串分割后的最大得分
            //限制：1.字符串只由0，1组成；2.左右子字符串非空
            //决策：动态规划，1.先遍历一遍s，算出 左1，右x 的得分；2.在这个得分基础上，从index=1再次开始遍历，如果是0则+1，如果是1则-1，如果当前得分高于基准得分，则更新最大得分
            //关键变量：基准得分，当前得分

            char[] c = s.toCharArray();
            int maxScore = 0;
            if (c[0] == '0') {
                maxScore++;
            }
            for (int i = 1; i < c.length; i++) {
                if (c[i] == '1') {
                    maxScore++;
                }
            }

            int currentScore = maxScore;
            for (int i = 1; i < c.length; i++) {

                if (currentScore > maxScore) {
                    maxScore = currentScore;
                }

                if (c[i] == '0') {
                    currentScore++;
                } else {
                    currentScore--;
                }

            }

            return maxScore;

            //总结：想尽可能多的边界例子来进行验证，在本题中如"00","11"
            //变化：1.如果左字符串只有0，或者右字符串只有1，则得分双倍计算。---> 额外计算左边只有0和右边只有1的总得分
            //2.如果左右有连续的0或者右边有连续的1，则连续部分得分双倍计算
        }
    }
//IMPORTANT!! Submit Code Region End(Do not remove this line)


    public static void main(String[] args) {
        // add your test code

        Solution2 solution2 = new Solution2();
        int maxScore = solution2.maxScore("011001");
        System.out.println("maxScore = " + maxScore);
    }


    /*
        针对变化2
        增量维护
            从i -> i+1 只影响左边连续的0和右边连续的1
            s[i] = '0'时，连续0有3种情况
                1.第1个0，则+1
                2.第2个0，即第一次形成连续的0，需要把第一个0的分补上所以是+3
                4.第n个0（n>2),已经连续了，所以直接+2
            如果是'1',反向同理
        所以，第一次遍历，计算左部得分
             第二次遍历，计算右部得分
             第三次遍历，计算总得分
     */
    static class Solution2 {
        public int maxScore(String s) {
            int n = s.length();
            int[] leftScore = new int[n + 1];//leftScore[i] 表示左子字符串s[0]到s[i-1]的得分
            int leftRun = 0; //初始0个连续'0'
            for (int i = 0; i < n; i++) {
                if (s.charAt(i) == '0') {
                    leftRun++;
                    if (leftRun == 1) {
                        leftScore[i + 1] = leftScore[i] + 1;
                    } else if (leftRun == 2) {
                        leftScore[i + 1] = leftScore[i] + 3;
                    } else {
                        leftScore[i + 1] = leftScore[i] + 2;
                    }
                } else {
                    leftRun = 0; //重置连续0
                    leftScore[i + 1] = leftScore[i];
                }
            }

            int[] rightScore = new int[n + 1];//rightScore[i] 表示右子字符串s[i]到s[n-1]的得分
            int rightRun = 0;//初始0个连续'1'
            for (int i = n - 1; i >= 0; i--) {
                if (s.charAt(i) == '1') {
                    rightRun++;
                    if (rightRun == 1) {
                        rightScore[i] = rightScore[i + 1] + 1;
                    } else if (rightRun == 2) {
                        rightScore[i] = rightScore[i + 1] + 3;
                    } else {
                        rightScore[i] = rightScore[i + 1] + 2;
                    }
                } else {
                    rightRun = 0;
                    rightScore[i] = rightScore[i + 1];
                }
            }

            int maxScore = Integer.MIN_VALUE;
            for (int i = 0; i < n; i++) {
                maxScore = Math.max(maxScore, leftScore[i] + rightScore[i]);
            }

            return maxScore;
        }
    }

}