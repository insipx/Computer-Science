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
};

struct node {
  int userid;
  int index;
  struct node *next;
};

typedef struct node Node;
typedef struct player Player;

//method definitions

int askinput(Node **head, int fd, int index);

void insert(Node **head, Node *newNode);

void printPlayers(Node **head, int fd);
void printPlayer(int index, int fd);

Node* add(Node **head, int fd, int index);
void update(Node **head, int fd);
void query(Node **head, int fd);

void write_node(Player *node, int fd, int index);

void kill(Node **head);
void read_player(int fd, int index, Player *play);
void die(const char *message);

//LL = Linked List


int main(int argc, char *argv[]) {
  int index = 0, fd = 0; 
 
  if (argc <= 1){
    fprintf(stderr, "Usage: './asgn4.out record_file.dat'\n");
    exit(1);
  }else{
    fd = open(argv[1], O_TRUNC|O_RDWR|O_CREAT, S_IRWXU);
  }
  
  Node *head = NULL; //head node initialized in main
  
 
  index = askinput(&head, fd, index);

  close(fd);
  
  kill(&head);
  printf("END\n");
}


void read_player(int fd, int index, Player *play){
  lseek(fd, index*sizeof(Player),0);
  if(read(fd, play, sizeof(Player)) == -1)
    die("[ERROR] read failed");
}

void write_node(Player *node, int fd, int index){
  struct player play = *node;
  lseek(fd, index*sizeof(Player), 0);
  if(write(fd, &play, sizeof(Player)) == -1)
    die("[ERROR] write failed");
}

//get first character in input,
//go to method to do what user asks
int askinput(Node **head, int fd, int index){
  
  Node *temp = *head;
  char c;

  //make input look nicer 
  printf("> ");

  do{
    c = getchar();
    
    if(c == '+'){
      *head = add(&temp, fd, index);
      index++;
    }
    else if(c == '*')
      update(&temp, fd);
    else if (c == '?')
      query(&temp, fd); 
    else if (c == '\n');
      //ignore newline chars, reduced 'Error Invalid Input'
    else if (c == '#'){
      printf("TERMINATE\n");
      printPlayers(&temp, fd);
      return index;
    }
  }while(c != '#');

  return index;
}

Node* add(Node **head, int fd, int index){
  
  Node *temp = *head;
  Player *aNode = NULL;
    
  //scan in the rest of the line
  aNode = (Player *) malloc(sizeof(Player));
  scanf("%d%s%s%d%d%d", &aNode->userid, aNode->last, aNode->first, 
                        &aNode->wins, &aNode->losses, &aNode->ties);
  
  while(temp != NULL){
    if(temp->userid == aNode->userid){
      printf("ERROR - userid exists.\n");
      printPlayer(temp->index, fd);
      printf("> ");
      return *head;
    }else temp = temp -> next;
  }

  //reset temp after iterating through LL
  temp = *head;

  aNode->index = index; 

  //write node to binary file 
  write_node(aNode, fd, index);
     
  printf("%s", "ADD: ");
  
  printPlayer(aNode->index, fd);
  //allocate mem for LL node 
  //set new node to values of node just written to
  //persistant file 
  Node *newNode = (Node*)malloc(sizeof(Node));
  newNode->userid = aNode->userid;
  newNode->index = aNode->index;
  
  insert(&temp, newNode);
  *head = temp;

  
  //make input look nicer 
  printf("> ");
  
  free(aNode);

  return *head;
}
 


void update(Node **head, int fd){
  int userid, wins, losses, ties;
  Node *temp = *head;

  //scan in the rest of the line
  scanf("%d%d%d%d", &userid, &wins, &losses, &ties);
   
  //find data for playerid user entered, change info 
  //for that node
  while(temp != NULL){
    if(temp->userid == userid) {
      printf("%s", "BEFORE: ");
      printPlayer(temp->index, fd);
      Player *node = (Player *)malloc(sizeof(Player));
      lseek(fd, temp->index*sizeof(Player), 0);
      read_player(fd, temp->index, node);
      node->userid = userid;
      node->wins = wins;
      node->losses=losses;
      node->ties=ties;
      write_node(node, fd, temp->index);
      printf("%s", "AFTER: ");
      printPlayer(temp->index, fd);
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

void query(Node **head, int fd){
  int userid;
  Node *temp = *head;

  //scan in the rest of the line
  scanf("%d", &userid);

  //find data user needs based on given ID
  //print that player data
  while(temp != NULL){
    if(temp->userid == userid) {
      printf("QUERY: ");
      printPlayer(temp->index, fd);

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

void printPlayers(Node **head, int fd){
  Node *temp = *head;
  while(temp != NULL){
    printPlayer(temp->index, fd);
    temp = temp->next;
  }
}

void printPlayer(int index, int fd){
  struct player play;
  read_player(fd, index, &play);
  
  printf("%d, %s, %s, %d, %d, %d \n", play.userid, 
                                      play.last, 
                                      play.first, 
                                      play.wins, 
                                      play.losses, 
                                      play.ties);
  return;

}

void insert(Node **head, Node *newNode){
  Node *temp = *head;


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
  Node *curr;
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
void kill(Node **head){
  Node *node = *head; 
  Node *temp;
  while(node != NULL) {
    temp = node;
    node = node->next;    
    temp->next = NULL;
    free(temp);
  }
  *head = NULL;
}

void die(const char *message){
  if(errno)
    perror(message);
  else
    printf("ERROR: %s\n", message);

  exit(1);
}
