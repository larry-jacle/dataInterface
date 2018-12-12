package com.gildata.byinterserver;

/**
 * Created by LiChao on 2018/11/14
 */
public class Test {

//    @org.junit.Test
//    public void test(){
//        String str = "hello\n world";
//        System.out.println(str);
//
//        //替换换行符为\0x5
//        String replacedStr=str.replaceAll("\n",String.valueOf((char)5));
//        //还原换行符
//        String destStr=replacedStr.replaceAll(String.valueOf((char)5),"\n");
//
//        System.out.println(replacedStr);
//        System.out.println("--------------------");
//        System.out.println(destStr);
//    }
//
//
//    @org.junit.Test
//    public void test2(){
//        System.out.println("\u0005");
//        String str = "上海市\u0005 上海市奉贤区莲都路55号\u0005";
//        System.out.println(str);
//        System.out.println(str.replaceAll("\u0005","\n"));
//    }


    @org.junit.Test
    public void test(){
        System.out.println("hello "+ "\\n" + "world");
        System.out.println("hello "+ "\\\\n" + "world");
    }


    @org.junit.Test
    public void test2(){
        String str = "------";
//        String[] strSplits = str.split("\\^\\^");
        String[] strSplits = str.split("--");
        System.out.println(strSplits.length);
    }

}
