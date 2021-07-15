package Collection;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

/*
 * Editor: 张恒
 * tel： XXXXXXXXXXXXX
 * Wechat：zh17530588817
 * date: 2021/4/16/23:02
 */
public class Demo2 {
    public static void main(String[] args) {
        Collection collection=new ArrayList();
        Student s1=new Student("划水",18);
        Student s2=new Student("摸鱼", 20);
        Student s3=new Student("起飞", 19);
        //1.添加数据
        collection.add(s1);
        collection.add(s2);
        collection.add(s3);
        //collection.add(s3);可重复添加相同对象
        System.out.println("元素个数："+collection.size());
        System.out.println(collection.toString());
        //2.删除数据
        collection.remove(s1);
        System.out.println("删除之后："+collection.size());
        //3.遍历数据
        //3.1 增强for
        for(Object object:collection) {
            Student student=(Student) object;
            System.out.println(student.toString());
        }
        //3.2迭代器
        //迭代过程中不能使用collection的删除方法
        Iterator iterator=collection.iterator();
        while (iterator.hasNext()) {
            Student student=(Student) iterator.next();
            System.out.println(student.toString());
        }
        //4.判断和上一块代码类似。
    }
}

class Student {
    private String name;
    private int age;
    public Student(String name, int age) {
        super();
        this.name = name;
        this.age = age;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }
    @Override
    public String toString() {
        return "Student [name=" + name + ", age=" + age +"]";
    }
}