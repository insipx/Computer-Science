/* Program:	Hello World
   author:	Andrew Plaza
   Date:	Aug. 22, 2016
   file name:   asgn1-plazaa2.c
   compile:	gcc -o asgn1 asgn1-plazaa2.c
   run:		./asgn1

   This C program accepts user's firstname and a positive
   integer N; it then prints a personal greeting N times.
*/
#include <stdio.h>	// this is equivalent to Java's import

int main()   {
    char name[20];	// A string of chars, <= 19 chars
    int times;
    int i = 0;
    
    printf("Please enter your firstname and a number:\n");
    scanf("%s%d", name, &times);

    // print greeting
    for (i = 0; i < times; i++){
        printf("Hello %s, your first program works!\n", name);
    }
}

