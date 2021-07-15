package Collection;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

/*
 * Editor: 张恒
 * tel： XXXXXXXXXXXXX
 * Wechat：zh17530588817
 * date: 2021/4/16/22:53
 */

/*
 * Collection接口的使用（一）
 * 1.添加元素
 * 2.删除元素
 * 3.遍历元素
 * 4.判断
 */
public class Demo1 {
    public static void main(String[] args) {
        Collection collection = new ArrayList();

        collection.add("apple");
        collection.add("西瓜");
        collection.add("李子");
        System.out.println("个数:" + collection.size());
        System.out.println(collection);

        System.out.println("删除apple:" + collection.remove("apple"));
        System.out.println("删除后:" + collection);

        //遍历
        for (Object o:collection){
            System.out.println(o);
        }
        //使用迭代器
        Iterator iterator = collection.iterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }

        //判断
        System.out.println(collection.contains("李子"));
        System.out.println(collection.isEmpty());
    }
}
