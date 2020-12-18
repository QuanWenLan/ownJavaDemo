package com.lanwq.java8;

import com.sun.deploy.util.StringUtils;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author Vin lan
 * @className secondCateNameList
 * @description TODO
 * @createTime 2020-12-15  11:20
 **/
public class SecondCateNameList {
    public static void main(String[] args) {
        //假设前端用户在多选框勾选后传入后端VO的二级类目字符串为secondCateNames
        String secondCateNames = "女性护理,美妆工具,口腔护理";

        //将字符串转换成数组，使用apache的StringUtils.split(string,char)
        String[] split = StringUtils.splitString(secondCateNames,",");

        //使用Stream转换，过滤，用单引号进行拼接。
        List<String> secondCateNameList = Arrays.asList(split).stream().filter(Objects::nonNull)
                .map(secondCateName -> "'" + secondCateName + "'").collect(Collectors.toList());
        System.out.println("secondCateNameList:"+secondCateNameList);

        //将集合转换成字符串
        //方法一:join(Collection var0, String var1)
        String a = StringUtils.join(secondCateNameList, ",");
        System.out.println("AND eod.second_cate_name in("+ a + ")");
        //方法二:joining(CharSequence delimiter, CharSequence prefix, CharSequence suffix)
        String b = secondCateNameList.stream().collect(Collectors.joining(",","(",")"));
        System.out.println("AND eod.second_cate_name in"+ b);
        String c = Arrays.asList(split).stream().filter(Objects::nonNull)
                .map(secondCateName -> "'" + secondCateName + "'").collect(Collectors.joining(",","(",")"));
        System.out.println("AND eod.second_cate_name in"+ c);
    }
}
