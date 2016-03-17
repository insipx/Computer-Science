;The "Mean" Program
; CMPS 250 - Spring 2016
;P.M.J
;This is a solution to Assignment 3 in CMPS 250 for Spring 2016
;I, Andrew Plaza, am the sole developer of this work
;Did not collaborate with anyone, I worked alone.
;There are no flaws of which I am aware
;




           BR        main
;----------------------------------------------------------------------
; String declarations
;----------------------------------------------------------------------
tmsg:     .ASCII    "Total is \x00"
cmsg:     .ASCII    "Count is \x00"
mmsg:     .ASCII    "Mean is \x00"
;----------------------------------------------------------------------
; Variables
;----------------------------------------------------------------------
value:   .BLOCK    2
total:   .BLOCK    2
count:   .BLOCK    2  ;two blocks of 16 bites = 32 bits. Pep is a 16 bit processor
mCnt:    .BLOCK    2  ;so 2 bytes = 1 block,  4 bytes = 32 bits, etc...it all makes sense!
mean:    .BLOCK    2
;----------------------------------------------------------------------
main:     NOP0
          ;------------------------------------------------------------
          ; Loop to read and accumulate integer input values
          ;------------------------------------------------------------
loop:     DECI      value,d ; get the std.in and put it in value
          LDA       value,d ; load it
          CPA       0,i     ; copy immediate adressing mode to 0
          BREQ      done
          LDA       total,d     ;+ total = total + value;
          ADDA      value,d     ;|
          STA       total,d     ;+
          LDA       count,d     ;+ count = count + 1;
          ADDA      1,i         ;|
          STA       count,d     ;+
          BR        loop
done:     NOP0
	  ; this stuff is needed for finding the mean. mean will start by = total, but needs to be divided by count
          LDA       total,d      ; d is the accumulator, or direct addressing mode
          STA       mean, d      ; this copies the total into mean. total = mean 
          ;-------------------------------------------------------------
          ;Find the mean
          ;--------------------------------------------------------------
        
divLoop:  LDA       mean, d   ;load mean into accumulator again, so that the last sub5 method doesn't factor into our calculations
          SUBA      count, d  ;subtract 5 from accumulator value
          STA       mean,  d  ;store accumulator back in mean
          LDA       mCnt, d   ;load mean count into accumulator 
          ADDA      1,     i  ;increment counter
          STA       mCnt, d   ;store accumulator in meancount
          LDA       mean, d   ;load mean in accum
          SUBA      count,d   ;subtract 5 from count, but do not store it
          CPA       0, i      ;compare 0 and mean 
          BRGT      divLoop   ;if Greater than or Equal to, branch 
finished: NOP0                ;No operation, this is for readability 
          
          ;------------------------------------------------------------
          ; Report the total and count
          ;------------------------------------------------------------
          ;output total
          STRO      tmsg,d
          DECO      total,d
          CHARO     '\n',i
          ;output count
          STRO      cmsg,d
          DECO      count,d
          CHARO     '\n',i
          ;output mean
          STRO      mmsg,d
          DECO      mCnt, d
          CHARO     '\n',i
          STOP
          .END
