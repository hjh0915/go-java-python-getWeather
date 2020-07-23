/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package Jweather;

import java.util.*;

import Jweather.com.work.JsonClient;
import Jweather.com.work.JsonConvert;
import Jweather.com.work.Weather;

import java.io.IOException;

public class App {
    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();    //获取开始时间
        HashMap<String, String> hmap = Utils.initData();
        JsonClient jsonClient = new JsonClient();
        JsonConvert jsonConvert = new JsonConvert();

        for (String citycode: hmap.values()) {
            String cityUrl = jsonClient.getUrl(citycode);
            try {
                String cityVisit = jsonClient.visit(cityUrl);
                Weather w = jsonConvert.deal(cityVisit);

                System.out.println(w.getCity());
                System.out.println(w.getData().get(0));
            } catch (Exception e) {
            } 
        }
        long endTime = System.currentTimeMillis();    //获取结束时间
        System.out.println("程序运行时间：" + (endTime - startTime) + "ms");    //输出程序运行时间
    }
}