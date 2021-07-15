package List;

import java.util.Iterator;
import java.util.LinkedList;

/*
 * Editor: 张恒
 * tel： XXXXXXXXXXXXX
 * Wechat：zh17530588817
 * date: 2021/4/17/9:23
 */
public class LinkListTest {
    public static void main(String[] args) {
        LinkedList linkedList=new LinkedList<>();
        Student s1=new Student("唐", 21);
        Student s2=new Student("何", 22);
        Student s3=new Student("余", 21);
        //1.添加元素
        linkedList.add(s1);
        linkedList.add(s2);
        linkedList.add(s3);
        linkedList.add(s3);
        System.out.println("元素个数："+linkedList.size());
        System.out.println(linkedList.toString());
        System.out.println("------------------------------------------------");
        //2.删除元素

        linkedList.remove(new Student("唐", 21));

        System.out.println(linkedList.toString());
        System.out.println("------------------------------------------------");

        //3.遍历
        //3.1 使用for
        for(int i=0;i<linkedList.size();++i) {
            System.out.println(linkedList.get(i));
        }
        System.out.println("------------------------------------------------");

        //3.2 使用增强for
        for(Object object:linkedList) {
            Student student=(Student) object;
            System.out.println(student.toString());
        }
        System.out.println("------------------------------------------------");

        //3.3 使用迭代器
        Iterator iterator =linkedList.iterator();
        while (iterator.hasNext()) {
            Student student = (Student) iterator.next();
            System.out.println(student.toString());
        }
        System.out.println("------------------------------------------------");

        //3.4 使用列表迭代器（略）
        //4. 判断
        System.out.println(linkedList.contains(s1));
        System.out.println(linkedList.isEmpty());
        System.out.println(linkedList.indexOf(s3));
    }
}
