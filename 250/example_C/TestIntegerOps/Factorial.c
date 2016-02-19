#include <stdio.h>
int main()  {
   int result;
   int fact;
   int calc;
   printf("PMJ's Factorial.c (Spring 2016) ...\n");
   printf("Enter an integer:");
   scanf("%d", &calc);
   fact = calc;
   result = 1;
   while(calc >= 1) {
      result = result * calc;
      calc = calc - 1;
   }
   printf("%d%s%d\n", fact,"! = ",result);
}
