/* Created by Andrew Plaza on 02/27/2017 */

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <GL/glut.h>
#include "gl_macros.h"
#include "img/hl3.c"

Vectors asgn2_data;

void draw_triangles()
{
	glBegin(GL_TRIANGLES);
    //squares on traditional X/Y/Z Axis
    for(int i = 0; i < 12; i+=2) {
      if(i % 4 == 0) {
        //alternate colors for each 2 squares. Squares across from each other should be the same color
        glColor3f( (i == 0 || i==8 )? 1.0f : 0.0f, (i == 4 || i == 8)? 1.0f : 0.0f, i == 8 ? 1.0f : 0.0f );
      }
      DRAW_SQUARE(asgn2_data.triangles[i], asgn2_data.triangles[i+1]);
    }
  glEnd();    
}

void transform_eye(double theta, double* eyex, double* eyey, double* eyez) 
{

  double origPos[4] = {5, 5, 5, 1};
  double cosT = cos(theta), sinT = sin(theta);
  
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
  
  /* Texture Init */  
  GLuint image;
  glEnable(GL_TEXTURE_2D);
  glGenTextures(1, &image);
  glBindTexture(GL_TEXTURE_2D, image);
  glTexImage2D (
    GL_TEXTURE_2D,
    0,
    4,
    hl3_image.width,
    hl3_image.height,
    0,
    GL_RGBA,
    GL_UNSIGNED_BYTE,
    hl3_image.pixel_data
  );
  glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_NEAREST);
  glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);

  //initialize Vector Data Structure
  memcpy(asgn2_data.vertices, (float[8][3]) 
    {
      {-.5, .5, .5}, //0
      // -  + +
      {-.5, -.5, .5}, //1
      // - - +
      {.5, .5, .5}, //2
      // + + +
      {.5, -.5, .5},  //3
      //+ - +
      {-.5, .5, -.5}, //4
      //- + -
      {.5, -.5, -.5}, //5
      //+ - - 
      {.5, .5, -.5}, //6
      //+ + -
      {-.5, -.5, -.5} //7
      // ---
    }, sizeof(asgn2_data.vertices));

  memcpy(asgn2_data.triangles, (float *[12][3])
    {
      //square 1
      {asgn2_data.vertices[2], asgn2_data.vertices[3], asgn2_data.vertices[0]},
      {asgn2_data.vertices[1], asgn2_data.vertices[0], asgn2_data.vertices[3]},

      //square 1
      {asgn2_data.vertices[4], asgn2_data.vertices[7], asgn2_data.vertices[6]},
      {asgn2_data.vertices[5], asgn2_data.vertices[6], asgn2_data.vertices[7]},
      
      //square 2
      {asgn2_data.vertices[6], asgn2_data.vertices[5], asgn2_data.vertices[2]},
      {asgn2_data.vertices[3], asgn2_data.vertices[2], asgn2_data.vertices[5]},

      //square 2( 4 7 0) (1 0 7)
      {asgn2_data.vertices[0], asgn2_data.vertices[1], asgn2_data.vertices[4]}, 
      {asgn2_data.vertices[7], asgn2_data.vertices[4], asgn2_data.vertices[1]},

      //Square 3
      {asgn2_data.vertices[0], asgn2_data.vertices[4], asgn2_data.vertices[2]},
      {asgn2_data.vertices[6], asgn2_data.vertices[2], asgn2_data.vertices[4]},

      //square 3
      {asgn2_data.vertices[1], asgn2_data.vertices[7], asgn2_data.vertices[3]},
      {asgn2_data.vertices[5], asgn2_data.vertices[3], asgn2_data.vertices[7]}
    }, sizeof(asgn2_data.triangles));

  /* set up standard orthogonal view with clipping */
  /* box as cube of side 2 centered at origin */
  /* This is default view and these statements could be removed */
  /*
	glMatrixMode (GL_PROJECTION);
  glLoadIdentity ();
	glOrtho(-1.0, 1.0, -1.0, 1.0, -1.0, 1.0); 
  */
}