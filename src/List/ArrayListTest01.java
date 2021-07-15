package List;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.ListIterator;

/*
 * Editor: 张恒
 * tel： XXXXXXXXXXXXX
 * Wechat：zh17530588817
 * date: 2021/4/17/9:06
 */
public class ArrayListTest01 {
    public static void main(String[] args) {
        ArrayList arrayList=new ArrayList<>();
        //1.添加元素
        Student s1=new Student("唐", 21);
        Student s2=new Student("何", 22);
        Student s3=new Student("余", 21);
        arrayList.add(s1);
        arrayList.add(s2);
        arrayList.add(s3);
        System.out.println("元素个数："+arrayList.size());
        System.out.println(arrayList.toString());
        System.out.println("------------------------------------------");

        //2.删除元素
        arrayList.remove(s1);
        //arrayList.remove(new Student("唐", 21));
        //注：这样可以删除吗（不可以）？显然这是两个不同的对象。
        //假如两个对象属性相同便认为其是同一对象，那么如何修改代码？
        System.out.println("------------------------------------------");

        //3.遍历元素
        //3.1使用迭代器
        Iterator iterator=arrayList.iterator();
        while(iterator.hasNext()) {
            System.out.println(iterator.next());
        }
        //3.2使用列表迭代器
        ListIterator listIterator=arrayList.listIterator();
        //从前往后遍历
        while(listIterator.hasNext()) {
            System.out.println(listIterator.next());
        }
        //从后往前遍历
        while(listIterator.hasPrevious()) {
            System.out.println(listIterator.previous());
        }
        System.out.println("------------------------------------------");

        //4.判断
        System.out.println(arrayList.isEmpty());
        //System.out.println(arrayList.contains(new Student("何", 22)));
        //注：与上文相同的问题。
        //5.查找
        System.out.println(arrayList.indexOf(s1));
        System.out.println("------------------------------------------");

    }
}

class Student {
    private String name;
    private int age;
    public Student(String name, int age) {
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
    @Override
    public boolean equals(Object obj) {
        //1.是否为同一对象
        if (this==obj) {
            return true;
        }
        //2.判断是否为空
        if (obj==null) {
            return false;
        }
        //3.判断是否是Student类型
        if (obj instanceof Student) {
            Student student=(Student) obj;
            //4.比较属性
            if(this.name.equals(student.getName())&&this.age==student.age) {
                return true;
            }
        }
        //不满足，返回false
        return false;
    }
}
