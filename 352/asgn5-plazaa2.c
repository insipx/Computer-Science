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
int num_builtins();
char **getArgs();
int readWord(char **tmp);
int retLogin(char **getlog);
int sh_cd(char **args);
int sh_exit(char **args);
int launch(char **args);
int execute(char **args);
int isNextArg();
void die(const char *message);
void free_a(char **strA, int args_size);


//if done in this way, adding new builtins (if needed)
//is cake
//built-ins
char *builtin_str[] = {
  "cd",
  "exit"
};

int (*builtin_func[]) (char **) = {
  &sh_cd,
  &sh_exit
};

int num_builtins() {
  return sizeof(builtin_str) / sizeof(char *);
}

// create a trim whitespace function

int main(){
  //args_size will be the argc to our argv for execvp
  int args_size, status; 
  char **args, *login;

  //max login length is 32 bytes, 33 for good luck
  login = malloc(33 *sizeof(char));
  memset(login, 0, 33);
  //get the login 
  retLogin(&login);

  do{
    
    printf("%c%s%c%c ", '$', login, '_', '>');
    args = getArgs(&args_size);  
    status = execute(args);
    
    free_a(args, args_size);
    free(args);

  }while(status);

  free_a(args, args_size);
  free(args);
  free(login);

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


//getlogin is deprecated on ArchLinux (& newer distro's in general)
//works fine on the servers, for the purposes of this proj
int retLogin(char **tmp){
  char *getlog = *tmp; 
  getlog = getlogin();  

  if(!getlog){
    perror("getlogin() error");
    free(getlog);
    return -1;
  }else
    return 0;
}

int sh_cd(char **args){
  if(args[1] == NULL)
    fprintf(stderr, "Error: user-entered command and parameters");
  else{
    if(chdir(args[1]) != 0){
      perror("[ERROR] chdir failed");
    }
  }
  //this way status will keep going
  return 1;
}


int sh_exit(char **args){
  exit(0);
}

int execute(char **args){
  int i; 

  if(args[0] == NULL){
    return 1; 
  }

  for(i = 0; i < num_builtins(); i++){
    if(strcmp(args[0], builtin_str[i]) == 0)
     return(*builtin_func[i])(args);
  
  }
  return launch(args);

}

int launch(char **args){
 int pid, status;
 
 pid = fork();
 if(pid == 0){
  if(execvp(args[0], args) == -1)
    die("[ERROR] did not execute correctly");
 }else if (pid < 0)
   die("[ERROR] forking");
 else{
  do {
    waitpid(pid, &status, WUNTRACED);
    } while (!WIFEXITED(status) && !WIFSIGNALED(status)); 
 }
 return 1;
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
    printf("[ERROR] %s\n", message);
  exit(1);
}

