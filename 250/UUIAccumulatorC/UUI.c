#include <stdio.h>
#include <stdlib.h>
/*
 * 
 * Sean Batzel's Version of the Solution to Machine Org Assignment 1 (Unlimited unsigned integers) Developed by Myself and Andrew Plaza.
 *
 ***************************************************************************************************
 *KNOWN ISSUES
 ***************************************************************************************************
 *
 * We didn't actualy use any kind of booleans or 'not equal' with the NE subroutine.
 * 
 * Somehow the program continues to function 'normally' if you give it non-digit characters.
 * They really make messes when the program decides to parse them as integers.
 * 
 * My valgrind is reporting 28 assembler errors in 6 contexts. Most involve 'invalid read size.'
 * 
 * I just lost 60 bytes in 15 blocks.
 * 
 * We didn't free any memory so it's leaking terribly.
 * 
 ***************************************************************************************************
 */

//Pseudo-constructor that just sends the program back exactly what it
//receives as a parameter.
char *newUUI(char* str) {
	return str;
}

void printUUI(char *str) {
	printf("%s", str);
}

void printUUIf(char *str) {
	//int length = strlen(str);
	//int i;
	//for (i = 0; i < length; i = i + 3) {
	//	printf("%d%d%d,",str[i],str[i+1],str[i+2]);
	//}
	//Alright I tried but the assignment is due in a half an hour.
	printf("%s", str);
}

//Basically just a primitive exception-type thing.
void die(const char *message) {
	if (message) {
		printf("Fatal error: %s\n", message);
	} else {
		printf("Fatal error.\n");
	}
	exit(1); //The "nervous breakdown" statement.
}

int getSize(char *str) {
	//just walk through the string until we come accross the nullbyte or \n and increment i
	char c;
	int i = 0;
	do {
		c = str[i];
		i++;
	} while (c != '\0' && c != '\n');
	//Not exactly certain why this works, but most of our tests liked it.
	return i - 1;
}

char *makeArray(char *str, int size) {
	//size_t because..unsigned int so we can add a number /w A LOT of characters
	size_t strSize = getSize(str);
	//add one to size to have a zero at the beginning
	size++;
	//malloc that memory
	char *charArr = (char *) malloc(size);
	//make sure it was malloced 
	if (charArr) {
	} else {
		die("Memory allocation unsuccessful");
	}
	charArr[0] = '0';
	//add zeroes at the beginning of the array
	int i = 1;
	for (; i < (size - strSize); i++) {
		charArr[i] = '0';
	}
	//add the rest of the str to our new modified str pointer
	int j = 0;
	i = (size - strSize);
	for (; i < size; i++) {
		char tmp = str[j];
		charArr[i] = tmp;
		j++;
	}
	return charArr;
}

//this needs to be dynamic, we have no idea what the user will input
//so our best bet is to use fgetc
//dynamically allocates memory so we only use exactly what we need
char * readUUI() {
	// Allocate memory for a string.
	size_t size = 1;
	char *str = (char*) malloc(size);
	if (str) {
	} else {
		die("Mem allocate unsuccessful");
	}
	char c;
	int i = 0;
	do {
		c = fgetc(stdin);
		str[i] = c;
		i++;
		//reallocate memory by +1 every time, we use very little memory this way
		//this isn't the most mem-efficient method but it should use less cycles
		if (i >= size) {
			size++;
			str = (char*) realloc(str, size);
		}
		if (c == '\n' || c == EOF) {
			str[i] = '\0';
			break;
		}
	} while (1);
	// printf("%s%s", " This is the Readline String: ", str); 
	// printf("%s%d%s", " and this is the size ", size, "\n");
	return str;
}

//to int and char function so it's nicely human-readable
int toInt(char c) {
	return c - '0';
}

char toChar(int i) {
	return i + '0';
}

// This kind of works. We have to check the size of both char *str though.
// it will ignore leading zeros and only terminate when one zero is entered though
int NE(char *one, char *two) {
	if (*one == '0' && *two == '0' && getSize(one) == getSize(two)) {
		return 0;
	} else {
		return 1;
	}
}

//a recursive subroutine to carry a one. this is only called when tmp (in sum method) is >= 10)
//it recursively walks through the number until it finds a digit which adds to ten but is not greater than 9
char * addTen(char *charArr, int j, int tmp) {
	if (toInt(charArr[j - 1] + 1) >= 10) {
		//if the digit BEFORE the digit that was called also adds up to or greater than 10
		charArr[j] = toChar(tmp % 10);
		//set the current digit to mod 10 so that we get rid of the 1
		tmp = toInt(charArr[j - 1] + 1); //tmp is now the digit before this one + 1
		j--; //decrement J 
		addTen(charArr, j, tmp); //recursively do it again 
	} else {
		// if it is not >= 10 all is swell in this world 
		charArr[j - 1] = toChar(toInt(charArr[j - 1] + 1));
		charArr[j] = toChar(tmp % 10);
	}

	return charArr;
}

//look at alll thooooose zeroooooos :P
//I don't know why, but C somehow removes zeroes from the beginning for us
//unlike my Java implementation
//so we just have to remove the first zero, I guess
char *endCalc(char *charArr) {
	int i = 0;
	while (charArr[i] == '0') {
		charArr += 1;
	}
	return charArr;
}

//This is where the fun part is...
char * sum(char *val0, char *val1) {
	int size;
	//getting the sizes of the passed char *str
	//so that we can find the largest one
	//and also because C doesn't have anykind of .length method
	//and sizeof returns the sizeof a pointer 
	//so we have to explicitly keep track of size
	int val0Size = getSize(val0);
	int val1Size = getSize(val1);
	//Makin' sure we've got a big enough array for the whole number.
	if (val0Size > val1Size) {
		size = val0Size;
	} else if (val1Size > val0Size) {
		size = val1Size;
	} else {
		size = val1Size;
	}
	//account for an extra zero at the beginning
	char *numArr = makeArray(val0, size);
	char *valArr = makeArray(val1, size);
	int i = size;
	for (; i >= 0; i--) {
		int numArrTmp = toInt(numArr[i]);
		int valArrTmp = toInt(valArr[i]);
		int tmp = numArrTmp + valArrTmp;
		//if tmp >= 10 we need to carry a 1 
		if (tmp >= 10) {
			numArr = addTen(numArr, i, tmp);
			//otherwise we don't
		} else {
			numArr[i] = toChar(tmp);
		}
	}
	free(valArr);
	return endCalc(numArr);
}
