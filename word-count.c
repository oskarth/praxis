#include <stdio.h>

#define IN 1
#define OUT 0

int main() {
  int c, nl, nc, nw, state;
  nl = nc = nw = 0;

  state = OUT;
  while ((c = getchar()) != EOF) {
    nc++;
    if (c == '\n')
      nl++;
    if (c == ' ' || c == '\t' || c == '\n')
      state = OUT;
    else if (state == OUT) {
      state = IN;
      nw++;
    }
  }
  printf("%d %d %d\n", nl, nw, nc);
}