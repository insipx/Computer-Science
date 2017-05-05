#include <math.h>

#ifndef FUNCTIONS_H
#define FUNCTIONS_H

#define DEBUG 0
#define GL_TEST 0

typedef struct tuple {
  float x;
  float y;
  float z;
} tTuplef; // type tuple float

typedef struct tuple2d {
  float a;
  float b;
} tTuple2df;

typedef struct triangle {
  tTuplef t1;
  tTuplef t2;
  tTuplef t3;
} tTriangle;

typedef struct {
  float u;
  tTuplef normal;
  tTuplef I;
  bool lit;
  int type; //-1 = light, 1 = triangle, 0 = NaN
  int ind;
} tIntersect;

typedef struct {
  tTriangle triangle_arr[1024];
  tTriangle *triangle_data; //by reference
  int len;
} asgn5_data;

typedef struct {
  tTuplef light_arr[1024];
  tTuplef *light_data;
  int len;
} light_data; 


    //\/\/\/\/\/\/\/\/\/
    //Vector Operations 
    //\/\/\/\/\/\/\/\/\/

#define CHECK_SIGNS(R, X, Y) ({                    \
    R = !((X >= 0) ^ (Y < 0));                     \
    })                                             \

//sphere          //tTuplef's
#define VECTOR_TEST_RADIUS(R, P1, P2, P3, RAD) ({  \
    float _u;                                      \
    VECTOR_U_SPHERE(_u, P1, P2, P3);               \
                                                   \
    tTuplef _p;                                    \
    VECTOR_DIST_SPHERE(_p, P1, P2, _u);            \
                                                   \
    tTuplef _sub;                                  \
    VECTOR_SUBTRACT(_sub, _p, P3);                 \
                                                   \
    float _sub_mag;                                \
    VECTOR_MAG(_sub_mag, _sub);                    \
                                                   \
    R = _sub_mag <= RAD;                           \
    })

// P1 = EYE P2 = SCREEN P3 = CENTER
#define VECTOR_DIST_SPHERE(R, P1, P2, U) ({   \
    tTuplef sr1;                              \
    VECTOR_SUBTRACT(sr1, P2, P1);             \
                                              \
    tTuplef mr1;                              \
    VECTOR_MULTIPLY_S(mr1, sr1, U);           \
    VECTOR_ADD(R, P1, mr1);                   \
    });

#define VECTOR_U_SPHERE(R, P1, P2, P3) ({  \
    float top_result =                        \
      ((P3.x - P1.x)*(P2.x-P1.x)) +           \
      ((P3.y-P1.y)*(P2.y-P1.y))   +           \
      ((P3.z-P1.z)*(P2.z-P1.z));              \
                                              \
    tTuplef bot_v_sub;                        \
    VECTOR_SUBTRACT(bot_v_sub, P2, P1);       \
                                              \
    float bot_result;                         \
    VECTOR_MAG(bot_result, bot_v_sub);        \
    bot_result = bot_result*bot_result;       \
                                              \
    R = top_result / bot_result;              \
    })

#define VECTOR_MAG(R, VEC) ({                           \
    R = sqrt(VEC.x*VEC.x + VEC.y*VEC.y + VEC.z*VEC.z);  \
    })                                                  \


//calculate all Ds
#define VECTOR_DS(D1, D2, D3, C1, C2, C3) ({      \
  VECTOR_DOT(D1, C1, C2);                         \
  VECTOR_DOT(D2, C2, C3);                         \
  VECTOR_DOT(D3, C3, C1);                         \
})                                                \

//calculate all Cs
#define VECTOR_CS(C1, C2, C3, V1, V2, V3) ({      \
  VECTOR_CROSS(C1, V1, V2);                       \
  VECTOR_CROSS(C2, V2, V3);                       \
  VECTOR_CROSS(C3, V3, V1);                       \
})                                                \

//calculate all Vs
#define VECTOR_VS(V1, V2, V3, T1, T2, T3, I) ({   \
  /* subtract I from T */                         \
  VECTOR_SUBTRACT(V1, T1, I);                     \
  VECTOR_SUBTRACT(V2, T2, I);                     \
  VECTOR_SUBTRACT(V3, T3, I);                     \
})                                                \

//stores results in r (result), a tTuplef
// I = P1 + U(P2 - P1)
// I = P1 + U(arg);
// return P1 + arg2;
#define VECTOR_INTERSECT(R, P1, P2, U) ({ \
  tTuplef arg1, arg2;                     \
  VECTOR_SUBTRACT(arg1, P2, P1);          \
  VECTOR_MULTIPLY_S(arg2, arg1, U);       \
  VECTOR_ADD(R, P1, arg2);                \
})                                        \

//calculate U
//accepts [P] (float), [N, T1, P1, P2] (tuples)
//stores result in P
#define VECTOR_U(P, N, T, P1, P2) ({      \
  float arg1=0, arg2=0;                   \
  tTuplef TminusP1 = {0,0,0},             \
          P2minusP1= {0,0,0};             \
  VECTOR_SUBTRACT(TminusP1, T, P1);       \
  VECTOR_SUBTRACT(P2minusP1, P2, P1);     \
  VECTOR_DOT(arg1, N, TminusP1);          \
  VECTOR_DOT(arg2, N, P2minusP1);         \
  P = arg1 / arg2;                        \
})                                        \

//calculate Normal
#define VECTOR_NORMAL(R, T1, T2, T3) ({     \
  tTuplef arg1={0,0,0}, arg2={0,0,0};       \
  VECTOR_SUBTRACT(arg1, T2, T1);            \
  VECTOR_SUBTRACT(arg2, T3, T1);            \
  VECTOR_CROSS(R, arg1, arg2);              \
})                                          \

//cross V1 with V2
#define VECTOR_CROSS(R, V1, V2) ({          \
  R.x = (V1.y * V2.z) - (V1.z * V2.y);      \
  R.y = (V1.z * V2.x) - (V1.x * V2.z);      \
  R.z = (V1.x * V2.y) - (V1.y * V2.x);      \
})                                          \

//perform a dot operation, V1 dot V2
//puts result in p, a float
#define VECTOR_DOT(P, V1, V2) ({            \
  P = (V1.x * V2.x) + (V1.y * V2.y) + (V1.z * V2.z);  \
})                                          \

// Multiply vector V1 by scalar S, and store
// in tTuplef R (result)
#define VECTOR_MULTIPLY_S(R, V1, s) ({      \
  R.x = V1.x * s;                           \
  R.y = V1.y * s;                           \
  R.z = V1.z * s;                           \
})                                          \

//add Vector tTuplef V1 to Vector tTuplef V2
#define VECTOR_ADD(R, V1, V2) ({            \
  R.x = V1.x + V2.x;                        \
  R.y = V1.y + V2.y;                        \
  R.z = V1.z + V2.z;                        \
})                                          \

//subtract V2 from V1, and store in vector R
#define VECTOR_SUBTRACT(R, V1, V2) ({       \
  R.x = V1.x - V2.x;                        \
  R.y = V1.y - V2.y;                        \
  R.z = V1.z - V2.z;                        \
})                                          \

//P1 = EYE
//P2 = SCREEN
//P3 = CENTER OF SPHERE

#endif /* FUNCTIONS_H */
