获取网站天气预报
=============
1、stuct嵌套
-----------
```go
Data []MyData `json:"data"`

Win []string `json:"win"`
```

2、补充url
---------
```go
url := fmt.Sprintf("%s&cityid=%s&appid=%s&appsecret=%s",URL,citycode,appid,appsecret)
```

3、获取网站信息
-------------
```go
resp, err := http.Get(url)
if err != nil {
	return
}
defer resp.Body.Close()
```

4、读取数据流
-----------
```go
input, err1 := ioutil.ReadAll(resp.Body)
if err1 != nil {
    return
}
```

5、解析json数据
--------------
```go
var weather Weather
err2 := json.Unmarshal(input, &weather)
if err2 != nil {
    return
}
```
Weather是type Weather struct数据结构中的Weather，weather是变量名
Marshal：将数据编码成json字符串
Unmarshal：将json字符串解码到相应的数据结构。第一个参数是json字符串，第二个参数是接受json解析的数据结构