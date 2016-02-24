#include <stdlib.h>
#include <stdio.h>
#include "UUI.h"

int main(){
  char *value;
  printf("Enter a value: ...");
  value = readUUI();
  printf("%s", value);
  free(value);

  return 0;
}
