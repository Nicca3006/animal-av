(define (filter1 rel-op alon t)
(cond
[(empty? alon) empty]
[else
(cond
[(rel-op (first alon) t)
(cons (first alon)
(filter1 rel-op (rest alon) t))]
[else
(filter1 rel-op (rest alon) t)])]))


;; T6.21-22
;; quicksort2: (listof number) -> (listof number)
;; returns a sorted list of all values in alon
;; example: (quicksort2 '(1 4 2 3)) is '(1 2 3 4)
(define (quicksort2 alon)
  (cond 
    [(empty? alon) empty]
    [else 
      (append 
        (quicksort2 (less-or-equal (rest alon) 
                                  (first alon)))
        (list (first alon))
        (quicksort2 (greater-than (rest alon)
                                 (first alon))))
    ]))

;; from T5.64+
;; filter1: (X Y -> boolean) (listof X) Y -> (listof X)
;; filters all elements from alon for which (rel-op elem t)
;; returns false
;; example: (filter1  (listof number)
;; creates a list of those elements in alon greater than threshold
;; example: (greater-than '(1 3 5 7) 4) is '(5 7)
(define (greater-than alon threshold)
   (filter1 > alon threshold))

;; less-or-equal (listof number) number -> (listof number)
;; creates a list of those elements in alon less than or equal to threshold
;; example: (less-or-equal '(1 3 5 7) 4) is '(1 3)
(define (less-or-equal alon threshold)
   (filter1 <= alon threshold))

;; Tests
(check-expect (quicksort2 '(1 3 7 5 2 6 8 4 9 10)) '(1 2 3 4 5 6 7 8 9 10))
(check-expect (quicksort2 '(10 9 8 7 6 5 4 3 2 1)) '(1 2 3 4 5 6 7 8 9 10))
(check-expect (quicksort2 '(1 1 1 1 1 1)) '(1 1 1 1 1 1))