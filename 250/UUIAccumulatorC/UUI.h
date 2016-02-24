//UUIAccumulator.h
//Andrew Plaza-Spring 2016
#define TRUE 1
#define FALSE 0

// loosely interpreted, this should basically act as a constructor, 
//struct UUI{
//  char *number;
//}

//char * newUUI(char *str);

//void destroyUUI(struct UUI *num);

//void printUUI(struct UUI *num);

//void printUUIf(struct UUI *num);
void destroy(char *str);
void die(const char *message);
char * readUUI();

//public int NE(char *val);

//public void add(char *val);

//private int checkEquality(char *charA, char *charB);

//should return a String
//private char * parseString(char *str, int size);

//private char * makeArray(char *str, int size);

//private char * addTen(char *charArr, int j, int tmp);

//possibly might statically allocate the size with const char*
//private char * endCalc(char *charArr);

