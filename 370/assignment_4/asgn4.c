#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>
#include "functions.h"


bool ray(tTuplef screen, tTuplef eye) {
  /* 
   * check for collisions
   * find one with closest u such that u>-
   * if (p1 -> p2 intersects) return true;
   * else return false;
   *
   * screen = p2
   * eye = p1
  */
  
  //the calculation  
  
  tTuplef t1 = {1, 0, 6};
  tTuplef t2 = {.5, 1, 6};
  tTuplef t3 = {0, 0, 6};

  tTuplef intersect = {0, 0, 0}, normal = {0, 0, 0};
  float u = 0.0;

  VECTOR_NORMAL(normal, t1, t2, t3);
  VECTOR_U(u, normal, t1, eye, screen);
  VECTOR_INTERSECT(intersect, eye, screen, u);
  tTuplef v1={0, 0, 0}, v2={0, 0, 0}, v3 = {0, 0, 0}, 
          c1={0,0,0}, c2={0,0,0}, c3={0,0,0};
  float d1=0.0, d2=0.0, d3=0.0;

  /* redirect to file for debug, ie: ./asgn4 > dump */
  printf("\n\n"); 
  printf("EYE: (%f,%f,%f)\n", eye.x, eye.y, eye.z);
  printf("SCREEN: (%f,%f,%f)\n", screen.x, screen.y, screen.z);
  printf("NORMAL: (%f,%f,%f)\n", normal.x, normal.y, normal.z);
  printf("U: %f \n", u);
  printf("INTERSECT: (%f,%f,%f) \n", intersect.x, intersect.y, intersect.z);
  VECTOR_VS(v1, v2, v3, t1, t2, t3, intersect);
  VECTOR_CS(c1, c2, c3, v1, v2, v3);
  VECTOR_DS(d1, d2, d3, c1, c2, c3);
  printf("START V (%f,%f,%f), (%f,%f,%f), (%f,%f,%f) END\n", 
      v1.x, v1.y, v1.z, v2.x, v2.y, v2.z, v3.x, v3.y, v3.z);
  printf("START C (%f,%f,%f), (%f,%f,%f), (%f,%f,%f) END\n", 
      c1.x, c1.y, c1.z, c2.x, c2.y, c2.z, c3.x, c3.y, c3.z);
  printf("START D %f %f %f END\n", d1, d2, d3);
  printf("\n \n");

  if((d1 > 0) && (d2 > 0) && (d3 > 0)) return true;
  else return false;
}

