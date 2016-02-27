;; 1228

(define (next)
  (cond ((eof-object? (peek-char)) (exit))
        ((char=? (peek-char) #\space) (read-char) (next))
        ((char=? (peek-char) #\newline) (read-char) 'nl)
        (else (read))))

(define (op token)
  (case token
    ((+) +)
    ((-) -)
    ((/) /)
    ((*) *)))

(let rpn ((token (next)) (stack '()))
  (cond ((eq? token 'nl) (newline) (display (car stack)) (rpn (next) (cdr stack)))
        ((number? token) (rpn (next) (cons token stack)))
        (else (rpn (next) (cons ((op token) (cadr stack) (car stack)) (cddr stack))))))
