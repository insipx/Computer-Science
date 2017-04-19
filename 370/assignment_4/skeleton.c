#include <GL/glut.h>         /* glut.h includes gl.h and glu.h*/
#include "asgn4.c"

void drawpixel(float x,float y,float r,float g,float b) // assume x,y 0-100
{
#define SZ  .02
	glBegin(GL_TRIANGLES);
		glColor3f(r,g,b);
	 	glVertex2f(-1.0+.02*(float)x,     -1.0+.02*(float)y);
	 	glVertex2f(-1.0+.02*(float)x,     -1.0+.02*(float)y+ SZ);
	 	glVertex2f(-1.0+.02*(float)x+ SZ, -1.0+.02*(float)y);

	 	glVertex2f(-1.0+.02*(float)x+ SZ, -1.0+.02*(float)y);
	 	glVertex2f(-1.0+.02*(float)x+ SZ, -1.0+.02*(float)y+ SZ);
	 	glVertex2f(-1.0+.02*(float)x,     -1.0+.02*(float)y+ SZ);
	glEnd();
}

void run_raytracer() {

  tTuplef eye = TUPLE(.5, .5, -2.0);
  
  float i = 0.0, j = 0.0;
  bool color;
  for(i; i < 1.0; i += 0.01){
    for(j; j < 1.0; j += 0.01){
      color = ray((tTuplef){i, j, 0}, eye);

      if(color) drawpixel(i, j, 1,1,1);
      else drawpixel(i, j, 0,0,0);
    }
  } 
}

void display(void)
{
	glClear(GL_COLOR_BUFFER_BIT); 
	drawpixel(50.0,50.0,1.0,1.0,1.0); 
  run_raytracer();
	glFlush(); 
}

int main(int argc, char** argv)
{
  glutInit(&argc,argv);
	glutCreateWindow("Assignment 4"); 
	glutDisplayFunc(display);
	glutMainLoop();
}
