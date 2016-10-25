/* Program: Programming Assignment 5, Create a Shell
 * author: Andrew Plaza
 * Date: Oct 15, 2016
 * File Name: asgn5-plazaa2.c
 * compile: cc -o asgn5.out asgn5-plazaa2.c -g -Wall
 * run: ./asgn5.out
 * debug: gdb ./asgn5.out
 * 
 * A simple shell program, without autocomplete, 
 * globbing, cool unicode stuff, but it's awesome
 */

#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <string.h>
#include <errno.h>
#include <fcntl.h>
#include <sys/types.h>
#include <sys/wait.h>
#include <sys/types.h>
#include <pwd.h>


//method definitions
char **getArgs(int *args_size);
int readWord(char **tmp);
int isNextArg();
int sh_cd(char **args);
int sh_exit(char **args);
int start(char **args);
int initChild(char **args);
void argsError(char **args);
void freeArgs(char **strA, int args_size);
void die(const char *message);


//if done in this way, adding new builtins (if needed)
//is cake
//built-ins
char *builtinStr[] = {
  "cd",
  "exit"
};

int (*builtinFunc[]) (char **) = {
  &sh_cd,
  &sh_exit
};

int numBuiltins() {
  return sizeof(builtinStr) / sizeof(char *);
}
//end built-ins

int main(){

  //args_size will be the argc to our argv for execvp
  int args_size, status; 
  char **args;

  //max login length is 32 bytes, 33 for good luck
  
  //get the login 

  //main shell loop
  do{
    printf("%c%s%c%c ", '$', getlogin(), '_', '>');
    args = getArgs(&args_size);  
    status = start(args);
    
    freeArgs(args, args_size);
    free(args);
  }while(status);

  freeArgs(args, args_size);
  free(args);

  return EXIT_SUCCESS;
}

char **getArgs(int *args_size){
  int i = 0;
  char **args = malloc(sizeof(char *) * 1);
  
  while(1){
    args[i] = malloc(sizeof(char) * 2);
    memset(args[i], 0, sizeof(char));
    if(readWord(&args[i]) == -1){
      i++;
      args = realloc(args, sizeof(char *) * (i+1));
    }
    else{
      //add one more char* that is NULL so execvp knows the length
      i++;
      args = realloc(args, sizeof(char *) * (i+1));
      args[i] = NULL;
      *args_size = i;
      return args;
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
    //it goes over the size (initially 1B)
		if (i >= size) {
			size++;
			*tmp = realloc(*tmp, sizeof(char) * size);
          str = *tmp;
		}

    //stops at a whitespace to get entire word
    if (c == '\n' || c == EOF || c == ' ') {
		  str[i]	= '\0';
      break;
		}else{
      str[i] = c;
      i++;
    }
	} while (1);
  
  if (c == ' '){
    ungetc(c, stdin);
    //this is for getting rid of whitespace
    return isNextArg();
  }else
    return 0;
}

//check if the next character is the start of another
//argument, and not simply whitespace
int isNextArg(){
  char c;
  int i;

  do{
    c = fgetc(stdin);
    i++;
  } while (c == ' ');
  
  if(c == EOF || c == '\n' )
    return 0;
  else{
    ungetc(c, stdin);
    return -1;
  }
}


int sh_cd(char **args){
  if(args[1] == NULL)
    fprintf(stderr, "Error: user-entered command and parameters");
  else{
    if(chdir(args[1]) != 0)
      perror("[ERROR] chdir failed");
  }

  //this way status will keep going
  return 1;
}


int sh_exit(char **args){
  exit(0);
}

int start(char **args){
  int i; 

  if(args[0] == NULL){
    return 1; 
  }

  for(i = 0; i < numBuiltins(); i++){
    if(strcmp(args[0], builtinStr[i]) == 0)
     return(*builtinFunc[i])(args);
  
  }
  return initChild(args);

}

int initChild(char **args){
 int pid, status, err;
 
 pid = fork();
 if(pid == 0){
  err = execvp(args[0], args);
  if(err == -1){
    argsError(args);
  }
 }else if (pid < 0)
   die("[ERROR] forking");
 else{
  do {
    waitpid(pid, &status, WUNTRACED);
    } while (!WIFEXITED(status) && !WIFSIGNALED(status)); 
 }
 return 1;
}

void argsError(char **args){
  int i = 0;
  printf("Error: ");
  while(args[i] != NULL){
    printf("%s%c", args[i], ' ');
    i++;
  }
  printf("\n");
  exit(1);
} 

void freeArgs(char **strA, int length){
  int i = 0;
  for(i = 0; i < length+1; i++){
    free(strA[i]);
  }
}

void die(const char *message){
  if(errno)
    perror(message);
  else
    printf("[ERROR] %s\n", message);
  exit(1);
}

