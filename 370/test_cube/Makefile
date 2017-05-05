FLAGS=-D_LINUX -ffast-math -funroll-loops -O3 -g 
LIBS=-lGL -lglut -lGLU -lm
INCDIR=-I../include
LIBDIR=-L../lib -L/usr/X11R6/lib
CC=gcc

asgn2: asgn2.c
	$(CC) asgn2.c $(FLAGS) $(INCDIR) $(LIBDIR) $(LIBS) -o asgn2
clean:
	rm asgn2
