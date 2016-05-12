;-------------------------
;BuildLst()
;------------------------
;---Local Variables----
size:      .EQUATE   0         ; size
head:      .BLOCK    2
curr:      .BLOCK    2         ;front of the list (head, first element)
next:      .BLOCK    2         ;end of the list
currN:     .BLOCK    2
string:    .BLOCK    2
heaprf:    .BLOCK    2
;----------------------


buildLst:  NOP0
           SAVEA
           SAVEX
           CLRA
           CLRX
loop:      NOP0
           LDA       4,i
           STA       -2,s
           CALL      new
           ADDSP     2,i
           STA       heaprf,d  ; storing the ref from new into curr
;-----------------------------; this is the place in the stack we will be using
           CALL      readSO    ; calling readSO to read the string
           STA       string,d  ;storing the reference to the String into temp
           LDA       size,d    ;Loading size into A
           TSTA                ;testing against 0
           BREQ      first     ;if it's the first element, branch to first
           LDX       0,i       ;load 0 into X
           STX       p,x
           LDX       2,i
           LDA       next,d
           STA       curr,x
           STRO      string,n
           CHARO     '\n',i
           LDBYTEA   currN,n
           CPA       0,i
           BREQ      done
           BR        loop

first:     LDA       string,d
           LDX       0,i
           STA       heaprf,x
           LDX       2,i
           CALL      readSO
           STA       heaprf,x
           STRO      string,n
           CHARO     '\n',i
           LDBYTEA   string,n
           CPA       0,i
           BREQ      done
           BR        loop

done:      RESTOREA
           RESTOREX
           RET0
