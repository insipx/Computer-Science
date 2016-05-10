;PMJ's MPP ver2012.0 ...
;Name:  Andrew Plaza
;CMPS 250 Spring 2016
;The following is a solution to Assignment 5
;I worked a bit with Sean Batzel, mostly worked alone.
;
; P.M.J., April 2016
;--------Includes-----------------------------------------------
;
;{ HeapInit.pep1 <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
;===================================================================================
; H E A P   I N I T I A L I Z A T I O N
;===================================================================================
           LDA       Heap,d                                         ;+ Hhead = @Heap
           LDA       1,d                                            ;|
           STA       Hhead,d                                        ;+
           ADDA      4,i                                            ;+ Heap->flink = Hsecond
           STA       Hhead,n                                        ;+
;===================================================================================
;} HeapInit.pep1 >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
           BR        main
;
;{ PEP2.pep1 <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
;-------------------------------------------------------------------
; Global "low memory" locations used for temporary static storage
;.GLOBAL    TEMP
TEMP:      .BLOCK    4
;.GLOBAL    JUNK
JUNK:      .BLOCK    2
;.GLOBAL    SAVED
SAVED:     .BLOCK    2
;.GLOBAL    SAVEA
SAVEA:     .BLOCK    2                                              ;where A is saved statically
;.GLOBAL    SAVEX
SAVEX:     .BLOCK    2                                              ;where X is saved statically
;.GLOBAL    SAVEPP
SAVEPP:    .BLOCK    2
;============================================================
;============================================================
;============================================================
;-------------------------------------------------------------CC Corruption
; Macro to dump the top portion of the stack
;============================================================
;} PEP2.pep1 >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
;------------local vars----------------------------------------
name:      .BLOCK    2
name2:     .BLOCK    2
           .BYTE     16
comp:      .ASCII    ""
aComp:     .ADDRSS   comp
;------------main----------------------------
main:      NOP0
           CALL      buildLst
;;;;;;;;;; PUSHA                                                    ;
           STA       -2,s                                           ;< PUSHA >
           SUBSP     2,i                                            ;< PUSHA >
           CALL      prntStgs
           ADDSP     2,i
;--------------------------
done:      NOP0
           STOP
           BR        STOPEND
;<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
;
;{ STRInput.pep1 <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
;.GLOBAL  STRInput
STRInput:  NOP0
Sobject:   .EQUATE   6
capacity:  .EQUATE   0
ignored:   .EQUATE   2
           SUBSP     4,i
           LDA       0,i
           STA       ignored,s
           LDX       -1,i
           LDA       0,i
           LDBYTEA   Sobject,sxf
           STA       capacity,s
           LDX       0,i
           CPX       capacity,s
           BRGE      error
           LDA       0,i
loop:      CPX       capacity,s
           BREQ      full
           CHARI     Sobject,sxf
           LDBYTEA   Sobject,sxf
           CPA       '\n',i
           BREQ      LL0
           ADDX      1,i
           BR        loop
full:      LDA       ignored,s
           ADDA      1,i
           STA       ignored,s
           SUBX      1,i
ignore:    CHARI     Sobject,sxf
           LDA       0,i
           LDBYTEA   Sobject,sxf
           CPA       '\n',i
           BREQ      LL0
           LDA       ignored,s
           ADDA      1,i
           STA       ignored,s
           BR        ignore
LL0:       LDBYTEA   0,i
           STBYTEA   Sobject,sxf
           LDA       ignored,s
           BR        return
error:     LDA       -1,i
return:    RET4
;} STRInput.pep1 >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
;
;{ BINOutpt.pep1 <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
;.GLOBAL  BINOutpt
m:         .EQUATE   4
BINOutpt:  NOP0
           SUBSP     0,i
           STA       SAVED,d
           LDA       0,i
           STA       -2,s
           SUBSP     2,i
           LDA       SAVED,d
           STA       SAVEA,d
           STX       SAVEX,d
           LDA       m,s
           LDX       16,i
BINOloop:  NOP0
           CPX       0,i
           BREQ      BINOdone
           ROLA
           BRC       BINO1
BINO0:     CHARO     '0',i
           BR        BINOnext
BINO1:     CHARO     '1',i
BINOnext:  NOP0
           SUBX      1,i
           BR        BINOloop
BINOdone:  NOP0
           LDX       SAVEX,d
           LDA       SAVEA,d
           ADDSP     0,s
           ADDSP     2,i
           RET0
;} BINOutpt.pep1 >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
;
;{ HEXOutpt.pep1 <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
;.GLOBAL  HEXOutpt
Hm:        .EQUATE   4
HEXtext:   .block    4
           .word     0
HEXOutpt:  NOP0
           SUBSP     0,i
           STA       SAVED,d
           LDA       0,i
           STA       -2,s
           SUBSP     2,i
           LDA       SAVED,d
           STA       SAVEA,d
           STX       SAVEX,d
           LDA       Hm,s
           LDX       3,i
HEXOloop:  NOP0
           CPX       0,i
           BRLT      HEXOdone
           ANDA      0x000F,i
           CPA       10,i
           BRLT      HEX1
           ADDA      0x0007,i
HEX1:      ADDA      0x0030,i
           STBYTEA   HEXtext,x
           LDA       Hm,s
           RORA
           RORA
           RORA
           RORA
           STA       Hm,s
           SUBX      1,i
           BR        HEXOloop
HEXOdone:  STRO      HEXtext,d
           LDX       SAVEX,d
           LDA       SAVEA,d
           ADDSP     0,s
           ADDSP     2,i
           RET0
;} HEXOutpt.pep1 >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
;
;{ DUMPS.pep1 <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
;.GLOBAL  DumpS
Acopy:     .BLOCK    2
Xcopy:     .BLOCK    2
display:   .BLOCK    2
actual:    .BLOCK    2
dmsg:      .ASCII    "------------------------------- DUMPS\n\x00"
cmsg:      .ASCII    ",\x00"
amsg:      .ASCII    "(A=\x00"
xmsg:      .ASCII    "(X=\x00"
dlmsg:     .ASCII    ": \x00"
DumpSN:    .EQUATE   2
DumpS:     NOP0
           STA       Acopy,d
           STX       Xcopy,d
           STRO      dmsg,d
           STA       actual,d
           STRO      amsg,d
           STA       SAVEPP,d
           LDA       actual,d
           STA       -2,s
           SUBSP     2,i
           LDA       SAVEPP,d
           CALL      HEXOutpt
           ADDSP     2,i
           STRO      cmsg,d
           DECO      actual,d
           CHARO     ')',i
           STRO      cmsg,d
           STX       actual,d
           STRO      xmsg,d
           STA       SAVEPP,d
           LDA       actual,d
           STA       -2,s
           SUBSP     2,i
           LDA       SAVEPP,d
           CALL      HEXOutpt
           ADDSP     2,i
           STRO      cmsg,d
           DECO      actual,d
           CHARO     ')',i
           CHARO     '\n',i
           LDX       DumpSN,s
           MOVSPA
           ADDA      4,i
           STA       actual,d
DumpLoop:  CPX       0,i
           BRLE      DumpDone
           STA       SAVEPP,d
           LDA       actual,d
           STA       -2,s
           SUBSP     2,i
           LDA       SAVEPP,d
           CALL      HEXOutpt
           ADDSP     2,i
           STRO      dlmsg,d
           LDA       actual,n
           STA       display,d
           STA       SAVEPP,d
           LDA       display,d
           STA       -2,s
           SUBSP     2,i
           LDA       SAVEPP,d
           CALL      HEXOutpt
           ADDSP     2,i
           STRO      cmsg,d
           DECO      display,d
           CHARO     '\n',i
           LDA       actual,d
           ADDA      2,i
           STA       actual,d
           SUBX      2,i
           BR        DumpLoop
DumpDone:  LDX       Xcopy,d
           LDA       Acopy,d
           RET0
;} DUMPS.pep1 >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
;
;{ readSO.pep2 <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
;---------------------------------------------------------------
;  address readSO()
;---------------------------------------------------------------
           .BYTE     63
value:     .BLOCK    63
;--------
length:    .BLOCK    2
ref:       .BLOCK    2
;---------------------------------------------------------------
msgtrunc:  .ASCII    "WARNING: input truncation \x00"
;---------------------------------------------------------------
;.GLOBAL   readSO
readSO:    NOP0
;;;;;;;;;; CLR       ref,d                                          ;
           STA       SAVEA,d                                        ;< CLR >
;;;;;;;;;; CLRA                                                     ;< CLR >
           LDA       0,i                                            ;< CLRA,CLR >
           STA       ref,d                                          ;< CLR >
           LDA       SAVEA,d                                        ;< CLR >
;;;;;;;;;; STRI      value,i                                        ;|   value = nextLine(); ;
;;;;;;;;;; PUSH      value,i                                        ;< STRI >
           STA       SAVEPP,d                                       ;< PUSH,STRI >
           LDA       value,i                                        ;< PUSH,STRI >
;;;;;;;;;; PUSHA                                                    ;< PUSH,STRI >
           STA       -2,s                                           ;< PUSHA,PUSH,STRI >
           SUBSP     2,i                                            ;< PUSHA,PUSH,STRI >
           LDA       SAVEPP,d                                       ;< PUSH,STRI >
           CALL      STRInput                                       ;< STRI >
           ADDSP     2,i                                            ;< STRI >
;;;;;;;;;; TSTA                                                     ;| + if(A > 0) { ;
           CPA       0,i                                            ;< TSTA >
           BREQ      move                                           ;| |
           STRO      msgtrunc,d                                     ;| |   print(msgtrunc);
;;;;;;;;;; DECOA                                                    ;| |   print(A); ;
           STA       TEMP,d                                         ;< DECOA >
           DECO      TEMP,d                                         ;< DECOA >
           CHARO     '\n',i                                         ;| |   println();
           NOP0                                                     ;| + }
;move:;;;; PUSH      value,i                                        ;| + length = Slength(value); ;
move:      NOP0                                                     ;< PUSH >
           STA       SAVEPP,d                                       ;< PUSH >
           LDA       value,i                                        ;< PUSH >
;;;;;;;;;; PUSHA                                                    ;< PUSH >
           STA       -2,s                                           ;< PUSHA,PUSH >
           SUBSP     2,i                                            ;< PUSHA,PUSH >
           LDA       SAVEPP,d                                       ;< PUSH >
           CALL      Slength                                        ;| |
           ADDSP     2,i                                            ;| |
           STA       length,d                                       ;| +
;;;;;;;;;; TSTA                                                     ;| + if(length != 0) { ;
           CPA       0,i                                            ;< TSTA >
           BREQ      LL1                                            ;| |
;---------                            ;| |
           LDA       length,d                                       ;| | + ref = new(length+2);
           ADDA      2,i                                            ;| | |
           STA       -2,s                                           ;| | |
           SUBSP     2,i                                            ;| | |
           CALL      new                                            ;| | |
           ADDSP     2,i                                            ;| | |
           STA       ref,d                                          ;| | +
;---------                            ;| |
           LDA       length,d                                       ;| | + *ref = (length++);
;;;;;;;;;; INCA                                                     ;| | | ;
           ADDA      1,i                                            ;< INCA >
           STA       length,d                                       ;| | |
           STBYTEA   ref,n                                          ;| | +
;;;;;;;;;; INC       ref,d                                          ;| |   ref = ref + 1; ;
;;;;;;;;;; SAVEA                                                    ;< INC >
           STA       SAVEA,d                                        ;< SAVEA,INC >
           LDA       ref,d                                          ;< INC >
           ADDA      1,i                                            ;< INC >
           STA       ref,d                                          ;< INC >
;;;;;;;;;; RESTOREA                                                 ;< INC >
           LDA       SAVEA,d                                        ;< RESTOREA,INC >
;---------
;Took out code here from readStrgs
;---------                            ;| |
           LDA       length,d                                       ;| | + memcpy(&value,ref,length);
           STA       -2,s                                           ;| | |
           LDA       ref,d                                          ;| | |
           STA       -4,s                                           ;| | |
           LDA       value,i                                        ;| | |
           STA       -6,s                                           ;| | |
           SUBSP     6,i                                            ;| | |
           CALL      memcpy                                         ;| | |
           ADDSP     6,i                                            ;| | +
;---------                            ;| |
;---------
LL1:       LDA       ref,d
           RET0
;} readSO.pep2 >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
;
;{ prntStgs.pep2 <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
;Andrew Plaza
;CMPS 250 Spring 2016
;The following is a solution to Assignment 5
;I worked alone
;No flaws of which I am aware
;---------------------------------------------------------------
;  int prntStgs(address p[], int n)
;---------------------------------------------------------------
p:         .EQUATE   2
n:         .EQUATE   4
;--------
count:     .BLOCK    2
ptemp:     .BLOCK    2
;---------------------------------------------------------------
;.GLOBAL   prntStgs
prntStgs:  NOP0
;;;;;;;;;; SAVEA                                                    ;save A and X and clear them for a clean slate ;
           STA       SAVEA,d                                        ;< SAVEA >
;;;;;;;;;; SAVEX                                                    ;
           STX       SAVEX,d                                        ;< SAVEX >
;;;;;;;;;; CLRA                                                     ;
           LDA       0,i                                            ;< CLRA >
           STA       count,d                                        ;count is set to 0 at first
;;;;;;;;;; CLRX                                                     ;
           LDX       0,i                                            ;< CLRX >
;------------------------------------------
LL2:       CPA       n,s                                            ;compare the count (loaded into A) with n(amount of str we have)
           BRGE      LL3                                            ;if it's Greater than or Equal to, we are LL3
           LDA       p,sxf                                          ;load the next addr of the p[] into A
           STA       ptemp,d                                        ;store a in ptempt
           STRO      ptemp,n                                        ;print the String using indirect addr mode (n)
           CHARO     '\n',i
           ADDX      2,i                                            ;add 2 to X to get to next addr
           LDA       count,d                                        ;load count into A, increment it, and LL2 back until all str are printed
;;;;;;;;;; INCA                                                     ;
           ADDA      1,i                                            ;< INCA >
           STA       count,d
           BR        LL2
;--------
;;;;;;;;;; RESTOREA                                                 ;
           LDA       SAVEA,d                                        ;< RESTOREA >
;;;;;;;;;; RESTOREX                                                 ;
           LDX       SAVEX,d                                        ;< RESTOREX >
LL3:       RET0
;} prntStgs.pep2 >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
;
;{ memcpy.pep2 <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
;-----------------------------------------------------------
; void memcpy(byte *from, byte *to, int n);
;-----------------------------------------------------------
from:      .EQUATE   2
to:        .EQUATE   4
LL4:       .EQUATE   6
;-----------------------------------------------------------
;-----------------------------------------------------------
;.GLOBAL  memcpy
;memcpy:;; SAVE                                                     ;
memcpy:    NOP0                                                     ;< SAVE >
;;;;;;;;;; SAVEA                                                    ;< SAVE >
           STA       SAVEA,d                                        ;< SAVEA,SAVE >
;;;;;;;;;; SAVEX                                                    ;< SAVE >
           STX       SAVEX,d                                        ;< SAVEX,SAVE >
;;;;;;;;;; CLRA                                                     ;
           LDA       0,i                                            ;< CLRA >
;;;;;;;;;; CLRX                                                     ;
           LDX       0,i                                            ;< CLRX >
LL5:       CPX       LL4,s
           BRGE      LL6
           LDBYTEA   from,sxf
           STBYTEA   to,sxf
;;;;;;;;;; INCX                                                     ;
           ADDX      1,i                                            ;< INCX >
           BR        LL5
;done:;;;; RESTORE                                                  ;
LL6:       NOP0                                                     ;< RESTORE >
;;;;;;;;;; RESTOREX                                                 ;< RESTORE >
           LDX       SAVEX,d                                        ;< RESTOREX,RESTORE >
;;;;;;;;;; RESTOREA                                                 ;< RESTORE >
           LDA       SAVEA,d                                        ;< RESTOREA,RESTORE >
           RET0
;} memcpy.pep2 >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
;
;{ Slength.pep2 <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
;************************************************************************
; String length subprogram that returns the count of the number of
; characters in the string.
;
;********  int Slength (char[] Sobject)
;
;-- Arguments -----------------------------------------------------------
Sobjectl:  .EQUATE   2                                              ;formal parameter; address of Sobject
;-- Local Data ----------------------------------------------------------
;-- Entry Point ---------------------------------------------------------
Slength:   NOP0
;;;;;;;;;; SAVEX                                                    ;
           STX       SAVEX,d                                        ;< SAVEX >
;;;;;;;;;; CLRA                                                     ;  A = 0; ;
           LDA       0,i                                            ;< CLRA >
;;;;;;;;;; CLRX                                                     ;  X = 0; ;
           LDX       0,i                                            ;< CLRX >
loopl:     LDBYTEA   Sobjectl,sxf                                   ;+ while(Sobjectl[X] != 0x00) {
           CPA       0,i                                            ;|
           BREQ      donel                                          ;|
           ADDX      1,i                                            ;|   X = x + 1;
           BR        loopl                                          ;+ }
donel:     STX       -2,s                                           ;+ A = X;
;;;;;;;;;; RESTOREX                                                 ;
           LDX       SAVEX,d                                        ;< RESTOREX >
           LDA       -2,s                                           ;+
           RET0                                                     ;
;} Slength.pep2 >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
;
;{ ScompTo.pep2 <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
;Name:  Andrew Plaza
;CMPS 250 Spring 2016
;The following is a solution to Assignment 5
;I worked with a bit with Sean Batzel, mostly alone
;
;************************************************************************
; String subprogram that compares two strings (Sobjects) and
; returns a number less than zero if the first object is lexiographicall
; before (i.e. less than) the second, zero if they are equivalent, and a
; positive number otherwise.
;
;********  int ScompTo (char[] Sobject1, char[] Sobject2)
;
;-- Arguments -----------------------------------------------------------
Sobject1:  .EQUATE   2                                              ;formal parameter; address of Sobject
Sobject2:  .EQUATE   4                                              ;formal parameter; address of Sobject
;-- Local Data ----------------------------------------------------------
hold2:     .BLOCK    2
;-- Entry Point ---------------------------------------------------------
;.GLOBAL   ScompTo
;ScompTo:; SAVEX                                                    ;
ScompTo:   NOP0                                                     ;< SAVEX >
           STX       SAVEX,d                                        ;< SAVEX >
;<<<<<<<<< Instrumentation
           STRO      Sobject1,sf
           CHARO     '-',i
           STRO      Sobject2,sf
           CHARO     '\n',d
;>>>>>>>>>
;;;;;;;;;; CLRA                                                     ;  A = 0; ;
           LDA       0,i                                            ;< CLRA >
;;;;;;;;;; CLRX                                                     ;
           LDX       0,i                                            ;< CLRX >
;---------
LL7:       NOP0
           LDBYTEA   Sobject2,sxf
           STA       hold2,d
           LDBYTEA   Sobject1,sxf
           CPA       hold2,d
           BRNE      done1
           CPA       0,i
           BREQ      equal
;;;;;;;;;; INCX                                                     ;
           ADDX      1,i                                            ;< INCX >
           BR        LL7
;---------
done1:     SUBA      hold2,d
           CPA       0,i
           BRGT      greater
           BRLT      less
greater:   NOP0
           LDA       1,i
           BR        LL8
less:      NOP0
           LDA       -1,i
           BR        LL8
equal:     NOP0
           LDA       0,i
           BR        LL8
;done:;;;; RESTOREX                                                 ;
LL8:       NOP0                                                     ;< RESTOREX >
           LDX       SAVEX,d                                        ;< RESTOREX >
;<<<<<<<<< Instrumentation
           CHARO     ',',i
;;;;;;;;;; DECOA                                                    ;
           STA       TEMP,d                                         ;< DECOA >
           DECO      TEMP,d                                         ;< DECOA >
           CHARO     '\n',i
;>>>>>>>>>
           RET0                                                     ;
;here's some code i was using to guide me
;int compareString(char *firstString, char *secondString){
;    if(firstString == NULL && secondString == NULL)
;        return 0;
;    int counter = 0;
;    while(firstString[counter] == secondString[counter]){
;        if(firstString[counter] == '\0' && secondString[counter] == '\0')
;            return 0;
;        counter++;
;    }
;    return firstString[counter] - secondString[counter];
;}
;
;
;
;
;
;
;
;} ScompTo.pep2 >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
;
;{ buildLst.pep2 <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
;-------------------------
;BuildLst()
;------------------------
;---Local Variables----
size:      .EQUATE   0                                              ; size
curr:      .BLOCK    2                                              ;front of the list (head, first element)
next:      .BLOCK    2                                              ;end of the list
currN:     .BLOCK    2
;----------------------
buildLst:  NOP0
;;;;;;;;;; SAVEA                                                    ;
           STA       SAVEA,d                                        ;< SAVEA >
;;;;;;;;;; SAVEX                                                    ;
           STX       SAVEX,d                                        ;< SAVEX >
;;;;;;;;;; CLRA                                                     ;
           LDA       0,i                                            ;< CLRA >
;;;;;;;;;; CLRX                                                     ;
           LDX       0,i                                            ;< CLRX >
LL9:       NOP0
           LDA       4,i
           STA       -2,s
           CALL      new
           ADDSP     2,i
           STA       curr,d
           CALL      readSO
           STA       currN,d
           LDX       0,i
           STX       p,x
           LDX       2,i
           LDA       next,d
           STA       curr,x
           STRO      currN,n
           CHARO     '\n',i
           LDBYTEA   currN,n
           CPA       0,i
           BREQ      LL10
           BR        LL9
;done:;;;; RESTOREA                                                 ;
LL10:      NOP0                                                     ;< RESTOREA >
           LDA       SAVEA,d                                        ;< RESTOREA >
;;;;;;;;;; RESTOREX                                                 ;
           LDX       SAVEX,d                                        ;< RESTOREX >
           RET0
;} buildLst.pep2 >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
;
;{ Heap.pep1 <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
;} Heap.pep1 >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
;
;{ Heap_new.pep1 <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
;===================================================================================
;.GLOBAL  new
;---------------------------------------------------------------------------------------
; address new(int length)
;---------------------------------------------------------------------------------------
result:    .EQUATE   0
LL11:      .EQUATE   4
;---------------------------------------------------------------------------------------
new:       SUBSP     2,i                                            ;
           LDA       LL11,s                                         ;+ if(length <=  255) {
           CPA       0,i                                            ;|
           BRLT      LL12                                           ;|
           CPA       255,i                                          ;|
           BRGT      LL12                                           ;|
           ADDA      1,i                                            ;| + A = malloc((length+1));
           SUBSP     2,i                                            ;| |
           STA       0,s                                            ;| |
           CALL      malloc                                         ;| |
           ADDSP     2,i                                            ;| +
           CPA       0,i                                            ;| + if(A != 0) {
           BRLE      LL12                                           ;| |
           STA       result,s                                       ;| | + set "before byte" to (length-1)
           SUBX      1,i                                            ;| | |
           STX       LL11,s                                         ;| | |
           LDA       LL11,s                                         ;| | |
           LDX       0,i                                            ;| | |
           STBYTEA   result,sxf                                     ;| | +
           LDA       result,s                                       ;| | }
           ADDA      1,i                                            ;| + A = A + 1;
           BR        LL13                                           ;+ }
LL12:      LDA       0,i                                            ;
LL13:      RET2                                                     ;
;} Heap_new.pep1 >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
;
;{ Heap_recycle.pep1 <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
;===================================================================================
;.GLOBAL  recycle
;---------------------------------------------------------------------------------------
; address recycle(address ref)
;---------------------------------------------------------------------------------------
LL14:      .EQUATE   2
;---------------------------------------------------------------------------------------
recycle:   NOP0
           LDX       -1,i                                           ;+ A = "before byte" value at LL14
           LDA       0,i                                            ;|
           LDBYTEA   LL14,sxf                                       ;+
           ADDA      1,i                                            ;+ free((ref-1),(A+1));
           STA       -2,s                                           ;|
           LDA       LL14,s                                         ;|
           SUBA      1,i                                            ;|
           STA       -4,s                                           ;|
           SUBSP     4,i                                            ;|
           CALL      free                                           ;|
           ADDSP     4,i                                            ;+
           RET0                                                     ;
;} Heap_recycle.pep1 >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
;
;{ Heap_malloc.pep1 <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
;===================================================================================
;.GLOBAL  malloc
;---------------------------------------------------------------------------------------
; address malloc(int need)
;---------------------------------------------------------------------------------------
need:      .EQUATE   8
;---------------------------------------------------------------------------------------
prev:      .EQUATE   0                                              ;pointer to previous free element
LL15:      .EQUATE   2                                              ;pointer to current free element
LL16:      .EQUATE   4                                              ;pointer to LL16 free element
;---------------------------------------------------------------------------------------
malloc:    SUBSP     6,i                                            ;  Room for 3 local variables
           LDA       need,s                                         ;+ if(need < 4) {
           CPA       4,i                                            ;|
           BRGE      mchkodd                                        ;|
           LDA       4,i                                            ;|   need = 4;
           STA       need,s                                         ;|
           BR        mstart                                         ;+ } else
;----------------------------
mchkodd:   RORA                                                     ;+ if((need%2) == 1) {
           BRC       madd1                                          ;|
           BR        mstart                                         ;|
madd1:     ROLA                                                     ;|
           ADDA      1,i                                            ;|   need = need + 1;
           STA       need,s                                         ;+ }
;----------------------------
mstart:    LDA       Hhead,d                                        ;+ LL15 = Hhead
           STA       LL15,s                                         ;+
;----------------------------
mloop:     CPA       0,i                                            ;+ while((curr != null) &&
           BREQ      mERROR                                         ;|       (curr->size < need) {
           ADDA      2,i                                            ;|
           STA       HEA,d                                          ;|
           LDA       HEA,n                                          ;|   // A = curr->size;
           CPA       need,s                                         ;|
           BRGE      mdo                                            ;|
           LDA       LL15,s                                         ;|   + prev = LL15;
           STA       prev,s                                         ;|   +
           LDA       LL15,sf                                        ;|   + LL15 = curr->flink;
           STA       LL15,s                                         ;|   +
           BR        mloop                                          ;+ }
;----------------------------
mdo:       SUBA      need,s                                         ;+ if((curr->size - need) < 4) {
           CPA       4,i                                            ;|
           BRGE      mok                                            ;|
           LDA       HEA,n                                          ;|  + need = curr->size;
           STA       need,s                                         ;|  +
           LDX       LL15,s                                         ;|  + prev->flink = curr->flink;
           STX       HEA,d                                          ;|  |
           LDA       HEA,n                                          ;|  |
           STA       prev,sf                                        ;|  +
           BR        mfill                                          ;| } else {
mok:       NOP0                                                     ;|  +
           LDA       LL15,s                                         ;|  + LL16 = LL15 + need;
           ADDA      need,s                                         ;|  |
           STA       LL16,s                                         ;|  +
;----------------------------;|
           STA       prev,sf                                        ;|    prev->flink = LL16
;----------------------------;|
           LDA       LL15,sf                                        ;|  + next->flink = curr->flink
           STA       LL16,sf                                        ;|  +
;----------------------------;|
           LDX       LL15,s                                         ;|  + X = (curr->size - need);
           ADDX      2,i                                            ;|  |
           STX       HEA,d                                          ;|  |
           LDX       HEA,n                                          ;|  |
           SUBX      need,s                                         ;|  +
;----------------------------;|
           LDA       LL16,s                                         ;|  + next->size = X
           ADDA      2,i                                            ;|  |
           STA       HEA,d                                          ;|  |
           STX       HEA,n                                          ;|  +
;----------------------------;+ }
mfill:     NOP0                                                     ;
;++++++++++++++++++++++++++++  TEMPORARY CODE TO FILL ALLOCATION
           LDA       mcode,d                                        ;+ hfill(curr,need,mcode);
           STA       -2,s                                           ;|
           LDA       need,s                                         ;|
           STA       -4,s                                           ;|
           LDA       LL15,s                                         ;|
           STA       -6,s                                           ;|
           SUBSP     6,i                                            ;|
           CALL      hfill                                          ;|
           ADDSP     6,i                                            ;+
           LDA       mcode,d                                        ;+ if(mcode != "ZZ") {
           CPA       0x5a5a,i                                       ;|
           BREQ      AtLimit                                        ;|
           ADDA      0x0101,i                                       ;|   mcode =  mcode + 0x0101; //next character
           STA       mcode,d                                        ;|
AtLimit:   NOP0                                                     ;+ }
;++++++++++++++++++++++++++++
           LDA       LL15,s                                         ;  A = LL15;
           LDX       need,s                                         ;  X = need;
           BR        mreturn                                        ;
;----------------------------
mERROR:    LDA       0,i                                            ;  A = null
;----------------------------
mreturn:   RET6                                                     ;  Remove 3 local variables
mcode:     .WORD     0x4141                                         ;  a code value
;-------------------------------------------------------------------
;} Heap_malloc.pep1 >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
;
;{ Heap_free.pep1 <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
;===================================================================================
;.GLOBAL  free
;---------------------------------------------------------------------------------------
; address free(address addr, int size)
;---------------------------------------------------------------------------------------
addr:      .EQUATE   6                                              ;an address obtainted from a call to malloc
LL17:      .EQUATE   8                                              ;the corresponding "need" used when allocating
;---------------------------------------------------------------------------------------
fprev:     .EQUATE   0                                              ;pointer to previous free element
fcurr:     .EQUATE   2                                              ;pointer to current free element
;---------------------------------------------------------------------------------------
free:      SUBSP     4,i                                            ;  Room for 2 local variables
;----------------------------;
           LDA       LL17,s                                         ;+ if((size >= 4) && ((size%2) == 0)) {
           CPA       4,i                                            ;|
           BRLT      ferror                                         ;|
           RORA                                                     ;|
           BRC       ferror                                         ;|
;----------------------------;|
fstart:    LDA       Hhead,d                                        ;| + if(Hhead < addr) {
           CPA       addr,s                                         ;| |
           BRGE      ferror                                         ;| |
           STA       fcurr,s                                        ;| |   fcurr = Hhead;
floop:     CPA       0,i                                            ;| | + while((fcurr != null) &&
           BREQ      ffound                                         ;| | |
           CPA       addr,s                                         ;| | |       (fcurr < addr) ) {
           BRGE      ffound                                         ;| | |
           LDA       fcurr,s                                        ;| | | + fprev = fcurr;
           STA       fprev,s                                        ;| | | +
           LDA       fcurr,sf                                       ;| | | + fcurr = fcurr->flink;
           STA       fcurr,s                                        ;| | | +
           BR        floop                                          ;| | |
ffound:    NOP0                                                     ;| | + }
           LDA       addr,s                                         ;| | + if((addr+size) <= fcurr) {
           ADDA      LL17,s                                         ;| | |
           CPA       fcurr,s                                        ;| | |
           BRGT      ferror                                         ;| | |
           LDA       fcurr,s                                        ;| | | + addr->flink = fcurr;
           STA       addr,sf                                        ;| | | +
           LDA       addr,s                                         ;| | | + fprev->flink = addr;
           STA       fprev,sf                                       ;| | | +
           ADDA      2,i                                            ;| | | + addr->size = LL17;
           STA       HEA,d                                          ;| | | |
           LDA       LL17,s                                         ;| | | |
           STA       HEA,n                                          ;| | | +
           NOP0                                                     ;| | + }
           NOP0                                                     ;| + }
;fdofill:  NOP0
;++++++++++++++++++++++++++++++++++++  TEMPORARY CODE TO FILL DEALLOCATION
           LDA       fcode,d                                        ;| + hfill((addr+4),(size-4),fcode);
           STA       -2,s                                           ;| |
           LDA       LL17,s                                         ;| |
           SUBA      4,i                                            ;| |   //(size-4),
           STA       -4,s                                           ;| |
           LDA       addr,s                                         ;| |
           ADDA      4,i                                            ;| |   //(addr+4)
           STA       -6,s                                           ;| |
           SUBSP     6,i                                            ;| |
           CALL      hfill                                          ;| |
           ADDSP     6,i                                            ;| +
;+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
           LDA       0,i                                            ;   A = 0;  //indicates success
           BR        freturn
ferror:    LDA       addr,s
freturn:   RET4
fcode:     .WORD     0x2a2a                                         ;  a code value
;===================================================================================
;} Heap_free.pep1 >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
;
;{ Heap_body.pep1 <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
;===================================================================================
;.GLOBAL  hfill
hfaddr:    .EQUATE   2
hfsize:    .EQUATE   4
hfcode:    .EQUATE   6
;===================================================================================
;  void hfill(address hfaddr, int hfsize, int hfcode)
;===================================================================================
hfill:     NOP0
           LDX       hfaddr,s
           ADDX      hfsize,s
hfloop:    NOP0
           SUBX      2,i
           STX       HEA,d
           LDA       hfcode,s
           STA       HEA,n
           CPX       hfaddr,s
           BRGT      hfloop
           RET0
;===================================================================================
;.GLOBAL  Heap
;.GLOBAL  Hhead
;.GLOBAL  HEA
;===================================================================================
; H E A P   D A T A
;===================================================================================
           .ASCII    "<<<<<<<<"                                     ;just a visible marker for the start of the heap
Hhead:     .WORD     0                                              ;head of the free list
Heap:      .WORD     0                                              ;flink to next element
           .WORD     0                                              ;empty size of the first element
Hsecond:   .WORD     0                                              ;flink to next element; null
           .WORD     1024                                           ;size of this second element
           .BLOCK    1024                                           ;the actual heap space
HEA:       .BLOCK    2                                              ;temporary static used by the Heap subprograms
Hmarker:   .ASCII    ">>>>>>>>"                                     ;just a visible marker for the end of the heap
;===================================================================================
;} Heap_body.pep1 >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
;>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
STOPEND:   STOP
           .END
;Resolver Report:
; done --> LL0
; done --> LL1
; loop --> LL2
; done --> LL3
; n --> LL4
; loop --> LL5
; done --> LL6
; loop --> LL7
; done --> LL8
; loop --> LL9
; done --> LL10
; length --> LL11
; error --> LL12
; return --> LL13
; ref --> LL14
; curr --> LL15
; next --> LL16
; size --> LL17
