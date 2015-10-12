// Selection sort
// http://programmingpraxis.com/2009/10/27/three-quadratic-sorts/
// Selection sort works by repeatedly passing through the array, at each pass
// finding the minimum element of the array, interchanging it with the first
// element of the array, then repeating on the sub-array that excludes the first
// element of the array. 

#include <stdio.h>
#include <stdbool.h>

void swap(int *arr, int i, int j) {
  int temp;

  temp = arr[i];
  arr[i] = arr[j];
  arr[j] = temp;
}

int min_index(int *arr, int start, int end) {
  int i, min_index;

  min_index= start;
  for (i = start; i < end; i++) {
    if (arr[i] < arr[min_index]) {
      min_index = i;
    }
  }
  return min_index;
}

void selection_sort(int *arr, int n) {
  int i;

  for (i = 0; i < n; i++) {
    swap(arr, i, min_index(arr, i, n));
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
  int n;
  int arr[] = {3, 5, 2, 1, 6};

  n = sizeof(arr)/sizeof(arr[0]);
  print_array(arr, n);
  selection_sort(arr, n); 
  print_array(arr, n);
}
