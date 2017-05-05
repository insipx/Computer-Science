
#ifndef FUNCTIONS_H
#define FUNCTIONS_H

typedef struct {
  float x;
  float y;
  float z;
} tTuplef; // type tuple float

typedef struct {
  tTuplef t1;
  tTuplef t2;
  tTuplef t3;
} tTriangle;

typedef struct {
  tTriangle triangle_data[1];
} Triangles;

typedef struct {
  float u;
  bool lit;
} tIntersect;

    //\/\/\/\/\/\/\/\/\/
    //Object Construction
    //\/\/\/\/\/\/\/\/\/

#define BUILD_TRIANGLEX(v1, v2, v3, plane) ({                     \
      {plane, v1.y, v1.z},                                        \
      {plane, v2.y, v2.z},                                        \
      {plane, v3.y, v3.z},                                        \
    })                                                            \

#define BUILD_TRIANGLEY(v1, v2, v3, plane) ({                     \
      {v1.x, plane, v1.z},                                        \
      {v2.x, plane, v2.z},                                        \
      {v3.x, plane, v3.z},                                        \
    })                                                            \

#define BUILD_TRIANGLEZ(v1, v2, v3, plane) ({                     \
      {v1.x, v1.y, plane},                                        \
      {v2.x, v2.y, plane},                                        \
      {v3.x, v2.y, plane},                                        \
    })

#define BUILD_SQUAREX(v1, v2, v3, v4, plane) ({                   \
    BUILD_TRIANGLEX(v1,v2,v4,plane);                              \
    BUILD_TRIANGLEX(v4,v3,v2,plane);                              \
    })

#define BUILD_SQUAREY(v1, v2, v3, v4, plane) ({                   \
    BUILD_TRIANGLEY(v1,v2,v4,plane);                              \
    BUILD_TRIANGLEY(v4,v3,v2,plane);                              \
    })

#define BUILD_SQUAREZ(v1, v2, v3, v4, plane) ({                   \
    BUILD_TRIANGLEZ(v1,v2,v4,plane);                              \
    BUILD_TRIANGLEZ(v4,v3,v2,plane);                              \
    })

#define BUILD_CUBE(center, offset) ({                             \
    BUILD_SQUAREZ(center.x-offset, center.y+offset, center.z);    \
    BUILD_SQUAREZ(center.x+offset, center.y-offset, center.z);    \
                                                                  \
    BUILD_SQUAREX(center.x, center.y+offset, center.z-offset);    \
    BUILD_SQUAREX(center.x, center.y-offset, center.z+offset);    \
                                                                  \
    BUILD_SQUAREY(center.x+offset, center.y, center.z-offset);    \
    BIULD_SQUAREY(center.x-offset, center.y, center.z-offset);    \
                                                                  \
    })


    //\/\/\/\/\/\/\/\/\/
    //Vector Operations 
    //\/\/\/\/\/\/\/\/\/

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

#endif /* FUNCTIONS_H */
