#ifndef TRUE
#define TRUE 1
#else
#endif
#ifndef FALSE
#define FALSE 0
#else
#endif
#include <stdio.h>
#include <stdlib.h>


void destroy(char* str){
  free(str);
}
//this needs to be dynamic, we have no idea what the user will input
//so our best bet is to use getchar()
char * readUUI()
{
  //allocate 64bytes for usage
  unsigned int size = 64;
  char *str = (char*)malloc(size);
  char c;
  int i = 0;
  do
  {
    c = getchar(); 
    str[i] = c;
    i++;
    //reallocate memory by a factor of 1.5
    //this isn't the most mem-efficient method but it should use less cycles
    if(i >= size){
      size = size * 1.5;
      str = (char *) realloc(str, size);
    }
  
  }while(c != '\0' || c != EOF);
  
  return str;
}
