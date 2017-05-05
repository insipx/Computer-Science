#define BUILD_SQUAREZ(center, offset, ptr) ({                    \
    BUILD_TRIANGLEZ(                                              \
        ((struct tuple2d) {.x=(center.x-offset), .y=(center.y+offset)}),   \
        ((struct tuple2d) {.x=center.x+offset, .y=center.y-offset}),   \
        ((struct tuple2d) {.x=center.x-offset, .y=center.y-offset}),   \
        center.z-offset,ptr);                                         \
                                                                  \
    BUILD_TRIANGLEZ(                                              \
        ((struct tuple2d) {.x=center.x-offset, .y=center.y+offset}),   \
        ((struct tuple2d) {.x=center.x+offset, .y=center.y-offset}),   \
        ((struct tuple2d) {.x=center.x-offset, .y=center.y-offset}),   \
        center.z+offset,ptr+1);                                         \
    })

#define BUILD_SQUAREY(center, offset, ptr) ({                  \
    BUILD_TRIANGLEY(                                              \
        ((struct tuple2d) {.x=center.x-offset, .z=center.z+offset}),   \
        ((struct tuple2d) {.x=center.x+offset, .z=center.z-offset}),   \
        ((struct tuple2d) {.x=center.x-offset, .z=center.z-offset}),   \
        center.y-offset, ptr);                                         \
                                                                  \
    BUILD_TRIANGLEY(                                              \
        ((struct tuple2d) {.x=center.x-offset, .z=center.z+offset}),   \
        ((struct tuple2d) {.x=center.x+offset, .z=center.z-offset}),   \
        ((struct tuple2d) {.x=center.x-offset, .z=center.z-offset}),   \
        center.y+offset, ptr+1);                                         \
    })

#define BUILD_SQUAREX(center, offset, ptr) ({                     \
     BUILD_TRIANGLEX(                                             \
        ((struct tuple2d) {.y=center.y-offset, .z=center.z+offset}),       \
        ((struct tuple2d) {.y=center.y+offset, .z=center.z-offset}),       \
        ((struct tuple2d) {.y=center.y-offset, .z=center.z-offset}),       \
        center.x-offset, ptr);                                         \
                                                                  \
    BUILD_TRIANGLEX(                                              \
        ((struct tuple2d) {.y=center.y-offset, .z=center.z+offset}),       \
        ((struct tuple2d) {.y=center.y+offset, .z=center.z-offset}),       \
        ((struct tuple2d) {.y=center.y-offset, .z=center.z-offset}),       \
        center.x+offset, ptr+1);                                         \
    })

