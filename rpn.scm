(define (next)
  (cond ((char=? (peek-char) #\space) (read-char) (next))
        ((char=? (peek-char) #\newline) (read-char) 'nl)
        (else (read))))

(define (op token) (case token ((+) +) ((-) -) ((*) *) ((/) /)))

(let rpn ((token (next)) (stk '()))
  (display token)
  (newline)
  (cond ((eq? token 'nl) (display (car stk)) (newline) (rpn (next) (cdr stk)))
        ((number? token) (rpn (next) (cons token stk)))
        (else (rpn (next) (cons ((op token) (cadr stk) (car stk)) (cddr stk))))))
