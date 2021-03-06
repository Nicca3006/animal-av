;; fib: number -> number
;; determines the Fibonacci number for n
;; example: (fib 2) is 1
(define (fib n)
  (cond
    [(<= n 0) 0]
    [(= n 1) 1]
    [else
      (+ (fib (- n 1)) (fib (- n 2)))]))

;; Tests
(check-expect (fib 0) 0)
(check-expect (fib 1) 1)
(check-expect (fib 2) 1)
(check-expect (fib 3) 2)
(check-expect (fib 10) 55)


;; enumerate-tree: number number -> number
;; enumerates all elements between (and including) lo and hi into a list
;; example: (emumerate-interval 3 6) is '(3 4 5 6)
(define (enumerate-interval lo hi)
  (cond 
    [(> lo hi) empty]
    [else 
       (cons 
          lo 
          (enumerate-interval (+ lo 1) hi))]))

;; Tests
(check-expect (enumerate-interval 10 8) empty)
(check-expect (enumerate-interval 3 7) '(3 4 5 6 7))

;; variant using fold
;; even-fibs-fold number -> (listof number)
;; returns a list of those Fibonacci values for 0..n that are even
;; example: (even-fibs-fold 10) is '(0 2 8 34)
(define (even-fibs-fold n)
  (foldr ;; Note: foldl would fold in the opposite direction!
    cons 
    empty
    (filter even?
            (map fib 
                 (enumerate-interval 0 n)))))


(check-expect (even-fibs-fold 1) (list 0))
(check-expect (even-fibs-fold 10) (list 0 2 8 34))
