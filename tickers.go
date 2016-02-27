package main

import "time"
import "fmt"

// this one atm.
func main() {
  ticker := time.NewTicker(time.Millisecond * 500)
  go func() {
  // ticker.C?
    for t := range ticker.C {
      fmt.Println("Foo", &ticker.C)
      fmt.Println("Tick at", t)
    }
  }()

  time.Sleep(time.Millisecond * 1600)
  ticker.Stop()
  fmt.Println("Ticker stopped")
}
