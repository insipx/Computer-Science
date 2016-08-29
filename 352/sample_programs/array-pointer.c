/* Program:     String Arrays and Pointers in C
   Author:      Y. Bi
   Date:        August 22, 2016
   File name:   array-pointer.c
   Compile:     cc -o array-pointer array-pointer.c
   Run:         array-pointer

   The program accepts a sequence of a char string and an integer, 
   and prints them from variable structures. 

   The program is intended to show to how to handle char arrays and
   pointers.
*/
#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <fcntl.h>
#include <sys/types.h>
#include <sys/uio.h>
#include <sys/stat.h>

/******************************************************************
A string must end with a '\0', so you must allocate an extra cell
in each array to hold this special symbol. 

When a pointer is declared, only 8-bytes of space is allocated for
the pointer to point to an object. for example, Student* ptr, 
declares a pointer to a Student. ptr's value is the address of
the object of Student. If not assigned, the pointer may have a
null or invalid value - pointing to an invalid address. This works
the same way for primitive data types such as int, char, double. 
for example: we may declare:

int x = 30000;
int* ptr2Int;
ptr2Int = &x;  // the & operator - returning the address of the operand

now ptr2Int points to variable x, or the value of ptr2Int is the 
address of variable x. 

You CANNOT compare two arrays directly using the == operator 
to determine if two arrays contain the same strings or not, you
will need to compare char by char. 
******************************************************************/
typedef struct {
   char string[10];        //edu: it can store up to 9 characters
   int integer;
} Structure;;
   
int main() {
   char string[10];       // edu: it can store up to 9 characters
   int integer;

   Structure aStructure;
   Structure anArrayOfStructure[2];
   Structure* anArrayOfPointers2Structure[10];
   Structure* aPointer2Structure;

   int i;

   //edu: read a string and an integer separated by white spaces, 
   //     then print them on screen
   scanf("%s%d", string, &integer);
   printf("output: %s, %d\n", string, integer);

   //edu: read a string and an integer into a Structure
   //     then print them from the structure.
   scanf("%s%d", aStructure.string, &aStructure.integer);
   printf("output: %s, %d\n", aStructure.string, aStructure.integer);

   //edu: read a string and an integer into a Structure in an array
   //     then print them from the structure in the array.
   scanf("%s%d", anArrayOfStructure[0].string, &anArrayOfStructure[0].integer);
   printf("output: %s, %d\n", anArrayOfStructure[0].string, anArrayOfStructure[0].integer);

   //edu: dynamically allocate space for a structure and read input to it
   //     then print the structure.
   aPointer2Structure = (Structure*) malloc(sizeof(Structure));
   scanf("%s%d", aPointer2Structure->string, &(aPointer2Structure->integer));
   printf("output: %s, %d\n", aPointer2Structure->string, aPointer2Structure->integer);
   free(aPointer2Structure);  // NECESSARY - otherwise memory leak

   //edu: dynamically allocate a structure and assign it to an array
   aPointer2Structure = (Structure*) malloc(sizeof(Structure));
   anArrayOfPointers2Structure[3] = aPointer2Structure;
   scanf("%s%d", anArrayOfPointers2Structure[3]->string, 
                 &(anArrayOfPointers2Structure[3]->integer));
   printf("output: %s, %d\n", anArrayOfPointers2Structure[3]->string, 
                 anArrayOfPointers2Structure[3]->integer);

   //edu: assign a dynamically allocated structure to an array
   anArrayOfPointers2Structure[5] = (Structure*) malloc(sizeof(Structure));
   scanf("%s%d", anArrayOfPointers2Structure[5]->string, 
                 &(anArrayOfPointers2Structure[5]->integer));
   printf("output: %s, %d\n", anArrayOfPointers2Structure[5]->string, 
                 anArrayOfPointers2Structure[5]->integer);
   free(anArrayOfPointers2Structure[3]);
   free(anArrayOfPointers2Structure[5]);

   //edu: you CANNOT assign one char array to another. you will need to 
   //     copy char by char from the source array to the destination.
   scanf("%s%d", string, &integer);
   printf("output: %s, %d\n", string, integer);
   // aStructure.string = string; -- this would not compile
   for (i=0; i<10 && string[i] != '\0'; i++) {
      printf("index - char: %d -- %c\n", i, string[i]);
      aStructure.string[i] = string[i];
   }
   aStructure.string[i] = string[i];  // copy '\0' 
   aStructure.integer = integer;
   printf("output: %s, %d\n", aStructure.string, aStructure.integer);
}
