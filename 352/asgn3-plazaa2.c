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

void insert(Player **head, Player *newNode);
void printPlayers(Player **head);
void kill(Player **head);

void add(Player **temp);
void update(Player **head);
void query(Player **head);
void del(Player **head);

void askinput(Player **head);

int main() {
  int userid, wins, losses, ties, numrec, i;
  char last[20], first[20];
  
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
  
  printPlayers(&head);
  askinput(&head);
  printPlayers(&head);
  kill(&head);
  
}
//might have to change this to getc
void askinput(Player **head){
  Player *temp = *head;
  //char *istring;
  char c;
  do{
    c = getchar();
    
    if(c == '+'){
      add(&temp);
    }else if(c == '*'){
      update(&temp) ;
    }else if (c == '?'){
      query(&temp); 
    }else if(c == '-'){
      del(&temp);
     
    }else if (c == '\n'){
      //ignore newline chars
    }else if (c == '#'){
      //to get rid of "invalid input" when a '#' is entered 
    }
    else printf("Invalid Input. Try Again.\n");
  }while(c != '#');
}

void add(Player **temp){
  Player *head = *temp; 
  Player *aNode = NULL;
  int userid, wins, losses, ties, numrec, i;
  char last[20], first[20];

  aNode = (Player *) malloc(sizeof(Player));
  scanf("%d%s%s%d%d%d", &aNode->userid, aNode->last, aNode->first, 
                        &aNode->wins, &aNode->losses, &aNode->ties);
  insert(&head, aNode);
}

void update(Player **head){
  //do nothing

}

void query(Player **head){
  //do nothing

}

void del(Player **head){
  //do nothing

}

void printPlayers(Player **head){
  printf("Current Players: \n");
  Player *temp = *head;
  while(temp != NULL){
    printf("%d, %s, %s, %d, %d, %d \n", temp->userid, temp ->last, temp->first, 
                                        temp->wins, temp->losses,temp->ties);
    temp = temp->next;
  }

}

//pointer pointer because want value of head || two de-reference operators
//dereference once, goes to the adress of head, dereference again and go to the value of what that adress holds
void insert(Player **head, Player *newNode){
  Player *temp = *head;
  newNode -> next = NULL;

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
  while(temp->next !=NULL) {
    curr = temp;
    temp = temp -> next;
    
    if(newNode->userid < temp->userid) {
      curr->next = newNode;
      newNode->next=temp;
      return;
    }
    //if we are at the end of the list
    else if(temp->  == NULL ){
      printf("in the if statementmofo \n");
      curr->next = newNode;
      newNode->next = NULL;
      return;
    }
  }
  temp-> next = NULL;

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
