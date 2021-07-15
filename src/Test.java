import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/*
 * Editor: 张恒
 * tel： XXXXXXXXXXXXX
 * Wechat：zh17530588817
 * date: 2021/4/17/15:37
 */
public class Test {
    public static void main(String[] args) {
        List<Integer> list=new ArrayList<Integer>();
        list.add(20);
        list.add(10);
        list.add(30);
        list.add(90);
        list.add(70);

        //sort排序
        System.out.println("排序前：" + list.toString());
        Collections.sort(list);
        System.out.println("排序后：" + list.toString());
        System.out.println("---------");

        //binarySearch二分查找
        int i=Collections.binarySearch(list, 10);
        System.out.println(i);
        System.out.println("---------");
        //copy复制
        List<Integer> list2=new ArrayList<Integer>();
        for(int i1=0;i1<5;++i1) {
            list2.add(0);
        }
        //该方法要求目标元素容量大于等于源目标
        Collections.copy(list2, list);
        System.out.println(list2.toString());
        System.out.println("---------");
        //reserve反转
        Collections.reverse(list2);
        System.out.println(list2.toString());
        System.out.println("---------");
        //shuffle 打乱
        Collections.shuffle(list2);
        System.out.println(list2.toString());
        System.out.println("---------");
        //补充：list转成数组
        Integer[] arr=list.toArray(new Integer[0]);
        System.out.println(arr.length);
        //补充：数组转成集合
        String[] nameStrings= {"tang","he","yu"};
        //受限集合，不能添加和删除
        List<String> list3= Arrays.asList(nameStrings);
        System.out.println(list3);
        System.out.println("---------");

        //注：基本类型转成集合时需要修改为包装类
    }
}
