//package Map;
///*
// * Editor: 张恒
// * tel： XXXXXXXXXXXXX
// * Wechat：zh17530588817
// * date: 2021/4/17/15:34
// */
//public class TreeMap {
//    public static void main(String[] args) {
//        TreeMap<Student, Integer> treeMap=new TreeMap<Student, Integer>();
//        Student s1=new Student("tang", 36);
//        Student s2=new Student("yu", 101);
//        Student s3=new Student("he", 10);
//        //1.添加元素
//        treeMap.put(s1, 21);
//        treeMap.put(s2, 22);
//        treeMap.put(s3, 21);
//        //不能直接打印，需要实现Comparable接口，因为红黑树需要比较大小
//        System.out.println(treeMap.toString());
//        //2.删除元素
//        treeMap.remove(new Student("he", 10));
//        System.out.println(treeMap.toString());
//        //3.遍历
//        //3.1 使用keySet()
//        for (Student key : treeMap.keySet()) {
//            System.out.println(key+" "+treeMap.get(key));
//        }
//        //3.2 使用entrySet()
//        for (Entry<Student, Integer> entry : treeMap.entrySet()) {
//            System.out.println(entry.getKey()+" "+entry.getValue());
//        }
//        //4.判断
//        System.out.println(treeMap.containsKey(s1));
//        System.out.println(treeMap.isEmpty());
//    }
//}
