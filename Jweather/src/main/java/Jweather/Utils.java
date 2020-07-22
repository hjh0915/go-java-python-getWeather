package Jweather;

import java.util.HashMap;

class Utils {
    public static HashMap<String, String> initData() {
        HashMap<String, String> map = new HashMap<String, String>();    
        map.put("天津", "101030100");
        map.put("南昌", "101240101");
        map.put("北京", "101010100");
        map.put("上海", "101020100");
        map.put("香港", "101320101");
        map.put("澳门", "101330101");
        map.put("杭州", "101210101");
        map.put("苏州", "101190401");
        map.put("南京", "101190101");

        return map;
    }
}