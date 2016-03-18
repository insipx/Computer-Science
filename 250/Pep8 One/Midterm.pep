;Checking the midterm question.
;This program receives two integers from stdin and finds the sum of all the numbers between them.

         br       main       ;Branch to the main subprogram.
                             ;Variable declaration.
msg:      .ASCII  " is the sum.\n\x00" 
value1:   .BLOCK  2
value2:   .BLOCK  2
result:   .BLOCK  2

main:    NOP0
         DECI    value1,d
         DECI    value2,d
         DECO    value1,d
         CHARO   "\n",i
         DECO    value2,d
         CHARO   "\n",i
         LDA     value1,d
loop:    ADDA    1,i
         STA     result,d
         ADDA    result,d
         STA     result,d
         LDA     value1,d
         ADDA    1,i
         STA     value1,d
         CPA     value2,d
         BREQ    done
         BR      loop
done:    DECO    result,d
         STRO    msg,d
         STOP
         .END