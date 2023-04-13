package com.jiangxin.demo.algorithm.basic.class07;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.With;

import java.lang.annotation.ElementType;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author: xin.jiang.cn
 * @date: 2023年04月13日 18:11:15
 * @description: 抽奖
 * 给定一个整型数组，int[] arr；
 * 和一个布尔类型数组，boolean[] op
 * 两个数组一定等长，假设长度为N，arr[i]表示客户编号，op[i]表示客户操作
 * arr = [ 3 , 3 , 1 , 2, 1, 2, 5… ]
 * op = [ T , T, T, T, F, T, F… ]
 * 依次表示：3用户购买了一件商品，3用户购买了一件商品，1用户购买了一件商品，2用户购买了一件商品，1用户退货了一件商品，2用户购买了一件商品，5用户退货了一件商品…
 *
 * 一对arr[i]和op[i]就代表一个事件：
 * 用户号为arr[i]，op[i] == T就代表这个用户购买了一件商品
 * op[i] == F就代表这个用户退货了一件商品
 * 现在你作为电商平台负责人，你想在每一个事件到来的时候， 都给购买次数最多的前K名用户颁奖。 所以每个事件发生后，你都需要一个得奖名单（得奖区）。
 *
 * 得奖系统的规则：
 * 1，如果某个用户购买商品数为0，但是又发生了退货事件， 则认为该事件无效，得奖名单和上一个事件发生后一致，比如例子中的5用户
 * 2，某用户发生购买商品事件，购买商品数+1，发生退货事件，购买商品数-1
 * 3，每次都是最多K个用户得奖，K也为传入的参数
 * 如果根据全部规则，得奖人数确实不够K个，那就以不够的情况输出结果
 * 4，得奖系统分为得奖区和候选区，任何用户只要购买数>0， 一定在这两个区域中的一个
 * 5，购买数最大的前K名用户进入得奖区， 在最初时如果得奖区没有到达K个用户，那么新来的用户直接进入得奖区
 * 6，如果购买数不足以进入得奖区的用户，进入候选区
 * 7，如果候选区购买数最多的用户，已经足以进入得奖区， 该用户就会替换得奖区中购买数最少的用户（大于才能替换），
 * 如果得奖区中购买数最少的用户有多个，就替换最早进入得奖区的用户
 * 如果候选区中购买数最多的用户有多个，机会会给最早进入候选区的用户
 * 8，候选区和得奖区是两套时间， 因用户只会在其中一个区域，所以只会有一个区域的时间，另一个没有 从得奖区出来进入候选区的用户，得奖区时间删除，
 * 进入候选区的时间就是当前事件的时间（可以理解为arr[i]和op[i]中的i） 从候选区出来进入得奖区的用户，候选区时间删除，
 * 进入得奖区的时间就是当前事件的时间（可以理解为arr[i]和op[i]中的i）
 * 9，如果某用户购买数==0，不管在哪个区域都离开，区域时间删除， 离开是指彻底离开，哪个区域也不会找到该用户 如果下次该用户又发生购买行为，产生>0的购买数， 会再次根据之前规则回到某个区域中，进入区域的时间重记
 *
 * 请遍历arr数组和op数组，遍历每一步输出一个得奖名单
 */
public class Lottery {
    public List<List<Integer>> topK(int[] arr, boolean[] op, int k){
      return enhancedHeapMethod(arr, op, k);
    }

    /**
     * 使用加强堆优化版本
     */
    public List<List<Integer>> enhancedHeapMethod(int[] arr, boolean[] op, int k){
        Map<Integer,Customer> customerMap = Maps.newHashMap();
        EnhancedHeap<Customer> candidates = new EnhancedHeap<>(new CandidateSortRule());
        EnhancedHeap<Customer> awards = new EnhancedHeap<>(new AwardSortRule());
        List<List<Integer>> ans = new ArrayList<>(k);

        for (int i = 0; i < arr.length; i++) {
            //1.先排除无效事件
            boolean buyOrRefund = op[i];
            //既不是购买事件，这个顾客也不在候选区或者得奖区，无效事件
            if (!buyOrRefund && !customerMap.containsKey(i)){
                continue;
            }
            /*
                还剩下三种情况
                    顾客在候选区/得奖区，发生了退款事件
                    顾客在候选区/得奖区，发生了购买事件
                    顾客不在任意一个区，发生了购买事件
             */
            //2.顾客不在任意一个区，发生了购买事件，先将顾客添加进map，用于后续判断是否存在
            if (!customerMap.containsKey(i)){
                customerMap.put(i, new Customer(arr[i],0,0));
            }
            //3.计算该顾客的购买次数
            Customer customer = customerMap.get(i);
            if (buyOrRefund){
                customer.buyCount++;
            }else {
                customer.buyCount--;
            }
            if (customer.buyCount == 0){
                customerMap.remove(i);
            }
            //4.如果是新客户，加入相应区
            if (!candidates.contains(customer) && !awards.contains(customer)){
                if (awards.size() < k){
                    customer.setEnterTime(i);
                    awards.push(customer);
                }else{
                    customer.setEnterTime(i);
                    candidates.push(customer);
                }
            }
            //如果当前顾客购买数为0，清理掉
            //kp 主要优化了删除和排序的时间复杂度，原来这块是O(N)的复杂度，用了加强堆以后，变成了O(logN)
            else if (candidates.contains(customer)){
                if (customer.buyCount == 0){
                    candidates.remove(customer);
                }else {
                    candidates.resign(customer);
                }
            }else {
                if (customer.buyCount == 0){
                    awards.remove(customer);
                }else {
                    awards.resign(customer);
                }
            }
            //5.移动
            //kp 这里也是，原来O(N)的复杂度，用了加强堆以后，变成了O(logN)
            heapMove(candidates, awards, k, i);
            //6.获取结果
            List<Customer> customers = awards.getAllElements();
            ans.add(customers.stream().map(Customer::getId).collect(Collectors.toList()));
        }
        return ans;
    }

    private void heapMove(EnhancedHeap<Customer> candidates, EnhancedHeap<Customer> awards, int k, int time) {
        if (candidates.isEmpty()){
            return;
        }
        if (awards.size() < k){
            Customer customer = candidates.pop();
            customer.enterTime = time;
            awards.push(customer);
        }else {
            if (candidates.peek().buyCount > awards.peek().buyCount){
                Customer newCandidate = awards.pop();
                Customer newAward = candidates.pop();
                newCandidate.enterTime = time;
                newAward.enterTime = time;
                candidates.push(newCandidate);
                awards.push(newAward);
            }
        }
    }


    /**
     * 暴力解法
     */
    private List<List<Integer>> violentMethod(int[] arr, boolean[] op, int k){
        Map<Integer,Customer> customerMap = Maps.newHashMap();
        List<Customer> candidateList = Lists.newArrayList();
        List<Customer> awardList = new ArrayList<>(k);
        List<List<Integer>> ans = new ArrayList<>(k);

        for (int i = 0; i < arr.length; i++) {
            //1.先排除无效事件
            boolean buyOrRefund = op[i];
            //既不是购买事件，这个顾客也不在候选区或者得奖区，无效事件
            if (!buyOrRefund && !customerMap.containsKey(i)){
                continue;
            }
            /*
                还剩下三种情况
                    顾客在候选区/得奖区，发生了退款事件
                    顾客在候选区/得奖区，发生了购买事件
                    顾客不在任意一个区，发生了购买事件
             */
            //2.顾客不在任意一个区，发生了购买事件，先将顾客添加进map，用于后续判断是否存在
            if (!customerMap.containsKey(i)){
                customerMap.put(i, new Customer(arr[i],0,0));
            }
            //3.计算该顾客的购买次数
            Customer customer = customerMap.get(i);
            if (buyOrRefund){
                customer.buyCount++;
            }else {
                customer.buyCount--;
            }
            if (customer.buyCount == 0){
                customerMap.remove(i);
            }
            //4.如果是新客户，加入相应区
            if (!candidateList.contains(customer) && !awardList.contains(customer)){
                if (awardList.size() < k){
                    customer.setEnterTime(i);
                    awardList.add(customer);
                }else{
                    customer.setEnterTime(i);
                    candidateList.add(customer);
                }
            }
            //5.清理两个候选区中购买数为0的顾客
            removeZero(candidateList);
            removeZero(awardList);
            //6.排序并移动
            candidateList.sort(new CandidateSortRule());
            awardList.sort(new AwardSortRule());
            move(candidateList,awardList,k,i);
            //7.获取结果
            ans.add(awardList.stream().map(Customer::getId).collect(Collectors.toList()));
        }
        return ans;
    }
    private void move(List<Customer> candidateList, List<Customer> awardList,int k,int time) {
        if (candidateList.size() ==0){
            return;
        }
        if (awardList.size() < k){
            Customer customer = candidateList.remove(0);
            customer.enterTime = time;
            awardList.add(customer);
        }else {
            if (candidateList.get(0).buyCount > awardList.get(0).buyCount){
                Customer newCandidate = awardList.get(0);
                Customer newAward = candidateList.get(0);
                awardList.remove(0);
                candidateList.remove(0);
                newCandidate.enterTime = time;
                newAward.enterTime = time;
                candidateList.add(newCandidate);
                awardList.add(newAward);

            }
        }
    }

    /**
     * 排序规则：购买数降序，购买数相同，进入候选区时间升序（进入约早的在越前面）
     */
    public static class CandidateSortRule implements Comparator<Customer>{
        @Override
        public int compare(Customer o1, Customer o2) {
            return o1.buyCount != o2.buyCount ? (o2.buyCount - o1.buyCount) : (o1.enterTime - o2.enterTime);
        }
    }

    /**
     * 排序规则：购买数升序，购买数相同，进去获奖区时间升序（进入约早的在越前面）
     */
    public static class AwardSortRule implements Comparator<Customer>{
        @Override
        public int compare(Customer o1, Customer o2) {
            return o1.buyCount != o2.buyCount ? (o1.buyCount - o2.buyCount) : (o1.enterTime - o2.enterTime);
        }
    }

    private void removeZero(List<Customer> list) {
        List<Customer> retainCustomers = new ArrayList<>();
        for (Customer customer : list) {
            if (customer.buyCount != 0){
                retainCustomers.add(customer);
            }
        }
        list.clear();
        list.addAll(retainCustomers);
    }

    @Data
    @AllArgsConstructor
    public static class Customer{
        private int id;
        private int buyCount;
        private int enterTime;
    }
}
