;P.M.J. (Spring 2016)
;A program to determine the sum of integers in an inclusive interval
;   
; This is a Solution to problem VIII on the Midterm in CMPS 250 for Spring 2016
; I, Andrew Plaza, am the sole developer of this work
; I worked Alone
; There are no flaws of which I am aware
;

         BR         main
message: .ASCII     " is the sum.\n"
         .BYTE      0x00
value1:  .BLOCK     2
value2:  .BLOCK     2
result:  .BLOCK     2
main:    NOP0
         DECI       value1,d
         DECI       value2,d       
         
loop:    LDA        value1,d
         CPA        value2,d
         BRGT       done       
         LDA        result,d
         ADDA       value1,d
         STA        result,d
         LDA        value1,d
         ADDA       1,i
         STA        value1,d
         BR         loop
      
done:    NOP0         
         DECO       result,d
         STRO       message,d
         CHARO      '\n',i
         STOP  
        .END   
