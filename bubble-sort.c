#include <stdbool.h>
#include <stdio.h>

// Bubble sort
// http://programmingpraxis.com/2009/10/27/three-quadratic-sorts/

void swap(int *arr, int i, int j) {
  int temp;
  temp = arr[i];
  arr[i] = arr[j];
  arr[j] = temp;
}

void bubble_sort(int *arr, int n) {
  int i;
  bool modded;
  modded = true;

  while (modded) {
    modded = false;
    for (i = 0; i < (n-1); i++) {
      if (arr[i+1] < arr[i]) {
        swap(arr, i, i+1);
        modded = true;
      }
    }
  }
}

void print_arr(int *arr, int n) {
  int i;

  for (i = 0; i < n; i++) {
    printf("%d ", arr[i]);
  }
  printf("\n");
}

int main() {
  int n;
  int arr[] = { 3, 7, 5, 10, 2, 1 };

  n = sizeof(arr) / sizeof(arr[0]);
  print_arr(arr, n);
  bubble_sort(arr, n);
  print_arr(arr, n);
}
