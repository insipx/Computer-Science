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
;------------main----------------------------
main:      NOP0
           CALL      buildLst
;;;;;;;;;; PUSHA                                                    ;
           STA       -2,s                                           ;< PUSHA >
           SUBSP     2,i                                            ;< PUSHA >
           CALL      prntLst
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
;{ buildLst.pep2 <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
;-------------------------
;BuildLst()
;------------------------
;---Local Variables----
size:      .BLOCK    2                                              ; size
head:      .BLOCK    2
stringrf:  .BLOCK    2                                              ;the addr of the str from readSo
next:      .BLOCK    2                                              ;the addr where we can start putting stuff in the heap
node:      .BLOCK    2
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
           STA       size,d
LL1:       NOP0
;;;;;;;;;; CLRA                                                     ;
           LDA       0,i                                            ;< CLRA >
           CALL      readSO                                         ; calling readSO to read the string
           STA       stringrf,d                                     ;storing the reference to the String into temp
;------------------------------
           CPA       0,i                                            ; if readSo returns 0 we are LL2
           BREQ      LL2
;-------------------------------
           LDA       4,i                                            ;length of 4 bytes
           STA       -2,s
           SUBSP     2,i
           CALL      new
           ADDSP     2,i
           STA       node,d                                         ; storing the ref from new into node
;-----------------------------; this is the place in the stack we will be using
           LDA       size,d                                         ;Loading size into A
;;;;;;;;;; TSTA                                                     ;testing against 0 ;
           CPA       0,i                                            ;< TSTA >
           BREQ      first                                          ;if it's the first element, branch to first
           LDA       node,d
           STA       next,n                                         ;store the ref to current node in next for the prev node
;;;;;;;;;; MOVE      node,d,next,d                                  ;
;;;;;;;;;; SAVEA                                                    ;< MOVE >
           STA       SAVEA,d                                        ;< SAVEA,MOVE >
           LDA       node,d                                         ;< MOVE >
           STA       next,d                                         ;< MOVE >
;;;;;;;;;; RESTOREA                                                 ;< MOVE >
           LDA       SAVEA,d                                        ;< RESTOREA,MOVE >
;;;;;;;;;; ADD       next,d,2,i                                     ;
;;;;;;;;;; SAVEA                                                    ;< ADD >
           STA       SAVEA,d                                        ;< SAVEA,ADD >
           LDA       next,d                                         ;< ADD >
           ADDA      2,i                                            ;< ADD >
           STA       next,d                                         ;< ADD >
;;;;;;;;;; RESTOREA                                                 ;< ADD >
           LDA       SAVEA,d                                        ;< RESTOREA,ADD >
           LDA       0,i
           STA       next,n
           BR        insert
;-----------------------------
back:      LDA       stringrf,d                                     ;store string in first node cell
           STA       node,n
;-----------------------------          ;make ref cell just 0
;;;;;;;;;; MOVE      node,d,next,d                                  ;LDA    node,d  STA next,d ;
;;;;;;;;;; SAVEA                                                    ;< MOVE >
           STA       SAVEA,d                                        ;< SAVEA,MOVE >
           LDA       node,d                                         ;< MOVE >
           STA       next,d                                         ;< MOVE >
;;;;;;;;;; RESTOREA                                                 ;< MOVE >
           LDA       SAVEA,d                                        ;< RESTOREA,MOVE >
;;;;;;;;;; ADD       next,d,2,i                                     ;
;;;;;;;;;; SAVEA                                                    ;< ADD >
           STA       SAVEA,d                                        ;< SAVEA,ADD >
           LDA       next,d                                         ;< ADD >
           ADDA      2,i                                            ;< ADD >
           STA       next,d                                         ;< ADD >
;;;;;;;;;; RESTOREA                                                 ;< ADD >
           LDA       SAVEA,d                                        ;< RESTOREA,ADD >
           LDA       0,i
           STA       next,n
;;;;;;;;;; INC       size,d                                         ;
;;;;;;;;;; SAVEA                                                    ;< INC >
           STA       SAVEA,d                                        ;< SAVEA,INC >
           LDA       size,d                                         ;< INC >
           ADDA      1,i                                            ;< INC >
           STA       size,d                                         ;< INC >
;;;;;;;;;; RESTOREA                                                 ;< INC >
           LDA       SAVEA,d                                        ;< RESTOREA,INC >
;-----------------------------
           BR        LL1
first:     LDA       node,d                                         ;store the node ref in head, b/c this is the first node
           STA       head,d
;------------------------------
           BR        back
;--------------------------------------------------------
;-------Insertion Sort-----------------------------------
;--------------------------------------------------------
;Local Vars
;-----------------------------
htemp:     .BLOCK    2
htnext:    .BLOCK    2
iNode:     .BLOCK    2
iNodeNx:   .BLOCK    2
sRfTemp:   .BLOCK    2
currSr:    .BLOCK    2
nexSr:     .BLOCK    2
prev:      .BLOCk    2
;-----------------------------
;insert:;; MOVE      node,d,iNode,d                                 ;next node of curr node ;
insert:    NOP0                                                     ;< MOVE >
;;;;;;;;;; SAVEA                                                    ;< MOVE >
           STA       SAVEA,d                                        ;< SAVEA,MOVE >
           LDA       node,d                                         ;< MOVE >
           STA       iNode,d                                        ;< MOVE >
;;;;;;;;;; RESTOREA                                                 ;< MOVE >
           LDA       SAVEA,d                                        ;< RESTOREA,MOVE >
;;;;;;;;;; MOVE      node,d,iNodeNx,d                               ;
;;;;;;;;;; SAVEA                                                    ;< MOVE >
           STA       SAVEA,d                                        ;< SAVEA,MOVE >
           LDA       node,d                                         ;< MOVE >
           STA       iNodeNx,d                                      ;< MOVE >
;;;;;;;;;; RESTOREA                                                 ;< MOVE >
           LDA       SAVEA,d                                        ;< RESTOREA,MOVE >
;;;;;;;;;; ADD       iNodeNx,d,2,i                                  ;
;;;;;;;;;; SAVEA                                                    ;< ADD >
           STA       SAVEA,d                                        ;< SAVEA,ADD >
           LDA       iNodeNx,d                                      ;< ADD >
           ADDA      2,i                                            ;< ADD >
           STA       iNodeNx,d                                      ;< ADD >
;;;;;;;;;; RESTOREA                                                 ;< ADD >
           LDA       SAVEA,d                                        ;< RESTOREA,ADD >
;;;;;;;;;; MOVE      head,d,htemp,d                                 ;> ;
;;;;;;;;;; SAVEA                                                    ;< MOVE >
           STA       SAVEA,d                                        ;< SAVEA,MOVE >
           LDA       head,d                                         ;< MOVE >
           STA       htemp,d                                        ;< MOVE >
;;;;;;;;;; RESTOREA                                                 ;< MOVE >
           LDA       SAVEA,d                                        ;< RESTOREA,MOVE >
;------------------------------ getting the addresses
;;;;;;;;;; MOVE      htemp,d,htnext,d                               ;>     ;next node of head ;
;;;;;;;;;; SAVEA                                                    ;< MOVE >
           STA       SAVEA,d                                        ;< SAVEA,MOVE >
           LDA       htemp,d                                        ;< MOVE >
           STA       htnext,d                                       ;< MOVE >
;;;;;;;;;; RESTOREA                                                 ;< MOVE >
           LDA       SAVEA,d                                        ;< RESTOREA,MOVE >
;;;;;;;;;; ADD       htnext,d,2,i                                   ;> ;
;;;;;;;;;; SAVEA                                                    ;< ADD >
           STA       SAVEA,d                                        ;< SAVEA,ADD >
           LDA       htnext,d                                       ;< ADD >
           ADDA      2,i                                            ;< ADD >
           STA       htnext,d                                       ;< ADD >
;;;;;;;;;; RESTOREA                                                 ;< ADD >
           LDA       SAVEA,d                                        ;< RESTOREA,ADD >
;--------------------------------
;;;;;;;;;; MOVE      htemp,n,currSr,d                               ;must do first element first ;
;;;;;;;;;; SAVEA                                                    ;< MOVE >
           STA       SAVEA,d                                        ;< SAVEA,MOVE >
           LDA       htemp,n                                        ;< MOVE >
           STA       currSr,d                                       ;< MOVE >
;;;;;;;;;; RESTOREA                                                 ;< MOVE >
           LDA       SAVEA,d                                        ;< RESTOREA,MOVE >
;;;;;;;;;; CLRA                                                     ;
           LDA       0,i                                            ;< CLRA >
;;;;;;;;;; PUSH      stringrf,d                                     ;> ;
           STA       SAVEPP,d                                       ;< PUSH >
           LDA       stringrf,d                                     ;< PUSH >
;;;;;;;;;; PUSHA                                                    ;< PUSH >
           STA       -2,s                                           ;< PUSHA,PUSH >
           SUBSP     2,i                                            ;< PUSHA,PUSH >
           LDA       SAVEPP,d                                       ;< PUSH >
;;;;;;;;;; PUSH      currSr,d                                       ;> ;
           STA       SAVEPP,d                                       ;< PUSH >
           LDA       currSr,d                                       ;< PUSH >
;;;;;;;;;; PUSHA                                                    ;< PUSH >
           STA       -2,s                                           ;< PUSHA,PUSH >
           SUBSP     2,i                                            ;< PUSHA,PUSH >
           LDA       SAVEPP,d                                       ;< PUSH >
           CALL      ScompTo
           ADDSP     4,i
           CPA       0,i
           BREQ      equal
           BRLT      less
           BRGT      greatr2
;-------------------------------
iloop:     LDA       htnext,n                                       ;?
           CPA       0,i                                            ;>
           BREQ      back                                           ;>
;------------------------------------checks if next addr is null
;;;;;;;;;; MOVE      htnext,n,nexSr,d                               ;
;;;;;;;;;; SAVEA                                                    ;< MOVE >
           STA       SAVEA,d                                        ;< SAVEA,MOVE >
           LDA       htnext,n                                       ;< MOVE >
           STA       nexSr,d                                        ;< MOVE >
;;;;;;;;;; RESTOREA                                                 ;< MOVE >
           LDA       SAVEA,d                                        ;< RESTOREA,MOVE >
;;;;;;;;;; PUSH      currSr,d                                       ;> ;
           STA       SAVEPP,d                                       ;< PUSH >
           LDA       currSr,d                                       ;< PUSH >
;;;;;;;;;; PUSHA                                                    ;< PUSH >
           STA       -2,s                                           ;< PUSHA,PUSH >
           SUBSP     2,i                                            ;< PUSHA,PUSH >
           LDA       SAVEPP,d                                       ;< PUSH >
;;;;;;;;;; PUSH      nexSr,n                                        ;> ;
           STA       SAVEPP,d                                       ;< PUSH >
           LDA       nexSr,n                                        ;< PUSH >
;;;;;;;;;; PUSHA                                                    ;< PUSH >
           STA       -2,s                                           ;< PUSHA,PUSH >
           SUBSP     2,i                                            ;< PUSHA,PUSH >
           LDA       SAVEPP,d                                       ;< PUSH >
           CALL      ScompTo                                        ;>
           ADDSP     4,i
           CPA       0,i                                            ;>
           BRLT      less                                           ;>
           BRGT      egreater                                       ;>
less:      NOP0
;;;;;;;;;; MOVE      htemp,d,prev,d                                 ;move curr into prev ;
;;;;;;;;;; SAVEA                                                    ;< MOVE >
           STA       SAVEA,d                                        ;< SAVEA,MOVE >
           LDA       htemp,d                                        ;< MOVE >
           STA       prev,d                                         ;< MOVE >
;;;;;;;;;; RESTOREA                                                 ;< MOVE >
           LDA       SAVEA,d                                        ;< RESTOREA,MOVE >
;;;;;;;;;; MOVE      htnext,n,htemp,d                               ;> ;
;;;;;;;;;; SAVEA                                                    ;< MOVE >
           STA       SAVEA,d                                        ;< SAVEA,MOVE >
           LDA       htnext,n                                       ;< MOVE >
           STA       htemp,d                                        ;< MOVE >
;;;;;;;;;; RESTOREA                                                 ;< MOVE >
           LDA       SAVEA,d                                        ;< RESTOREA,MOVE >
;;;;;;;;;; MOVE      htemp,d,htnext,d                               ;> ;
;;;;;;;;;; SAVEA                                                    ;< MOVE >
           STA       SAVEA,d                                        ;< SAVEA,MOVE >
           LDA       htemp,d                                        ;< MOVE >
           STA       htnext,d                                       ;< MOVE >
;;;;;;;;;; RESTOREA                                                 ;< MOVE >
           LDA       SAVEA,d                                        ;< RESTOREA,MOVE >
;;;;;;;;;; ADD       htnext,d,2,i                                   ;> ;
;;;;;;;;;; SAVEA                                                    ;< ADD >
           STA       SAVEA,d                                        ;< SAVEA,ADD >
           LDA       htnext,d                                       ;< ADD >
           ADDA      2,i                                            ;< ADD >
           STA       htnext,d                                       ;< ADD >
;;;;;;;;;; RESTOREA                                                 ;< ADD >
           LDA       SAVEA,d                                        ;< RESTOREA,ADD >
           LDA       htnext,n
           CPA       0,i
           BREQ      mv2Org
;;;;;;;;;; MOVE      htemp,n,currSr,d                               ;
;;;;;;;;;; SAVEA                                                    ;< MOVE >
           STA       SAVEA,d                                        ;< SAVEA,MOVE >
           LDA       htemp,n                                        ;< MOVE >
           STA       currSr,d                                       ;< MOVE >
;;;;;;;;;; RESTOREA                                                 ;< MOVE >
           LDA       SAVEA,d                                        ;< RESTOREA,MOVE >
           BR        iloop
egreater:  NOP0
;;;;;;;;;; MOVE      stringrf,d,iNode,n                             ;
;;;;;;;;;; SAVEA                                                    ;< MOVE >
           STA       SAVEA,d                                        ;< SAVEA,MOVE >
           LDA       stringrf,d                                     ;< MOVE >
           STA       iNode,n                                        ;< MOVE >
;;;;;;;;;; RESTOREA                                                 ;< MOVE >
           LDA       SAVEA,d                                        ;< RESTOREA,MOVE >
;;;;;;;;;; MOVE      htemp,d,iNodeNx,n                              ;
;;;;;;;;;; SAVEA                                                    ;< MOVE >
           STA       SAVEA,d                                        ;< SAVEA,MOVE >
           LDA       htemp,d                                        ;< MOVE >
           STA       iNodeNx,n                                      ;< MOVE >
;;;;;;;;;; RESTOREA                                                 ;< MOVE >
           LDA       SAVEA,d                                        ;< RESTOREA,MOVE >
           LDA       prev,d
;;;;;;;;;; ADD       prev,d,2,i                                     ;
;;;;;;;;;; SAVEA                                                    ;< ADD >
           STA       SAVEA,d                                        ;< SAVEA,ADD >
           LDA       prev,d                                         ;< ADD >
           ADDA      2,i                                            ;< ADD >
           STA       prev,d                                         ;< ADD >
;;;;;;;;;; RESTOREA                                                 ;< ADD >
           LDA       SAVEA,d                                        ;< RESTOREA,ADD >
;;;;;;;;;; MOVE      iNode,d,prev,n                                 ;move current into previous ;
;;;;;;;;;; SAVEA                                                    ;< MOVE >
           STA       SAVEA,d                                        ;< SAVEA,MOVE >
           LDA       iNode,d                                        ;< MOVE >
           STA       prev,n                                         ;< MOVE >
;;;;;;;;;; RESTOREA                                                 ;< MOVE >
           LDA       SAVEA,d                                        ;< RESTOREA,MOVE >
           BR        LL1
greatr2:   NOP0
           LDA       stringrf,d
           STA       iNode,n
;;;;;;;;;; MOVE      htemp,d,iNodeNx,n                              ;
;;;;;;;;;; SAVEA                                                    ;< MOVE >
           STA       SAVEA,d                                        ;< SAVEA,MOVE >
           LDA       htemp,d                                        ;< MOVE >
           STA       iNodeNx,n                                      ;< MOVE >
;;;;;;;;;; RESTOREA                                                 ;< MOVE >
           LDA       SAVEA,d                                        ;< RESTOREA,MOVE >
;;;;;;;;;; MOVE      htemp,d,head,d                                 ;
;;;;;;;;;; SAVEA                                                    ;< MOVE >
           STA       SAVEA,d                                        ;< SAVEA,MOVE >
           LDA       htemp,d                                        ;< MOVE >
           STA       head,d                                         ;< MOVE >
;;;;;;;;;; RESTOREA                                                 ;< MOVE >
           LDA       SAVEA,d                                        ;< RESTOREA,MOVE >
           BR        LL1
mv2Org:    NOP0
;;;;;;;;;; MOVE      htemp,d,node,d                                 ;
;;;;;;;;;; SAVEA                                                    ;< MOVE >
           STA       SAVEA,d                                        ;< SAVEA,MOVE >
           LDA       htemp,d                                        ;< MOVE >
           STA       node,d                                         ;< MOVE >
;;;;;;;;;; RESTOREA                                                 ;< MOVE >
           LDA       SAVEA,d                                        ;< RESTOREA,MOVE >
;;;;;;;;;; MOVE      htnext,d,next,d                                ;
;;;;;;;;;; SAVEA                                                    ;< MOVE >
           STA       SAVEA,d                                        ;< SAVEA,MOVE >
           LDA       htnext,d                                       ;< MOVE >
           STA       next,d                                         ;< MOVE >
;;;;;;;;;; RESTOREA                                                 ;< MOVE >
           LDA       SAVEA,d                                        ;< RESTOREA,MOVE >
           BR        back
LL2:       LDA       head,d
           RET0
;} buildLst.pep2 >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
;
;{ readSO.pep2 <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
;------------------------------------------------------------------
;  int readSO()
;------------------------------------------------------------------
;------------------
           .BYTE     16
string:    .BLOCK    31
length:    .BLOCK    2                                              ;length of the string
ref:       .BLOCK    2
thing:     .ASCII    "this"
;---------------------------------------------------------------
msgtrunc:  .ASCII    "WARNING: input truncation \x00"
msgfull:   .ASCII    "WARNING: string array full\n\x00"
;.GLOBAL  readSO
readSO:    NOP0
;;;;;;;;;; CLRA                                                     ;
           LDA       0,i                                            ;< CLRA >
;;;;;;;;;; CLRX                                                     ;
           LDX       0,i                                            ;< CLRX >
           STA       ref,d
;;;;;;;;;; STRI      string,i                                       ;
;;;;;;;;;; PUSH      string,i                                       ;< STRI >
           STA       SAVEPP,d                                       ;< PUSH,STRI >
           LDA       string,i                                       ;< PUSH,STRI >
;;;;;;;;;; PUSHA                                                    ;< PUSH,STRI >
           STA       -2,s                                           ;< PUSHA,PUSH,STRI >
           SUBSP     2,i                                            ;< PUSHA,PUSH,STRI >
           LDA       SAVEPP,d                                       ;< PUSH,STRI >
           CALL      STRInput                                       ;< STRI >
           ADDSP     2,i                                            ;< STRI >
           CPA       0,i
           BREQ      move                                           ;if A == 0 we good
           STRO      msgtrunc,d                                     ; else branch
;;;;;;;;;; DECOA                                                    ;
           STA       TEMP,d                                         ;< DECOA >
           DECO      TEMP,d                                         ;< DECOA >
           CHARO     '\n',i
           NOP0
;---------------------------------     length= Slength(value)
;move:;;;; PUSH      string,i                                       ;
move:      NOP0                                                     ;< PUSH >
           STA       SAVEPP,d                                       ;< PUSH >
           LDA       string,i                                       ;< PUSH >
;;;;;;;;;; PUSHA                                                    ;< PUSH >
           STA       -2,s                                           ;< PUSHA,PUSH >
           SUBSP     2,i                                            ;< PUSHA,PUSH >
           LDA       SAVEPP,d                                       ;< PUSH >
           CALL      Slength
           ADDSP     2,i
           STA       length,d
           CPA       0,i                                            ; if(length!=0)
           BREQ      LL3
;----------------------------------
           LDA       length,d                                       ; ref = new(length+2)
           ADDA      2,i
           STA       -2,s
           SUBSP     2,i
           CALL      new                                            ;heap init new
           ADDSP     2,i
           STA       ref,d
;----------------------------------
           LDA       length,d                                       ;*ref = length++
;;;;;;;;;; INCA                                                     ;
           ADDA      1,i                                            ;< INCA >
           STA       length,d
           STBYTEA   ref,n
;;;;;;;;;; INC       ref,d                                          ;ref++ ;
;;;;;;;;;; SAVEA                                                    ;< INC >
           STA       SAVEA,d                                        ;< SAVEA,INC >
           LDA       ref,d                                          ;< INC >
           ADDA      1,i                                            ;< INC >
           STA       ref,d                                          ;< INC >
;;;;;;;;;; RESTOREA                                                 ;< INC >
           LDA       SAVEA,d                                        ;< RESTOREA,INC >
;----------------------------------
           LDA       length,d
           STA       -2,s
           LDA       ref,d
           STA       -4,s
           LDA       string,i
           STA       -6,s
           SUBSP     6,i
           CALL      memcpy
           ADDSP     6,i
LL3:       LDA       ref,d
           RET0
LL4:       STRO      msgfull,d
           LDA       ref,d
           RET0
;} readSO.pep2 >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
;
;{ prntLst.pep2 <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
;---------------------------------------------------------------
;  void prntLst(address head);
;---------------------------------------------------------------
LL5:       .EQUATE   2
;--------
LL6:       .BLOCK    2
LL7:       .BLOCK    2
;---------------------------------------------------------------
;.GLOBAL   prntLst
prntLst:   NOP0
;;;;;;;;;; MOVE      LL5,s,LL6,d                                    ;start by moving p off the stack into ptemp ;
;;;;;;;;;; SAVEA                                                    ;< MOVE >
           STA       SAVEA,d                                        ;< SAVEA,MOVE >
           LDA       LL5,s                                          ;< MOVE >
           STA       LL6,d                                          ;< MOVE >
;;;;;;;;;; RESTOREA                                                 ;< MOVE >
           LDA       SAVEA,d                                        ;< RESTOREA,MOVE >
;;;;;;;;;; MOVE      LL6,d,LL7,d                                    ;and also getting the second cell by adding 2 ;
;;;;;;;;;; SAVEA                                                    ;< MOVE >
           STA       SAVEA,d                                        ;< SAVEA,MOVE >
           LDA       LL6,d                                          ;< MOVE >
           STA       LL7,d                                          ;< MOVE >
;;;;;;;;;; RESTOREA                                                 ;< MOVE >
           LDA       SAVEA,d                                        ;< RESTOREA,MOVE >
;;;;;;;;;; ADD       LL7,d,2,i                                      ;to the address ;
;;;;;;;;;; SAVEA                                                    ;< ADD >
           STA       SAVEA,d                                        ;< SAVEA,ADD >
           LDA       LL7,d                                          ;< ADD >
           ADDA      2,i                                            ;< ADD >
           STA       LL7,d                                          ;< ADD >
;;;;;;;;;; RESTOREA                                                 ;< ADD >
           LDA       SAVEA,d                                        ;< RESTOREA,ADD >
LL8:       NOP0
;;;;;;;;;; MOVE      LL6,n,LL6,d                                    ;move the address stored in the ptemp addr ;
;;;;;;;;;; SAVEA                                                    ;< MOVE >
           STA       SAVEA,d                                        ;< SAVEA,MOVE >
           LDA       LL6,n                                          ;< MOVE >
           STA       LL6,d                                          ;< MOVE >
;;;;;;;;;; RESTOREA                                                 ;< MOVE >
           LDA       SAVEA,d                                        ;< RESTOREA,MOVE >
           STRO      LL6,n                                          ;into ptemp, and print it out
           CHARO     '\n',i
;---------------------------    ;check if the LL7 node is null
           LDA       0,i                                            ; if it is, exit
           CPA       LL7,n
           BREQ      LL9
;---------------------------    ;move the addr hold in the 'next' cell into ptemp
;;;;;;;;;; MOVE      LL7,n,LL6,d                                    ;this will become our current addr cell ;
;;;;;;;;;; SAVEA                                                    ;< MOVE >
           STA       SAVEA,d                                        ;< SAVEA,MOVE >
           LDA       LL7,n                                          ;< MOVE >
           STA       LL6,d                                          ;< MOVE >
;;;;;;;;;; RESTOREA                                                 ;< MOVE >
           LDA       SAVEA,d                                        ;< RESTOREA,MOVE >
;;;;;;;;;; MOVE      LL6,d,LL7,d                                    ;add two to the addr for the LL7 cell ;
;;;;;;;;;; SAVEA                                                    ;< MOVE >
           STA       SAVEA,d                                        ;< SAVEA,MOVE >
           LDA       LL6,d                                          ;< MOVE >
           STA       LL7,d                                          ;< MOVE >
;;;;;;;;;; RESTOREA                                                 ;< MOVE >
           LDA       SAVEA,d                                        ;< RESTOREA,MOVE >
;;;;;;;;;; ADD       LL7,d,2,i                                      ;
;;;;;;;;;; SAVEA                                                    ;< ADD >
           STA       SAVEA,d                                        ;< SAVEA,ADD >
           LDA       LL7,d                                          ;< ADD >
           ADDA      2,i                                            ;< ADD >
           STA       LL7,d                                          ;< ADD >
;;;;;;;;;; RESTOREA                                                 ;< ADD >
           LDA       SAVEA,d                                        ;< RESTOREA,ADD >
           BR        LL8
;--------
LL9:       RET0
;} prntLst.pep2 >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
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
LL10:      CPX       n,s
           BRGE      LL11
           LDBYTEA   from,sxf
           STBYTEA   to,sxf
;;;;;;;;;; INCX                                                     ;
           ADDX      1,i                                            ;< INCX >
           BR        LL10
;done:;;;; RESTORE                                                  ;
LL11:      NOP0                                                     ;< RESTORE >
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
; returns a number LL13 than zero if the first object is lexiographicall
; before (i.e. LL13 than) the second, zero if they are equivalent, and a
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
LL12:      NOP0
           LDBYTEA   Sobject2,sxf
           STA       hold2,d
           LDBYTEA   Sobject1,sxf
           CPA       hold2,d
           BRNE      done1
           CPA       0,i
           BREQ      equal
;;;;;;;;;; INCX                                                     ;
           ADDX      1,i                                            ;< INCX >
           BR        LL12
;---------
done1:     SUBA      hold2,d
           CPA       0,i
           BRGT      greater
           BRLT      LL13
greater:   NOP0
           LDA       1,i
           BR        LL14
LL13:      NOP0
           LDA       -1,i
           BR        LL14
equal:     NOP0
           LDA       0,i
           BR        LL14
;done:;;;; RESTOREX                                                 ;
LL14:      NOP0                                                     ;< RESTOREX >
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
LL15:      .EQUATE   4
;---------------------------------------------------------------------------------------
new:       SUBSP     2,i                                            ;
           LDA       LL15,s                                         ;+ if(length <=  255) {
           CPA       0,i                                            ;|
           BRLT      LL16                                           ;|
           CPA       255,i                                          ;|
           BRGT      LL16                                           ;|
           ADDA      1,i                                            ;| + A = malloc((length+1));
           SUBSP     2,i                                            ;| |
           STA       0,s                                            ;| |
           CALL      malloc                                         ;| |
           ADDSP     2,i                                            ;| +
           CPA       0,i                                            ;| + if(A != 0) {
           BRLE      LL16                                           ;| |
           STA       result,s                                       ;| | + set "before byte" to (length-1)
           SUBX      1,i                                            ;| | |
           STX       LL15,s                                         ;| | |
           LDA       LL15,s                                         ;| | |
           LDX       0,i                                            ;| | |
           STBYTEA   result,sxf                                     ;| | +
           LDA       result,s                                       ;| | }
           ADDA      1,i                                            ;| + A = A + 1;
           BR        LL17                                           ;+ }
LL16:      LDA       0,i                                            ;
LL17:      RET2                                                     ;
;} Heap_new.pep1 >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
;
;{ Heap_recycle.pep1 <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
;===================================================================================
;.GLOBAL  recycle
;---------------------------------------------------------------------------------------
; address recycle(address ref)
;---------------------------------------------------------------------------------------
LL18:      .EQUATE   2
;---------------------------------------------------------------------------------------
recycle:   NOP0
           LDX       -1,i                                           ;+ A = "before byte" value at LL18
           LDA       0,i                                            ;|
           LDBYTEA   LL18,sxf                                       ;+
           ADDA      1,i                                            ;+ free((ref-1),(A+1));
           STA       -2,s                                           ;|
           LDA       LL18,s                                         ;|
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
LL19:      .EQUATE   0                                              ;pointer to previous free element
curr:      .EQUATE   2                                              ;pointer to current free element
LL20:      .EQUATE   4                                              ;pointer to LL20 free element
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
mstart:    LDA       Hhead,d                                        ;+ curr = Hhead
           STA       curr,s                                         ;+
;----------------------------
mloop:     CPA       0,i                                            ;+ while((curr != null) &&
           BREQ      mERROR                                         ;|       (curr->size < need) {
           ADDA      2,i                                            ;|
           STA       HEA,d                                          ;|
           LDA       HEA,n                                          ;|   // A = curr->size;
           CPA       need,s                                         ;|
           BRGE      mdo                                            ;|
           LDA       curr,s                                         ;|   + LL19 = curr;
           STA       LL19,s                                         ;|   +
           LDA       curr,sf                                        ;|   + curr = curr->flink;
           STA       curr,s                                         ;|   +
           BR        mloop                                          ;+ }
;----------------------------
mdo:       SUBA      need,s                                         ;+ if((curr->size - need) < 4) {
           CPA       4,i                                            ;|
           BRGE      mok                                            ;|
           LDA       HEA,n                                          ;|  + need = curr->size;
           STA       need,s                                         ;|  +
           LDX       curr,s                                         ;|  + prev->flink = curr->flink;
           STX       HEA,d                                          ;|  |
           LDA       HEA,n                                          ;|  |
           STA       LL19,sf                                        ;|  +
           BR        mfill                                          ;| } else {
mok:       NOP0                                                     ;|  +
           LDA       curr,s                                         ;|  + LL20 = curr + need;
           ADDA      need,s                                         ;|  |
           STA       LL20,s                                         ;|  +
;----------------------------;|
           STA       LL19,sf                                        ;|    prev->flink = LL20
;----------------------------;|
           LDA       curr,sf                                        ;|  + next->flink = curr->flink
           STA       LL20,sf                                        ;|  +
;----------------------------;|
           LDX       curr,s                                         ;|  + X = (curr->size - need);
           ADDX      2,i                                            ;|  |
           STX       HEA,d                                          ;|  |
           LDX       HEA,n                                          ;|  |
           SUBX      need,s                                         ;|  +
;----------------------------;|
           LDA       LL20,s                                         ;|  + next->size = X
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
           LDA       curr,s                                         ;|
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
           LDA       curr,s                                         ;  A = curr;
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
LL21:      .EQUATE   8                                              ;the corresponding "need" used when allocating
;---------------------------------------------------------------------------------------
fprev:     .EQUATE   0                                              ;pointer to previous free element
fcurr:     .EQUATE   2                                              ;pointer to current free element
;---------------------------------------------------------------------------------------
free:      SUBSP     4,i                                            ;  Room for 2 local variables
;----------------------------;
           LDA       LL21,s                                         ;+ if((size >= 4) && ((size%2) == 0)) {
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
           ADDA      LL21,s                                         ;| | |
           CPA       fcurr,s                                        ;| | |
           BRGT      ferror                                         ;| | |
           LDA       fcurr,s                                        ;| | | + addr->flink = fcurr;
           STA       addr,sf                                        ;| | | +
           LDA       addr,s                                         ;| | | + fprev->flink = addr;
           STA       fprev,sf                                       ;| | | +
           ADDA      2,i                                            ;| | | + addr->size = LL21;
           STA       HEA,d                                          ;| | | |
           LDA       LL21,s                                         ;| | | |
           STA       HEA,n                                          ;| | | +
           NOP0                                                     ;| | + }
           NOP0                                                     ;| + }
;fdofill:  NOP0
;++++++++++++++++++++++++++++++++++++  TEMPORARY CODE TO FILL DEALLOCATION
           LDA       fcode,d                                        ;| + hfill((addr+4),(size-4),fcode);
           STA       -2,s                                           ;| |
           LDA       LL21,s                                         ;| |
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
; loop --> LL1
; done --> LL2
; done --> LL3
; full --> LL4
; head --> LL5
; htemp --> LL6
; next --> LL7
; loop --> LL8
; done --> LL9
; loop --> LL10
; done --> LL11
; loop --> LL12
; less --> LL13
; done --> LL14
; length --> LL15
; error --> LL16
; return --> LL17
; ref --> LL18
; prev --> LL19
; next --> LL20
; size --> LL21
