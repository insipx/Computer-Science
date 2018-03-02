IDENTIFICATION DIVISION.
PROGRAM-ID. Plus-One. 
AUTHOR.  R. McCloskey.
INSTALLATION.  U of S.
DATE-WRITTEN.  September 15, 1991.
* Abstract:
*   Given as input a three-digit number (via its first argument IN-VAL), 
*   this subprogram returns (via its second argument OUT-VAL) that number
*   plus one.
*
ENVIRONMENT DIVISION.

DATA DIVISION.

WORKING-STORAGE SECTION.

LINKAGE SECTION.

01 In-Val   PIC 9(3).
01 Out-Val  PIC 9(3).

PROCEDURE DIVISION USING In-Val Out-Val.

Main-Program.
    ADD 1 TO In-Val GIVING Out-Val
    EXIT PROGRAM
    .
