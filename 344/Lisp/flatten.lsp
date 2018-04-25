(defun flattenR (1 result)
  (COND ((NULL 1) result)
        ((LISTP (CAR 1)) (APPEND result
                                (flattenR (CAR 1) NIL)
                                (flattenR (CDR 1) NIL)
                        )
        )
        (T (APPEND result
                   (CONS (CAR 1) NIL)
                   (flattenR (CDR 1) NIL)
            )
        )
  
  )
)

; function probably don't work, missing listp?
