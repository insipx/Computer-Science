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

  tTuplef eye = {.5, .5, -2.0};
   
  for(float i = 0; i < 1.0; i += 0.01){
    for(float j = 0; j < 1.0; j += 0.01){
      tTuplef screen = {i, j, 0};
      if(ray(screen, eye)) drawpixel(i*100, j*100, 1,1,1);
      else drawpixel(i*100, j*100, 0,0,0);
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
