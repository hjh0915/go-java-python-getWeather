package main

import (
	"encoding/json"
	"fmt"
	"io/ioutil"
	"net/http"
)

const (
	URL = "http://www.tianqiapi.com/api/?version=v1"
	appid = "68364224"
	appsecret = "2BgyEsir"
)

type Weather struct {
	Cityid string `json:"cityid"`
	Update_time string `json:"update_time"`
	City string `json:"city"`
	CityEn string `json:"cityEn"`
	Country string `json:"country"`
	CountryEn string `json:"countryEn"`

	Data []MyData `json:"data"`
}

type MyData struct {
	Day string `json:"day"`
	Date string `json:"date"`
	Week string `json:"week"`
	Wea string `json:"wea"`
	Wea_img string `json:"wea_img"`
	Air int `json:"air"`
	Humidity int `json:"humidity"`
	Air_level1 string `json:"air_level1"`
	Air_tips string `json:"air_tips"`
	Tem1 string `json:"tem1"`
	Tem2 string `json:"tem2"`
	Tem string `json:"tem"`
	Win_speed string `json:"win_speed"`
	Win []string `json:"win"`

	Alarm Alarm `json:"alarm"`
	Hour []Hour `json:"hour"`
	Index []Index `json:"index"`
}

type Alarm struct {
	Alarm_type string `json:"alarm_type"`
	Alarm_level string `json:"alarm_level"`
	Alarm_content string `json:"alarm_content"`
}

type Hour struct {
	Day string `json:"day"`
	Wea string `json:"wea"`
	Win string `json:"win"`
	Win_speed string `json:"win_speed"`
}

type Index struct {
	Title string `json:"title"`
	Level string `json:"level"`
	Desc string `json:"desc"`
}


func getWeather(citycode string) {
	url := fmt.Sprintf("%s&cityid=%s&appid=%s&appsecret=%s",URL,citycode,appid,appsecret)
	resp, err := http.Get(url)
	if err != nil {
		return
	}

	defer resp.Body.Close()
	input, err1 := ioutil.ReadAll(resp.Body)    //读取数据流
	if err1 != nil {
		return
	}

	var weather Weather
	err2 := json.Unmarshal(input, &weather)   //解析json数据
	if err2 != nil {
		return
	}

	if len(weather.City) != 0 {   //判断有无解析数据
		for i := 0; i < 3; i++ {
			fmt.Printf("城市:%s 时间:%s 温度:%s 天气:%s\n", 
				weather.City, weather.Data[i].Date, weather.Data[i].Tem, weather.Data[i].Wea)
		}
    }
}

func main(){
	getWeather("101240101")
}