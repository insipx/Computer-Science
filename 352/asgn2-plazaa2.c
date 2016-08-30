/* Program: Programming Assignment 1 (first real asgn), Linked List 
 * author: Andrew Plaza
 * Date: Aug. 29, 2016
 * File Name: asgn2-plazaa2.c
 * compile: gcc -o asgn2 asgn2-plazaa2.c
 * run: ./asgn2
 *
 * This C program accepts player records from the keyboard, reads it into an ordered linked list, nd then prints the records in the list in the order they are stored.
 */

// problems to solve:
// ordered linkedlist: what is supposed to be ordered? Lexicographic? By most Wins? by most losses?

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

// instead of Node we have Player
//
void append(Player **head, Player *newPlayer);
void insert(Player **head, Player *newNode);

int main(){
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
    printf("%s%d%c%s%c%s%c%d%c%d%c%d%c", "Player read: ", aNode->userid,' ', aNode->last,' ', 
                                        aNode->first,' ', aNode->wins, ' ', aNode->losses,
                                        ' ', aNode->ties, '\n');
    append(&head, aNode);

  } 
  printf("the list:\n");
  Player *temp = head;
  while(temp != NULL){
    printf("Player: %s %s\n", temp ->first, temp->last);
    temp = temp->next;
  }
}

void append(Player **head, Player *newNode) {
  Player *temp = *head;
  if(*head == NULL) {
    *head = newNode;
    return;
  }
  while (temp->next != NULL) temp = temp->next;

  temp->next = newNode;
  newNode->next = NULL;
}
