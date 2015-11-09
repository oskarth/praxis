// reverse string 1226, 9m
// not optimal soln? seems a bit borky
#include <stdio.h>
#include <string.h> // didn't use myself

void reverse_string(char s[], int n) {
  int i, j;
  char temp;
  // use of strlen takes care of -1
  for (i = 0, j=n-2; i < j; i++, j--) {
    temp = s[i];
    s[i] = s[j];
    s[j] = temp;
  }
}

// ANSWER from K&R - could also use int for s[i]?
void reverse(char s[]) {
  int c, i, j;
  for (i = 0, j = strlen(s) - 1; i < j; i++, j--) {
    c = s[i], s[i] = s[j], s[j] = c;
  }
}

int main() {
  int n;
  char s[] = "foobar";

  n = sizeof(s)/sizeof(s[0]);
  printf("strlen %lu\n", strlen(s));
  printf("%s\n", s);
  //reverse_string(s, n);
  reverse(s);
  printf("%s\n", s);
}
