;; define a structure for storing two values
(define-struct xy (x y))

;; make-rat: number number -> rat
;; define a method to create a rational number (rat)
(define (make-rat n d) 
  (make-xy n d))

;; numer: rat -> number
;; define a method to access the numerator of a rat
;; example: (numer (make-rat 3 7)) = 3
(define (numer r) 
  (/ (xy-x r) (gcd (xy-x r) (xy-y r))))

;; denom: rat -> number
;; define a method to access the denominator of a rat
;; example: (denom (make-rat 3 7)) = 7
(define (denom r) 
  (/ (xy-y r) (gcd (xy-x r) (xy-y r))))

;; add-rat: rat rat -> rat
;; Adds two rational numbers
;; Example: (add-rat (make-rat 1 2) (make-rat 2 3))
;;          = (make-rat 7 6)
(define (add-rat x y)
  (make-rat (+ (* (numer x)
                  (denom y))
               (* (numer y)
                  (denom x)))
            (* (denom x)
               (denom y))))



;; mul-rat: rat rat -> rat
;; Multiplies two rational numbers
;; Example: (mul-rat (make-rat 1 2) (make-rat 2 3))
;;          = (make-rat 2 6)
(define (mul-rat x y)
  (make-rat 
     (* (numer x)
        (numer y))
     (* (denom x)
        (denom y))))


;; equal-rat: rat rat -> boolean
;; Compares two rational numbers
;; Example: (equal-rat (make-rat 2 3) (make-rat 4 6)) is true
(define (equal-rat? x y)
  (= (* (numer x) (denom y))
     (* (numer y) (denom x))))



;; print-rat: rat -> String
;; Prints a rational number as a String
;; Example: (print-rat (make-rat 3 7)) is "3/7"
(define (print-rat x)
  (string-append
  (number->string (numer x))
    "/"
    (number->string (denom x))))

;; Tests
(check-expect (print-rat (make-rat 3 7)) "3/7")
(check-expect (print-rat (make-rat 12 27)) "4/9")

;; define some examples and test operations
(define one-third (make-rat 1 3))
(define four-fifths (make-rat 4 5))

;; some tests
(check-expect (print-rat one-third) "1/3")
(check-expect (print-rat (mul-rat one-third four-fifths)) "4/15")
(check-expect (print-rat (add-rat four-fifths four-fifths)) "8/5")