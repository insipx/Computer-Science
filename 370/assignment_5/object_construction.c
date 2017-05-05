#include <stdbool.h>
#include "functions.h"

    //\/\/\/\/\/\/\/\/\/
    //Object Construction
    //\/\/\/\/\/\/\/\/\/
    //passed by reference, should help a tiny bit in optimization

void build_trianglex(tTuple2df v1, tTuple2df v2, tTuple2df v3, float plane, tTriangle *ptr) 
{
    *(ptr) = ((struct triangle){                                   
          .t1={plane, v1.a, v1.b},                                
          .t2={plane, v2.a, v2.b},  
          .t3={plane, v3.a, v3.b}                              
        });

}

void build_triangley(tTuple2df v1, tTuple2df v2, tTuple2df v3, float plane, tTriangle *ptr)  
{
    *(ptr) = ((struct triangle){                                   
      .t1={v1.a, plane, v1.b},                                    
      .t2={v2.a, plane, v2.b},                                   
      .t3={v3.a, plane, v3.b}                                     
    });                                                           

}

void build_trianglez(tTuple2df v1, tTuple2df v2, tTuple2df v3, float plane, tTriangle *ptr) 
{
    *(ptr) = (struct triangle) {                                  
      .t1={v1.a, v1.b, plane},                                    
      .t2={v2.a, v2.b, plane},                                    
      .t3={v3.a, v3.b, plane}                                     
    };                                                       

}

void build_squarez(tTuplef center, float offset, tTriangle *ptr, int dir)
{
    float centerOff;
    if(dir == 0) centerOff = center.z;
    else if (dir < 0) centerOff = center.z - offset;
    else centerOff = center.z + offset;

    build_trianglez(                                             
        ((struct tuple2d) {.a=center.x-offset, .b=center.y+offset}),   
        ((struct tuple2d) {.a=center.x+offset, .b=center.y+offset}),   
        ((struct tuple2d) {.a=center.x-offset, .b=center.y-offset}),   
        centerOff, ptr);                                         
                                                                  
    build_trianglez(                                              
        ((struct tuple2d) {.a=center.x+offset, .b=center.y+offset}),   
        ((struct tuple2d) {.a=center.x+offset, .b=center.y-offset}),   
        ((struct tuple2d) {.a=center.x-offset, .b=center.y-offset}),   
        centerOff, ptr+1);                                         
}

void build_squarey(tTuplef center, float offset, tTriangle *ptr, int dir)
{
    float centerOff;
    if(dir == 0) centerOff = center.y;
    else if (dir < 0) centerOff = center.y - offset;
    else centerOff = center.y + offset;

    build_triangley(                                              
        ((struct tuple2d) {.a=center.x-offset, .b=center.z+offset}),   
        ((struct tuple2d) {.a=center.x+offset, .b=center.z+offset}),   
        ((struct tuple2d) {.a=center.x-offset, .b=center.z-offset}),   
        centerOff, ptr);                                         
                                                                  
    build_triangley(                                             
        ((struct tuple2d) {.a=center.x+offset, .b=center.z+offset}),   
        ((struct tuple2d) {.a=center.x+offset, .b=center.z-offset}),  
        ((struct tuple2d) {.a=center.x-offset, .b=center.z-offset}),   
        centerOff, ptr+1);                                         

}

void build_squarex(tTuplef center, float offset, tTriangle *ptr, int dir)
{
    float centerOff;
    if(dir == 0) centerOff = center.x;
    else if (dir < 0) centerOff = center.x - offset;
    else centerOff = center.x + offset;

    build_trianglex(                                             
        ((struct tuple2d) {.a=center.y-offset, .b=center.z+offset}),       
        ((struct tuple2d) {.a=center.y+offset, .b=center.z+offset}),       
        ((struct tuple2d) {.a=center.y-offset, .b=center.z-offset}),       
        centerOff, ptr);                                         
                                                                  
    build_trianglex(                                              
        ((struct tuple2d) {.a=center.y+offset, .b=center.z+offset}),       
        ((struct tuple2d) {.a=center.y+offset, .b=center.z-offset}),      
        ((struct tuple2d) {.a=center.y-offset, .b=center.z-offset}),       
        centerOff, ptr+1);                                         
}
   
void build_cube(tTuplef center, float offset, tTriangle *ptr) 
{
  build_squarex(center,offset, ptr, -1);
  build_squarex(center,offset, ptr+2, +1);

  build_squarey(center,offset, ptr+4, -1);
  build_squarey(center,offset, ptr+6, +1);

  build_squarez(center,offset, ptr+8, -1);
  build_squarez(center,offset,ptr+10, +1);
}

