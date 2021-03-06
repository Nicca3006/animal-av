;; insert : number list-of-numbers (sorted)
;;              -> list-of-numbers (sorted)
;; to create a list of numbers from n and the numbers in
;; alon that is sorted in ascending order; alon is sorted
;; example: (insert 3 (list 1 2 4)) is (list 1 2 3 4)
(define (insert n alon)
  (cond
    [(empty? alon) (cons n empty)]
    [else (cond
            [(<= n (first alon)) 
             (cons n alon)]
            [(>  n (first alon)) 
             (cons 
              (first alon) 
              (insert n (rest alon)))])]))

;; Tests
(check-expect (insert 3 '(1 2 4)) '(1 2 3 4))
(check-expect (insert 1 '(2 3 4)) '(1 2 3 4))
(check-expect (insert 4 '(1 2 3)) '(1 2 3 4))

;; sort: (listof number) -> (listof number)
;; purpose: sorts a list of numbers in ascending order
;; example: (sort (list 1 3 2 4)) is (list 1 2 3 4)
(define (sort alon)
  (cond
    ;; an empty list is already sorted
    [(empty? alon) empty]
    [else 
     ;; otherwise, insert the first element...
     (insert (first alon)
             ;; into the list resulting from sorting
             ;; the rest of the list
             (sort (rest alon)))]))

;; Tests
(check-expect (sort '(1 3 2 4)) '(1 2 3 4))
(check-expect (sort empty) empty)
(check-expect (sort '(1 2 3 4)) '(1 2 3 4))
(check-expect (sort '(4 3 2 1)) '(1 2 3 4))