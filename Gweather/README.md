并发获取天气(waitgroup)
=====================
```go
wg.Done()

for _, value := range cmap {
    wg.Add(1)
    go getWeather(value, &wg)
}

wg.Wait()
```