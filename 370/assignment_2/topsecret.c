#include <stdio.h>
#include <stdlib.h>
#include <GL/glut.h>
#include "gl_macros.h"

Vectors asgn2_data;

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

void transform_eye(double theta, double* eyex, double* eyey, double* eyez) 
{

  double origPos[4] = {5, 5, 5, 1};
  

  double cosT = cos(theta);
  double sinT = sin(theta);

  double rotateArr[4][4] =
  {
    {cosT, 0, sinT, 0},
    {0, 1, 0, 0},
    {-sinT, 0, cosT, 0},
    {0, 0, 0, 1},
  };

  int i = 0, j = 0;
  double result[4];
  for(i = 0; i < 4; i++) {
    for(j = 0; j < 4; j++) {
      result[i] += rotateArr[i][j] * origPos[j];
    }
  }

  *eyex = result[0];
  *eyey = result[1];
  *eyez = result[2];
}


void init_mod() 
{
  glClearColor (0.0, 0.0, 0.0, 0.0);
  /* set fill  color to white */

  glColor3f(1.0, 1.0, 1.0);
  
  //initialize Vector Data Structure
  asgn2_data.vectors = (float [8][3]){
    {-.5, .5, .5},
    {.5, -.5, .5}, 
    {-.5, .5, -.5},
    {.5, -.5, -.5}
  };

 
  /* set up standard orthogonal view with clipping */
  /* box as cube of side 2 centered at origin */
  /* This is default view and these statements could be removed */
  /*
	glMatrixMode (GL_PROJECTION);
  glLoadIdentity ();
	glOrtho(-1.0, 1.0, -1.0, 1.0, -1.0, 1.0); 
  */
}
