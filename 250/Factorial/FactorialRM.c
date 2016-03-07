#include <stdio.h>
int factorial(int n);
void DumpS(int n);

int main()  {
   int n, f;
   printf("Enter an integer:");
   scanf("%d",&n);
   DumpS(64);
   f = factorial(n);
   DumpS(64);
   printf("%d! is %d\n",n,f);
}
