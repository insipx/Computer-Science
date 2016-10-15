/* Program: Programming Assignment 5, Create a Shell
 * author: Andrew Plaza
 * Date: Oct 15, 2016
 * File Name: asgn5-plazaa2.c
 * compile: cc -o asgn5.out asgn5-plazaa2.c -g -Wall
 * run: ./asgn5.out
 * debug: gdb ./asgn5.out
 * 
 * DESCRIBE PROGRAM HERE
 */

#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <string.h>
#include <errno.h>
#include <fcntl.h>


//method definitions
char *readln();
int retLogin(char *getlog);
int is_whitespace(char c);
void die(const char *message);

int main(int argc, char *argv[]){
  char *cmd;
  //max login length is 32 bytes, 33 for good luck
  char *login = malloc(33);
  memset(login, 0, 33);

  retLogin(login);

  while(1){
    printf("%c%s%c%c ", '$', login, '_', '>');
    cmd = readln();
    printf(cmd);
    if(strcmp(cmd, "exit") == 0) exit(0);
  }
  free(cmd);
  free(login);
  return 0;
}


char *readln() {
	// Allocate memory for a string.
  // avoid buffer overflow through realloc
  
	size_t size = 20;
	char *str = (char*) malloc(size);
  memset(str, 0, size);
	if (str) {
	} else {
		die("Mem allocate unsuccessful");
	}

	char c;
	int i = 0;

	do {
		c = fgetc(stdin);
	  
    //printf("%c\n", c);
    //reallocate memory by 1B every time
    //it goes over the size (initially 20B)
		if (i >= size) {
			size++;
			str = realloc(str, size);
		}
    if (c == '\n' || c == EOF) {
		  *(str + i)	= '\0';
      break;
		}

		*(str + i) = c;
		i++;

	} while (1);

  return str;
}


//getlogin is deprecated on ArchLinux (& newer distro's in general)
//this is one way to 'trick' it into working (sometimes)  
//works fine on the servers, for the purposes of this proj
/*  char *getlog;
  getlog = (char*)malloc(50);
  memset(getlog,0, 50);
  getlog = getlogin();
  if(!getlog) 
    perror("getlogin() error");
  else
    printf("%s", getlog);
  return 0;
*/
int retLogin(char *getlog){
  
  getlog = getlogin();
  
  if(!getlog){
    //perror("getlogin() error");
    free(getlog);
    return -1;
  }else
    return 0;
}

int is_whitespace(char c) {
   int i = 0;
   char spaces[] = {' ', '\t'};
   while (spaces[i] != '\0') {
      if (c == spaces[i++])
         return 1;
   }
   return 0;
}

void die(const char *message){
  if(errno)
    perror(message);
  else
    printf("ERROR: %s\n", message);
  exit(1);
}

