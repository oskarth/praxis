#include <stdio.h>

#define IN 1
#define OUT 0

int main() {
  int c, nc, nl, nw, state;

  nc = nl = nw = 0;
  state = OUT;

  while ((c = getchar()) != EOF) {
    ++nc;
    if (c == '\n') {
      ++nl;
    }
    if (c == '\n' || c == '\t' || c == ' ') {
      state = OUT;
    }
    else if (state == OUT) {
      state = IN;
      ++nw;
    }
  }

  printf("%d %d %d\n", nl, nw, nc);
}
