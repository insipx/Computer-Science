/* Program:     Singly-linked List in C
   Author:      Y. Bi
   Date:        August 22, 2016
   File name:   asgn2-sample.c
   Compile:     cc -o asgn2-sample asgn2-sample.c
   Run:         asng2-sample

   The program accepts three pairs of a string and an integer, puts 
   them into a singly-linked list in a FIFO order, then prints them. 
*/
#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>

struct node {
   char string[10];     
   int integer;
   struct node *next;
};
typedef struct node Node;

void append(Node **head, Node *newNode);

int main() {
   char string[10]; 
   int integer;
   int i;

   Node *head = NULL;    // pointing to the first node
   Node *aNode = NULL;

   for (i = 0; i < 3; i++) {
      aNode = (Node *) malloc(sizeof(Node));
      scanf("%s%d", aNode->string, &(aNode->integer));
      printf("output: %s, %d\n", aNode->string, aNode->integer);
      append(&head, aNode);
   }
   printf("the list:\n");
   Node *temp = head;
   while (temp != NULL) {
      printf("Node: %s--%d\n", temp->string, temp->integer);
      temp = temp->next;
   }
}
void append(Node **head, Node *newNode) {
   Node *temp = *head;
   if (*head == NULL)  {
      *head = newNode;
      return;
   }
   while (temp->next != NULL) temp = temp->next;

   temp->next = newNode;
   newNode->next = NULL;
}
