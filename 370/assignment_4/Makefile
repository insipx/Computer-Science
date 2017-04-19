FLAGS=-D_LINUX -ffast-math -funroll-loops -O3 -g -std=gnu99 -trigraphs
LIBS=-lGL -lglut -lGLU -lm
INCDIR=-I../include
LIBDIR=-L../lib -L/usr/X11R6/lib
CC=gcc

asgn3: asgn3.c
	$(CC) asgn3.c $(FLAGS) $(INCDIR) $(LIBDIR) $(LIBS) -o asgn3
clean:
	rm asgn3
