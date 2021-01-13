package com.ccz.canaldemo.combine;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

@Slf4j
public class GetCombination {

    public static void main(String[] args) {

        String a = "[1,2,3,4,5]";
        List<String> b = new ArrayList<>(Arrays.asList(a));
        List<Integer> list = b.stream().map(Integer::valueOf).collect(Collectors.toList());
        System.out.println(list);
        /*
        * 假设
        * 1和所有都互斥
        * 3和4互斥
        * 4和3，5互斥
        * */
//        List<Integer> a = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5));
//        int size = a.size() + 1;
//        List<List> result = new ArrayList<>();
//        log.info(String.valueOf(System.currentTimeMillis()));
//        for (int i = 1; i != size; i++) {
//            combinationSelect(a, i, result);
//        }
//        log.info(JSON.toJSONString(result));
//        log.info(String.valueOf(System.currentTimeMillis()));
//        List<Integer[]> exclusiveParam = getExclusiveList();
//        log.info(JSON.toJSONString(exclusiveParam));
//        ArrayList<List> exclusiveList = new ArrayList<>();
//        result.forEach(li -> exclusiveParam.forEach(s -> {
//            if (li.contains(s[0]) && li.contains(s[1])) {
//                exclusiveList.add(li);
//            }
//        }));
//
//        List<List> reduce = result.stream().filter(item -> !exclusiveList.contains(item)).collect(toList());
//        log.info("差集：{}", JSON.toJSONString(reduce));
    }

    /**
     * 互斥汇总
     * @return list
     */
    private static List<Integer[]> getExclusiveList() {
        List<Integer[]> a = new ArrayList<>();
        Integer[] array = new Integer[2];
        array[0] = 3;
        array[1] = 4;
        a.add(array);
        Integer[] array2 = new Integer[2];
        array2[0] = 4;
        array2[1] = 5;
        a.add(array2);
        return a;
    }


    private static void combinationSelect(List<Integer> dataList, int n, List<List> result) {
        combinationSelect(dataList, 0, new Integer[n], 0, result);
    }

    /**
     * 递归求组合
     * @param dataList dataList
     * @param dataIndex dataIndex
     * @param resultList resultList
     * @param resultIndex resultIndex
     * @param result result
     */
    private static void combinationSelect(List<Integer> dataList, int dataIndex, Integer[] resultList, int resultIndex, List<List> result) {
        int resultLen = resultList.length;
        int resultCount = resultIndex + 1;
        if (resultCount > resultLen) { // 全部选择完时，输出组合结果
            result.add(Arrays.asList(resultList.clone()));
//            result.add(resultList.clone());
            return;
        }
        // 递归选择下一个
        for (int i = dataIndex; i < dataList.size() + resultCount - resultLen; i++) {
            resultList[resultIndex] = dataList.get(i);
            combinationSelect(dataList, i + 1, resultList, resultIndex + 1, result);
        }
    }
}