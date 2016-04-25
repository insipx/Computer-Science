;
;
; P.M.J., April 2016
;--------Includes-----------------------------------------------
         BR        main
         .INCLUDE  "PEP2.pep1"
         .APPEND   "readStgs.pep2"
;         .APPEND   "prntStgs.pep2"
;         .APPEND   "sortStgs.pep2"
         .APPEND   "memcpy.pep2"
         .APPEND   "Slength.pep2"
;------------local vars-----------------------------------------
prompt:  .ASCII    "Your name is \x00"
acount:  .ASCII    "The Count is: \x00"
allNms:  .ASCII    "All the names are: \x00"
;------------Strings-----------------------------------------
;the arrays
;--------
LENGTH:  .EQUATE   31                         ; Expresses the maximum length of a string
LIMIT:   .EQUATE   24                         ; Expresses the capacity of the array
         .ASCII    "<<<<<<<<"                 ; Just a marker for visual inspection
p:       .BLOCK    48                         ; The array of pointers; LIMIT * 2
         .ASCII    "--------"                 ; Just a marker for visual inspection
a:       .BLOCK    768                        ; The array of strings; (1+LENGTH) * LIMIT
         .ASCII    ">>>>>>>>"                 ; Just a marker for visual inspection
count:   .BLOCK    2                          ; The number of strings read and stored
;--------
Sobj:    .EQUATE   2

;basically reiterating whatever is in SortStrings.pep2

main:    NOP0
         PUSH      LIMIT,i                    ;the arguments for readStgs
         PUSH      LENGTH,i
         PUSH      a,i
         PUSH      p,i
         CALL      readStgs
         ADDSP     8,i                        ;ignore the last 4 things pushed on stack :D
         STA       count,d
;------------------all strings read
         STRO      acount,d
         DECO      count,d
         CHARO     '\n',i
;------------------output count
; PUSH      p,i
; LDBYTEA  Sobj,sxf
;PUSHA
; CALL      Slength
; ADDSP     2,i
; STA       value,d
; DECO      value,d
done:   STOP
        .END
