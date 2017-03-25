//
// Created by insi on 02/27/17.
//
//Macros to Construct 2D Squares on Different Planes
//Useful for creating Cubes out of Triangles
#include <GL/glut.h>
#include <math.h>

#ifndef GL_MACROS_H
#define GL_MACROS_H

typedef struct vectors {
  float vertices[8][3];
  float* triangles[12][3];
}Vectors;

/* for some safety */
// doesn't quite work
// but I will leave it here for the future
#define TYPECHECK(type, x)                              \
({                                                      \
  type dumb;                                            \
  typeof(x) dumb2;                                      \
  (void)(&dumb == &dumb2);                              \
})

//draw a square with given coordinates
//accepts two triangles
//triangles should be on the same plane to work properly
#define DRAW_SQUARE(tri1, tri2)                         \
({                                                      \
 glTexCoord3f(1.0, 0.0, 0.0); glVertex3f(tri1[0][0], tri1[0][1], tri1[0][2]);      \
 glTexCoord3f(1.0, 1.0, 0.0); glVertex3f(tri1[1][0], tri1[1][1], tri1[1][2]);      \
 glTexCoord3f(0.0, 0.0, 0.0); glVertex3f(tri1[2][0], tri1[2][1], tri1[2][2]);      \
                                                        \
 glTexCoord3f(0.0, -1.0, 0.0); glVertex3f(tri2[0][0], tri2[0][1], tri2[0][2]);     \
 glTexCoord3f(-1.0, -1.0, 0.0); glVertex3f(tri2[1][0], tri2[1][1], tri2[1][2]);     \
 glTexCoord3f(0.0, 0.0, 0.0); glVertex3f(tri2[2][0], tri2[2][1], tri2[2][2]);     \
 })
#endif /* GL_MACROS_H */
