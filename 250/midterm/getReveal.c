#include <stdio.h>
#include <stdlib.h>
#include <string.h>
 
int getSize(char *str){
  char c;
  int i = 0;
  do{
    c = str[i];
    i++;  
  }while(c != '\0' && c != '\n');
 
  printf("%d", i);
 
 
  return i - 1;
}
 
void getReveal(char *solution, char *letterSet, char *reveal){
  int solutionSize = strlen(solution);
  int setSize = strlen(letterSet);
  int revSize = strlen(reveal);
  char *newReveal = (char*)malloc (revSize);
  
  for(int i = 0; i < strlen(newReveal);i++){
    newReveal[i] = ' ';
  }
  newReveal[revSize] = '\0';
//  printf("%d%s", solutionSize, "\n");
//  printf("%d%s", setSize, "\n");
//  printf("%d%s", revSize, "\n");
  for(int i = 0; i < solutionSize; i ++){
    for(int j = 0; j < setSize; j++){
      char c = letterSet[j];
      char b = solution[i];
      if(c == b){
        newReveal[i] = letterSet[j];
      }
    }
  }

//j printf("%d%s", strlen(newReveal), "\n") ;

  printf("%s%s", strlen(newReveal), "\n");
  
  free(newReveal);
 
}
 
int main(){
 
  getReveal("Hello", "aeiou", " e  o");
  getReveal("George Washington", "wjkpgoWt", "  o g  W     gto ");
  getReveal("Today is Friday!!!","","                  ");
  getReveal("hi","abdefgijlnoprtuvxz","hi");
}
