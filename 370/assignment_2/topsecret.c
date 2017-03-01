#include <stdio.h>
#include <stdlib.h>
#include <GL/glut.h>
#include "gl_macros.h"
void draw_triangles()
{
	glBegin(GL_TRIANGLES);
    //squares on traditional X/Y/Z Axis
    glColor3f(1.0, 0, 0); // red
    DRAW_SQUARE(-.5, .5, .5, -.5, .5);
    DRAW_SQUARE(-.5, .5, .5, -.5, -.5);
    //squares with Z operating as X (visually)
    glColor3f(0, 1.0, 0); // green
    DRAW_SQUARE_YZ(-.5, .5, .5, -.5, .5); //right side
    DRAW_SQUARE_YZ(-.5, .5, .5, -.5, -.5); // left side
    //squares with Z operating as Y (visually) 
    glColor3f(0, 0, 1.0);
    DRAW_SQUARE_ZX(-.5, .5, .5, -.5, .5);
    DRAW_SQUARE_ZX(-.5, .5, .5, -.5, -.5);
	glEnd();

}

void transform_eye(int rotate) 
{
  float rotateVector[4] = {5, 5, 5, 1};
  int eye[3] = {5, 5, 5};
  int target[3] = {0.0, 0.0, 0.0};
  int up[3] = {0.0, 1.0, 0.0 }; 
  
  ROTATE_Y(rotate, rotateVector, eye, target, up);
}


void init_mod() 
{
  glClearColor (0.0, 0.0, 0.0, 0.0);
  /* set fill  color to white */

  glColor3f(1.0, 1.0, 1.0);

}



