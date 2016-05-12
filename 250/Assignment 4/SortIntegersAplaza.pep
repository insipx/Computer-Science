;SortIntegers.pep - Read,Print and Sort integers
; This is a solution to Assignment 4 in CMPS 250 for Spring 2016
; I, Andrew Plaza, am the sole developer of this program and will be submitting it
; I worked alone on this assignment
; No known flaws or deficiencies in terms of sorting ints, but the code 
; itself can probably be formatted/devised better for better modularity

; P.M.J., April 2016
;---------------------------------------------------------------
         BR      main
;---------------------------------------------------------------
capacity:.WORD   64              ;the capacity of the array
N:       .WORD   0               ;count of the number of integers
A:       .BLOCK  64              ;the array of integers
B:       .WORD   9999 
;--------
ZERO:    .WORD   0               ;the input sentinel value
NL:      .BYTE   '\n'            ;the "new line" character
;---------------------------------------------------------------
main:    NOP0
         LDA     capacity,d  ;+ N = readInts(A,capacity);
         STA     -2,s        ;|
         LDA     A,i         ;| 
         STA     -4,s        ;|
         SUBSP   4,i         ;|
         CALL    readInts    ;|
         ADDSP   4,i         ;|
         STA     N,d         ;+
;--------
         LDA     N,d         ;| sortInts(A,N) (array, and length)
         STA     -2,s        ;| STORE N -2 displacement from Stack
         LDA     A,i         ;|
         STA     -4,s        ;|Store A -4 displacement from stack
         SUBSP   4,i         ;|
         CALL    sortInts    ;|
         ADDSP   4,i         ;|
;--------
         LDA     N,d         ;+ prntInts(A,N);
         STA     -2,s        ;|
         LDA     A,i         ;|
         STA     -4,s        ;|
         SUBSP   4,i         ;|
         CALL    prntInts    ;|
         ADDSP   4,i         ;+
;--------
         STOP
;-----------------------------------------------------------
; int readInts(int array[], int capacity);
;-----------------------------------------------------------
ia:     .EQUATE 2
ic:     .EQUATE 4 
;-----------------------------------------------------------
ivalue:    .BLOCK  2              ;local static variable
readInts:  NOP0
           LDX     0,i            ; X = 0;
iloop:     CPX     ic,s           ;+ while(X < capacity) {
           BRGE    idone          ;|  
           DECI    ivalue,d       ;|   ivalue = nextInt();
           LDA     ivalue,d       ;| + if(ivalue == ZERO) {
           CPA     ZERO,d         ;| |   EXIT while
           BREQ    idone          ;| + } else {
           ASLX                   ;| | + array[X] = ivalue;
           STA     ia,sxf         ;| | |
           ASRX                   ;| | +
           ADDX    1,i            ;| | + X = X + 1;
                                  ;| + }
           BR      iloop          ;+ }
idone:     STX     ivalue,d       ;+ A = X;
           LDA     ivalue,d       ;+
           RET0
;-----------------------------------------------------------
; int prntInts(int array[], int n);
;-----------------------------------------------------------
pa:        .EQUATE  2
pn:        .EQUATE  4
pvalue:    .BLOCK   2
prntInts:  NOP0
           LDX     0,i
ploop:     CPX     pn,s
           BRGE    pdone
           ASLX
           LDA     pa,sxf
           STA     pvalue,d
           DECO    pvalue,d
           CHARO   '\n',i
           ASRX
           ADDX    1,i
           BR      ploop
pdone:     RET0

;-----------------------------------------------------------
; int sortInts(int array[], int n);
;-----------------------------------------------------------
; roughly equivalent psuedo-C program (not correct syntax, made for reference)
;for ( count= 0; count < cloop; c++)
;         int dloop = n-c-1
;        for(dcount = 0; dcount < dloop; dcount++)
;                if(array[d] > array[d+1];
;                            swap = array[d];
;                            array[dcount] = array[dcount+1];
;                            array[dcount+1] = swap;
;
;
;-----------------------------------------------------------
; LOCAL VARIABLES
;-----------------------------------------------------------
sa:      .EQUATE  2
sn:      .EQUATE  4

cloop:   .WORD    0     ; the n-1 in for(count = 0 count < sn - 1; count++)
dloop:   .WORD    0     ; the n-c -1
count:   .WORD    0     ; to keep count of outer loop
dcount:  .WORD    0     ; keep count of inner loop

dval:    .BLOCK   2     ; tmp for Comparing dval and swapping

;-----------------------------------------------------------

sortInts:NOP0
         LDX   0,i           ;load 0 into X reg
         LDA   sn,s          ; load N into A reg
         SUBA  1,i           ;subtract 1 from A reg
         STA   cloop,d       ; store N-1 in A reg

cnloop:  NOP0
         LDA   0,i
         STA   dcount,d      ;set dcount back to 0 for new loop
         LDA   count,d       ;load count into A for outer loop
         CPA   cloop,d       ;Compare A with cloop
         BRGE  sdone         ;while X < cloop
         LDA   sn,s          ;load n into A
         SUBA  count,d       ;==  n-c-1
         SUBA  1,i           ;==  n-c-1
         STA   dloop,d       ;==  n-c-1
         LDX   0,i

dnloop:  NOP0
         LDA   dcount,d     ;load dcount into A reg for inner loop 
         CPA   dloop,d      ; Compare A to dcount
         BRGE  incCnt       ; Basically if(A(dcount) >= dloop (dcount = 0, A=  n-c-1
         LDA   sa,sxf
         STA   dval,d
         ADDX  2,i
         LDA   sa,sxf
         CPA   dval,d
         BRLE  swap         ;swap(d, d+1)
out:     NOP0            
         LDA   dcount,d     ;Increment dcount
         ADDA  1,i
         STA   dcount,d 
         BR    dnloop

incCnt:  NOP0               ;Increment outer loop
         LDA   count,d
         ADDA  1,i
         STA   count,d
         BR    cnloop
         
swap:    NOP0 
         LDA   sa,sxf       ;load arr[d+1] in A
         SUBX  2,i          ;get back to arr[d]
         STA   sa,sxf       ;store arr[d+1] in d position
         ADDX  2,i          ;get to array[d+1]
         LDA   dval,d       ;Load array[d] into A
         STA   sa,sxf       ;store array[d] in d+1 position
         BR    out
         
sdone:  RET0
        .END