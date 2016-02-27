package main

import "time"
import "fmt"

func main() {
  ch1 := make(chan string, 1)

  go func() {
    time.Sleep(time.Second *2)
    ch1 <- "res 1"
  }()

  select {
    case res := <-ch1:
      fmt.Println(res)
    case <-time.After(time.Second):
      fmt.Println("timeout")
  }

}
