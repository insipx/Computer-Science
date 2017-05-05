#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>
#include <string.h>
#include <math.h>
#include "functions.h"
#include "object_construction.c"

asgn5_data triangles = {
  .triangle_data = &triangles.triangle_arr[0], //by reference
  .len = 0
};

light_data lights = {
  .light_data = &lights.light_arr[0],
  .len = 0
};

tIntersect query_intersection(tTuplef screen, tTuplef eye, int triangle);
tIntersect query_lightray(tTuplef screen, tTuplef eye, tTuplef center, float radius);

float ray(tTuplef screen, tTuplef eye) {
  #define LIGHT -1.0
  #define TRIANGLE 1.0
  float bright = 0.0;
  
  //check all collisions(lights + tris)
  //find one with closest u such that u > 0
  //if(light) right = 1;
  //if(tri) foreach light where signs of oldray dot N and newray dot N differ {
  //  bright += ray(I to light)/|I - light|
  //}
  //return bright
 
  //check collisions  
  
  tIntersect result, closest_u;
  closest_u.u = INFINITY; 
  
  //check triangle collisions
  for (int i = 0; i < triangles.len; i++) {
    result = query_intersection(screen, eye, i);
    if(result.u < closest_u.u && result.u > 0 && result.lit){
      closest_u = result;
      closest_u.type = TRIANGLE;
    }
  }

  //check light collisions
  for (int i = 0; i< lights.len; i++) {
    result = query_lightray(screen, eye, lights.light_data[i], .1);
    if(result.u < closest_u.u && result.u > 0 && result.lit){
      closest_u = result;
      closest_u.type = LIGHT;
    }
  }
 
  if(closest_u.type == LIGHT)  bright = 1.0;
  if(closest_u.type == TRIANGLE) {
    bright = 0.1;
    for(int i = 0; i < lights.len; i++) {
      tTuplef oldray;
      VECTOR_SUBTRACT(oldray, eye, screen);
      tTuplef newray;
      VECTOR_SUBTRACT(newray, closest_u.I, lights.light_data[i]); 
      float dot_old, dot_new;
      VECTOR_DOT(dot_old, oldray, closest_u.normal);
      VECTOR_DOT(dot_new, newray, closest_u.normal);
      //foreach light where signs of oldray dot N and newray dot N differ {}  
      bool differs;
      CHECK_SIGNS(differs, dot_old, dot_new);

      if(differs) {
        tTuplef sub_diff; VECTOR_SUBTRACT(sub_diff, closest_u.I, lights.light_data[i]);
        float mag;
        VECTOR_MAG(mag, sub_diff);
        bright += (ray(lights.light_data[i], closest_u.I)) / (mag);
      }
    }
  }

  return bright;
}


// TODO: could make a meta-macro
tIntersect query_intersection(tTuplef screen, tTuplef eye, int triangle) {
  
  //init to 0
  tIntersect result = { 0.0, {0.0, 0.0, 0.0}, {0.0, 0.0, 0.0}, false, 0, 0};

  //just one triangle 
 tTuplef t1 = triangles.triangle_data[triangle].t1, 
         t2 = triangles.triangle_data[triangle].t2, 
         t3 = triangles.triangle_data[triangle].t3;
  
  VECTOR_NORMAL(result.normal, t1, t2, t3);

  VECTOR_U(result.u, result.normal, t1, eye, screen);

  VECTOR_INTERSECT(result.I, eye, screen, result.u);

  tTuplef v1={0, 0, 0}, v2={0, 0, 0}, v3 = {0, 0, 0}, 
          c1={0,0,0}, c2={0,0,0}, c3={0,0,0};

  float d1=0.0, d2=0.0, d3=0.0;

  // it's best to redirect output to file for debug 
  if(DEBUG) {
    printf("\n\n"); 
    printf("EYE: (%f,%f,%f)\n", eye.x, eye.y, eye.z);
    printf("SCREEN: (%f,%f,%f)\n", screen.x, screen.y, screen.z);
    printf("NORMAL: (%f,%f,%f)\n", result.normal.x, result.normal.y, result.normal.z);
    printf("U: %f \n", result.u);
    printf("INTERSECT: (%f,%f,%f) \n", result.I.x, result.I.y, result.I.z);
  }

  VECTOR_VS(v1, v2, v3, t1, t2, t3, result.I);
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

  return result;
}

tIntersect query_lightray(tTuplef screen, tTuplef eye, tTuplef center, float radius) {
  //eye screen center
  tIntersect result = { 0.0, false };
  
  float u;                                       
  VECTOR_U_SPHERE(u, eye, screen, center);                
                                                  
  tTuplef p;                                     
  VECTOR_DIST_SPHERE(p,eye,screen,u);
                                                  
  tTuplef sub;                                   
  VECTOR_SUBTRACT(sub, p, center);                  
                                                  
  float sub_mag;                                 
  VECTOR_MAG(sub_mag, sub);                     
                                                  
  result.u = u;
  result.lit = sub_mag <= radius;
  return result;
}




void init_mod() {

  build_squarey(((struct tuple){.x=0.5, .y=0.0, .z=0.5}), 0.5, triangles.triangle_data, 0); // 2 tri
  //*(triangles + 1)
  build_cube(((struct tuple ){.x=0.2, .y=0.15, .z=0.6}), 0.1, triangles.triangle_data+2); // 12 tri

  lights.light_data[0] = (struct tuple){.x=0.5, .y=0.5, .z=1.5};
  lights.len += 1;
  

  if(DEBUG) {
    printf("=================================================================\n");
    printf("                             INIT_MOD                            \n");
    printf("=================================================================\n");
    if(GL_TEST) {
      for(int i = 10; i < 14; i++) {

        if(i == 2) printf("glColor3f(%d,%d,%d);\n", 1,0,0);
        if(i == 6) printf("glColor3f(%d,%d,%d);\n", 0,0,1);
        if(i == 10) printf("glColor3f(%d,%d,%d);\n", 0,1,0);
        printf("glVertex3f(%f, %f, %f);\n", i, triangles.triangle_data[i].t1.x, triangles.triangle_data[i].t1.y, triangles.triangle_data[i].t1.z);
        printf("glVertex3f(%f, %f, %f);\n", i, triangles.triangle_data[i].t2.x, triangles.triangle_data[i].t2.y, triangles.triangle_data[i].t2.z);
        printf("glVertex3f(%f, %f, %f);\n", i, triangles.triangle_data[i].t3.x, triangles.triangle_data[i].t3.y, triangles.triangle_data[i].t3.z);

      }
    } else {
      for(int i = 8; i < 14; i++) {
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

