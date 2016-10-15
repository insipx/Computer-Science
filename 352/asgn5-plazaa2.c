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
char *get_cmd();
char *readln();
char *plogin();
int is_whitespace(char c);
void die(const char *message);

int main(int argc, char *argv[]){
  int i = 0;
  while(i < 2){
    printf("%s%c \n", plogin(), '>');
    i++;
  }
  return 0;
}


char *get_cmd(){
  char c;
   
  return NULL;
}

char *readln() {
	// Allocate memory for a string.
  // avoid buffer overflow through realloc
  
	size_t size = 8;
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
		str[i] = c;
		i++;
		
    //reallocate memory by 1B every time,
		if (i >= size) {
			size++;
			str = (char*) realloc(str, size);
      memset(str+size, 0, 1);
		}
		if (c == '\n' || c == EOF) {
			str[i] = '\0';
			break;
		}
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
char *plogin(){
  char *getlog = (char*)malloc(50);
  memset(getlog,0, 50);
  getlog = getlogin();
  
  if(!getlog){
    perror("getlogin() error");
    return NULL;
  }else
    return getlog;
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

