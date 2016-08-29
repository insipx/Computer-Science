/* Program: Programming Assignment 1, Linked List 
 * author: Andrew Plaza
 * Date: Aug. 29, 2016
 * File Name: asgn1-plazaa2.c
 * compile: gcc -o asgn1 asgn1-plazaa2.c
 * run: ./asgn1
 *
 * This C program accepts player records from the keyboard, reads it into an ordered linked list, nd then prints the records in the list in the order they are stored.
 */
#include<stdio.h>

struct player {
  int userid;
  char first[20];
  char last[20];
  int wins;
  int losses;
  int ties;
  struct player*next;
}

