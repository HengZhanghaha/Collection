package Set;

import java.util.HashSet;
import java.util.Iterator;

/*
 * Editor: 张恒
 * tel： XXXXXXXXXXXXX
 * Wechat：zh17530588817
 * date: 2021/4/17/10:46
 */
/*
HashSet集合的使用
 * 存储结构：哈希表（数组+链表+红黑树）
 * 1.添加元素
 * 2.删除元素
 * 3.遍历
 * 4.判断
 */
public class HashSetTest {
    public static void main(String[] args) {
        HashSet<Person> hashSet=new HashSet<>();
        Person p1=new Person("tang",21);
        Person p2=new Person("he", 22);
        Person p3=new Person("yu", 21);
        //1.添加元素
        hashSet.add(p1);
        hashSet.add(p2);
        hashSet.add(p3);
        //重复，添加失败
        hashSet.add(p3);
        //直接new一个相同属性的对象，依然会被添加，不难理解。
        //假如相同属性便认为是同一个对象，怎么修改？
        hashSet.add(new Person("yu", 21));
        System.out.println(hashSet.toString());
        //2.删除元素
        hashSet.remove(p2);
        //3.遍历
        //3.1 增强for
        for (Person person : hashSet) {
            System.out.println(person);
        }
        //3.2 迭代器
        Iterator<Person> iterator=hashSet.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
        //4.判断
        System.out.println(hashSet.isEmpty());
        //直接new一个相同属性的对象结果输出是false，不难理解。
        //注：假如相同属性便认为是同一个对象，该怎么做？
        System.out.println(hashSet.contains(new Person("tang", 21)));
    }
}

class Person {
    private String name;
    private int age;
    public Person(String name,int age) {
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
        return "Peerson [name=" + name + ", age=" + age + "]";
    }

}
