package List;

import java.util.ArrayList;
import java.util.List;

/*
 * Editor: 张恒
 * tel： XXXXXXXXXXXXX
 * Wechat：zh17530588817
 * date: 2021/4/16/23:13
 */
public class Demo2 {
    public static void main(String[] args) {
        List list=new ArrayList();
        //1.添加数字数据（自动装箱）
        list.add(20);
        list.add(30);
        list.add(40);
        list.add(50);
        System.out.println("元素个数："+list.size());
        System.out.println(list.toString());
        System.out.println("------------------------------------------");

        //2.删除元素
        list.remove(0);
//        list.remove(20);//很明显数组越界错误，改成如下
        //list.remove(Object(20));
        //list.remove(new Integer(20));
        System.out.println("元素个数："+list.size());
        System.out.println(list.toString());
        System.out.println("------------------------------------------");

        //3-5不再演示，与之前类似
        //6.补充方法subList，返回子集合，含头不含尾
        List list2=list.subList(1, 3);
        System.out.println(list2.toString());
    }
}
