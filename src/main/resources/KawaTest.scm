(define-simple-class Bar () ;; This is a long ass string, comment thing
    (foo 10))
(define test (Bar))
(os:println test:foo)