package List;

import java.util.Enumeration;
import java.util.Vector;

/*
 * Editor: 张恒
 * tel： XXXXXXXXXXXXX
 * Wechat：zh17530588817
 * date: 2021/4/17/9:20
 */
public class VectorTest {
    public static void main(String[] args) {
        Vector vector=new Vector<>();
        //1.添加数据
        vector.add("tang");
        vector.add("he");
        vector.add("yu");
        System.out.println("元素个数："+vector.size());

        //2.删除数据

         vector.remove(0);
         vector.remove("tang");

        //3.遍历
        //使用枚举器
        Enumeration enumeration=vector.elements();
        while (enumeration.hasMoreElements()) {
            String s = (String) enumeration.nextElement();
            System.out.println(s);
        }
        //4.判断
        System.out.println(vector.isEmpty());
        System.out.println(vector.contains("he"));
        //5. Vector其他方法
        //firstElement()  lastElement()  ElementAt();
    }
}
