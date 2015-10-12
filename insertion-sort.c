// Insertion sort
// http://programmingpraxis.com/2009/10/27/three-quadratic-sorts/
//  Insertion sort works the same way that card players generally sort their
//  hands; starting from an empty hand, they pick up a card, insert it into the
//  correct position, then repeat with each new card until no cards remain.

#include <stdio.h>

void swap(int *arr, int i, int j) {
  int temp;
  temp = arr[i];
  arr[i] = arr[j];
  arr[j] = temp;
}

void insertion_sort(int *arr, int n) {
  int i, j;

  for (i = 0; i < n; i++) {
    j = i;
    while (j > 0 && arr[j-1] > arr[j]) {
      swap(arr, j-1, j); 
      j--;
    }
  }
}

void print_array(int *arr, int n) {
  int i;
  
  for (i = 0; i < n; i++) {
    printf("%d ", arr[i]);
  }
  printf("\n");
}

int main() {
  int arr[] = {5, 7, 2, 3, 1, 4};
  int n;

  n = sizeof(arr)/sizeof(arr[0]);
  print_array(arr, n);
  insertion_sort(arr, n);
  print_array(arr, n);
}
