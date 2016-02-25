#define TRUE 1
#define FALSE 0
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

//Alright I'm up to like 750 mb of memory used.

//Pseudo-constructor that just sends the program back exactly what it
//receives as a parameter.
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
//Basically just a primitive exception-type thing.
void die(const char *message)
{
  if(message){
    printf("ERROR: %s\n", message);
  }else{
  }
  exit(1); //The "nervous breakdown" statement.
}
int getSize(char *str){
  char c;
  int i = 0;
  do{
    c=str[i];
    i++;
  }while(c != '\0');

  //because somehow the nullbyte is still there
  printf("%s%s", "==================================", "\n");
  printf("%s%s%s", "GETSIZE: this is the str: ", str, "\n" );
  printf("%s%d%s", "GETSIZE: this is the size of str: ", i-1, "\n" );
  printf("%s%s", "==================================", "\n");

  return i - 1;
}

char *makeArray(char *str, int size)
{
  //printf("%s%d%s", "the size passed: ", size, "\n");
  //we are assuming here that we never have to add a number with a 
  //greater amount of characters than 32,767
  //that would be a bit crazy
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
      //There's actually a isDigit() function.
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
//Does it really, though?
char * readUUI()
{
  //allocate 64bytes for usage
  size_t size = 1;
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


//I don't know if we need this if we just use strcmp. We shouldn't even need
//the boolean typedef.
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

//I handled NE like this, I just ix-nayed the oolean-bay and it works perfectly.
//We might want to try that it'll also speed us up and we'll take up slightly
//less space in memory.
//int NE(char *one, char *two){
//    return strcmp(one,two);
//}
//Yep okay I tried replacing the original with this, didn't work. Just kept adding more 0's to the string??? Yeah actually does the same thing the way it is right now.


//What's the idea behind this sub?
char * addTen(char *charArr, int j, int tmp)
{
  if(toInt(charArr[j-1] + 1) >= 10) //If the last digit is greater than 1 digit
                                    //this actually takes the digit before the digit passed so if
                                    //099+099
                                    // ^
                                    // this is checking if that one is >= ten and if it is that means that
                                    // we need to carry the one to the next place, so
                                    // recursively do that
                                    // the next one that it will check is
                                    // 099+099
                                    // ^
                                    // that one
  {
    charArr[j] = toChar(tmp % 10); //Take the 1's place,   || because this + 1 >= ten we need the mod
    tmp = toInt(charArr[j-1] + 1); //Carry the 10's place. || so 11 becomes 1, because we are carrying the one
    j--; //Go to the next index of the array ||  in order to check if that one is >= 10 when 1 is added
    addTen(charArr, j, tmp); //Continue the operation. Hang on, what's tmp? tmp is now 
  } else { //Else if it's just 1 digit, IE we don't have to worry about carrying a one 
    charArr[j-1] = toChar(toInt(charArr[j-1] + 1)); // Add one? Why? because we are carrying a one. so we have to do that to the digit before the current pos in the array this method is only used 
                                                    // when what we are adding is >= 10
    //Okay, so the last index is the character of the integer of the itself, plus 1?
    charArr[j] = toChar(tmp % 10); //Take the 1's place. This might be marginally overcomplicated. || I don't think so
                                   //EXPLANATION
                                   //Because the place before it is not >= ten, so we are able to carry the one
                                   //so the current index needs to be mod ten because we carried the one
                                   //EX we have  a tmp of 12 and need to carry the one to the index before
                                   //so because we are carrying a one, 12 should become 2, hence 12 % 2
                                   //this also terminates the recursive function
    } 

  return charArr;
}

char * endCalc(char *charArr)
{
  //look at alll thooooose zeroooooos :P
  //what does this even do?
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


//This is where the fun part is...
char * sum(char *val0, char *val1)
{
  int size;
  
  // -1 because nullbyte 
  //Okay, here we're just getting the sizes of the variables we need?
  int val0Size = getSize(val0);
  int val1Size = getSize(val1);
  
  //debug stuff 
  //printf("%s%d%s", " || This is val0 ", val0Size, " || \n"); 
  //printf("%s%d%s", " || This is val1 ", val1Size, " || \n"); 

  //Makin' sure we've got a big enough array for the whole number.
  //Don't forget we need it 1 bigger.
  if (val0Size > val1Size){
    size = val0Size;
  }
  else if (val1Size > val0Size){
    size = val1Size;
  } else {
    size = val1Size;
  }
  printf("%s%s%s", "This is numArr before: ", val0, "\n");
  printf("%s%d%s", "This is the size ", size, "\n");
  printf("%s%d%s", "This is the val0Size ", val0Size, "\n");

  //I'm sitting here watching my memory usage slowly climb... 
  //Something on my Mint system has a huge leak and I think this might be it.
  //account for an extra zero at the beginning
  char *numArr = makeArray(val0, size);
  printf("%s%s%s", "This is numARR AFTER: ", numArr, "\n");
  char *valArr = makeArray(val1, size); 
  //printf("%s%s%s", " this is numArr: ", numArr, "\n");
  //printf("%s%s%s", " this is valArr: ", valArr, "\n"); 

  int i = getSize(valArr);
  printf("%s%d%s", " || This Is i: ", i, " ||\n");
  printf("%s%d%s", " || This Is the size of valArr: ", getSize(valArr), " ||\n");
  printf("%s%s%s", " || This Is valArr: ", valArr, " ||\n");
  for (i; i >=0; i--)
  {
    int tmp = toInt(numArr[i] + toInt(valArr[i]));
    printf("%s%d%s", "THIS IS TEMP MOFO: ", tmp, "\n");
    
    if(tmp >= 10){
      numArr = addTen(numArr, i, tmp);
      valArr[i] = '0';
    } else {
      numArr[i] = toChar(tmp);
      valArr[i] = '0';
    }
  }
  //let's leave endCalc out of this for now
  //Honestly, what does this subroutine even do?
  //val0 = endCalc(numArr);
  val0 = numArr;  
  printf("%s%s%s", "this is val0", val0, "\n"); 
//  free(numArr);
//  free(valArr);
 
  return val0;

}

