package Map;

import java.util.HashMap;
import java.util.Map;

/*
 * Editor: 张恒
 * tel： XXXXXXXXXXXXX
 * Wechat：zh17530588817
 * date: 2021/4/17/15:16
 */
public class HashMapTest {

    public static void main(String[] args) {
        HashMap<Student, String> hashMap=new HashMap<Student, String>();
        Student s1=new Student("tang", 36);
        Student s2=new Student("yu", 101);
        Student s3=new Student("he", 10);
        //1.添加元素
        hashMap.put(s1, "成都");
        hashMap.put(s2, "杭州");
        hashMap.put(s3, "郑州");
        System.out.println(hashMap.toString());
        //添加失败，但会更新值
        System.out.println(hashMap.put(s3, "上海"));
        System.out.println(hashMap.toString());
        System.out.println("-------------------------------------------");
        //添加成功，不过两个属性一模一样；
        //注：假如相同属性便认为是同一个对象，怎么修改？
        hashMap.put(new Student("he", 10),"上海");
        System.out.println(hashMap.toString());
        System.out.println("-------------------------------------------");

        //2.删除元素
        hashMap.remove(s3);
        System.out.println(hashMap.toString());
        System.out.println("-------------------------------------------");

        //3.遍历
        //3.1 使用keySet()遍历
        for (Student key : hashMap.keySet()) {
            System.out.println(key+" "+hashMap.get(key));
        }
        System.out.println("-------------------------------------------");

        //3.2 使用entrySet()遍历
        for (Map.Entry<Student, String> entry : hashMap.entrySet()) {
            System.out.println(entry.getKey()+" "+entry.getValue());
        }
        System.out.println("-------------------------------------------");

        //4.判断
        //注：同上
        System.out.println(hashMap.containsKey(new Student("he", 10)));
        System.out.println(hashMap.containsValue("成都"));
        System.out.println("-------------------------------------------");

    }
}
class Student {
    private String name;
    private int id;
    public Student(String name, int id) {
        super();
        this.name = name;
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    @Override
    public String toString() {
        return "Student [name=" + name + ", age=" + id + "]";
    }
}