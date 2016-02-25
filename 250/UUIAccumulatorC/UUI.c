#define TRUE 0
#define FALSE 0
#include <stdio.h>
#include <stdlib.h>
#include <string.h>


char *newUUI(char* str){
  return str;
}
void printUUI(char *str){
  
  printf("%s", str);
}
void printUUIf(char *str){
  
  printf("%s", str);
}
//my n00b error message function
void die(const char *message)
{
  if(message){
    printf("ERROR: %s\n", message);
  }else{
  }
  exit(1);
}
char *makeArray(char *str, int size)
{
  //we are assuming here that we never have to add a number greater than 32,767
  //that would be a bit crazy
  //char is one so division here is a bit redundant
  int strSize = sizeof(str) / sizeof(char);
  //dont forget the nullbyte
  char *charArr = (char *)malloc(2+size);
  if(charArr){
  }else{
    die("Memory allocation unsuccessful");
  }
  charArr[0] = '0';

  //add zeroes at the beginning of the array
  for(int i = 1; i < (size-strSize); i++){
    charArr[i]='0';
  }
  int j = 0;
  int i = (size-strSize + 1);
  while(charArr[i] != '\0')
  {
    char tmp = str[j];
    charArr[i] = tmp;
    j++;
    i++;
  }

  return charArr;
}
char * parseString(char *str, int size)
{
  char *result = str;
  int resultLength = sizeof(result) / sizeof(int);

  //let's assume the best case for now, and no numerals are being entered
  //make sure every element is a digit
  //for(int i=0; i < resultLength; i++){
  //  if(result[i] >= '0' && result[i] <= '9')
  //  {
  //    
  //  }else{
  //    //if it's not a digit remove it and make the string smaller
  //    //don't do nothing yet I will develop this later
  //  }
  //
  char *charArr = makeArray(result, size);

  return charArr;

}

//this needs to be dynamic, we have no idea what the user will input
//so our best bet is to use fgetc
//dynamically allocates memory so we only use exactly what we need
//I KNOW THIS METHOD WORkS FOR FACT DON'T TOUCH IT
char * readUUI()
{
  //allocate 64bytes for usage
  unsigned int size = 3;
  char *str = (char*)malloc(size);
  if(str){
  }else{
    die("Mem allocate unsuccessful");
  }

  unsigned char c;
  int i = 0;
  do
  {
    c = fgetc(stdin); 
    str[i] = (char) c;
    i++;
    //reallocate memory by +1 every time, we use very little memory this way
    //this isn't the most mem-efficient method but it should use less cycles
    if(i >= size){
      size = size + 1;
      str = (char*) realloc(str, size);
    }
    if(c == '\n' || c == EOF){
      str[i] = '\0';
      break;
    }
  
  }while(1);
  
  return str;
}

int NE(char *val0, char *val1){
  if(strcmp(val1, val0) == 0)
  {
    return FALSE;
  }else{
    return TRUE;
  }
   
    return 1;
}

int toInt(char c){ return c - '0'; }

char toChar(int i){ return i + '0'; }

char * addTen(char *charArr, int j, int tmp)
{
  if(toInt(charArr[j-1] + 1) >= 10)
  {
    charArr[j] = toChar(tmp % 10);
    tmp = toInt(charArr[j-1] + 1);
    j--;
    addTen(charArr, j, tmp);
  } else {
    charArr[j-1] = toChar(toInt(charArr[j-1] + 1));
    charArr[j] = toChar(tmp % 10);
  } 
  return charArr;
}

char * endCalc(char *charArr)
{
  //look at alll thooooose zeroooooos :P
  char *endCalc = "";
  if(charArr[0] == '0'){
    int i = 0;
    while (toChar(charArr[i] == 0 && i < (sizeof(charArr) / sizeof(char) + 1)))
    {
      i++;
    }
    for(; i < sizeof(charArr)/sizeof(char);i ++)
    {
      endCalc += charArr[i];
    }
  } else {
    for (int i = 0; i < (sizeof(charArr)/sizeof(char)) ; i++)
    {
      endCalc += charArr[i];
    }
  }
  return endCalc; 
}


char * sum(char *val0, char *val1)
{
  int size;
  //this is in effect just dividing by 1 but whatever
 
  int val0Size = sizeof(val0) / sizeof(char);
  int val1Size = sizeof(val1) / sizeof(char);
  
  if (val0Size > val1Size){
    size = val0Size;
  }
  else if (val1Size > val0Size){
    size = val1Size;
  } else {
    size = val1Size;
  }

  //malloc with some rudimentery checking if the mem actually allocated 
  char *numArr = (char *)malloc(sizeof(parseString(val0, size)));
  if(numArr){}else{die("malloc unsuccessful");}
  char *valArr = (char *)malloc(sizeof(parseString(val1, size))); 
  if(valArr){}else{die("malloc unsuccessful");}

  int i = sizeof(valArr) / sizeof(char) - 1;
  for (i; i >=0; i--)
  {
    int tmp = toInt(numArr[i] + toInt(valArr[i]));
    if(tmp >= 10){
      numArr = addTen(numArr, i, tmp);
      valArr[i] = '0';
    } else {
      numArr[i] = toChar(tmp);
      valArr[i] = '0';
    }
  }
  //let's leave endCalc out of this for now
  //val0 = endCalc(numArr);
  val0 = numArr;  
  
  free(numArr);
  free(valArr);
 
  return val0;

}





