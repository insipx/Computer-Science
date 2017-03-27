/* Code Online Used as Starter 
 * Modified by Andrew Plaza
 */

#include <GL/glut.h>         /* glut.h includes gl.h and glu.h*/
#include "topsecret.c"

double rotate = 0;

void display(void)
{
  rotate += .0005; //rotate by .01 degrees
  if(rotate >= 360) rotate = 0; //reset rotate, reduces floating point errors

  // rotate eye about y
  double eyex = 5, eyey = 5, eyez = 0;
  transform_eye(rotate, &eyex, &eyey, &eyez);
  //printf("[%lf, %lf, %lf]\n", eyex, eyey, eyez); //debugging
  
  // set up new view
  glMatrixMode(GL_PROJECTION);
  glLoadIdentity();
  //set perspective
  double w = glutGet(GLUT_WINDOW_WIDTH);
  double h = glutGet(GLUT_WINDOW_HEIGHT);
  gluPerspective( 40.0, w/h, 1.0, 10000.0 );
  glMatrixMode(GL_MODELVIEW);
  glLoadIdentity();
  gluLookAt((float)eyex, (float)eyey, (float)eyez, // eye
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
  //GLuint image;
 	glutInit(&argc,argv); 
	glutInitDisplayMode(GLUT_DOUBLE | GLUT_RGBA | GLUT_DEPTH | GLUT_ALPHA);
	glutCreateWindow("Assignment 2"); 
	glutDisplayFunc(display);
	glutIdleFunc(glutPostRedisplay);
	glEnable(GL_DEPTH_TEST);
  glEnable(GL_MULTISAMPLE);

  /* Texture Init 
  glGenTextures(1, &image);
  gluBuild2DMipmaps(GL_TEXTURE_2D, hl3_image.bytes_per_pixel, hl3_image.width, hl3_image.height, GL_RGBA, GL_UNSIGNED_BYTE, hl3_image.pixel_data);
  */

	init_mod();
	glutMainLoop();
}
