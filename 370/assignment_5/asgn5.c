#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>
#include <string.h>
#include "functions.h"

Triangles triangles;
tIntersect query_intersection(tTuplef screen, tTuplef eye, int triangle);

bool ray(tTuplef screen, tTuplef eye) {

  tIntersect result = query_intersection(screen, eye, 0);
  return result.lit;
}


// TODO: could make a meta-macro
tIntersect query_intersection(tTuplef screen, tTuplef eye, int triangle) {
  
  tIntersect result = { 0.0, false };

  tTuplef intersect = {0, 0, 0}, normal = {0, 0, 0};
  float u = 0.0;
 
  //just one triangle 
  tTuplef t1 = triangles.triangle_data[triangle].t1, 
          t2 = triangles.triangle_data[triangle].t2, 
          t3 = triangles.triangle_data[triangle].t3;
  
  VECTOR_NORMAL(normal, t1, t2, t3);
  VECTOR_U(u, normal, t1, eye, screen);
  VECTOR_INTERSECT(intersect, eye, screen, u);
  tTuplef v1={0, 0, 0}, v2={0, 0, 0}, v3 = {0, 0, 0}, 
          c1={0,0,0}, c2={0,0,0}, c3={0,0,0};
  float d1=0.0, d2=0.0, d3=0.0;

 /* // it's best to redirect output to file for debug 
  printf("\n\n"); 
  printf("EYE: (%f,%f,%f)\n", eye.x, eye.y, eye.z);
  printf("SCREEN: (%f,%f,%f)\n", screen.x, screen.y, screen.z);
  printf("NORMAL: (%f,%f,%f)\n", normal.x, normal.y, normal.z);
  printf("U: %f \n", u);
  printf("INTERSECT: (%f,%f,%f) \n", intersect.x, intersect.y, intersect.z);*/

  VECTOR_VS(v1, v2, v3, t1, t2, t3, intersect);
  VECTOR_CS(c1, c2, c3, v1, v2, v3);
  VECTOR_DS(d1, d2, d3, c1, c2, c3);

  /*printf("START V (%f,%f,%f), (%f,%f,%f), (%f,%f,%f) END\n", 
      v1.x, v1.y, v1.z, v2.x, v2.y, v2.z, v3.x, v3.y, v3.z);
  printf("START C (%f,%f,%f), (%f,%f,%f), (%f,%f,%f) END\n", 
      c1.x, c1.y, c1.z, c2.x, c2.y, c2.z, c3.x, c3.y, c3.z);
  printf("START D %f %f %f END\n", d1, d2, d3);
  printf("\n \n"); */

  if((d1 > 0) && (d2 > 0) && (d3 > 0)) result.lit = true;
  else result.lit = false;
  result.u = u;

  return result;
}

void init_mod() {
  tTuplef t1 = {1, 0, 6}, t2 = {.5, 1, 6}, t3 = {0, 0, 6};
  tTriangle T1 = {t1, t2, t3};

  memcpy(triangles.triangle_data, (tTriangle[1])
    {T1},
    sizeof(triangles.triangle_data));
    
}
