#include <stdlib.h>
#include <stdio.h>
#include "UUI.h"

int main()  {
  char *SENTINEL = newUUI("0");
  char *value;
  char *accumulator = newUUI("0");
  printf("SENTINEL is ") ; printUUI(SENTINEL);  printf("\n");
  printf("Accumulator is ") ; printUUI(accumulator);  printf("\n");
  do {
    printf("Enter an UUI:");
    value = readUUI();
    //printUUI(value); printf("\n");
    if(NE(value,SENTINEL)) {
      accumulator = sum(accumulator,value);
      printf("Current accumulation is "); printUUI(accumulator);   printf("\n");
      printf("Which is equivalent to "); printUUIf(accumulator);  printf("\n");
    }
  } while(NE(value,SENTINEL));
  printf("Final accumulation is   "); printUUI(accumulator);   printf("\n");
  printf("Which is equivalent to  "); printUUIf(accumulator);  printf("\n");
}

