FLAGS=-D_LINUX -ffast-math -funroll-loops -O0 -g -std=gnu99 -trigraphs
LIBS=-lGL -lglut -lGLU -lm
INCDIR=-I../include
LIBDIR=-L../lib -L/usr/X11R6/lib
CC=gcc

asgn5: skeleton.c
	$(CC) skeleton.c $(FLAGS) $(INCDIR) $(LIBDIR) $(LIBS) -o asgn5
clean:
	rm asgn5
