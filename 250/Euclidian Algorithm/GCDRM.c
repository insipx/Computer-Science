/*This is a Solution to Assignment 2 in CMPS 250 for Spring 2016
 *
 * I, Andrew Plaza, am the sole developer of this program
 * I worked alone on this assignment
 *
 * Buffer Overflow is possible because of the static use of scanf() as opposed to a 
 * method which dynamically allocates memory for user input. Also, user input comes 
 * in unsanitized.
 *
 */

#include <stdio.h>
int gcd(int p, int q);
void DumpS(int n);

int main() {

  int p, q, result;
  printf("Enter an Integer: ");
  scanf("%d", &p);
  printf("Enter another Integer: ");
  scanf("%d",&q);
  DumpS(64);
  result = gcd(p, q);
  DumpS(64);
  printf("the GCD of %d and %d is %d\n", p, q, result);

}

