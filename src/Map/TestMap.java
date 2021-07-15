package Map;

import java.util.HashMap;
import java.util.Map;

/*
 * Editor: 张恒
 * tel： XXXXXXXXXXXXX
 * Wechat：zh17530588817
 * date: 2021/4/17/11:05
 */
public class TestMap {
    public static void main(String[] args) {
        Map<String,Integer> map=new HashMap<String, Integer>();
        //1.添加元素
        map.put("tang", 21);
        map.put("he", 22);
        map.put("fan", 23);
        System.out.println(map.toString());
        //2.删除元素
        map.remove("he");
        System.out.println(map.toString());
        //3.遍历
        //3.1 使用keySet();
        for (String key : map.keySet()) {
            System.out.println(key+"="+map.get(key));
        }
        //3.2 使用entrySet();效率较高
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            System.out.println(entry.getKey()+" ="+entry.getValue());
        }
    }
}
