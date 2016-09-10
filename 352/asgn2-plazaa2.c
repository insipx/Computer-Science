/* Program: Programming Assignment 1 (first real asgn), Linked List 
 * author: Andrew Plaza
 * Date: Aug. 29, 2016
 * File Name: asgn2-plazaa2.c
 * compile: cc -o asgn2 asgn2-plazaa2.c
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
  struct player*next;

};
typedef struct player Player;

// instead of Node we have Player. In this prog, player 'nodes' are still just referred
// to as nodes. This is for simplicity.

void insert(Player **head, Player *newNode);
void kill(Player **head);

int main() {
  
  int userid;
  char last[20];
  char first[20];
  int wins;
  int losses;
  int ties; 
  
  int numrec;
  int i;

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
   //debugging printf 
   // printf("%s%d%c%s%c%s%c%d%c%d%c%d%c", "Player read: ", aNode->userid,' ', aNode->last,' ', 
   //                                     aNode->first,' ', aNode->wins, ' ', aNode->losses,
   //                                    ' ', aNode->ties, '\n');
    
    insert(&head, aNode);

  } 
  printf("the list:\n");
  Player *temp = head;
  while(temp != NULL){
    printf("%d, %s, %s, %d, %d, %d \n", temp->userid, temp ->last, temp->first, 
                                        temp->wins, temp->losses,temp->ties);
    temp = temp->next;
  }
  kill(&head);
  
}

void insert(Player **head, Player *newNode){
  Player *temp = *head;
  if(*head == NULL) {
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
    
    //once we find the right place to put the node, ie if userid is < than nextt 
    if(newNode->userid < temp->userid) {
      curr->next = newNode;
      newNode->next=temp; 
      return;
    }

    //if we are at the end of the list
    else if(temp->next == NULL ){
      temp->next = newNode;
      newNode->next = NULL;
      return;
    }

  }

}

//free all the memory
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
