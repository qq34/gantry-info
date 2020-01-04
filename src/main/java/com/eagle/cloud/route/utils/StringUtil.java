package com.eagle.cloud.route.utils;
/**
 * @title: StringUtil
 * @projectName demo
 * @description: TODO
 * @date 2019-11-4 11:37
 */

import com.alibaba.fastjson.JSON;
import org.apache.commons.lang.StringUtils;

/**
 *@Description: TODO
 *@Author: yunbao
 *@Date: 2019-11-4 11:37
 *@Verion: 1.0
 **/
public class StringUtil {
    /*
   替换string
     */
    public static String myreplace(String str,String searchString,String replacement){
        return StringUtils.replace(str, searchString, replacement);
    }
    /*
    string 转double [] []
     */
    public static  Double[][] stringResult(String amapString){
        //String str = "{{10.14, 11.24, 44.55, 41.01},{12.10, 14.21, 52.14, 50.44},{14.44, 16.12, 45.42, 47.55}}";
        //amapString = amapString.replace("[", "[").replace("]", "]");
        String[][] arr = JSON.parseObject(amapString, String[][].class);
        Double[][] ds = new Double[arr.length][arr[0].length];
        for(int j=0;j<arr.length;j++){
            for(int i=0;i<arr[0].length;i++){
                ds[j][i] = Double.valueOf(arr[j][i]);
            }
        }
        return  ds;
    }
}
