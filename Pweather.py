import threading
from queue import Queue 
import datetime
import requests

CITY = {
    '天津': '101030100',
    '南昌': '101240101',
    '北京': '101010100',
    '上海': '101020100',
    '香港': '101320101',
    '澳门': '101330101',
    '杭州': '101210101',
    '苏州': '101190401',
    '南京': '101190101'
}

class Weather(object):
    def __init__(self):
        self.q = Queue()

    def get_all_cities_weather(self, cities: dict):
        threads = []
        for k, v in cities.items():
            t = threading.Thread(target=self.get_city_weather, args=(v,))
            t.start()
            threads.append(t)
 
        #等待线程结束
        for i in threads:
            i.join()
        
        results = []
        while not self.q.empty():
            results.append(self.q.get())
        
        results.sort(key=lambda x: x['cityid'])
        return results

    def get_city_weather(self, citycode: str):
        '''根据城市代码获取城市天气'''

        url = 'http://www.tianqiapi.com/api/?version=v1&cityid={0}&appid=68364224&appsecret=2BgyEsir'.format(citycode)
        r = requests.get(url)

        self.q.put(r.json())

if __name__ == '__main__':
    start = datetime.datetime.now()
    w = Weather()
    cities = w.get_all_cities_weather(CITY)
    for c in cities:
        result = '城市: {city}, 时间: {time}, 温度: {temp}'.format(
            city=c['city'],
            time=c['data'][0]['date'], 
            temp=c['data'][0]['wea']
        )
        print(result)
    end = datetime.datetime.now()
    print(end-start)