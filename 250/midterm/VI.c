#include <stdlib.h>
#include <stdio.h>

int main(){
  int w = 1000;
  int x = 1;
  int y = 1;
  int z;

  printf("%d%s", x, "\n");
  printf("%d%s", y, "\n");

  while(x+y < w){
    z = x+y;
    printf("%d%s", z, "\n");
    x = y;
    y = z;
  }


}

