#include <stdio.h>
#include "IntegerOps.h"
#define LIMIT 16

int readA(int a[]) {
  int n = -1;
  while((n < 0) || ((n < (LIMIT-1)) && (a[n] != 0))) {
    n = n + 1;
    scanf("%d",&a[n]);
  }
  return n;
}

void printA(int a[], int lower, int upper) {
  int i;
  for(i=lower; i<upper; i++) {
    printf("%d:%d",i,a[i]);
    if(i < (upper-1)) printf(",");
  }
}

int main() {
  int a[LIMIT];
  int n = readA(a);
  int i;
  printA(a,0,n);  printf("\n");

  if(n > 2) {
    printf("Max of %d and %d is %d\n",a[0],a[1],Max(a[0],a[1]));
    printf("Min of %d and %d is %d\n",a[0],a[1],Min(a[0],a[1]));
    printf("Max of %d, %d and %d is %d\n",a[0],a[1],a[2],Max3(a[0],a[1],a[2]));
    printf("Min of %d, %d and %d is %d\n",a[0],a[1],a[2],Min3(a[0],a[1],a[2]));

    for(i=n; i>0; i--) {
      printf("For ");  printA(a,0,i);  printf("\n"); 
      printf("    Max is %d, Min is %d\n",MaxA(a,0,i),MinA(a,0,i));
    }
  }
}
