;-----------------------------------------------------------
; int sortStgs(address p[], int n);
;-----------------------------------------------------------
p:         .EQUATE  8
n:         .EQUATE  10
;----------
dmax:      .EQUATE  0
pmax:      .EQUATE  2
temp:      .EQUATE  4


save:        .BLOCK   2

oloopCo:     .WORD    0      ;n-1 (condition) in for(count=0 count < n - 1; count++)
iloopCo:     .WORD    0      ;n-c -1 (condition)
oloopc:      .WORD    0      ;outer loop count
iloopc:      .WORD    0      ;inner loop count

;----------
           .GLOBAL  sortStgs
sortStgs:  SUBSP    6,i            ;   Reserve Space
           SAVEA
           SAVEX
           CLRA
           CLRX
           LDA  n,s
           SUBA 1,i
           STA  oloopCo,d
;---------
oloop:  NOP0
        CLRA
        STA     iloopc,d
        LDA     oloopc,d
        CPA     oloopCo,d
        BRGE    done
        LDA     n,s
        SUBA    oloopc,d
        SUBA    1,i
        STA     iloopCo
        CLRX

iloop:  NOP0
        LDA     iloopc,d
        CPA     iloopCo,d
        BRGE    incCnt
        LDA     p,sxf
        STA     pmax,s
        ADDX    2,i
        LDA     p,sxf
        STA     temp,s
        CALL    ScompTo
        CPA     0,i
        BRGE    swap

incCnt: NOP0
        LDA     oloopc
        INCA
        STA     oloopc,d
        BR      oloop

swap:   NOP0
        SUBX    2,i
        LDA     p,sxf
        STA     save,d
        ADDX    2,i
        LDA     p,sxf
        SUBX    2,i
        STA     p,sxf
        LDA     save,d
        ADDX    2,i
        STA     p,sxf

;---------
done:      RESTOREA
           RESTOREX
           ADDSP    6,i            ;+ return
           RET0                    ;+
