IDENTIFICATION DIVISION.
PROGRAM-ID. PlusOneDriver.
*AUTHOR.  R. McCloskey.
*INSTALLATION.  U of S.
*DATE-WRITTEN.  September 26, 2000.

ENVIRONMENT DIVISION.

DATA DIVISION.

WORKING-STORAGE SECTION.

01 In-Number   PIC 9(3).
01 Out-Number  PIC 9(3).

01 Out-Message.
   02 In-Num  PIC ZZ9.
   02 FILLER  PIC X(7)  VALUE ' + 1 = '.
   02 Out-Num PIC ZZ9.

01 FILLER   PIC X   VALUE 'F'.
   88 Hell-Freezes-Over  VALUE 'T'.

PROCEDURE DIVISION.

Main-Program.
    PERFORM UNTIL Hell-Freezes-Over
       DISPLAY 'Enter a three-digit number: '  WITH NO ADVANCING
       ACCEPT In-Number
       CALL 'PlusOne' USING BY CONTENT In-Number
                            BY REFERENCE Out-Number
       PERFORM Display-Result
    END-PERFORM
    STOP RUN
    .


Display-Result.
    MOVE In-Number TO In-Num
    MOVE Out-Number TO Out-Num
    DISPLAY Out-Message
    DISPLAY ' '
    .
