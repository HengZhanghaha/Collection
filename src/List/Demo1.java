package List;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/*
 * Editor: 张恒
 * tel： XXXXXXXXXXXXX
 * Wechat：zh17530588817
 * date: 2021/4/16/23:07
 */
/*
 * List子接口的使用（一）
 * 特点：1.有序有下标 2.可以重复
 *
 * 1.添加元素
 * 2.删除元素
 * 3.遍历元素
 * 4.判断
 * 5.获取位置
 */
public class Demo1 {
    public static void main(String[] args) {
        List list=new ArrayList<>();
        //1.添加元素
        list.add("tang");
        list.add("he");
        list.add(0,"yu");//插入操作
        System.out.println("元素个数："+list.size());
        System.out.println(list.toString());
        System.out.println("------------------------------------------");

        //2.删除元素
        list.remove(0);
        //list.remove("yu");结果同上
        System.out.println("删除之后："+list.size());
        System.out.println(list.toString());
        System.out.println("------------------------------------------");

        //3.遍历元素
        //3.1 使用for遍历
        for(int i=0;i<list.size();++i) {
            System.out.println(list.get(i));
        }
        //3.2 使用增强for
        for(Object object:list) {
            System.out.println(object);
        }
        //3.3 使用迭代器
        Iterator iterator=list.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
        System.out.println("------------------------------------------");

        //3.4使用列表迭代器，listIterator可以双向遍历，添加、删除及修改元素。
        ListIterator listIterator=list.listIterator();
        //从前往后
        while (listIterator.hasNext()) {
            System.out.println(listIterator.next());
        }
        //从后往前（此时“遍历指针”已经指向末尾）
        while(listIterator.hasPrevious()) {
            System.out.println(listIterator.previous());
        }
        System.out.println("------------------------------------------");
        //4.判断
        System.out.println(list.isEmpty());
        System.out.println(list.contains("tang"));
        //5.获取位置
        System.out.println(list.indexOf("tang"));
    }
}
