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
  
  tTuplef t1 = TUPLE(1, 0, 6);
  tTuplef t2 = TUPLE(.5, 1, 6);
  tTuplef t3 = TUPLE(0, 0, 6);

  tTuplef intersect = {0, 0, 0}, normal = {0, 0, 0};
  float u = 0.0;

  VECTOR_NORMAL(normal, t1, t2, t3);
  VECTOR_U(u, normal, t1, eye, screen);
  VECTOR_INTERSECT(intersect, eye, screen, u);
  tTuplef v1={0, 0, 0}, v2={0, 0, 0}, v3 = {0, 0, 0}, c1={0,0,0}, c2={0,0,0}, c3={0,0,0};
  float d1=0.0, d2=0.0, d3=0.0;

  VECTOR_VS(v1, v2, v3, t1, t2, t3, intersect);
  VECTOR_CS(c1, c2, c3, v1, v2, v3);
  VECTOR_DS(d1, d2, d3, c1, c2, c3);
  
  if(d1 > 0 && d2 > 0 && d3 > 0) return true;
  else return false;
}

