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
  (IF (EQUAL S SEMPTY) T NIL)
)

;-------------------------------------------------------------------------------

(DEFUN SMEMBERP (E S)
  (IF (SEMPTYP S) NIL        ; first if
    (IF (EQUAL E (FIRST S)) T ; nested if
      (SMEMBERP E (REST S)) ; else
    )
  )
)

(DEFUN SINCLUDE (E S)
  (IF (SMEMBERP E S) S      ; if
    (IF (member E S :test 'equal) S
    (APPEND S (CONS E NIL)) ; else
    )
  )
)

; - not sure what these functions do yet
(DEFUN PREFIX (E S RESULT)
  (COND ((EQUAL E (FIRST S)) RESULT)
        (T (PREFIX E (REST S) (APPEND RESULT (CONS (FIRST S) NIL))))
  )
)

(DEFUN SUFFIX (E S RESULT)
  (COND ((EQUAL E (FIRST S)) (REST S))
        (T (SUFFIX E (REST S) RESULT))
  )
)

(DEFUN SEXCLUDE (E S)
  (IF (SMEMBERP E S) (APPEND (PREFIX E S SEMPTY)
                             (SUFFIX E S SEMPTY)
                     )
    S ; else
  )
)

;-------------------------------------------------------------------------------

(DEFUN SUNION (S1 S2)
  (IF (SEMPTYP S2) S1
      (IF(SMEMBERP (FIRST S2) S1) (SUNION S1 (REST S2))
        (SINCLUDE (FIRST S2) (SUNION S1 (REST S2)))
      )
  )
)

(DEFUN SINTERSECTION (S1 S2)
  (IF (SEMPTYP S2) SEMPTY
    (IF (SMEMBERP (FIRST S2) S1) (SINCLUDE (FIRST S2)
                                  (SINTERSECTION S1 (REST S2))
                                  )
      (SINTERSECTION S1 (REST S2)) ;else
    )
  )
)

(DEFUN SDIFFERENCE (S1 S2)
  (IF (SEMPTYP S1) SEMPTY
    (IF (SMEMBERP (FIRST S1) S2) (SDIFFERENCE (REST S1) S2)
      (SINCLUDE (FIRST S1) (SDIFFERENCE (REST S1) S2))
    )
  )
)

(DEFUN SSYMMETRICDIFFERENCE (S1 S2)
  (SUNION (SDIFFERENCE S1 S2) (SDIFFERENCE S2 S1))
)

;-------------------------------------------------------------------------------

(DEFUN SSUBSETP (S1 S2)
  (IF (SEMPTYP S1) T
    (IF (SMEMBERP (FIRST S1) S2) (SSUBSETP (REST S1) S2)
      NIL
    )
  )
)

(DEFUN SEQUALP (S1 S2)
  (IF (SUBSETP S1 S2) (SSUBSETP S2 S1)
    (IF (SSUBSETP S2 S1) (SSUBSETP S1 S2)
      NIL
    )
  )
)

(DEFUN SPROPERP (S1 S2)
  (IF (SEMPTYP S1) NIL
    (IF (SEMPTYP S2) NIL
      (IF (SEQUALP S1 S2) NIL 
        (SSUBSETP S1 S2)
  )))
)
