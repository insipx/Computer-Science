#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <string.h>
#include <errno.h>
#include <fcntl.h>


int main(int argc, char *argv[]){
  
  char *getlog;
  getlog = (char*)malloc(50);
  memset(getlog,0, 50);
  getlog = getlogin();
  if(!getlog) 
    perror("getlogin() error");
  else
    printf("%s", getlog);
  return 0;
}

