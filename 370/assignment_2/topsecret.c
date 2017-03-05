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
    glColor3f(1.0, 1.0, 1.0);
  
  //initialize Vector Data Structure
  asgn2_data.vectors = (float [8][3])
  {
    {-.5, .5, .5},
    {.5, -.5, .5}, 
    {-.5, .5, -.5},
    {.5, -.5, -.5}
  };

  /*asgn2_data.triangles = (float [10][3]) 
  {
    {asgn2_data.vectors[0][1], asgn2_data.vectors[0][1], asgn2_data.vectors[0][2]},
    {}
  };*/

/*   DRAW_SQUARE(-.5, .5, .5, -.5, .5);
    DRAW_SQUARE(-.5, .5, .5, -.5, -.5);
*/
 
  /* set up standard orthogonal view with clipping */
  /* box as cube of side 2 centered at origin */
  /* This is default view and these statements could be removed */
  /*
	glMatrixMode (GL_PROJECTION);
  glLoadIdentity ();
	glOrtho(-1.0, 1.0, -1.0, 1.0, -1.0, 1.0); 
  */
}
