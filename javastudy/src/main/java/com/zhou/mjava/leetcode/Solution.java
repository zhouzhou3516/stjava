package com.zhou.mjava.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author liqingzhou on 18/7/1
 */
class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        Map<Integer,Integer[]> map = new HashMap<Integer,Integer[]>();
        List<List<Integer>> ret = new ArrayList<>();
        for(int i=0;i<nums.length;i++){
            for(int j=i+1;j<nums.length;j++){
                map.put(nums[i]+nums[j],new Integer[]{i,j});
            }
        }
        for(int i=0; i<nums.length; i++){
            if(map.get(-nums[i]) !=null){
                Integer[] arr = map.get(-nums[i]);
                List<Integer> list = new ArrayList<>();
                list.add(nums[i]);
                list.add(nums[arr[0]]);
                list.add(nums[arr[1]]);
                ret.add(list);
            }
        }
        return ret;
    }
}
