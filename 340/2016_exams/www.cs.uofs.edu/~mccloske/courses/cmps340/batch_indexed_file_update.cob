IDENTIFICATION DIVISION.
PROGRAM-ID.  Courses-Interact.
AUTHOR.  R. McCloskey.
DATE-WRITTEN.  December 3, 1999.
************************************************************************
* Program Abstract:
*    This program performs batch update on an indexed file, where the
*    updates are specified in a transaction file.  In effect, this problem
*    is analogous to the sequential file update problem, except that we
*    need not bother to sort the transactions before applying them.
*    Transaction types are Add, Change, Delete, and Print.  
*
*    Each transaction contains a code ('A', 'C', 'D', or 'P') to identify
*    its type, followed by the key (in the Trans-Key field) identifying the
*    master record to which it pertains, followed by whatever data is needed
*    to fully specify the transaction.
*
*    An Add transaction contains, in addition, the data to be placed into
*    the Rest-of-Data-Rec field of the new record.
*
*    A Change transaction contains, in addition, the data that is to
*    replace whatever is in the Rest-of-Data-Rec field of the record
*    being changed.
*
*    A Delete transaction contains, in addition, nothing.
*
*    Print transactions are not like the others, both in that they do not
*    specify a change to be made to the file and in that their format is
*    not like the others'.  This kind of transaction contains two keys
*    (the first in the Trans-Key field and the second in the remainder
*    of the record), and specifies that all records in Index-File having
*    a key greater than or equal to the first but less than or equal to 
*    the second should be displayed on the screen.
*
*    The purpose of this somewhat contrived program is to illustrate the
*    use of indexed files; in particular, it illustrates applying the
*    "randomized" versions of READ, WRITE, and REWRITE, as well as the
*    START verb and the "sequential" version of the READ verb.
*
************************************************************************

ENVIRONMENT DIVISION.

CONFIGURATION SECTION.

INPUT-OUTPUT SECTION.

FILE-CONTROL.
    SELECT Indexed-File  ASSIGN TO <file-name>
       ORGANIZATION IS INDEXED
       ACCESS IS RANDOM
       RECORD KEY IS Key-Field.

    SELECT Transaction-File  ASSIGN TO <file-name>
       ORGANIZATION IS SEQUENTIAL
       ACCESS IS SEQUENTIAL.

DATA DIVISION.

FILE SECTION.

FD Indexed-File
     DATA RECORD IS Data-Rec.
01 Data-Rec.
   02 Key-Field         PIC X(8).
   02 Rest-of-Data-Rec  PIC X(17).

FD Transaction-File
     DATA RECORD IS Trans-Rec.
01 Trans-Rec.
   02 Trans-Type   PIC X.
      88 Add-Trans    VALUE 'A'.
      88 Change-Trans VALUE 'C'.
      88 Delete-Trans VALUE 'D'.
      88 Print-Trans  VALUE 'P'.
   02 Trans-Key             PIC X(8).
   02 Rest-of-Trans-Rec     PIC X(17).
   02 Rest-of-Add-Trans     REDEFINES Rest-of-Trans-Rec  PIC X(17).
   02 Rest-of-Change-Trans  REDEFINES Rest-of-Trans-Rec  PIC X(17).
   02 Rest-of-Delete-Trans  REDEFINES Rest-of-Trans-Rec  PIC X(17).
   02 Rest-of-Print-Trans   REDEFINES Rest-of-Trans-Rec.
      03 Upper-Bound-Key    PIC X(8).
      03 FILLER             PIC X(9).

FD Error-Transaction-File
01 Err-Trans-Rec  PIC X(26).


WORKING-STORAGE SECTION.

01 Trans-EOF-Flag  PIC X.
   88 Trans-EOF      VALUE '1'.
   88 Trans-EOF-Not  VALUE '0'.

01 Data-EOF-Flag  PIC X.
   88 Data-EOF      VALUE '1'.
   88 Data-EOF-Not  VALUE '0'.


PROCEDURE DIVISION.

Main-Paragraph.
    OPEN I-O Indexed-File
    OPEN INPUT Transaction-File
    SET Trans-EOF-Not TO TRUE

    PERFORM UNTIL Trans-EOF
       READ Transaction-File
          AT END     SET Trans-EOF TO TRUE
          NOT AT END PERFORM Process-Transaction
       END-READ
    END-PERFORM

    CLOSE Indexed-File
    CLOSE Transaction-File
    STOP RUN
    .


Process-Transaction.
    EVALUATE TRUE
       WHEN Add-Trans     PERFORM Process-Add-Trans
       WHEN Change-Trans  PERFORM Process-Change-Trans
       WHEN Delete-Trans  PERFORM Process-Delete-Trans
       WHEN Print-Trans   PERFORM Process-Print-Trans
       WHEN OTHER         PERFORM Write-Invalid-Trans
    END-EVALUATE
    .


Write-Invalid-Trans.
    WRITE Err-Trans-Rec FROM Trans-Rec
    .


*-----------------------------------------------------------------
* Inserts into Indexed-File a record with Key-Field = Trans-Key 
* and with rest of it containing Rest-of-Add-Trans
*-----------------------------------------------------------------
Process-Add-Trans.
    MOVE Trans-Key TO Key-Field
    READ Indexed-File
      INVALID KEY      MOVE Rest-of-Add-Trans TO Rest-of-Data-Rec
                       PERFORM Write-Data-Rec
      NOT INVALID KEY  PERFORM Write-Invalid-Trans
    END-READ
    .

Write-Data-Rec.
    WRITE Data-Rec
      INVALID KEY  DISPLAY 'Program error in Add:  Should never happen!'
    END-WRITE
    .

*-----------------------------------------------------------------
* Changes the record in Indexed-File with Key-Field = Trans-Key so
* that rest of it contains Rest-of-Change-Trans
*-----------------------------------------------------------------
Process-Change-Trans.
    MOVE Trans-Key TO Key-Field
    READ Indexed-File
      INVALID KEY      PERFORM Write-Invalid-Trans
      NOT INVALID KEY  MOVE Rest-of-Change-Trans TO Rest-of-Data-Rec
                       PERFORM Rewrite-Data-Rec
    END-READ
    .

Rewrite-Data-Rec.
    REWRITE Data-Rec
      INVALID KEY  DISPLAY 'Program error in Change:  Should never happen!'
    END-WRITE
    .


*-----------------------------------------------------------------
* Deletes from Indexed-File the record with Key-Field = Trans-Key
*-----------------------------------------------------------------
Process-Delete-Trans.
    MOVE Trans-Key TO Key-Field
    READ Indexed-File
      INVALID KEY      PERFORM Write-Invalid-Trans
      NOT INVALID KEY  PERFORM Delete-Data-Rec
    END-READ
    .

Delete-Data-Rec.
    DELETE Course-File 
       INVALID KEY  DISPLAY 'Program error in Delete:  Should never happen!'
    END-DELETE
    .


*----------------------------------------------------------
* Prints contents of each record in Indexed-File such that
* Trans-Key <= Key-Field <= Upper-Bound-Key
*-----------------------------------------------------------
Process-Print-Trans.
    DISPLAY 'Result of Range Query:'
    SET Data-EOF-NOT TO TRUE
    START Indexed-File  KEY IS NOT LESS THAN Trans-Key
       INVALID KEY  
          CONTINUE
       NOT INVALID KEY
          PERFORM Read-Next-Record
          PERFORM UNTIL Data-EOF  OR  (Key-Field > Upper-Bound-Key)
             DISPLAY Data-Rec
             PERFORM Read-Next-Record
          END-PERFORM
    END-START
    DISPLAY 'End of Range Query Result'
    .


Read-Next-Record.
    READ Indexed-File NEXT RECORD
       AT END  SET Data-EOF TO TRUE
    END-READ
    .

END-PROGRAM.
