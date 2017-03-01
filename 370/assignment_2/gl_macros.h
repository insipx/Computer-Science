//
// Created by insi on 02/27/17.
//
//Macros to Construct 2D Squares on Different Planes
//Useful for creating Cubes out of Triangles ;)
#include <GL/glut.h>
#include <math.h>

#ifndef GL_MACROS_H
#define GL_MACROS_H

/* for some safety */
/* Taken but modified from linux kernel */
#define TYPECHECK(type, x)                              \
({                                                      \
  type dumb;                                            \
  typeof(x) dumb2;                                      \
  (void)(&dumb == &dumb2);                              \
  1;                                                    \
})

// Operates on traditional X, Y, Z Plane (no rotation)
// in front/behind 2d-squares, y as distance
#define DRAW_SQUARE(x1,x2,y1,y2,z)                    \
({                                                    \
  /* Triangle 1, Top-Left Half */                     \
  glVertex3f(x1, y1, z);                              \
  glVertex3f(x1, y2, z);                              \
  glVertex3f(x2, y1, z);                              \
                                                      \
  /* Triangle 2  Bottom-Right Half */                 \
  glVertex3f(x2, y2, z);                              \
  glVertex3f(x2, y1, z);                              \
  glVertex3f(x1, y2, z);                              \
})

//left side/right side 2d-squares, x as distance
//(swap x and z)
#define DRAW_SQUARE_YZ(z1, z2, y1, y2, x)             \
({                                                    \
  /* Triangle 1, Top-Left half */                     \
  glVertex3f(x, y1, z1);                              \
  glVertex3f(x, y2, z1);                              \
  glVertex3f(x, y1, z2);                              \
                                                      \
  /* Triangle 2 Bottom-Right Half */                  \
  glVertex3f(x, y2, z2);                              \
  glVertex3f(x, y1, z2);                              \
  glVertex3f(x, y2, z1);                              \
})

//Top and Bottom 2d-squares, y as distance
//(swap y and z)
#define DRAW_SQUARE_ZX(x1, x2, z1, z2, y)             \
({                                                    \
  /* Triangle 1, Top-Left Half */                     \
  glVertex3f(x1, y, z1);                              \
  glVertex3f(x1, y, z2);                              \
  glVertex3f(x2, y, z1);                              \
                                                      \
  /* Triangle 2, Bottom-Right Half */                 \
  glVertex3f(x2, y, z2);                              \
  glVertex3f(x2, y, z1);                              \
  glVertex3f(x1, y, z2);                              \
})

#endif /* GL_MACROS_H */
