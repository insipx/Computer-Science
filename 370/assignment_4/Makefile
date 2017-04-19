FLAGS=-D_LINUX -ffast-math -funroll-loops -O3 -g -std=gnu99 -trigraphs
LIBS=-lGL -lglut -lGLU -lm
INCDIR=-I../include
LIBDIR=-L../lib -L/usr/X11R6/lib
CC=gcc

asgn4: asgn4.c
	$(CC) asgn4.c $(FLAGS) $(INCDIR) $(LIBDIR) $(LIBS) -o asgn4
clean:
	rm asgn4
