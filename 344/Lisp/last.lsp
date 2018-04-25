(defun las (L)
  (if (null (rest L)) ; if the rest of L is null
    (first L)
    (las (rest L))
  )
)

