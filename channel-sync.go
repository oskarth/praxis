package main

import "time"
import "fmt"

func worker(done chan bool) {
  fmt.Print("working...")
  time.Sleep(time.Second)
  fmt.Println("done")

  done <- true
}

func main() {
  done := make(chan bool, 1)
  go worker(done)
  
  fmt.Println("non-block stuff")

  <- done
}
