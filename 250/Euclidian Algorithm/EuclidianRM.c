#include <stdio.h>
int gcd(int p, int q);
void DumpS(int n);

int main() {

  int p, q, result;
  printf("Enter an Integer");
  scanf("%d", &p);
  printf("Enter another Integer");
  scanf("%d",&q);
  DumpS(64);
  result = gcd(p, q);
  DumpS(64);
  printf("the GCD of %d and %d is %d\n", p, q, result);

}

