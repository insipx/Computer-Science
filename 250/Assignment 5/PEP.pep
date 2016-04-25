;PMJ's MPP ver2012.0 ...
;Name:  Andrew Plaza
;CMPS 250 Spring 2016
;The following is a solution to Assignment 5
;I worked a bit with Sean Batzel, mostly worked alone.
;
; P.M.J., April 2016
;--------Includes-----------------------------------------------
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
;------------local vars-----------------------------------------
           .BYTE     16                                             ;beforebyte
Name1:     .BLOCK    31                                             ;the name
aName1:    .ADDRSS   Name1                                          ;address
           .BYTE     16                                             ;same as above
Name2:     .BLOCK    31
aName2:    .ADDRSS   Name2
result:    .BLOCK    2
;;;;;;;;;; CLRA                                                     ;
           LDA       0,i                                            ;< CLRA >
;;;;;;;;;; CLRX                                                     ;
           LDX       0,i                                            ;< CLRX >
;------------------
main:      NOP0
;loop:;;;; STRI      aName1,d                                       ;
loop:      NOP0                                                     ;< STRI >
;;;;;;;;;; PUSH      aName1,d                                       ;< STRI >
           STA       SAVEPP,d                                       ;< PUSH,STRI >
           LDA       aName1,d                                       ;< PUSH,STRI >
;;;;;;;;;; PUSHA                                                    ;< PUSH,STRI >
           STA       -2,s                                           ;< PUSHA,PUSH,STRI >
           SUBSP     2,i                                            ;< PUSHA,PUSH,STRI >
           LDA       SAVEPP,d                                       ;< PUSH,STRI >
           CALL      STRInput                                       ;< STRI >
           ADDSP     2,i                                            ;< STRI >
;;;;;;;;;; STRI      aName2,d                                       ;
;;;;;;;;;; PUSH      aName2,d                                       ;< STRI >
           STA       SAVEPP,d                                       ;< PUSH,STRI >
           LDA       aName2,d                                       ;< PUSH,STRI >
;;;;;;;;;; PUSHA                                                    ;< PUSH,STRI >
           STA       -2,s                                           ;< PUSHA,PUSH,STRI >
           SUBSP     2,i                                            ;< PUSHA,PUSH,STRI >
           LDA       SAVEPP,d                                       ;< PUSH,STRI >
           CALL      STRInput                                       ;< STRI >
           ADDSP     2,i                                            ;< STRI >
           LDBYTEA   Name1,d
           CPA       0,i
           BREQ      done
           LDA       aName2,d
;;;;;;;;;; PUSHA                                                    ;
           STA       -2,s                                           ;< PUSHA >
           SUBSP     2,i                                            ;< PUSHA >
           LDA       aName1,d
;;;;;;;;;; PUSHA                                                    ;
           STA       -2,s                                           ;< PUSHA >
           SUBSP     2,i                                            ;< PUSHA >
           CALL      ScompTo
           ADDSP     4,i
           BR        loop
done:      STOP
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
LL0:       CPX       capacity,s
           BREQ      full
           CHARI     Sobject,sxf
           LDBYTEA   Sobject,sxf
           CPA       '\n',i
           BREQ      LL1
           ADDX      1,i
           BR        LL0
full:      LDA       ignored,s
           ADDA      1,i
           STA       ignored,s
           SUBX      1,i
ignore:    CHARI     Sobject,sxf
           LDA       0,i
           LDBYTEA   Sobject,sxf
           CPA       '\n',i
           BREQ      LL1
           LDA       ignored,s
           ADDA      1,i
           STA       ignored,s
           BR        ignore
LL1:       LDBYTEA   0,i
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
;{ readStgs.pep2 <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
;---------------------------------------------------------------
;  int readStgs(address p[], byte a[], int length, int limit)
;---------------------------------------------------------------
p:         .EQUATE   2
a:         .EQUATE   4
length:    .EQUATE   6
limit:     .EQUATE   8
;--------
           .BYTE     31
value:     .BLOCK    31
index:     .BLOCK    2
disp:      .BLOCK    2
;---------------------------------------------------------------
msgtrunc:  .ASCII    "WARNING: input truncation \x00"
msgfull:   .ASCII    "WARNING: string array full\n\x00"
;---------------------------------------------------------------
;.GLOBAL   readStgs
readStgs:  NOP0
;;;;;;;;;; CLR       index,d                                        ;
           STA       SAVEA,d                                        ;< CLR >
;;;;;;;;;; CLRA                                                     ;< CLR >
           LDA       0,i                                            ;< CLRA,CLR >
           STA       index,d                                        ;< CLR >
           LDA       SAVEA,d                                        ;< CLR >
;loop:;;;; CPM       index,d,limit,s                                ;
LL2:       NOP0                                                     ;< CPM >
           LDA       index,d                                        ;< CPM >
           CPA       limit,s                                        ;< CPM >
           BRGE      LL3
;;;;;;;;;; STRI      value,i                                        ;
;;;;;;;;;; PUSH      value,i                                        ;< STRI >
           STA       SAVEPP,d                                       ;< PUSH,STRI >
           LDA       value,i                                        ;< PUSH,STRI >
;;;;;;;;;; PUSHA                                                    ;< PUSH,STRI >
           STA       -2,s                                           ;< PUSHA,PUSH,STRI >
           SUBSP     2,i                                            ;< PUSHA,PUSH,STRI >
           LDA       SAVEPP,d                                       ;< PUSH,STRI >
           CALL      STRInput                                       ;< STRI >
           ADDSP     2,i                                            ;< STRI >
;;;;;;;;;; TSTA                                                     ;
           CPA       0,i                                            ;< TSTA >
           BREQ      move
           STRO      msgtrunc,d
;;;;;;;;;; DECOA                                                    ;
           STA       TEMP,d                                         ;< DECOA >
           DECO      TEMP,d                                         ;< DECOA >
           CHARO     '\n',i
;move:;;;; PUSH      value,i                                        ;
move:      NOP0                                                     ;< PUSH >
           STA       SAVEPP,d                                       ;< PUSH >
           LDA       value,i                                        ;< PUSH >
;;;;;;;;;; PUSHA                                                    ;< PUSH >
           STA       -2,s                                           ;< PUSHA,PUSH >
           SUBSP     2,i                                            ;< PUSHA,PUSH >
           LDA       SAVEPP,d                                       ;< PUSH >
           CALL      Slength
           ADDSP     2,i
;;;;;;;;;; TSTA                                                     ;
           CPA       0,i                                            ;< TSTA >
           BREQ      LL4
;---------
           LDA       length,s
;;;;;;;;;; INCA                                                     ;
           ADDA      1,i                                            ;< INCA >
           STA       -2,s
           LDX       index,d
           ASLX
           ASLX
           ASLX
           ASLX
           ASLX
           STX       disp,d
;;;;;;;;;; ADD       disp,d,a,s                                     ;
;;;;;;;;;; SAVEA                                                    ;< ADD >
           STA       SAVEA,d                                        ;< SAVEA,ADD >
           LDA       disp,d                                         ;< ADD >
           ADDA      a,s                                            ;< ADD >
           STA       disp,d                                         ;< ADD >
;;;;;;;;;; RESTOREA                                                 ;< ADD >
           LDA       SAVEA,d                                        ;< RESTOREA,ADD >
;;;;;;;;;; MOVE      disp,d,-4,s                                    ;
;;;;;;;;;; SAVEA                                                    ;< MOVE >
           STA       SAVEA,d                                        ;< SAVEA,MOVE >
           LDA       disp,d                                         ;< MOVE >
           STA       -4,s                                           ;< MOVE >
;;;;;;;;;; RESTOREA                                                 ;< MOVE >
           LDA       SAVEA,d                                        ;< RESTOREA,MOVE >
           LDA       value,i
;;;;;;;;;; DECA                                                     ;
           SUBA      1,i                                            ;< DECA >
           STA       -6,s
           SUBSP     6,i
           CALL      memcpy
           ADDSP     6,i
;---------
;;;;;;;;;; INC       disp,d                                         ;
;;;;;;;;;; SAVEA                                                    ;< INC >
           STA       SAVEA,d                                        ;< SAVEA,INC >
           LDA       disp,d                                         ;< INC >
           ADDA      1,i                                            ;< INC >
           STA       disp,d                                         ;< INC >
;;;;;;;;;; RESTOREA                                                 ;< INC >
           LDA       SAVEA,d                                        ;< RESTOREA,INC >
           LDX       index,d
           ASLX
;;;;;;;;;; MOVE      disp,d,p,sxf                                   ;
;;;;;;;;;; SAVEA                                                    ;< MOVE >
           STA       SAVEA,d                                        ;< SAVEA,MOVE >
           LDA       disp,d                                         ;< MOVE >
           STA       p,sxf                                          ;< MOVE >
;;;;;;;;;; RESTOREA                                                 ;< MOVE >
           LDA       SAVEA,d                                        ;< RESTOREA,MOVE >
;---------
;;;;;;;;;; INC       index,d                                        ;
;;;;;;;;;; SAVEA                                                    ;< INC >
           STA       SAVEA,d                                        ;< SAVEA,INC >
           LDA       index,d                                        ;< INC >
           ADDA      1,i                                            ;< INC >
           STA       index,d                                        ;< INC >
;;;;;;;;;; RESTOREA                                                 ;< INC >
           LDA       SAVEA,d                                        ;< RESTOREA,INC >
           BR        LL2
;--------
LL3:       STRO      msgfull,d
;--------
LL4:       LDA       index,d
           RET0
;} readStgs.pep2 >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
;
;{ memcpy.pep2 <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
;-----------------------------------------------------------
; void memcpy(byte *from, byte *to, int n);
;-----------------------------------------------------------
from:      .EQUATE   2
to:        .EQUATE   4
n:         .EQUATE   6
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
LL5:       CPX       n,s
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
LL7:       LDBYTEA   Sobject2,sxf
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
;>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
STOPEND:   STOP
           .END
;Resolver Report:
; loop --> LL0
; done --> LL1
; loop --> LL2
; full --> LL3
; done --> LL4
; loop --> LL5
; done --> LL6
; loop --> LL7
; done --> LL8
