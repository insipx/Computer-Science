/* Program: Programming Assignment 4, Binary Files
 * author: Andrew Plaza
 * Date: Sept 29, 2016
 * File Name: asgn4-plazaa2.c
 * compile: cc -o asgn4.out asgn4-plazaa2.c -g -Wall
 * run: ./asgn4.out
 * debug: gdb ./asgn4.out
 *
 * This C program accepts player records from the keyboard, 
 * reads it into an ordered linked list, nd then prints the records
 * in the list in the order they are stored.
 */

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <errno.h>
#include <unistd.h>
#include <fcntl.h>

//typedefs, player is basically a node in the Linked List

struct player {
  int userid;
  char first[20];
  char last[20];
  int wins;
  int losses;
  int ties;
  int index;
  struct player *next;
};

typedef struct player Player;

//method definitions

int askinput(Player **head, int fd, int index);

void insert(Player **head, Player *newNode);

void printPlayers(Player **head);
void printPlayer(Player **player);

Player* add(Player **temp, int fd, int index);

void update(Player **head);
void query(Player **head);

void kill(Player **head);
void write_node(Player **node, int fd, int index);

//LL = Linked List


int main(int argc, char *argv[]) {
  int numrec, i, index = 0, fd = 0; 
 
  if (argc <= 1){
    fprintf(stderr, "Usage: './asgn4.out record_file.dat'\n");
    exit(1);
  }else{
    fd = open(argv[1], O_RDWR|O_CREAT, S_IRWXU);
  }
    
  Player *head = NULL; //head node, always first node in the LL
  Player *aNode = NULL; // current node we are on

  // the first line of input contains a non-negative integer indicating the # of player
  // records to be entered from the keyboard 
  printf("%s", "Enter the number of player records being entered: ");
  scanf("%d", &numrec); 
  
  for(i = 0; i < numrec; i ++){

    aNode = (Player *) malloc(sizeof(Player));
    printf("> ");
    //userid, last name, first name, # wins, # loss, # ties. Seperated by whitespace
    scanf("%d%s%s%d%d%d", &aNode->userid, aNode->last, aNode->first, 
                          &aNode->wins, &aNode->losses, &aNode->ties);
    aNode->index = index;

    insert(&head, aNode);
    write_node(&aNode, fd, index);
    index++;

  } 

  
  index = askinput(&head, fd, index);

  close(fd);
  //kill memory because nothing needs it anymore
  kill(&head);
  
}

void write_node(Player **node, int fd, int index){
  Player *pnode = *node;  
  if(write(fd, &pnode, index*sizeof(pnode)) == -1)
    printf("[ERROR] write failed");
}

//get first character in input,
//go to method to do what user asks
int askinput(Player **head, int fd, int index){
  
  Player *temp = *head;
  char c;

  //make input look nicer 
  printf("> ");

  do{
    c = getchar();
    
    if(c == '+'){
      *head = add(&temp, fd, index);
      index++;
    }else if(c == '*')
      update(&temp);
    else if (c == '?')
      query(&temp); 
    else if (c == '\n');
      //ignore newline chars, reduced 'Error Invalid Input'
    else if (c == '#'){
      printf("TERMINATE\n");
      printPlayers(&temp);
    }
  }while(c != '#');

  return index;
}

Player* add(Player **head, int fd, int index){
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
    }else temp = temp -> next;
  }
  aNode->index = index; 
  temp = *head;
  //write node to binary file 
  write_node(&aNode, fd, index);

  printf("%s", "ADD: ");
  printPlayer(&aNode);
  insert(&temp, aNode);
  *head = temp;

  //need to return pointer here else the pointer val gets stuck 
  //on the stack, since it's a local var.
  
  
  //make input look nicer 
  printf("> ");

  return *head;
}
 


void update(Player **head){
  int userid, wins, losses, ties;
  Player *temp = *head;

  //scan in the rest of the line
  scanf("%d%d%d%d", &userid, &wins, &losses, &ties);
   
  //find data for playerid user entered, change info 
  //for that node
  while(temp != NULL){
    if(temp->userid == userid) {
      printf("%s", "BEFORE: ");
      printPlayer(&temp);
      temp->wins = wins;
      temp->losses = losses;
      temp->ties = ties;
      printf("%s", "AFTER: ");
      printPlayer(&temp);
     
      //make input look nicer 
      printf("> ");

      return;
    }else{
      temp = temp->next;
    }
  }
  printf("ERROR - player does not exist.");

  //make input look nicer 
  printf("> ");
  
  return;

}

void query(Player **head){
  int userid;
  Player *temp = *head;

  //scan in the rest of the line
  scanf("%d", &userid);

  //find data user needs based on given ID
  //print that player data
  while(temp != NULL){
    if(temp->userid == userid) {
      printf("QUERY: ");
      printPlayer(&temp);

      //make input look nicer 
      printf("> ");

      return;
    }else{
      temp = temp->next;
    }
  }
  //ERROR
  printf("%s\n", "ERROR - player does not exist.");

  //make input look nicer 
  printf("> ");

  return;
}

void printPlayers(Player **head){
  Player *temp = *head;
  while(temp != NULL){
    printPlayer(&temp);
    temp = temp->next;
  }
}

void printPlayer(Player **player){
  Player *temp = *player;
  printf("%d, %s, %s, %d, %d, %d \n", temp->userid, temp ->last, temp->first, 
                                      temp->wins, temp->losses,temp->ties);

}


//pointer pointer because want value of head |_| two de-reference operators
//dereference once, goes to the adress of head, dereference again and 
//go to the value of what that address holds
//    |Player **head 0xmemaddr| |addr of the addr where head is|
//                                             ↓  
//      <---------------------------------------  dereference once
//     ↓
//    |Pointer *head 0xmemaddr| |addr of head|
//                              ↓
//              ↓ <--------------                 dereference again
//    |addr of head||head value|

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

//free memory starting from head
void kill(Player **head){
  Player *node = *head; 
  Player *temp;
  while(node != NULL) {
    temp = node;
    node = node->next;    
    temp->next = NULL;
    free(temp);
  }
  *head = NULL;
}
