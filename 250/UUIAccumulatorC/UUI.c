#define TRUE 1
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
int getSize(char *str){
  char c = str[0];
  int i = 0;
  while(c != '\0'){
    i++;
    c = str[i]; 
  }
  //because somehow the nullbyte is still there
  
  return i - 1;
}

char *makeArray(char *str, int size)
{
  //printf("%s%d%s", "the size passed: ", size, "\n");
  //we are assuming here that we never have to add a number with a 
  //greater amount of characters than 32,767
  //that would be a bit crazy
  //char is one so division here is a bit redundant
  size_t strSize = getSize(str);
  //printf("%s%s%s", "this is the string: ", str, "\n"); 

  //printf("%s%d%s", " this is the strSize: ", strSize, "\n"); 
  //for the extra 0
  size++; 
  char *charArr = (char *)malloc(size);
  if(charArr){
  }else{
    die("Memory allocation unsuccessful");
  }
   
  charArr[0] = '0';

  //add zeroes at the beginning of the array
int i;  
for(i = 1; i < (size-strSize); i++){
    charArr[i]='0';
  }
  //printf("%s%s%s", "there should be only zeroes: ", charArr, "\n"); 
  //add the rest of the string
  
  
  //printf("%s%d%s", "this is size-strSize: ", size-strSize, "\n");

  int j = 0;
  i = (size-strSize);
  for (i; i< size; i++ )
  {
    char tmp = str[j];
    charArr[i] = tmp;
    j++;
  }

  //printf("%s%s%s", " CharArr ends up being: ", charArr, "\n");
  return charArr;
}
//ignoring this method for now
char * parseString(char *str, int size)
{
  char *result = str;
  int resultSize = getSize(result);

  //make sure every element is a digit
int i;  
for(i=0; i < resultSize; i++){
    if(result[i] >= '0' && result[i] <= '9')
    {
      
    }else{
      //if it's not a digit remove it and make the string smaller
     //don't do nothing yet I will develop this later
    }
  }
  
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
  size_t size = 0;
  char *str = (char*)malloc(size);
  if(str){
  }else{
    die("Mem allocate unsuccessful");
  }

  char c;
  int i = 0;
  do
  {
    c = fgetc(stdin); 
    str[i] = c;
    i++;
    //reallocate memory by +1 every time, we use very little memory this way
    //this isn't the most mem-efficient method but it should use less cycles
    if(i >= size){
      size++;
      str = (char*) realloc(str, size);
    }
    if(c == '\n' || c == EOF){
      str[i] = '\0';
      break;
    }
  
  }while(1);
  //printf("%s%s", " This is the Readline String: ", str); 
  //printf("%s%d%s", " and this is the size ", size, "\n");
  return str;
}

int toInt(char c){ return c - '0'; }

char toChar(int i){ return i + '0'; }

//because sizeof returns the size of the pointer -_-


int checkEquality(char *charA, char *charB){
int i;  
for(i = 0; i < getSize(charA); i++)
  {
    int a, b;
    a = toInt(charA[i]);
    b = toInt(charB[i]);
    if(a == b){
    } else {
      return FALSE;
    }
  
  }
  return TRUE;
}

int NE(char *val0, char *val1){
  int val0Size = getSize(val0);
  int val1Size = getSize(val1); 

  if(val0Size == val1Size){
    if(checkEquality(val0, val1) == TRUE){
      return FALSE;
    } else {
      return TRUE;
    }
  } else {
    return TRUE;
  }

}

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
  int i = 0;
  if(charArr[0] == '0'){
    while (toChar(charArr[i] == 0 && i < (sizeof(charArr) / sizeof(char) + 1)))
    {
      i++;
    }
    for(; i < sizeof(charArr)/sizeof(char);i ++)
    {
      endCalc += charArr[i];
    }
  } else {
    for (i = 0; i < (sizeof(charArr)/sizeof(char)) ; i++)
    {
      endCalc += charArr[i];
    }
  }
  return endCalc; 
}


char * sum(char *val0, char *val1)
{
  int size;
  
  // -1 because nullbyte 
  int val0Size = getSize(val0);
  int val1Size = getSize(val1);
  
  //debug stuff 
  //printf("%s%d%s", " || This is val0 ", val0Size, " || \n"); 
  //printf("%s%d%s", " || This is val1 ", val1Size, " || \n"); 

  if (val0Size > val1Size){
    size = val0Size;
  }
  else if (val1Size > val0Size){
    size = val1Size;
  } else {
    size = val1Size;
  }
  //account for an extra zero at the beginning
  char *numArr = makeArray(val0, size);
  char *valArr = makeArray(val1, size); 
  //printf("%s%s%s", " this is numArr: ", numArr, "\n");
  //printf("%s%s%s", " this is valArr: ", valArr, "\n"); 

  int i = getSize(valArr);
  printf("%s%d%s", " || This Is i: ", i, " ||\n");
  for (i; i >=0; i--)
  {
    int tmp = toInt(numArr[i] + toInt(valArr[i]));
    //printf("%s%d%s", " || this is tmp ", tmp, " ||\n");
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
  
  //free(numArr);
  //free(valArr);
 
  return val0;

}




