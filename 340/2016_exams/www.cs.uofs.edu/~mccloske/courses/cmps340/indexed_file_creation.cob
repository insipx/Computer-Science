IDENTIFICATION DIVISION.
PROGRAM-ID.  Courses-Create.
AUTHOR.  R. McCloskey.
DATE-WRITTEN.  November 20, 1992.
*              modified Dec. 9, 1994 for fall 94 file (with section #
*              in course id)
****************************************************************
* Program Abstract:  (CMPS340, First part of Prog. Ass. #6, Fall 1992)
*    This program simply constructs an indexed course file from an
*    existing sequentially organized course file.
****************************************************************

ENVIRONMENT DIVISION.

CONFIGURATION SECTION.
SOURCE-COMPUTER. VAX6320.
OBJECT-COMPUTER. VAX6320.

INPUT-OUTPUT SECTION.

FILE-CONTROL.
    SELECT Old-Course-File  ASSIGN TO "Courses-Old.Dat"
       ORGANIZATION IS SEQUENTIAL.

    SELECT New-Course-File  ASSIGN TO "Courses.IDX"
       ORGANIZATION IS INDEXED
       ACCESS IS SEQUENTIAL
       RECORD KEY IS Course-ID.

DATA DIVISION.

FILE SECTION.

FD Old-Course-File
       RECORD CONTAINS 32 TO 923 CHARACTERS.
01 Old-Course-Record. 
   02 FILLER                      PIC X(30).
   02 Enrollment.
      03 Old-Number-Of-Students   PIC 9(2).
      03 Class-List.
         04 Student-ID  PIC X(9)
              OCCURS 0 to 99 TIMES DEPENDING ON Old-Number-Of-Students.

FD New-Course-File
      RECORD CONTAINS 32 TO 923 CHARACTERS.
01 New-Course-Record.
   02 Course-ID        PIC X(9).
   02 FILLER           PIC X(21).
   02 Enrollment.
      03 New-Number-Of-Students   PIC 9(2).
      03 Class-List.
         04 Student-ID   PIC X(9)
              OCCURS 0 TO 99 TIMES DEPENDING ON New-Number-Of-Students.


WORKING-STORAGE SECTION.

01 Old-Course-EOF-Flag  PIC X.
   88 Old-Course-EOF      VALUE '1'.
   88 Old-Course-EOF-NOT  VALUE '0'.


PROCEDURE DIVISION.

Main-Paragraph.
    OPEN INPUT Old-Course-File
    OPEN OUTPUT New-Course-File
    SET Old-Course-EOF-NOT TO TRUE
    PERFORM UNTIL Old-Course-EOF
      READ Old-Course-File
        AT END SET Old-Course-EOF TO TRUE
        NOT AT END
          MOVE Old-Course-Record TO New-Course-Record
          WRITE New-Course-Record
            INVALID KEY DISPLAY 'Error in creating indexed course file.'
                        DISPLAY 'Guilty record=', New-Course-Record
          END-WRITE
      END-READ
    END-PERFORM
    CLOSE Old-Course-File  New-Course-File
    STOP RUN
    .

