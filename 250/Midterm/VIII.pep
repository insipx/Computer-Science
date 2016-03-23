;Completed by Sean Batzel
;Solution to midterm question VIII
;P.M.J. (Spring 2016)
;A program to determine the sum of integers in an inclusive interval
;
         BR      main        
message: .ASCII  " is the sum.\n"
         .BYTE   0x00        
value1:  .BLOCK  2           ;First value.
value2:  .BLOCK  2           ;Last value.
result:  .BLOCK  2           ;The result of the calculation.
count:   .BLOCK  2           ;Keeping track of where we're currently at.
main:    NOP0                ;Nope.
         DECI    value1,d    ;Get the first number from stdin.
         DECI    value2,d    ;Get the lat number from stdin.
         LDA     value1,d    ;Load value1 into the accumulator.
         ADDA    1,i         ;Just to make sure we get the right count.
         STA     count,d     ;Store the accumulator as count.
         LDA     value1,d    ;We want to start with value1 in the accumulator.
loop:    NOP0                ;Here's where we loop to.
         ADDA    count,d     ;Add the current count to the accumulator.
         STA     result,d    ;The current result is in the accumulator. I think.
         LDA     count,d     ;Load the count into the accumulator.
         ADDA    1,i         ;Increment the count.
         STA     count,d     ;Oh. I forgot to store count back into memory.
         CPA     value2,d    ;Compare to value2.
         BRGT    done        ;Branch to 'done' if A is equal to value2.
         LDA     result,d    ;Make sure we've got the result in the accumulator.
         BR      loop
done:    NOP0                ;Just a placeholder.
         DECO    result,d
         STRO    message,d
         STOP                ;Halt execution and turn back over to the operating system.
         .END                  