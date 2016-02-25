//UUIAccumulator.h
//Andrew Plaza-Spring 2016

#define TRUE 0
#define FALSE 1



char *newUUI(char *str);

//void destroyUUI(struct UUI *num);

void printUUI(char *str);

void printUUIf(char *num);

void die(const char *message);

char * makeArray(char *str, int size);

char * addTen(char *charArr, int j, int tmp);

char * readUUI();

int NE(char *val0, char *val1);

int toInt(char c);

char toChar(int i);

char * sum(char *val0, char *val1);


//should return a String
char * parseString(char *str, int size);

//possibly might statically allocate the size with const char*
char * endCalc(char *charArr);

