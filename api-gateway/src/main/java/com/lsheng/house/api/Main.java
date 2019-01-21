package com.lsheng.house.api;

import java.util.Arrays;

/**
 * @author zhonglunsheng
 * @Description
 * @create 2018-11-27 18:44
// */
public class Main {

    public  int enhance(int[] src, int[] des){
        if (src == null || des == null){
            return 0;
        }

        Arrays.sort(src);

        int indexA = 0;
        int indexB = 0;
        int count = 0;
        while (indexA < src.length && indexB < des.length){
            if (src[indexA] > des[indexB]){
                count ++;
            }else{
                int temp = indexA;
                while (src[temp] < des[indexB]){
                    temp ++;
                    if (src.length == temp){
                        return count;
                    }
                }
                swap(src, temp, indexA);
                count ++;
            }
            indexA ++;
            indexB ++;
        }
        return count;

    }

    public void swap(int[] arr, int num1, int num2){
        int help = arr[num1];
        arr[num1] = arr[num2];
        arr[num2] = help;
    }

}
