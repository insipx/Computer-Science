#include <GL/glut.h>         /* glut.h includes gl.h and glu.h*/
#include "asgn5.c"

void drawpixel(float x,float y,float r,float g,float b) // assume x,y 0-100
{
  //#define SZ  0.004
  #define SZ  0.002

  glBegin(GL_TRIANGLES);
	  glColor3f(r,g,b);
	 	glVertex2f(-1.0+SZ*(float)x,     -1.0+SZ*(float)y);
	 	glVertex2f(-1.0+SZ*(float)x,     -1.0+SZ*(float)y+ SZ);
	 	glVertex2f(-1.0+SZ*(float)x+ SZ, -1.0+SZ*(float)y);

	 	glVertex2f(-1.0+SZ*(float)x+ SZ, -1.0+SZ*(float)y);
	 	glVertex2f(-1.0+SZ*(float)x+ SZ, -1.0+SZ*(float)y+ SZ);
	 	glVertex2f(-1.0+SZ*(float)x,     -1.0+SZ*(float)y+ SZ);
	glEnd();
}

void run_raytracer() {
 
#define INC 0.001 //verify /* seems to work */

  tTuplef eye = {0.5, 0.5, -1.0};
   
  for(float i = 0; i < 1.0; i += INC){
    for(float j = 0; j < 1.0; j += INC){
      tTuplef screen = {i, j, 0};
      float color = ray(screen, eye);
      drawpixel(i*1000, j*1000, color, color, color);
    }
  } 
}

void display(void)
{
	glClear(GL_COLOR_BUFFER_BIT); 
  run_raytracer();
	glFlush(); 
}

int main(int argc, char** argv)
{
  glutInit(&argc,argv);
	glutCreateWindow("Assignment 4"); 
  init_mod();
	glutDisplayFunc(display);
	glutMainLoop();
}
