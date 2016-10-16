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
#include <sys/types.h>
#include <sys/wait.h>

//method definitions
char **getArgs();
int readWord(char **tmp);
int retLogin(char **getlog);
int is_whitespace(char c);
void die(const char *message);
void free_a(char **strA, int args_size);

int main(int argc, char *argv[]){
  
  //args_size will be the argc to our argv for execvp
  int args_size, status; 
  char **ex_argv;
  //just one byte, realloced in readWord 
  char *cmd = malloc(2 * sizeof(char));
  memset(cmd, 0, 2);
  
  //max login length is 32 bytes, 33 for good luck
  char *login = malloc(33 *sizeof(char));
  memset(login, 0, 33);

  //get the login 
  retLogin(&login);

  while(1){
    printf("%c%s%c%c ", '$', login, '_', '>');
    if(readWord(&cmd) == -2)
      ex_argv = getArgs(&args_size);  
    if(strcmp(cmd, "exit") == 0) 
      break;
    if(fork() != 0){
      waitpid(-1, &status, WNOHANG);
    }else{
      execvp(cmd, ex_argv);
    }
  }

  free_a(ex_argv, args_size);
  free(ex_argv);
  free(cmd);
  free(login);
  return 0;
}

char **getArgs(int *args_size){
  int i = 0;
  char **ex_argv = malloc(sizeof(char *) * 1);
  
  while(1){
    ex_argv[i] = malloc(sizeof(char) * 2);
    memset(ex_argv[i], 0, sizeof(char));
    if(readWord(&ex_argv[i]) == -2){
      i++;
      ex_argv = realloc(ex_argv, sizeof(char *) * (i+1));
    }
    else{
      *args_size = i;
      return ex_argv;
    }
  } 
}

int readWord(char **tmp) {
  char *str = *tmp; 
	// Allocate memory for a string.
  // avoid buffer overflow through realloc
  size_t size = strlen(str);	

	char c;
	int i = 0;

	do {
		c = fgetc(stdin);
	  
    //reallocate memory by 1B every time
    //it goes over the size (initially 8B)
		if (i >= size) {
			size++;
			*tmp = realloc(*tmp, sizeof(char) * size);
      str = *tmp;
		}
    if (c == '\n' || c == EOF || c == ' ') {
		  str[i]	= '\0';
      break;
		}

		str[i] = c;
		i++;
	} while (1);
  
  if (c == ' ')
    return -2;
  else if(str)
    return 0;
  else
    return -1;
}


//getlogin is deprecated on ArchLinux (& newer distro's in general)
//works fine on the servers, for the purposes of this proj
int retLogin(char **tmp){
  char *getlog = *tmp; 
  getlog = getlogin();  

  if(!getlog){
    //perror("getlogin() error");
    //free(getlog);
    return -1;
  }else
    return 0;
}

void free_a(char **strA, int length){
  int i = 0;
  for(i = 0; i < length+1; i++){
    free(strA[i]);
  }
}

void die(const char *message){
  if(errno)
    perror(message);
  else
    printf("ERROR: %s\n", message);
  exit(1);
}

