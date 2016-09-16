/* Program: Programming Assignment 1 (first real asgn), Linked List 
 * author: Andrew Plaza
 * Date: Aug. 29, 2016
 * File Name: asgn2-plazaa2.c
 * compile: gcc -o asgn2 asgn2-plazaa2.c
 * run: ./asgn2
 *
 * This C program accepts player records from the keyboard, reads it into an ordered linked list, nd then prints the records in the list in the order they are stored.
 */

#include<stdio.h>
#include<stdlib.h>
#include <unistd.h>


//typedefs

struct player {

  int userid;
  char first[20];
  char last[20];
  int wins;
  int losses;
  int ties;
  struct player *next;

};

typedef struct player Player;

// instead of Node we have Player

void askinput(Player **head);

void insert(Player **head, Player *newNode);
void printPlayers(Player **head);
void printPlayer(Player **player);

Player* add(Player **temp);

void update(Player **head);
void query(Player **head);
void del(Player **head);
void checkExistence(Player **head, int userid);

void kill(Player **head);


int main() {
 
  int numrec, i; 
  
  Player *head = NULL;
  Player *aNode = NULL; // current node we are on
 
  // the first line of input contains a non-negative integer indicating the # of player records to
  //be entered from the keyboard 
  printf("%s", "Enter the number of player records being entered: ");
  scanf("%d", &numrec); 
  
  for(i = 0; i < numrec; i ++){
    aNode = (Player *) malloc(sizeof(Player));
    //userid, last name, first name, # wins, # loss, # ties. Seperated by whitespace
    scanf("%d%s%s%d%d%d", &aNode->userid, aNode->last, aNode->first, 
                          &aNode->wins, &aNode->losses, &aNode->ties);
    
    insert(&head, aNode);

  } 
  askinput(&head);
  kill(&head);
  
}
//might have to change this to getc
void askinput(Player **head){
  Player *temp = *head;
  char c;
  do{
    c = getchar();
    
    if(c == '+'){
      *head = add(&temp);
    }else if(c == '*'){
      update(&temp);
    }else if (c == '?'){
      query(&temp); 
    }else if(c == '-'){
      del(&temp);
     
    }else if (c == '\n'){
      //ignore newline chars
    }else if (c == '#'){
      printf("TERMINATE\n");
      printPlayers(&temp);
    }
    else printf("Invalid Input. Try Again.\n");
  }while(c != '#');
}

Player* add(Player **head){
  Player *temp = *head;
  Player *aNode = NULL;

  //scan in the rest of the line
  aNode = (Player *) malloc(sizeof(Player));
  scanf("%d%s%s%d%d%d", &aNode->userid, aNode->last, aNode->first, 
                        &aNode->wins, &aNode->losses, &aNode->ties);
  while(temp != NULL){
    if(temp->userid == aNode->userid){
      printf("ERROR - userid exists.\n");
      return *head;
    }else{
      temp = temp -> next;
    }
  }

  temp = *head;

  printf("%s", "ADD: ");
  printPlayer(&aNode);
  insert(&temp, aNode);
  *head = temp;

  //need to return pointer here or else, or at least i think, the pointer val gets stuck on the stack, 
  //since it's a local var.
  return *head;
}
 


void update(Player **head){
  int userid, wins, losses, ties;
  Player *temp = *head;

  //scan in the rest of the line
  scanf("%d%d%d%d", &userid, &wins, &losses, &ties);
 
  while(temp != NULL){
    if(temp->userid == userid) {
      printf("%s", "BEFORE: ");
      printPlayer(&temp);
      temp->wins = wins;
      temp->losses = losses;
      temp->ties = ties;
      printf("%s", "AFTER: ");
      printPlayer(&temp);
      return;
    }else{
      temp = temp->next;
    }
  }
  printf("ERROR - player does not exist.");
  return;

}

void query(Player **head){
  int userid;
  Player *temp = *head;

  //scan in the rest of the line
  scanf("%d", &userid);
 
  while(temp != NULL){
    if(temp->userid == userid) {
      printf("QUERY: ");
      printPlayer(&temp);
      return;
    }else{
      temp = temp->next;
    }
  }
  //ERROR
  printf("%s\n", "ERROR - player does not exist.");
  return;
}

void del(Player **head){
  Player *temp = *head;
  int userid;

  //scan in the rest of the line
  scanf("%d", &userid);

  Player *curr;
  curr = temp;

  while(temp != NULL){
    temp = temp->next;
    if(curr->userid == userid){
      printf("DELETE: ");
      printPlayer(&curr);

      *head = curr->next;
      curr->next = NULL;
      free(curr);
      return;
    }
    if(temp->userid == userid) {
      curr->next = temp->next;
      temp->next = NULL;
      free(temp);
      return;
    }else{
      temp = temp->next;
    }
    curr = temp;
  }
  printf("%s\n", "ERROR - player does not exist.");
  return;
}

void printPlayers(Player **head){
  Player *temp = *head;
  while(temp != NULL){
    printf("%d, %s, %s, %d, %d, %d \n", temp->userid, temp ->last, temp->first, 
                                        temp->wins, temp->losses,temp->ties);
    temp = temp->next;
  }

}

void printPlayer(Player **player){
  Player *temp = *player;
  printf("%d, %s, %s, %d, %d, %d \n", temp->userid, temp ->last, temp->first, 
                                      temp->wins, temp->losses,temp->ties);

}

//pointer pointer because want value of head || two de-reference operators
//dereference once, goes to the adress of head, dereference again and go to the value of what that adress holds
void insert(Player **head, Player *newNode){
  Player *temp = *head;

  if(*head == NULL){
    *head = newNode;
    return;
  }
  // this is the (if newNode->userid < head ) case
  if(newNode->userid < temp->userid) {
    newNode->next = *head;
    *head = newNode;
    return;
  }

  // if newNode userid is not < head, needs to be inserted into list
  Player *curr;
  curr = temp;
  while(temp->next !=NULL) {
    temp = temp -> next;
    
    if(newNode->userid < temp->userid) {
      curr->next = newNode;
      newNode->next=temp;
      return;
    }
    //if we are at the end of the list
    curr = temp;
  }
  if(curr->next == NULL){
    curr->next = newNode;
    newNode->next = NULL;
    return;
  }

}

void kill(Player **head){
  Player *node = *head; 
  Player *temp;
  while(node->next != NULL) {
    temp = node;
    node = node->next;    
    free(temp);
  }
  *head = NULL;

}
