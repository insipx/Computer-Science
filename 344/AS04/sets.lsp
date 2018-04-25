;-------------------------------------------------------------------------------
; Common LISP Function Definitions for typical set operations
; by P. M. Jackowitz, Revised Spring 2016
;
; Included in this collection are:
;    SEMPTY - an atom representing the empty set
;    SEMPTYP - returns T is the set is empty
;    SMEMBERP - returns T if the element is a member of the set
;    SINCLUDE - returns the set with the given element as a member
;    SEXCLUDE - returns the set with the given element not a member
;    SUNION  - returns the set of all elements in either set
;    SINTERSECTION - returns the set of all elements in both sets
;    SDIFFERENCE - returns the set of all elements in the first set which are
;                 not elements in the second set
;    SSYMMETRICDIFFERENCE - returns the set of all elements in the either set
;                          which are not in the other set
;    SSUBSETP - returns T if the first set is a subset of the second
;    SEQUALP - returns T if the two sets contain the exact same elements
;    SPROPERP - returns T if the first set is a proper subset of the second
; 
;-------------------------------------------------------------------------------
(SET 'SEMPTY NIL)

(DEFUN SEMPTYP (S)
  (COND ((EQ S SEMPTY) T)
        (T NIL)
  )
)

;-------------------------------------------------------------------------------

(DEFUN SMEMBERP (E S)
  (COND ((SEMPTYP S) NIL)
        ((EQ E (FIRST S)) T)
        (T (SMEMBERP E (REST S)))
  )
)

(DEFUN SINCLUDE (E S)
  (COND ((SMEMBERP E S) S)
        (T (APPEND S (CONS E NIL)))
  ) 
)

(DEFUN PREFIX (E S RESULT)
  (COND ((EQ E (FIRST S)) RESULT)
        (T (PREFIX E (REST S) (APPEND RESULT (CONS (FIRST S) NIL))))
  )
)

(DEFUN SUFFIX (E S RESULT)
  (COND ((EQ E (FIRST S)) (REST S))
        (T (SUFFIX E (REST S) RESULT))
  )
)

(DEFUN SEXCLUDE (E S)
  (COND ((SMEMBERP E S) (APPEND (PREFIX E S SEMPTY)
                               (SUFFIX E S SEMPTY))
        )
        (T S)
  )
)

;-------------------------------------------------------------------------------

(DEFUN SUNION (S1 S2)
  (COND ((SEMPTYP S2) S1)
        ((SMEMBERP (FIRST S2) S1) (SUNION S1 (REST S2)))
        (T (SINCLUDE (FIRST S2) (SUNION S1 (REST S2))))
  )
)

(DEFUN SINTERSECTION (S1 S2)
  (COND ((SEMPTYP S2) SEMPTY)
        ((SMEMBERP (FIRST S2) S1) (SINCLUDE (FIRST S2) 
                                        (SINTERSECTION S1 (REST S2))
                               )
        )
        (T (SINTERSECTION S1 (REST S2)))
  )
)

(DEFUN SDIFFERENCE (S1 S2)
  (COND ((SEMPTYP S1) SEMPTY)
        ((SMEMBERP (FIRST S1) S2) (SDIFFERENCE (REST S1) S2))
        (T (SINCLUDE (FIRST S1) (SDIFFERENCE (REST S1) S2)))
  )
)

(DEFUN SSYMMETRICDIFFERENCE (S1 S2)
  (SUNION (SDIFFERENCE S1 S2) (SDIFFERENCE S2 S1))
)

;-------------------------------------------------------------------------------

(DEFUN SSUBSETP (S1 S2)
  (COND ((SEMPTYP S1) T)
        ((SMEMBERP (FIRST S1) S2) (SSUBSETP (REST S1) S2))
        (T NIL)
  )
)

(DEFUN SEQUALP (S1 S2)
  (COND ((SSUBSETP S1 S2) (SSUBSETP S2 S1))
        ((SSUBSETP S2 S1) (SSUBSETP S1 S2))
        (T NIL)
  )
)

(DEFUN SPROPERP (S1 S2)
  (COND ((SEMPTYP S1) NIL)
        ((SEMPTYP S2) NIL)
        ((SEQUALP S1 S2) NIL)
        (T (SSUBSETP S1 S2))        
  )
)

