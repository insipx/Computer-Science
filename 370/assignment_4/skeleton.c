#include <GL/glut.h>         /* glut.h includes gl.h and glu.h*/

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

void display(void)
{
	glClear(GL_COLOR_BUFFER_BIT); 
	drawpixel(50.0,50.0,1.0,1.0,1.0); 
	glFlush(); 
}


int main(int argc, char** argv)
{
        glutInit(&argc,argv);
	glutCreateWindow("simple"); 
	glutDisplayFunc(display);
	glutMainLoop();
}
