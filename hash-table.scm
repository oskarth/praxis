(define (make-hash hash eql? size)
  (let ((table (make-vector size '())))
    (lambda (message . args)
      (if (eq? message 'enlist)
          (let loop ((k 0) (acc '()))
            (if (= k size) acc
                (loop (+ k 1) (append (vector-ref table k) acc))))
          (let* ((k (car args))
                 (i (modulo (hash k) size))
                 (b (vector-ref table i)))
            (case message
              ((lookup)
               (let loop ((b b))
                 (cond ((null? b) '())
                       ((eql? (caar b) k) (cdar b))
                       (else (loop (cdr b))))))
              ((insert)
               (vector-set!
                table i
                (let loop ((b b))
                  (cond ((null? b) (list (cons k (cadr args))))
                        ((eql? (caar b) k) (cons (cons k (cadr args)) (cdr b)))
                        (else (car b (loop (cdr b))))))))))))))

(define (string->hash s)
  (let loop ((cs (string->list s)) (hash 0))
    (if (null? cs) hash
        (loop (cdr cs) (+ (* hash 31) (char->integer (car cs)))))))

(define foo (make-hash string->hash string=? 4096))
