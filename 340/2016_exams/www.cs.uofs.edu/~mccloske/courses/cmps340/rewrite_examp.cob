* CMPS 340 (File Processing)
* Sample COBOL program illustrating use of REWRITE verb to modify a
* sequential file in place
*
* Comments introduced by left-pointing arrows are "meta-comments" in that
* their purpose is to provide information to the reader (assumed to be a
* COBOL novice) that would not normally be included in a program.  As
* these meta-comments do not conform to the syntax of COBOL, they must be
* removed in order to make the program syntactically correct.

IDENTIFICATION DIVISION.
PROGRAM-ID. REWRITE-Example.
AUTHOR. R. McCloskey.
INSTALLATION. University of Scranton.        <--- optional paragraph
DATE-WRITTEN. September 29, 1998.            <---    "        "

*******************************************************************************
*
*  Program Abstract:
*    This program demonstrates the use of the REWRITE verb to modify a
*    sequential file "in place".  Specifically, the records in an Employee
*    file are modified to reflect changes to be made to the hourly wages of
*    some of the employees.
*
*  INPUT:
*    There are two input files.  The Employee file contains a record for each
*    employee, which includes fields for Employee ID, Name, and Hourly-Wage.
*    The Change-Wage file contains a record for each employee whose hourly
*    wage is to be changed.  Each such record contains an Employee ID and
*    the new hourly wage of the corresponding employee.  It is assumed that
*    each file is ordered according to its Employee ID field and that any such
*    ID occurring in the Change-Wage file also occurs in the Employee file.
*    (These assumptions are vital in that the program will not work correctly
*    if the input files fail to satisfy the stated conditions.)
*
*  OUTPUT:
*    The Employee file is updated so that the Hourly-Wage field in the
*    appropriate records are modified in accord with the Change-Wage file.
*    A message is displayed indicating the changes that are being made
*    to the file.
*                    
*******************************************************************************

ENVIRONMENT DIVISION.

INPUT-OUTPUT SECTION.
FILE-CONTROL.
    SELECT Employee-File     ASSIGN TO "employee-file.txt". 
    SELECT Change-Wage-File  ASSIGN TO "wage-change.txt".

DATA DIVISION.

FILE SECTION.

FD  Employee-File
    DATA RECORD IS Employee-Rec.           <--- optional clause

01  Employee-Rec.
    02 Empl-ID       PIC X(6).
    02 Name.
       03 Last-Name  PIC X(14).
       03 First-Name PIC X(10).
    02 Hourly-Wage   PIC 999V99.


FD  Change-Wage-File
    DATA RECORD IS Change-Wage-Rec.        <--- optional clause

01  Change-Wage-Rec.
    02 CW-Empl-ID   PIC X(6).
    02 New-Wage     PIC 999V99. 


WORKING-STORAGE SECTION.

01  End-of-Empl-File   PIC X  VALUE 'F'.
    88 EOF-Empl               VALUE 'T'.

***                                                                        ***
***             P R O C E D U R E   D I V I S I O N                        ***
***                                                                        ***

PROCEDURE DIVISION.

Main-Program.
    OPEN I-O   Employee-File
         INPUT Change-Wage-File

    PERFORM Read-Employee-Rec
    PERFORM Read-Wage-Rec

*   loop invariant:
*      (1) Empl-ID <= CW-Empl-ID  &
*      (2) Empl-ID > all values previously occupying CW-Empl-ID &
*      (3) all records in the Employee file preceding the current one
*          (i.e., the one occupying Employee-Rec) have been updated (or
*          left unchanged) in accord with the Change-Wage file.

    PERFORM UNTIL EOF-Empl
       IF Empl-ID = CW-Empl-ID
          MOVE New-Wage TO Hourly-Wage
          REWRITE Employee-Rec
          DISPLAY 'New wage of employee ', Empl-ID, ' is ', New-Wage
          PERFORM Read-Wage-Rec
       ELSE
*         assertion: Empl-ID < CW-Empl-ID.  Hence, the current employee
*         record is not to be changed; as there is no need to REWRITE 
*         such a record, do nothing
          CONTINUE 
       END-IF
       PERFORM Read-Employee-Rec
    END-PERFORM

    CLOSE Employee-File, Change-Wage-File
    STOP RUN
    .

Read-Employee-Rec.
    READ Employee-File
       AT END SET EOF-Empl TO TRUE
    END-READ
    .

Read-Wage-Rec.
    READ Change-Wage-File
       AT END CONTINUE
    END-READ
    .

END-PROGRAM.
