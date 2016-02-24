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
void die(const char *message)
{
  if(message){
    printf("ERROR: %s\n", message);
  }else{
  }
  exit(1);
}

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
  if(str){
  }else{
    die("Mem allocate unsuccessful");
  }

  unsigned char c;
  int i = 0;
  do
  {
    c = fgetc(stdin); 
    str[i] = (char) c;
    i++;
    //reallocate memory by a factor of 1.5
    //this isn't the most mem-efficient method but it should use less cycles
    if(i >= size){
      size = size * 2;
      str = (char*) realloc(str, size);
    }
    if(c == '\n' || c == EOF){
      str[i] = '\0';
      break;
    }
  
  }while(1);
  
  return str;
}
