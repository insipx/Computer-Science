* Template for a COBOL program.


IDENTIFICATION DIVISION.
PROGRAM-ID. <name>.
AUTHOR. <name>.
DATE-WRITTEN. <date>.

*******************************************************************************
*
*  Program Abstract: ...
*
*  INPUT: ...
*
*  OUTPUT: ...
*                    
*******************************************************************************

ENVIRONMENT DIVISION.

INPUT-OUTPUT SECTION.
FILE-CONTROL.
    SELECT <internal-file-name>  ASSIGN TO <external-file-name>. 
    SELECT <internal-file-name>  ASSIGN TO <external-file-name>. 


****************************************************************************** 
***                   D A T A   D I V I S I O N                            ***
****************************************************************************** 

DATA DIVISION.

FILE SECTION.

FD  <internal-file-name>
    DATA RECORD IS <record-name>.       <---optional clause

01  <record-name>.
    02 <name> ...
       03 <name> ...
       03 <name> ...
    02 <name> ...
    ...


WORKING-STORAGE SECTION.

01  <record-name>.
    02 <name> ...
       03 <name> ...
       03 <name> ...
    02 <name> ...


****************************************************************************** 
***             P R O C E D U R E   D I V I S I O N                        ***
****************************************************************************** 

PROCEDURE DIVISION.

Main-Program.
    <sequence of statements>
    STOP RUN
    .

<par-name>.
    <sequence of statements>
    .

<par-name>.
    <sequence of statements>
    .


END-PROGRAM.
