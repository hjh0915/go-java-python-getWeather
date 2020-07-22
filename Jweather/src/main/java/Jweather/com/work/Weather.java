package Jweather.com.work;

import java.util.*;
import java.util.stream.Collectors;

import lombok.Data;

@Data
public class Weather {
    String cityid;
    String update_time; 
    String city;
    String cityEn;
    String country;
    String countryEn;

    List<MyData> data;

    public List<MyData> getNotToday() {
        return data.stream().filter(x -> (!x.day.contains("今天"))).collect(Collectors.toList());
    }
}

@Data
class MyData {
    public String day;
    public String date;
    public String week;
    public String wea;
    public String wea_img;
    public int air;
    public int humidity;
    public String air_leve1;
    public String air_tips;

    public Alarm alarm;

    public String tem1;
    public String tem2;
    public String tem;

    public List<String> win;

    public String win_speed;

    public List<Hour> hours;
    public List<Index> indexes;


}

@Data
class Alarm {
    public String alarm_type;
    public String alarm_level;
    public String alarm_content;
}

@Data
class Hour {
    public String day;
    public String wea;
    public String tem;
    public String win;
    public String win_speed;

    public String getTime() {
        int d = day.indexOf("日");
        int len = day.length();

        return day.substring(d+1, len);
    }
}

@Data
class Index {
    public String title;
    public String level;
    public String desc;
}