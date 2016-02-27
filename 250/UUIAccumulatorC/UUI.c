#include <stdio.h>
#include <stdlib.h>


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
//just walk through the string until we come accross the nullbyte or \n and increment i
  char c;
  int i = 0;
  do{
    c=str[i];
    i++;
  }while(c != '\0' && c!= '\n');
  //i - 1 because....i'm not really sure. but in my debug statements it was returning a size that was 1 more than what it should be
  //nullbyte maybe?
  return i - 1;
}

char *makeArray(char *str, int size)
{
  //size_t because..unsigned int so we can add a number /w ALOT of characters
  size_t strSize = getSize(str);
  //add one to size to have a zero at the beginning
  size++; 
  //malloc that memory
  char *charArr = (char *)malloc(size);
 
  //make sure it was malloced 
  if(charArr){
  }else{
    die("Memory allocation unsuccessful");
  }
   
  charArr[0] = '0';

  //add zeroes at the beginning of the array
int i = 1;  
for(i; i < (size-strSize); i++){
    charArr[i]='0';
  }
  //add the rest of the str to our new modified str pointer
  int j = 0;
  i = (size-strSize);
  for (i; i< size; i++ )
  {
    char tmp = str[j];
    charArr[i] = tmp;
    j++;
  }

  return charArr;
}
// didn't finish this yet
// to parse and make sure that all digits are acually digits 
// i think alot of memcpy would be used here
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
 // printf("%s%s", " This is the Readline String: ", str); 
 // printf("%s%d%s", " and this is the size ", size, "\n");
  return str;
}

//to int and char function so it's nicely human-readable
int toInt(char c){ return c - '0'; }

char toChar(int i){ return i + '0'; }

// This kind of works. We have to check the size of both char *str though.
// it will ignore leading zeros and only terminate when one zero is entered though
int NE(char *one, char *two){
  if (*one == '0' && *two == '0' && getSize(one) == getSize(two)){
    return 0;
  } else {
    return 1;
  }
}

//a recursive subroutine to carry a one. this is only called when tmp (in sum method) is >= 10)
//it recursively walks through the number until it finds a digit which adds to ten but is not greater than 9
char * addTen(char *charArr, int j, int tmp)
{
  if(toInt(charArr[j-1] + 1) >= 10)   {
    //if the digit BEFORE the digit that was called also adds up to or greater than 10
    charArr[j] = toChar(tmp % 10); 
    //set the current digit to mod 10 so that we get rid of the 1
    tmp = toInt(charArr[j-1] + 1);  //tmp is now the digit before this one + 1
    j--; //decrement J 
    addTen(charArr, j, tmp);//recursively do it again 
  } else { 
    // if it is not >= 10 all is swell in this world 
    charArr[j-1] = toChar(toInt(charArr[j-1] + 1));  
    charArr[j] = toChar(tmp % 10); 
  } 

  return charArr;
}


//look at alll thooooose zeroooooos :P
//I don't know why, but C somehow removes zeroes from the beginning for us
//unlike my Java implementation
//so we just have to remove the first zero, I guess
char * endCalc(char *charArr)
{
  int i = 0;
  while(charArr[i] == '0'){
    charArr += 1;
  } 
  return charArr;
}

//This is where the fun part is...
char * sum(char *val0, char *val1)
{
  int size;
  //getting the sizes of the passed char *str
  //so that we can find the largest one
  //and also because C doesn't have anykind of .length method
  //and sizeof returns the sizeof a pointer 
  //so we have to explicitly keep track of size
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

  //this is probably not how to debug C
  //but without these I'm probably never going to finish
  //printf("%s%s%s", "This is numArr before: ", val0, "\n");
  //printf("%s%s", "++++++++++++++++++++++++ ", "\n");
  //printf("%s%s%s", "This is valArr before: ", val1, "\n");
  //printf("%s%d%s", "This is the size ", size, "\n");
 // printf("%s%d%s", "This is the val0Size ", val0Size, "\n");
  //printf("%s%d%s", "This is the val1Size ", val1Size, "\n");
   //printf("%s%s", "++++++++++++++++++++++++ ", "\n");

   //account for an extra zero at the beginning
  char *numArr = makeArray(val0, size);
  char *valArr = makeArray(val1, size); 

  //  printf("%s%s%s", "This is numARR AFTER: ", numArr, "\n");

  int i = size;

  for (i; i >=0; i--)
  {
    int numArrTmp = toInt(numArr[i]);
    int valArrTmp = toInt(valArr[i]);
    int tmp = numArrTmp + valArrTmp;

    //if tmp >= 10 we need to carry a 1 
    if(tmp >= 10){
      numArr = addTen(numArr, i, tmp);
    //  valArr[i] = '0';
    //  otherwise we don't
    } else {
      numArr[i] = toChar(tmp);
  //    valArr[i] = '0';
    }
  }

  free(valArr); 
//  free(numArr);
  
  return endCalc(numArr);
  //valgrind is saying that we lost 17 bytes in 5 blocks,
  //none of that memory being reachable
  //with 19 allocs and only 14 frees
  //I called it twice
  //so we are missing two frees somewhere
  //but I'm not sure how to add them
  //maybe memcpy val0, numarr valarr into 
  //another pointer?
  //idk
  //i'm not sure how to do this, this a definite weakness in this code
  //maybe freeing it in main would do it
  //not sure if i can touch that, though
}

