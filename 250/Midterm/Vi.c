// Sean Batzel
// Solution to Midterm question VI

#include <stdio.h>

int main(){
	int w = 1000;
	int x = 1;
	int y = 1;
	int z;
	printf("%d\n", x);
	printf("%d\n", y);
	do{
		z = x + y;
		if (z > w) break;
		printf("%d\n", z);
		x = y;
		y = z;
	}while(z < w);
}