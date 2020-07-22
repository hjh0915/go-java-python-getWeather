package Jweather.com.work;

import java.util.*;
import java.util.concurrent.*;

import java.io.IOException;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class JsonClient {
    final String APIURL = "http://www.tianqiapi.com/api/?version=v1&cityid=";
    final String appid = "68364224";
    final String appsecret = "2BgyEsir";

    OkHttpClient client = new OkHttpClient();
    BlockingQueue<String> queue = new LinkedBlockingQueue<String>();

    class MyThread extends Thread {
        String url;
        String cityname;
        public MyThread(String cityname, String url) {
            this.cityname = cityname;
            this.url = url;
        }

        public String getCityName() {
            return this.cityname;
        }

        @Override
        public void run() {
            try {
                //System.out.println(this.cityname);
                String x = visit(this.url);
                queue.offer(x);
            } catch (IOException e) {
                //System.out.println("789");
            }
        }
    }

    public String visit(String url) throws IOException {
        Request request = new Request.Builder()
            .url(url)
            .build();

        try (Response response = this.client.newCall(request).execute()) {
            return response.body().string();
        }
    }

    public List<String> getResults(HashMap<String, String> hmap) throws IOException, InterruptedException {
        List<String> al = new ArrayList<String>(); 
        List<MyThread> results = new ArrayList<MyThread>();

        for (Map.Entry<String, String> me : hmap.entrySet()) {
            MyThread t = new MyThread(me.getKey(), this.getUrl(me.getValue()));
            t.start();
            results.add(t);
        }

        for (MyThread x : results) {
            x.join();
        }

        while (queue.peek() != null) {
            al.add(queue.poll());
        }
        return al;
    }

    public String getUrl(String citycode) {
        return APIURL+citycode+"&appid="+appid+"&appsecret="+appsecret; //添加密钥
    }
}