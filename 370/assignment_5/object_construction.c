//Put shapes in data structure

#define BUILD_TRIANGLEX(v1, v2, v3, plane, ptr) ({                \
    *(ptr) = ((struct triangle){                                   \
          .t1={plane, v1.a, v1.b},                                \
          .t2={plane, v2.a, v2.b},                                \
          .t3={plane, v3.a, v3.b}                                 \
        });                                                       \
    })                                                            \

#define BUILD_TRIANGLEY(v1, v2, v3, plane, ptr) ({                \
    *(ptr) = ((struct triangle){                                   \
      .t1={v1.a, plane, v1.b},                                    \
      .t2={v2.a, plane, v2.b},                                    \
      .t3={v3.a, plane, v3.b}                                     \
    });                                                           \
    })                                                            \

#define BUILD_TRIANGLEZ(v1, v2, v3, plane, ptr) ({                \
    *(ptr) = (struct triangle) {                                  \
      .t1={v1.a, v1.b, plane},                                    \
      .t2={v2.a, v2.b, plane},                                    \
      .t3={v3.a, v2.b, plane}                                     \
        };                                                       \
    })

#define BUILD_SQUAREZ(center, offset, ptr) ({                    \
    BUILD_TRIANGLEZ(                                              \
        ((struct tuple2d) {.a=center.x-offset, .b=center.y+offset}),   \
        ((struct tuple2d) {.a=center.x+offset, .b=center.y-offset}),   \
        ((struct tuple2d) {.a=center.x-offset, .b=center.y-offset}),   \
        center.z-offset, ptr);                                         \
                                                                  \
    BUILD_TRIANGLEZ(                                              \
        ((struct tuple2d) {.a=center.x-offset, .b=center.y+offset}),   \
        ((struct tuple2d) {.a=center.x+offset, .b=center.y-offset}),   \
        ((struct tuple2d) {.a=center.x-offset, .b=center.y-offset}),   \
        center.z+offset,ptr+1);                                         \
    })

#define BUILD_SQUAREY(center, offset, ptr) ({                  \
    BUILD_TRIANGLEY(                                              \
        ((struct tuple2d) {.a=center.x-offset, .b=center.z+offset}),   \
        ((struct tuple2d) {.a=center.x+offset, .b=center.z-offset}),   \
        ((struct tuple2d) {.a=center.x-offset, .b=center.z-offset}),   \
        center.y-offset, ptr);                                         \
                                                                  \
    BUILD_TRIANGLEY(                                              \
        ((struct tuple2d) {.a=center.x-offset, .b=center.z+offset}),   \
        ((struct tuple2d) {.a=center.x+offset, .b=center.z-offset}),   \
        ((struct tuple2d) {.a=center.x-offset, .b=center.z-offset}),   \
        center.y+offset, ptr+1);                                         \
    })

#define BUILD_SQUAREX(center, offset, ptr) ({                     \
     BUILD_TRIANGLEX(                                             \
        ((struct tuple2d) {.a=center.y-offset, .b=center.z+offset}),       \
        ((struct tuple2d) {.a=center.y+offset, .b=center.z-offset}),       \
        ((struct tuple2d) {.a=center.y-offset, .b=center.z-offset}),       \
        center.x-offset, ptr);                                         \
                                                                  \
    BUILD_TRIANGLEX(                                              \
        ((struct tuple2d) {.a=center.y-offset, .b=center.z+offset}),       \
        ((struct tuple2d) {.a=center.y+offset, .b=center.z-offset}),       \
        ((struct tuple2d) {.a=center.y-offset, .b=center.z-offset}),       \
        center.x+offset, ptr+1);                                         \
    })
   

#define BUILD_CUBE(center, offset, ptr) ({                        \
    BUILD_SQUAREX(center,offset, ptr);                            \
    BUILD_SQUAREY(center,offset, ptr+2);                          \
    BUILD_SQUAREZ(center,offset, ptr+4);                            \
    })

