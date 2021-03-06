;; contract: square: number -> number
;; purpose: determines the square value of the parameter
;; example: (square 5) is 25
(define (square x) 
  (* x x))

;; Tests
(check-expect (square 5) 25)
(check-expect (square -5) 25)

;; contract: average: number number -> number
;; purpose: determines the average of the values x and y
;; example: (average 3 5) is 4
(define (average x y) 
  (/ (+ x y) 2))

;; Tests
(check-expect (average -10 10) 0)
(check-expect (average 3 5) 4)
(check-expect (average 5 3) 4)

;; example application, combining both procedures; use "Step"!
(average 5 (square 3))