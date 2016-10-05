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

//method definitions (same order as methods in file)

void readp(int fd, int index, Player *play);
void writep(int fd, int index, Player *play);

void print_nodes(Node **head);
void print_players(int fd, Node **head);
void printp(int fd, int index);

void ask_input(int fd, int index, Node **head);

Node* add(int fd, int index, Node **head);
void insert(Node **head, Node *newNode);

void update(int fd, Node **head);
void query(int fd, Node **head);


void kill(Node **head);
void die(const char *message);

//LL = Linked List


int main(int argc, char *argv[]) {
  int index = 0, fd = 0; 
 
  if (argc <= 1){
    fprintf(stderr, "Usage: './asgn4.out filename'\n");
    exit(1);
  }else{
    fd = open(argv[1], O_TRUNC|O_RDWR|O_CREAT, S_IRWXU);
  }
  
  Node *head = NULL; //head node initialized in main
  
  ask_input(fd, index, &head);
  
  //end sequence
  close(fd);
  print_nodes(&head);
  kill(&head);
  printf("END\n");
}


void readp(int fd, int index, Player *play){
  lseek(fd, index*sizeof(Player),0);
  if(read(fd, play, sizeof(Player)) == -1)
    die("[ERROR] read failed");
}
//Player *node, int fd, int index
void writep(int fd, int index, Player *play){
  struct player rec = *play;
  lseek(fd, index*sizeof(Player), 0);
  if(write(fd, &rec, sizeof(Player)) == -1)
    die("[ERROR] write failed");
}

void print_nodes(Node **head){
  Node *node = *head;
  printf("The linked list: ");
  while(node != NULL){
    printf("(%d, %d)", node->userid, node->index);
    node = node->next;
  }
  printf("\n");
}

void print_players(int fd, Node **head){
  Node *temp = *head;
  while(temp != NULL){
    printp(fd, temp->index);
    temp = temp->next;
  }
}

void printp(int fd, int index){
  struct player play;
  readp(fd, index, &play);
  
  printf("%d, %s, %s, %d, %d, %d \n", play.userid, 
                                      play.last, 
                                      play.first, 
                                      play.wins, 
                                      play.losses, 
                                      play.ties);
  return;

}


//get first character in input,
//go to method to do what user asks
void ask_input(int fd, int index, Node **head){
  
  Node *temp = *head;
  char c;

  //make input look nicer 
  printf("> ");

  do{
    c = getchar();
    
    if(c == '+'){
      *head = add(fd, index, &temp);
      index++;
    }
    else if(c == '*')
      update(fd, &temp);
    else if (c == '?')
      query(fd, &temp); 
    else if (c == '\n');
      //ignore newline chars, reduced 'Error Invalid Input'
    else if (c == '#'){
      printf("TERMINATE\n");
      print_players(fd, &temp);
      return;
    }
  }while(c != '#');

  return;
}

Node* add(int fd, int index, Node **head){
  
  Node *temp = *head;
  Player *aNode = NULL;
    
  //scan in the rest of the line
  aNode = (Player *) malloc(sizeof(Player));
  scanf("%d%s%s%d%d%d", &aNode->userid, aNode->last, aNode->first, 
                        &aNode->wins, &aNode->losses, &aNode->ties);
  
  while(temp != NULL){
    if(temp->userid == aNode->userid){
      printf("ERROR - userid exists.\n");
      printp(fd, temp->index);
      printf("> ");
      return *head;
    }else temp = temp -> next;
  }

  //reset temp after iterating through LL
  temp = *head;

  aNode->index = index; 

  //write node to binary file 
  writep(fd, index, aNode);
     
  printf("%s", "ADD: ");
  
  printp(fd, aNode->index);
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

void update(int fd, Node **head){
  int userid, wins, losses, ties;
  Node *temp = *head;

  //scan in the rest of the line
  scanf("%d%d%d%d", &userid, &wins, &losses, &ties);
   
  //find data for playerid user entered, change info 
  //for that node
  while(temp != NULL){
    if(temp->userid == userid) {
      printf("%s", "BEFORE: ");
      printp(fd, temp->index);
  
      Player *node = (Player *)malloc(sizeof(Player));
      lseek(fd, temp->index*sizeof(Player), 0);
      readp(fd, temp->index, node);
 
      node->userid = userid;
      node->wins = wins;
      node->losses=losses;
      node->ties=ties;

      writep(fd, temp->index, node);

      printf("%s", "AFTER: ");
      printp(fd, temp->index);
      //make input look nicer 
      printf("> ");

      free(node);

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

void query(int fd, Node **head){
  int userid;
  Node *temp = *head;

  //scan in the rest of the line
  scanf("%d", &userid);

  //find data user needs based on given ID
  //print that player data
  while(temp != NULL){
    if(temp->userid == userid) {
      printf("QUERY: ");
      printp(fd, temp->index);

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
