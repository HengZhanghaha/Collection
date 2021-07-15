package Set;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/*
 * Editor: 张恒
 * tel： XXXXXXXXXXXXX
 * Wechat：zh17530588817
 * date: 2021/4/17/10:33
 */
public class Test01 {
    public static void main(String[] args) {
        Set<String> set=new HashSet<String>();
        //1.添加数据
        set.add("tang");
        set.add("he");
        set.add("yu");
        System.out.println("数据个数："+set.size());
        System.out.println(set.toString());//无序输出
        //2.删除数据
        /*
         * set.remove("tang"); System.out.println(set.toString());
         */
        //3.遍历【重点】
        //3.1 使用增强for
        for (String string : set) {
            System.out.println(string);
        }
        //3.2 使用迭代器
        Iterator<String> iterator=set.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
        //4.判断
        System.out.println(set.contains("tang"));
        System.out.println(set.isEmpty());
    }
}
