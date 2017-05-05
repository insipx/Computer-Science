#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>
#include <string.h>
#include "functions.h"
#include "object_construction.c"

asgn5_data triangles = {
  .triangle_data = &triangles.triangle_arr[0], //by reference
  .len = 0
};

tIntersect query_intersection(tTuplef screen, tTuplef eye, int triangle);
tIntersect query_lightray(tTuplef screen, tTuplef eye, tTuplef center, float radius);

bool ray(tTuplef screen, tTuplef eye) {
  //check all collisions(lights + tris)
  //find one with closest u such that u > 0
  //if(light) right = 1;
  //if(tri) foreach light where signs of oldray dot N and newray dot N differ {
  //  bright += ray(I to light)/|I - light|
  //}
  //return bright
  
  //check collisoins  
  tIntersect result;
  for(int i = 0; i < triangles.len; i++) {
    tIntersect result = query_intersection(screen, eye, 0); 
  }

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

  // it's best to redirect output to file for debug 
  if(DEBUG) {
    printf("\n\n"); 
    printf("EYE: (%f,%f,%f)\n", eye.x, eye.y, eye.z);
    printf("SCREEN: (%f,%f,%f)\n", screen.x, screen.y, screen.z);
    printf("NORMAL: (%f,%f,%f)\n", normal.x, normal.y, normal.z);
    printf("U: %f \n", u);
    printf("INTERSECT: (%f,%f,%f) \n", intersect.x, intersect.y, intersect.z);
  }

  VECTOR_VS(v1, v2, v3, t1, t2, t3, intersect);
  VECTOR_CS(c1, c2, c3, v1, v2, v3);
  VECTOR_DS(d1, d2, d3, c1, c2, c3);

  if(DEBUG) {
    printf("START V (%f,%f,%f), (%f,%f,%f), (%f,%f,%f) END\n", 
        v1.x, v1.y, v1.z, v2.x, v2.y, v2.z, v3.x, v3.y, v3.z);
    printf("START C (%f,%f,%f), (%f,%f,%f), (%f,%f,%f) END\n", 
        c1.x, c1.y, c1.z, c2.x, c2.y, c2.z, c3.x, c3.y, c3.z);
    printf("START D %f %f %f END\n", d1, d2, d3);
    printf("\n \n");
  }

  if((d1 > 0) && (d2 > 0) && (d3 > 0)) result.lit = true;
  else result.lit = false;
  result.u = u;

  return result;
}

tIntersect query_lightray(tTuplef screen, tTuplef eye, tTuplef center, float radius) {
  //eye screen center
  tIntersect result = { 0.0, false };
  
  bool does_intersect;
  VECTOR_TEST_RADIUS(does_intersect, eye, screen, center, radius);
  tIntersect result
  return result;
}




void init_mod() {

  build_squarey(((struct tuple){.x=0.5, .y=0.0, .z=0.5}), 0.5, triangles.triangle_data, 0); // 2 tri
  //*(triangles + 1)
  build_cube(((struct tuple ){.x=0.2, .y=0.15, .z=0.6}), 0.1, triangles.triangle_data+2); // 12 tri

  if(DEBUG) {
    printf("=================================================================\n");
    printf("                             INIT_MOD                            \n");
    printf("=================================================================\n");
    if(GL_TEST) {
      for(int i = 2; i < 14; i++) {

        if(i == 2) printf("glColor3f(%d,%d,%d);\n", 1,0,0);
        if(i == 6) printf("glColor3f(%d,%d,%d);\n", 0,0,1);
        if(i == 10) printf("glColor3f(%d,%d,%d);\n", 0,1,0);
        printf("glVertex3f(%f, %f, %f);\n", i, triangles.triangle_data[i].t1.x, triangles.triangle_data[i].t1.y, triangles.triangle_data[i].t1.z);
        printf("glVertex3f(%f, %f, %f);\n", i, triangles.triangle_data[i].t2.x, triangles.triangle_data[i].t2.y, triangles.triangle_data[i].t2.z);
        printf("glVertex3f(%f, %f, %f);\n", i, triangles.triangle_data[i].t3.x, triangles.triangle_data[i].t3.y, triangles.triangle_data[i].t3.z);

      }
    } else {
      for(int i = 0; i < 14; i++) {
        printf("TRIANGLE %d\n", i);
        printf("    (%f, %f, %f)\n", i, triangles.triangle_data[i].t1.x, triangles.triangle_data[i].t1.y, triangles.triangle_data[i].t1.z);
        printf("    (%f, %f, %f)\n", i, triangles.triangle_data[i].t2.x, triangles.triangle_data[i].t2.y, triangles.triangle_data[i].t2.z);
        printf("    (%f, %f, %f)\n", i, triangles.triangle_data[i].t3.x, triangles.triangle_data[i].t3.y, triangles.triangle_data[i].t3.z);
      }
    }
    printf("=================================================================\n");
    printf("                           END INIT_MOD                          \n");
    printf("=================================================================\n");
  }

  triangles.len = 14;
}

