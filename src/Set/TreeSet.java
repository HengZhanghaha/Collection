//package Set;
//
///*
// * Editor: 张恒
// * tel： XXXXXXXXXXXXX
// * Wechat：zh17530588817
// * date: 2021/4/17/10:55
// */
//public class TreeSet<Person1> {
//    public void main(String[] args) {
//        TreeSet<Person1> persons=new TreeSet<Person1>();
//        Person p1=new Person("tang",21);
//        Person p2=new Person("he", 22);
//        Person p3=new Person("yu", 21);
//        //1.添加元素
//        persons.add(p1);
//        persons.add(p2);
//        persons.add(p3);
//        //注：直接添加会报类型转换错误，需要实现Comparable接口
//        System.out.println(persons.toString());
//        //2.删除元素
//        persons.remove(p1);
//        persons.remove(new Person("he", 22));
//        System.out.println(persons.toString());
//        //3.遍历（略）
//        //4.判断
//        System.out.println(persons.contains(new Person("yu", 21)));
//    }
//}
//class Person1 implements Comparable<Person1>{
//    private String name;
//    private int age;
//    public Person1(String name,int age) {
//        this.name = name;
//        this.age = age;
//    }
//    public String getName() {
//        return name;
//    }
//    public void setName(String name) {
//        this.name = name;
//    }
//    public int getAge() {
//        return age;
//    }
//    public void setAge(int age) {
//        this.age = age;
//    }
//    @Override
//    public String toString() {
//        return "Peerson [name=" + name + ", age=" + age + "]";
//    }
//
//
//    @Override
//    public int compareTo(Person1 o) {
//        int n1=this.getName().compareTo(o.getName());
//        int n2=this.age-o.getAge();
//        return n1==0?n2:n1;
//    }
//}