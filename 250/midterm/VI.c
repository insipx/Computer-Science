#include <stdlib.h>
#include <stdio.h>

/*
 * This is a Solution to problem VI on the Midterm in CMPS 250 for Spring 2016
 * I, Andrew Plaza, am the sole developer of this work
 * I worked Alone
 * No flaws of which I am aware
*/
 

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

