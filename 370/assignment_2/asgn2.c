// Code online at http://www.cs.uofs.edu/~bishop/proj1-2.c
#include <GL/glut.h>         /* glut.h includes gl.h and glu.h*/
#include "topsecret.c"
int rotate = 0;
void display(void)
{
  rotate += 1;
  // rotate eye about y
	transform_eye(rotate);
  int eyex = 5, eyey = 5, eyez = 5;

	// set up new view
	glMatrixMode(GL_PROJECTION);
	glLoadIdentity();
	gluPerspective( 40.0, 1.0, 1.0, 10000.0 );
	glMatrixMode(GL_MODELVIEW);
	glLoadIdentity();
	gluLookAt( eyex, eyey, eyez, // eye
	           0.0, 0.0, 0.0, // target
	           0.0, 1.0, 0.0  // up
		 );

	glClear(GL_COLOR_BUFFER_BIT|GL_DEPTH_BUFFER_BIT); 
	draw_triangles();
	
  //glFlush();
	glutSwapBuffers();
}

int main(int argc, char** argv)
{
 	glutInit(&argc,argv); 
	glutInitDisplayMode(GLUT_DOUBLE | GLUT_RGBA | GLUT_DEPTH);
	glutCreateWindow("Assignment 2"); 
	glutDisplayFunc(display);
	glutIdleFunc(glutPostRedisplay);
	glEnable(GL_DEPTH_TEST);
	init_mod();
	glutMainLoop();
}
