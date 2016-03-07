#! /bin/bash

cc -m32 GCDRM.c -S
cc -m32 GCDRF.c -S
cc -m32 GCDRM.c GCDRF.c DumpS32.s -o GCDRM
