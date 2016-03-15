;The "Mean" Program
;CMPS 250 - Spring 2016
;P.M.J.
;Updated to the MEANR version by Sean Batzel
;A lean, "mean", averagin' machine.
;This solution was developed solely by myself.
;
;------------------------------------------------------------------------------------------------------------------------------
;KNOWN ISSUES
;Floating-point arithmetic doesn't work.
;Because we're working with 16-bit integers, the overflow happens after 65,535.
;Can we declare blocks for integers larger than 2 bytes?
;------------------------------------------------------------------------------------------------------------------------------
;
         BR      main        ;Skip over the declarations during execution.
;------------------------------------------------------------------------------------------------------------------------------
; String declarations-
;------------------------------------------------------------------------------------------------------------------------------
tmsg:    .ASCII  "Total is \x00";So far as I can tell, \x00 is an endline. String terminator? Yeah, null character.
cmsg:    .ASCII  "Count is \x00";Labels are just like declaring variables or subroutines maybe? Yeah, identifiers basically.
mmsg:    .ASCII  "The mean is \x00";.ASCII tells the assembler to set aside a block of memory and read it as a string.
;------------------------------------------------------------------------------------------------------------------------------
; Memory allocations.
;------------------------------------------------------------------------------------------------------------------------------
value:   .BLOCK  2           ;2 byte block?
total:   .BLOCK  2           ;Must be.
count:   .BLOCK  2           ;I'm pretty sure pep/8 is a 16-bit architecture.
mean:    .BLOCK  2           ;That works for an integer.
check:   .BLOCK  2           ;Another integer block for tracking how many times we've subtracted.
;------------------------------------------------------------------------------------------------------------------------------
main:    NOP0                ;No OPerator - or as I like to call it, Nope. Basically just hands execution off to the next line.
;------------------------------------------------------------------------------------------------------------------------------
; Loop to read and accumulate integer input values
;------------------------------------------------------------------------------------------------------------------------------
loop:    DECI    value,d     ;Take a number.
         LDA     value,d     ;Load it into A.
         CPA     0,i         ;What is A compared to 0?
         BREQ    done        ;If it's 0, we're done here.
         LDA     total,d     ;Load total into A.
         ADDA    value,d     ;Add value to A.
         STA     total,d     ;Store A as total.
         LDA     count,d     ;Load count into A.
         ADDA    1,i         ;Increment.
         STA     count,d     ;Store A as count.
         BR      loop        ;Repeat the MAIN loop.
done:    NOP0                ;Nope.
         LDA     total,d     ;Load total into the accumulator.
         STA     mean,d      ;Copy total into mean.
;------------------------------------------------------------------------------------------------------------------------------
; Report the total and count - and now the mean.
;------------------------------------------------------------------------------------------------------------------------------
;Weird pseudodivision operation. What we're going to do is subtract the COUNT variable (the number
;of objects in the list) from the accumulator register (currently populted by MEAN) until it's
;less than or equal to 0. If that works, the value of mean when the loop terminates SHOULD be the
;average. This doesn't want to execute continuously, if we try doing it in the middle of the main
;loop it'll just confuse the program.
divide:  LDA     mean,d      ;Load mean into the accumulator.
         SUBA    count,d     ;Subtract the count from A.
         STA     mean,d      ;Put the accumulator back into the memory address associated with mean.
         LDA     check,d     ;Load CHECK into the accumulator.
         ADDA    1,i         ;Increment the counter.
         STA     check,d     ;Store it back into check.
         LDA     mean,d      ;Load mean into the accumulator.
         CPA     0,i         ;How does this compare to 0?
         BRGT    divide      ;If it's not less than or equal to, we can keep on loopin' on.
;Wow, it worked.
;Once that whole thing is done, we just move right along.
         STRO    tmsg,d      ;String Output - prints a string.
         DECO    total,d     ;Decimal Output - Hands stdin a number.
         CHARO   '\n',i      ;This little guy wraps up the line and hands it a carriage return.
         STRO    cmsg,d      ;Print a thing.
         DECO    count,d     ;Print another thing.
         CHARO   '\n',i      ;Ding. (Carriage return. "Ding" because typewriters used to go "ding")
         STRO    mmsg,d      ;Shouldn't need to print much more.
         DECO    check,d     ;I could be wrong.
         CHARO   '\n',i      ;Next line.
         STOP                ;Stop execution.
         .END                  ;Pseudo-operator to tell the assembler to take a minute for itself.