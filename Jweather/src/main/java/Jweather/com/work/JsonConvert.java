package Jweather.com.work;

import java.util.*;
import com.google.gson.Gson;

public class JsonConvert {

    public static Weather deal(String jsonStr) {
        Gson gson = new Gson();
        Weather w = gson.fromJson(jsonStr, Weather.class);
        return w;
    }
}
