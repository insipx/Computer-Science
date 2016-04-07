#include <stdlib.h>
#include <stdio.h>
#include <string.h>
#define LIMIT 128
#define NC '\0'

#define OLD 'a'
#define NEW 'X'

/*
 * This is a Solution to problem VII on the Midterm in CMPS 250 for Spring 2016
 * I, Andrew Plaza, am the sole developer of this work
 * I worked Alone
 * No flaws of which I am aware
*/

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
 
 int count = 0;
 for(int i = 0; i < strlen(array); i++){
  if(array[i] == c){
    count++;
  }
 } 
 
 char *result = (char*)malloc(strlen(array)-count);

 int j = 0; 
 for(int i = 0; i < strlen(array); i++){
  if(array[i] == c){
    
  }else{
    result[j] = array[i];
    j++;  
  }
 }

 strcpy(array, result); 
 free(result);
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
