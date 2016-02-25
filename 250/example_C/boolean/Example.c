#include <stdlib.h>
#include <stdio.h>
#include <string.h>

/* Subprogram that prints the given char the given number of times
*/
void printChars(char c, int length) {
  for(int i=0; i<length; i++) {
    printf("%c",c);
  }
}

/* Subprogram that prints blanks as an indent followed by the given number of dashes
*/
void printLine(int indent, int length) {
  printChars(' ',indent);
  printChars('-',length);
}

/* Subprogram that initializes the given string with the given capacity by filling it
   with the given char value.
*/
void initString(char *string, int capacity, char c) {
  for(int i=0; i<capacity; i++) {
    string[i] = c;
  }
  string[capacity] = '\0';
}

/* Main that illustrates some things about working with strings (i.e. arrays of char)
   in C.
*/
int main()  {

  //////////////////////////////////////////////////////////////////////////
  // Declare and initialize a string, explcitily one char at a time.
  char aString[8];
  aString[0] = 'H';
  aString[1] = 'e';
  aString[2] = 'l';
  aString[3] = 'l';
  aString[4] = 'o';
  aString[5] = '\0';
  printf("<%s>\n",aString);
  int aLength = strlen(aString);  //strlen is defined in string.h
  printf("<%s> has length %d\n",aString,aLength);

  //////////////////////////////////////////////////////////////////////////
  // Another way to declare and initialize a string
  char bString[] = {'G','o','o','d','-','b','y','e','\0'};
  int bLength = strlen(bString);
  printf("<%s> has length %d\n",bString,bLength);
  
  //////////////////////////////////////////////////////////////////////////
  // Dynamically allocate two strings and initialize them
  printf("Enter capacity:");
  int cCapacity;
  scanf("%d",&cCapacity);
  char *cString;
  char *dString;
  cString = malloc(sizeof(char)*(cCapacity+1));
  dString = malloc(sizeof(char)*(cCapacity+1));
  initString(dString,cCapacity,'X');
  printLine(13,cCapacity);  printf("\n");
  printf("Enter string:");
  scanf("%s",cString);
  int cLength = strlen(cString);
  printf("<%s> has length %d\n",cString,cLength);

  int dLength = strlen(dString);
  printf("<%s> has length %d\n",dString,dLength);

  //////////////////////////////////////////////////////////////////////////

}
