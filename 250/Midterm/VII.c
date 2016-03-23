//Sean Batzel
//Solution to midterm question VII

#include <stdlib.h>
#include <stdio.h>
#include <string.h>
#define LIMIT 128
#define NC '\0'

#define OLD 'a'
#define NEW 'X'

void replaceAll(char *array, char c, char replacement) {
  int index = 0;
  while(array[index] != NC) {
    if(array[index] == c) {
      array[index] = replacement;
    }
    index = index + 1;
  }
}

void removeAll(char *array, char c) {
   int index = 0;
   while(array[index] != NC){
	   if(array[index] == c){
		   int i = index;
		   while(array[index] != NC){
			   if (array[i+1] == NC){ 
				   array[i] = NC;
				   break;
			   }
			   else { 
				   array[i] = array[i + 1];
			   }
			   i++;
		   }
	   }
	   index++;
   }
}

int main()  {
  char buffer[LIMIT];
  int length;
  do {
    printf("Enter:");
    scanf("%s",buffer);
    length = strlen(buffer);
    if(length > 0) {
       printf("\"%s\"",buffer);
       replaceAll(buffer,OLD,NEW);
       printf(" becomes \"%s\"",buffer);
       removeAll(buffer,NEW);
       printf(" then \"%s\"\n",buffer);
    }
  } while(length > 0);
}
