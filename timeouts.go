package main

import "time"
import "fmt"

func main() {
  ch1 := make(chan string, 1)
  ch2 := make(chan string, 1)

  go func() {
    time.Sleep(time.Second * 2)
    ch1 <- "res 1"
  }()

  go func() {
    time.Sleep(time.Second * 2)
    ch2 <- "res 2"
  }()

  select {
    case res := <-ch1:
      fmt.Println(res)
    case <-time.After(time.Second * 1):
      fmt.Println("timeout 1")
  }

  select {
    case res := <-ch2:
      fmt.Println(res)
    case <-time.After(time.Second * 3):
      fmt.Println("timeout 2")
  }

}
