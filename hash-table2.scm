(define (make-hash hash eql? size)
  (let ((table (make-vector size '())))
    (lambda (message . args)
      (let* ((k (car args))
             (i (modulo (hash k) size))
             (b (vector-ref table i)))
        (case message
          ((lookup)
           (let loop ((b b))
             (cond ((null? b) '())
                   ((eql? k (caar b)) (cdar b))
                   (else (loop (cdr b))))))
          ((insert)
           (vector-set!
            table i
            (let loop ((b b))
              (cond ((null? b) (list (cons k (cadr args))))
                    ((eql? k (caar b))
                     (cons (cons k (cadr args)) (cdr b)))
                    (else (cons (car b) (loop (cdr b)))))))))))))


;; 1848. 5m
;; Assuming we have this:
(define (string->hash s)
  (let loop ((cs (string->list s)) (hash 0))
    (if (null? cs) hash
        (loop (cdr cs) (+ (* hash 31) (char->integer (car cs)))))))

(define foo (make-hash string->hash string=? 4096))
(foo 'insert "foo" "bar")
(foo 'insert "foo" "lux")
(foo 'insert "tux" "lux")
(foo 'lookup "foo")
